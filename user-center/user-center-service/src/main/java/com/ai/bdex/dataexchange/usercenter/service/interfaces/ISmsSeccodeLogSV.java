package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;

public interface ISmsSeccodeLogSV {

	/**
	 * 获取短信记录
	 * @return
	 * @throws Exception
	 */
	public SmsSeccodeReqDTO getSmsSendInfo(SmsSeccodeReqDTO input)throws Exception;
}
