package com.ai.bdex.dataexchange.usercenter.service.interfaces;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;

public interface IAuthStaffPassSV {
	
	/**
	 * 保存密码
	 * @return
	 * @throws BusinessException
	 */
	public int savePassInfo(AuthStaffPassDTO pass)throws Exception;

}
