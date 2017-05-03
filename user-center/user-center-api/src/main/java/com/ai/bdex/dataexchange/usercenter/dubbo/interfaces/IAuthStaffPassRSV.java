package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;

public interface IAuthStaffPassRSV {

	/**
	 * 保存密码
	 * @return
	 * @throws BusinessException
	 */
	public int savePassInfo(AuthStaffPassDTO pass)throws BusinessException;
	
	/**
	 * 校验密码是否正确
	 * @param pass
	 * @return
	 * @throws BusinessException
	 */
	public boolean validPasswd(AuthStaffPassDTO pass)throws BusinessException;
	
	/**
	 * 修改密码
	 * @param pass
	 * @return
	 * @throws BusinessException
	 */
	public int updatePasswd(AuthStaffPassDTO pass)throws BusinessException;
}
