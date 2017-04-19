package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffSignMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaff;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffSignExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;
import com.ai.bdex.dataexchange.usercenter.util.SendMailUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.SignUtil;

public class AuthStaffSVImpl implements IAuthStaffSV{

	@Autowired
	private AuthStaffMapper authStaffMapper;
	
	@Autowired
	private AuthStaffSignMapper authStaffSignMapper;
	
	@Override
	public void sendEmalForActive(SignInfoDTO info) throws Exception {
		//1.存入表t_auth_staff_sign
		if(!info.getConfirmPass().equals(info.getSignpass())){
			throw new Exception("确认密码与输入密码不一致，请确认");
		}
		//1.1 校验ID是否存在
		boolean flag = validUserIdExists(info.getStaffId());
		if(flag){
			throw new Exception("用户ID已经存在");
		}
		AuthStaffSign record =  new AuthStaffSign();		
		BeanUtils.copyProperties(info, record);
		String signId = SeqUtil.getString("SEQ_AUTH_STAFF_SIGN");
		record.setSignId(signId);
		record.setPassword(SignUtil.SHA1(info.getSignpass()));
		record.setActiveFlag("0");//未激活
		record.setCreateTime(DateUtil.getNowAsDate());
		int result = authStaffSignMapper.insertSelective(record);
		if(result>0){
			//2.发送激活邮件
			String content = SendMailUtil.getContentBySign(
					record.getSignId(), info.getStaffId(), info.getEmail());
			SendMailUtil.send(info.getEmail(), content);
		}
		
	}

	@Override
	public boolean validUserIdExists(String staffId) throws Exception {
		//判断用户表中是否存在
		AuthStaff staffInfo = authStaffMapper.selectByPrimaryKey(staffId);
		if(staffInfo!=null){
			return true;
		}else{
			//判断注册表是否存在有效记录
			AuthStaffSignExample example = new AuthStaffSignExample();
			AuthStaffSignExample.Criteria sql = example.createCriteria();
			sql.andStaffIdEqualTo(staffId);
			sql.andActiveFlagEqualTo("0");//未激活状态
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			Date beforeDate = calendar.getTime();
			sql.andCreateTimeGreaterThan(beforeDate);
			List<AuthStaffSign> signInfo = authStaffSignMapper.selectByExample(example);
			if(signInfo!=null&&signInfo.size()>0){
				return true;
			}else{
				AuthStaffSignExample example1 = new AuthStaffSignExample();
				AuthStaffSignExample.Criteria sql1 = example1.createCriteria();
				sql1.andStaffIdEqualTo(staffId);
				sql1.andActiveFlagEqualTo("1");//已经激活状态
				List<AuthStaffSign> signInfoyes = authStaffSignMapper.selectByExample(example);
				if(signInfoyes!=null&&signInfoyes.size()>0){
					return true;
				}
			}			
		}
		return false;
		
	}

	@Override
	public Map<String,Object> doActiveByEmail(String signId, String code) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		AuthStaffSign mainInfo = null;
		//1.根据signId查询注册数据
		mainInfo = authStaffSignMapper.selectByPrimaryKey(signId);
		if(mainInfo==null){
			result.put("flag", false);
			result.put("msg", "查询无数据");
		}else{
			String email = mainInfo.getEmail();
			String staffId = mainInfo.getStaffId();
			String newCode = SendMailUtil.getCode(staffId, email);
			Date createTime = mainInfo.getCreateTime();
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			Date beforeDate = calendar.getTime();
			if(!newCode.equals(code)){
				result.put("flag", false);
				result.put("msg", "验证链接与数据不符，请重试或重新注册！");
			}else if("1".equals(mainInfo.getActiveFlag())){
				//已经激活过
				result.put("flag", false);
				result.put("msg", "该链接已使用过！");
			}else if(!DateUtil.compareMs(createTime, beforeDate)){
				result.put("flag", false);
				result.put("msg", "该链接已过期！");
			}else{
				//同步用户表，密码表
				result.put("flag", true);
				result.put("msg", "激活成功！");
			}			
		}
		return result;
	}

}
