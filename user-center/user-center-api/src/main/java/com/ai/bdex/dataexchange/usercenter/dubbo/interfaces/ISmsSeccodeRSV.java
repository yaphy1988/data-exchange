package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;

/**
 * Title: WOEGO <br>
 * Description: 短信发送验证码的日志保存服务<br>
 * Date: 2016-4-22 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author zouwj
 */
public interface ISmsSeccodeRSV {
	
	/**
	 * 根据短信初始化信息，生成短信验证码，并发送；
	 * @param seccodeInitVO
	 * @return
	 * @throws BusinessException
	 * @author zouwj
	 */
	public SmsSeccodeReqDTO genSmsSecCode(SmsSeccodeReqDTO seccodeInitVO) throws BusinessException;
	
	/**
	 * 根据传入的tocken校验验证码是否正确；
	 * @param smsSecurityCheckVO
	 * @return
	 * @throws BusinessException
	 * @author zouwj
	 */                                                              
	public boolean checkSmsSecCode(SmsSeccodeReqDTO smsSecurityCheckVO) throws BusinessException;

}
