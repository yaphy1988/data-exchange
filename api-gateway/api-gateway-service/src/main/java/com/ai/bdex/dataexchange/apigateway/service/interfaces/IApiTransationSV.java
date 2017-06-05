package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;

public interface IApiTransationSV {
	public ApiTransationRespDTO invoke(String clientId,String logId,String serviceId,String version,Map<String,Object> paramMap)throws Exception;
}
