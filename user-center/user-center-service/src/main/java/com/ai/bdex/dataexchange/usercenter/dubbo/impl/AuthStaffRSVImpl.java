package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;

public class AuthStaffRSVImpl implements IAuthStaffRSV{
	private static final Logger log = Logger.getLogger(AuthStaffRSVImpl.class);

	@Autowired
	private IAuthStaffSV authStaffSV;
	@Override
	public void sendEmalForActive(SignInfoDTO info) throws BusinessException{
		try {
			authStaffSV.sendEmalForActive(info);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("发送邮件异常" + e.getMessage());
				throw new BusinessException("发送邮件异常:" + e.getMessage());
			}		
		}
	}
	
	@Override
	public boolean validUserIdExists(String staffId) throws BusinessException {
		try {
			return authStaffSV.validUserIdExists(staffId);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("校验用户ID是否存在异常" + e.getMessage());
				throw new BusinessException("校验用户ID是否存在异常:" + e.getMessage());
			}		
		}
	}
	
	@Override
	public Map<String,Object> doActiveByEmail(String signId, String code) throws BusinessException {
		try {
			return authStaffSV.doActiveByEmail(signId, code);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("根据邮件链接激活用户异常" + e.getMessage());
				throw new BusinessException("根据邮件链接激活用户异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public int insertInfoToAuthStaff(AuthStaffDTO record) throws BusinessException {
		try {
			return authStaffSV.insertInfoToAuthStaff(record);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("插入用户表数据异常" + e.getMessage());
				throw new BusinessException("插入用户表数据异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public boolean checkStaffAuthen(String staffId) throws BusinessException {
		try {
			return authStaffSV.checkStaffAuthen(staffId);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("判断用户是否认证异常" + e.getMessage());
				throw new BusinessException("判断用户是否认证异常:" + e.getMessage());
			}		
		}
	}

}
