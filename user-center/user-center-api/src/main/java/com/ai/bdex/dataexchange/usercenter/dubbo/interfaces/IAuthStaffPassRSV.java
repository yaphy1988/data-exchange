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
}
