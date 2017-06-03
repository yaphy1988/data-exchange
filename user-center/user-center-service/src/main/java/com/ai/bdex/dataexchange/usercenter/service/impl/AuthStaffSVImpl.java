package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRole2StaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.*;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffSignMapper;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffPassSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;
import com.ai.bdex.dataexchange.usercenter.util.SendMailUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.SignUtil;
import com.ai.paas.utils.StringUtil;

@Service("iAuthStaffSV")
public class AuthStaffSVImpl implements IAuthStaffSV{

	@Autowired
	private AuthStaffMapper authStaffMapper;
	
	@Autowired
	private AuthStaffSignMapper authStaffSignMapper;
	
	@Autowired
	private IAuthStaffPassSV iAuthStaffPassSV;

	@Autowired
	private AuthRole2StaffMapper authRole2StaffMapper;
	
	@Override
	public void sendEmalForActive(SignInfoDTO info) throws Exception {
		//1.存入表t_auth_staff_sign
		if(!info.getConfirmPass().equals(info.getSignpass())){
			throw new Exception("确认密码与输入密码不一致，请确认");
		}
		//1.1 校验ID是否存在
		boolean flag = validUserIdExists(info.getStaffId());
		if(flag){
			throw new BusinessException("用户ID已经存在");
		}
		//校验邮箱是否被使用
		boolean existsEmail = validEmail(info.getEmail());
		if(existsEmail){
			throw new BusinessException("该邮箱号已经被使用！");
		}
		AuthStaffSign record =  new AuthStaffSign();		
		BeanUtils.copyProperties(info, record);
		String signId = SeqUtil.getString("SEQ_AUTH_STAFF_SIGN");
		record.setSignId(signId);
		record.setPassword(SignUtil.SHA1(info.getSignpass()));
		record.setActiveFlag("0");//未激活
		record.setCreateTime(DateUtil.getNowAsTimestamp());
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
			result.put("success", false);
			result.put("msg", "查询无数据");
		}else{
			//校验用户ID是否被使用
			boolean existsStaffId = validStaffId(mainInfo.getStaffId());
			if(existsStaffId){
				result.put("success", false);
				result.put("msg", "该用户Id已经被使用");
				return result;
			}
			//校验邮箱是否被使用
			boolean existsEmail = validEmail(mainInfo.getEmail());
			if(existsEmail){
				result.put("success", false);
				result.put("msg", "该邮箱已经被使用");
				return result;
			}
			String email = mainInfo.getEmail();
			String staffId = mainInfo.getStaffId();
			String newCode = SendMailUtil.getCode(staffId, email);
			Date createTime = mainInfo.getCreateTime();
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中的小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			Date beforeDate = calendar.getTime();
			if(!newCode.equals(code)){
				result.put("success", false);
				result.put("msg", "验证链接与数据不符，请重试或重新注册！");
			}else if("1".equals(mainInfo.getActiveFlag())){
				//已经激活过
				result.put("success", false);
				result.put("msg", "该链接已使用过！");
			}else if(!DateUtil.compareMs(createTime, beforeDate)){
				result.put("success", false);
				result.put("msg", "该链接已过期！");
			}else{
				//同步用户表，密码表
				saveAuthStaffByEmailActive(mainInfo);				
				result.put("success", true);
				result.put("msg", "激活成功！");
			}			
		}
		return result;
	}

	@Override
	public int insertInfoToAuthStaff(AuthStaffDTO record) throws Exception {
		AuthStaff param = authStaffMapper.selectByPrimaryKey(record.getStaffId());
		if(param!=null){
			throw new BusinessException("该用户ID已经被使用！");
		}
		AuthStaff info = new AuthStaff();
		BeanUtils.copyProperties(record, info);
		int count = authStaffMapper.insertSelective(info);
		return count;
	}
	
	/**
	 * 激活用户同步到用户表和密码表
	 * @param mainInfo
	 * @return
	 * @throws Exception 
	 */
	private int saveAuthStaffByEmailActive(AuthStaffSign mainInfo) throws Exception{
		AuthStaffDTO dto = new AuthStaffDTO();
		dto.setStaffId(mainInfo.getStaffId());
		dto.setEmail(mainInfo.getEmail());
		dto.setCreateStaff(mainInfo.getStaffId());
		dto.setCreateTime(DateUtil.getNowAsDate());
		dto.setAuthenFlag("0");//是否已认证：1 认证，0未认证
		dto.setStartDate(DateUtil.getNowAsDate());
		dto.setEndDate(DateUtil.getFutureTime());
		dto.setCreateFrom("1");//表示帐号的创建方式（注册 1；管理员创建 2；后台自动生成 3）
		dto.setLockStatus("1");//锁定状态：0锁定，1正常，默认正常
		int insertcount = insertInfoToAuthStaff(dto);
		if(insertcount>0){
			AuthStaffPassDTO pass = new AuthStaffPassDTO();
			pass.setStaffId(mainInfo.getStaffId());
			pass.setStaffPasswd(mainInfo.getPassword());
			pass.setPasswdFlag("1");
			iAuthStaffPassSV.savePassInfo(pass);
			//修改注册表的状态为已激活
			AuthStaffSign record = new AuthStaffSign();
			record.setSignId(mainInfo.getSignId());
			record.setUpdateTime(DateUtil.getNowAsDate());
			record.setActiveFlag("1");
			authStaffSignMapper.updateByPrimaryKeySelective(record);
		}
		return insertcount;
	}

	@Override
	public boolean checkStaffAuthen(String staffId) throws Exception {
		AuthStaff info = authStaffMapper.selectByPrimaryKey(staffId);
		if(info==null){
			throw new BusinessException("用户不存在");
		}
		if("1".equals(info.getAuthenFlag())){
			return true;
		}
		return false;
	}
	
	private boolean validStaffId(String staffId){
		AuthStaff staff = authStaffMapper.selectByPrimaryKey(staffId);
		if(staff!=null){
			return  true;
		}
		return false;
	}
	
	private boolean validEmail(String email){
		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		sql.andEmailEqualTo(email);
		List<AuthStaff> staff = authStaffMapper.selectByExample(example);
		if(staff!=null&&staff.size()>0){
			return  true;
		}
		return false;
	}

	@Override
	public int saveSignInfoBysms(SignInfoDTO info) throws Exception {
		AuthStaff record = new AuthStaff();
		BeanUtils.copyProperties(info, record);
		record.setCreateStaff(info.getStaffId());
		record.setCreateTime(DateUtil.getNowAsTimestamp());
		record.setAuthenFlag("0");
		record.setStartDate(DateUtil.getNowAsTimestamp());
		record.setEndDate(DateUtil.getFutureTime());
		record.setCreateFrom("1");
		record.setLockStatus("1");
		int count = authStaffMapper.insertSelective(record);
		if(count>0){
			AuthStaffPassDTO pass = new AuthStaffPassDTO();
			pass.setStaffId(info.getStaffId());
			pass.setStaffPasswd(info.getConfirmPass());
			iAuthStaffPassSV.savePassInfo(pass);

			//增加默认角色
			AuthRole2StaffKey key = new AuthRole2StaffKey();
			key.setStaffId(info.getStaffId());
			key.setRoleId(Constants.Role.buyerRoleId);
			AuthRole2Staff authRole2Staff = authRole2StaffMapper.selectByPrimaryKey(key);
			if(authRole2Staff == null){
				authRole2Staff = new AuthRole2Staff();
				authRole2Staff.setStaffId(info.getStaffId());
				authRole2Staff.setRoleId(Constants.Role.buyerRoleId);
				authRole2Staff.setStatus("1");
				authRole2Staff.setUpdateId("system");
				authRole2Staff.setUpdateDate(new Date());
				authRole2StaffMapper.insertSelective(authRole2Staff);
			}
		}
		return count;
	}

	@Override
	public AuthStaffDTO findAuthStaffInfo(AuthStaffDTO input) throws Exception {
		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		if(!StringUtil.isBlank(input.getStaffId())){
			sql.andStaffIdEqualTo(input.getStaffId());
		}
		if(!StringUtil.isBlank(input.getEmail())){
			sql.andEmailEqualTo(input.getEmail());
		}
		if(!StringUtil.isBlank(input.getSerialNumber())){
			sql.andSerialNumberEqualTo(input.getSerialNumber());
		}
		List<AuthStaff> datas = authStaffMapper.selectByExample(example);
		if(!CollectionUtil.isEmpty(datas)){
			AuthStaffDTO dto = new AuthStaffDTO();
			AuthStaff bean = datas.get(0);
			BeanUtils.copyProperties(bean, dto);
			return dto;
		}
		return null;
	}

	@Override
	public int updateAuthStaffInfo(AuthStaffDTO input) throws Exception {
		AuthStaff record = new AuthStaff();
		BeanUtils.copyProperties(input, record);
//		record.setUpdateStaff(input.getStaffId());
		record.setUpdateTime(DateUtil.getNowAsTimestamp());		
		return authStaffMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateMobilePhone(AuthStaffDTO input) throws Exception {
		AuthStaff record = new AuthStaff();
		record.setStaffId(input.getStaffId());
		record.setSerialNumber(input.getSerialNumber());
		record.setUpdateStaff(input.getStaffId());
		record.setUpdateTime(DateUtil.getNowAsTimestamp());		
		return authStaffMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public boolean checkInfoByName(String name, String staffId) throws Exception {
		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		sql.andStaffIdNotEqualTo(staffId);
		sql.andStaffNameEqualTo(name);
		List<AuthStaff> result = authStaffMapper.selectByExample(example);
		if(!CollectionUtil.isEmpty(result)){
			return true;
		}
//		AuthStaffExample example1 = new AuthStaffExample();
//		AuthStaffExample.Criteria sql1 = example1.createCriteria();
//		sql1.andStaffIdNotEqualTo(staffId);
//		sql1.andAliasNameEqualTo(name);
//		List<AuthStaff> result1 = authStaffMapper.selectByExample(example1);
//		if(!CollectionUtil.isEmpty(result1)){
//			return true;
//		}
		return false;
	}

	/**
	 * 查询用户信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageResponseDTO<StaffInfoDTO> getStaffInfoPage(AuthStaffDTO vo)throws Exception{

		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		if(StringUtil.isBlank(vo.getStaffId()) == false){
			sql.andStaffIdLike(vo.getStaffId());
		}

		//设置分页
		int page = vo.getPageNo();
		int rows = vo.getPageSize();
		PageHelper.startPage(page, rows);
		List<AuthStaff> list = authStaffMapper.selectByExample(example);

		//转换分页
		PageInfo pageInfo = new PageInfo(list);

		//按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
		PageResponseDTO<StaffInfoDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,StaffInfoDTO.class);

		return respDTO;
	}

	@Override
	public PageResponseDTO<AuthStaffRespDTO> queryAuthStaffPage(AuthStaffDTO vo) throws Exception {
		AuthStaffExample example = new AuthStaffExample();
		AuthStaffExample.Criteria sql = example.createCriteria();
		if(StringUtil.isBlank(vo.getStaffId()) == false){
			sql.andStaffIdLike(vo.getStaffId());
		}

		//设置分页
		int page = vo.getPageNo();
		int rows = vo.getPageSize();
		PageHelper.startPage(page, rows);
		List<AuthStaff> list = authStaffMapper.selectByExample(example);

		//转换分页
		PageInfo pageInfo = new PageInfo(list);

		//按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
		PageResponseDTO<AuthStaffRespDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,AuthStaffRespDTO.class);

		return respDTO;
	}

	@Override
	public AuthStaff selectByPrimaryKey(String staffId) throws Exception {
		if (StringUtil.isBlank(staffId)){
			throw new BusinessException("根据staffId查询用户信息异常，入参为空！");
		}
		AuthStaff authStaff = authStaffMapper.selectByPrimaryKey(staffId);
		return authStaff;
	}
}
