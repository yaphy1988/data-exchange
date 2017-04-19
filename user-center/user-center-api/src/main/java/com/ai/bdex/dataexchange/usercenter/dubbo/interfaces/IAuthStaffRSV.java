package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;

public interface IAuthStaffRSV {

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
}
