package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffPassRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffPassSV;

@Service("iAuthStaffPassRSV")
public class AuthStaffPassRSVImpl implements IAuthStaffPassRSV{
	private static final Logger log = Logger.getLogger(AuthStaffPassRSVImpl.class);

	@Autowired
	private IAuthStaffPassSV iAuthStaffPassSV;
	@Override
	public int savePassInfo(AuthStaffPassDTO pass) throws BusinessException {
		try {
			return iAuthStaffPassSV.savePassInfo(pass);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("密码保存异常" + e.getMessage());
				throw new BusinessException("密码保存异常:" + e.getMessage());
			}		
		}
	}
	@Override
	public boolean validPasswd(AuthStaffPassDTO pass) throws BusinessException {
		try {
			return iAuthStaffPassSV.validPasswd(pass);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("校验异常" + e.getMessage());
				throw new BusinessException("校验异常" + e.getMessage());
			}		
		}
	}
	@Override
	public int updatePasswd(AuthStaffPassDTO pass) throws BusinessException {
		try {
			return iAuthStaffPassSV.updatePasswd(pass);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("修改密码异常" + e.getMessage());
				throw new BusinessException("修改密码异常" + e.getMessage());
			}		
		}
	}

}
