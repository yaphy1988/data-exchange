package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseLoginUrlDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;

import java.util.List;
import java.util.Map;

public interface IAuthBusiRSV {

	/**
	 * 获取免登陆URL数据
	 * @return
	 * @throws BusinessException
     */
	public List<BaseLoginUrlDTO> loadUnLoginUrls()throws BusinessException ;
}
