package com.ai.bdex.dataexchange.usercenter.service.interfaces;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;

public interface IAuthStaffPassSV {
	
	/**
	 * 保存密码
	 * @return
	 * @throws BusinessException
	 */
	public int savePassInfo(AuthStaffPassDTO pass)throws Exception;
	
	/**
	 * 校验密码是否正确
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public boolean validPasswd(AuthStaffPassDTO pass)throws Exception;
	
	/**
	 * 修改密码
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public int updatePasswd(AuthStaffPassDTO pass)throws Exception;

}
