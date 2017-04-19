package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;

public class AuthStaffRSVImpl implements IAuthStaffRSV{

	@Autowired
	private IAuthStaffSV authStaffSV;
	@Override
	public void sendEmalForActive(SignInfoDTO info) throws Exception{
		authStaffSV.sendEmalForActive(info);
	}
	
	@Override
	public boolean validUserIdExists(String staffId) throws Exception {
		return authStaffSV.validUserIdExists(staffId);
	}
	
	@Override
	public Map<String,Object> doActiveByEmail(String signId, String code) throws Exception {
		return authStaffSV.doActiveByEmail(signId, code);
	}

}
