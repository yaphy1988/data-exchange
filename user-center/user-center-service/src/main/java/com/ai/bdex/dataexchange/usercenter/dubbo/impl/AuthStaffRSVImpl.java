package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.util.Map;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffSV;

@Service("iAuthStaffRSV")
public class AuthStaffRSVImpl implements IAuthStaffRSV{
	private static final Logger log = Logger.getLogger(AuthStaffRSVImpl.class);

	@Autowired
	private IAuthStaffSV iAuthStaffSV;
	@Override
	public void sendEmalForActive(SignInfoDTO info) throws BusinessException{
		try {
			iAuthStaffSV.sendEmalForActive(info);
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
			return iAuthStaffSV.validUserIdExists(staffId);
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
			return iAuthStaffSV.doActiveByEmail(signId, code);
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
			return iAuthStaffSV.insertInfoToAuthStaff(record);
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
			return iAuthStaffSV.checkStaffAuthen(staffId);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("判断用户是否认证异常" + e.getMessage());
				throw new BusinessException("判断用户是否认证异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public int saveSignInfoBysms(SignInfoDTO info) throws BusinessException {
		try {
			return iAuthStaffSV.saveSignInfoBysms(info);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("保存注册信息异常" + e.getMessage());
				throw new BusinessException("保存注册信息异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public AuthStaffDTO findAuthStaffInfo(AuthStaffDTO input) throws BusinessException {
		try {
			return iAuthStaffSV.findAuthStaffInfo(input);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("查询用户信息异常" + e.getMessage());
				throw new BusinessException("查询用户信息异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public int updateAuthStaffInfo(AuthStaffDTO input) throws BusinessException {
		try {
			return iAuthStaffSV.updateAuthStaffInfo(input);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("修改用户信息异常：" + e.getMessage());
				throw new BusinessException("修改用户信息异常：" + e.getMessage());
			}		
		}
	}

	@Override
	public int updateMobilePhone(AuthStaffDTO input) throws BusinessException {
		try {
			return iAuthStaffSV.updateMobilePhone(input);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("修改用户手机号异常：" + e.getMessage());
				throw new BusinessException("修改用户手机号异常：" + e.getMessage());
			}		
		}
	}

	@Override
	public boolean checkInfoByName(String name, String staffId) throws BusinessException {
		try {
			return iAuthStaffSV.checkInfoByName(name, staffId);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("校验名字重复性异常：" + e.getMessage());
				throw new BusinessException("校验名字重复性异常：" + e.getMessage());
			}		
		}
	}

	/**
	 * 查询用户信息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public PageResponseDTO<StaffInfoDTO> getStaffInfoPage(AuthStaffDTO vo)throws BusinessException{
		try {
			return iAuthStaffSV.getStaffInfoPage(vo);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("查询用户信息异常：" + e.getMessage());
				throw new BusinessException("查询用户信息异常：" + e.getMessage());
			}
		}
	}

	@Override
	public PageResponseDTO<AuthStaffRespDTO> queryAuthStaffPage(AuthStaffDTO vo) throws BusinessException {
		try {
			return iAuthStaffSV.queryAuthStaffPage(vo);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("分页查询用户信息异常：" + e.getMessage());
				throw new BusinessException("分页查询用户信息异常：" + e.getMessage());
			}
		}
	}
}
