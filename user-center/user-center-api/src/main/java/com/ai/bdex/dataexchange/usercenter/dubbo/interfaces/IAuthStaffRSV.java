package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;

public interface IAuthStaffRSV {

	/**
	 * 用户点击注册发送激活邮件
	 * @param authStaffDTO
	 */
	public void sendEmalForActive(AuthStaffDTO authStaffDTO);
}
