package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.ServiceDTO;

public interface IServiceMessageRSV {
	/**
	 * 查询某个有效服务及与之有关其他的信息 ，serviceId 与serviceVersion构成主键
	 * @param serviceId 服务ID
	 * @param serviceVersion 服务版本
	 * @return
	 * @throws Exception
	 */
	ServiceDTO getServiceMessage(String serviceId,String serviceVersion)throws Exception;
}
