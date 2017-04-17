package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;

public interface IAuthStaffSV {
	
	/**
	 * 用户点击注册发送激活邮件
	 * @param authStaffDTO
	 */
	public void sendEmalForActive(AuthStaffDTO authStaffDTO);
}
