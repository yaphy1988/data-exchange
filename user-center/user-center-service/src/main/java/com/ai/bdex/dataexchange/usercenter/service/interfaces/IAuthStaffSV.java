package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;

public interface IAuthStaffSV {
	
	/**
	 * 用户点击注册发送激活邮件
	 * @param authStaffDTO
	 */
	public void sendEmalForActive(SignInfoDTO info)throws Exception;
	
	/**
	 * 判断用户Id是否存在
	 * @param staffId
	 * @return
	 * @throws Exception
	 */
	public boolean validUserIdExists(String staffId)throws Exception;
	
	/**
	 * 账户激活业务实现
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> doActiveByEmail(String signId,String code)throws Exception;
	
	/**
	 * 将数据插入到用户表
	 * @return
	 * @throws Exception
	 */
	public int insertInfoToAuthStaff(AuthStaffDTO record)throws Exception;
	
	/**
	 * 判断该用户是否已认证
	 * @param staffId
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkStaffAuthen(String staffId)throws Exception;
}
