package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;

public class AuthStaffRSVImpl implements IAuthStaffRSV{

	@Autowired
	private IAuthStaffSV authStaffSV;
	@Override
	public void sendEmalForActive(AuthStaffDTO authStaffDTO) {
		authStaffSV.sendEmalForActive(authStaffDTO);
	}

}
