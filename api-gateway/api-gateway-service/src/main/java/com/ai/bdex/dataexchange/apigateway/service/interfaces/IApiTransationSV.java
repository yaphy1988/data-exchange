package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

public interface IApiTransationSV {
	public Object invoke(String clientId,String logId,String serviceId,String version,Map<String,Object> paramMap)throws Exception;
}
