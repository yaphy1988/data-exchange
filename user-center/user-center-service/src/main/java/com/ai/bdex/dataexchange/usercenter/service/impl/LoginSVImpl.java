package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffPassMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaff;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffExample;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffPass;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.ILoginSV;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;

@Service("iLoginSV")
public class LoginSVImpl implements ILoginSV {
	private static final Logger log = Logger.getLogger(LoginSVImpl.class);
	
	@Autowired
	private AuthStaffMapper authStaffMapper;
	
	@Autowired
	private AuthStaffPassMapper authStaffPassMapper;

	@Override
	public StaffInfoDTO loginVerify(LoginInfoDTO loginInfo) throws Exception {

        long begin = System.currentTimeMillis();
        String loginName = loginInfo.getLoginName();
        String loginPwd = loginInfo.getLoginPwd();

        // 验证用户名正确性
        AuthStaff staffBean = verifyLoginName(loginName);
        // 验证密码正确性 如果是广东登录的不用校验其密码
        AuthStaffPass staffPassBean = new  AuthStaffPass();
        staffPassBean = this.verifyPwd(staffBean.getStaffId(), loginPwd,loginInfo);
        // 开关验证，后面补充

        // 登陆成功，返回VO对象
        StaffInfoDTO staffInfo = new StaffInfoDTO();
        BeanUtils.copyProperties(staffPassBean, staffInfo);
        staffInfo.setIsFirst(staffPassBean.getIsFirst());
        loginName = staffBean.getStaffId();

        staffInfo.setLoginIn(true);
        log.error("xujq_sv_time**" + (System.currentTimeMillis() - begin));
        return staffInfo;
    }
	
	public AuthStaff verifyLoginName(String loginName) throws Exception {
        long begin = System.currentTimeMillis();
        // 用户id唯一，所以只有一条，取第一条即可
        AuthStaff staffBean = this.verifyStaffName(loginName);

        // 账户状态不可用
        String staff_flag = staffBean.getStaffFlag();
        /*
         * 如果账号被锁定，返回前台展示：“系统登录失败：登陆账号已被锁定，请联系系统管理员” 写入登录操作日志表（登陆标识写失败，备注写失败提示）
         */
        if ("2".equals(staffBean.getStaffFlag())) {
            log.error("登录错误信息--用户:" + staffBean.getStaffId() + ",用户被锁定！", null);
            throw new BusinessException("登陆账号已被锁定！");
        }
        /* 临时锁定无法登陆，临时锁有效状态为3小时 */
        if ("0".equals(staffBean.getLockStatus())) {
            long currTime = (DateUtil.getNowAsTimestamp().getTime() - staffBean.getLockTime().getTime()) / 3600000;
            if (currTime <= 2) {
                log.error("登录错误信息--用户:" + staffBean.getStaffId() + ",用户被锁定！", null);
                throw new BusinessException("您的账号已被临时锁定<br>• 3小时后自动解锁<br>• 点击忘记密码进行找回！");
            }
        }
        /*
         * 如果有记录，判断当前账号状态，如果失效，返回前台展示：“系统登录失败：登陆账号已失效，请联系系统管理员”； 写入登录操作日志表（登陆标识写失败，备注写失败提示）
         */
        if ("0".equals(staff_flag)) {
            log.error("登录错误信息--用户:" + loginName + ",用户已失效！", null);
            throw new BusinessException("登陆账号已失效！");
        }
        // 账户失效
        Date end_date = staffBean.getEndDate();
        if (end_date.before(new Timestamp(System.currentTimeMillis()))) {
            log.error("用户:" + loginName + ",用户超过有效期！", null);
            throw new BusinessException("用户名不存在或密码错误！");
        }
        log.error("zouwj_user_time**" + (System.currentTimeMillis() - begin));
        return staffBean;
    }
	
	/**
     * 登陆帐号表中根据登陆账号、别名、手机号码、邮箱匹配查询有没有记录 
     * 
     * @param loginName
     * @param verifyCode
     * @return
     * @throws Exception
     * @author zouwj
     */
    public AuthStaff verifyStaffName(String loginName) throws Exception {
    	AuthStaffExample example = new AuthStaffExample();
    	AuthStaffExample.Criteria sql = example.createCriteria();
    	sql.andStaffIdEqualTo(loginName);
    	List<AuthStaff> staffInfoList = authStaffMapper.selectByExample(example);
        // 用工具类判断取出model是否为空
        /* 2） 根据录入的帐号（包括登陆账号/别名/手机号码/邮箱），在登陆帐号表中根据登陆账号、别名、手机号码、邮箱匹配查询有没有记录 */
        if (CollectionUtil.isEmpty(staffInfoList)) {
        	example = new AuthStaffExample();
        	sql = example.createCriteria();
        	sql.andEmailEqualTo(loginName);
        	staffInfoList = authStaffMapper.selectByExample(example);
            if (CollectionUtil.isEmpty(staffInfoList)) {
            	example = new AuthStaffExample();
            	sql = example.createCriteria();
                sql.andAliasNameEqualTo(loginName);
                staffInfoList = authStaffMapper.selectByExample(example);
                if (CollectionUtil.isEmpty(staffInfoList)) {
                	example = new AuthStaffExample();
                	sql = example.createCriteria();
                    sql.andSerialNumberEqualTo(loginName);
                    staffInfoList = authStaffMapper.selectByExample(example);
                    if (CollectionUtil.isEmpty(staffInfoList)) {
                    	 log.error("登录错误信息--用户：" + loginName + "不存在！", null);
                         throw new BusinessException("用户名不存在或密码错误！");
                    }
                }
            }
        }
        // 用户id唯一，所以只有一条，取第一条即可
        AuthStaff staffBean = staffInfoList.get(0);
        return staffBean;
    }
    
 // 密码验证
    @SuppressWarnings("static-access")
    private AuthStaffPass verifyPwd(String loginName, String loginPwd,LoginInfoDTO loginInfo) throws Exception {
        long begin = System.currentTimeMillis();
        AuthStaffPass passInfo = authStaffPassMapper.selectByPrimaryKey(loginName);
        log.error("xujq_pwd1_time**" + (System.currentTimeMillis() - begin));
        if (passInfo==null) {
            log.error("登录错误信息--用户：" + loginName + ",此用户id不存在，密码表中无此用户数据！", null);
            throw new BusinessException("用户名或密码错误！");
        }
        log.error("xujq_pwd2_time**" + (System.currentTimeMillis() - begin));
        if (!passInfo.getStaffPasswd().equals(loginPwd)) {
            log.error("登录错误信息--用户:" + loginName + ",密码错误！", null);
            throw new BusinessException("用户名或密码错误！");
        }
        log.error("zouwj_pwd3_time**" + (System.currentTimeMillis() - begin));
        return passInfo;
    }

	@Override
	public int updateLastLogin(String staffId) throws Exception {
		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		sql.andStaffIdEqualTo(staffId);
		AuthStaff record = new AuthStaff();
		record.setUpdateStaff(staffId);
		record.setUpdateTime(DateUtil.getNowAsDate());
		record.setLastLogin(DateUtil.getNowAsDate());
		int count = authStaffMapper.updateByExampleSelective(record, example);
		return count;
	}

}
