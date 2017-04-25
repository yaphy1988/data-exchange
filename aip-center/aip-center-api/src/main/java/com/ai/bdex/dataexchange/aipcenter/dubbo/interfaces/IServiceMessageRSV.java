package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.ServiceDTO;

public interface IServiceMessageRSV {
	ServiceDTO getServiceMessage(String serviceId,String serviceVersion)throws Exception;
}
