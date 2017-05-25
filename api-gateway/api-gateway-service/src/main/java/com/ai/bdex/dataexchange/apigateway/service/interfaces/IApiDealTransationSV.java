package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

public interface IApiDealTransationSV {
	Object deal(String serviceId, String version,Map<String, Object> paramMap)throws Exception;
	Map<String, Object> getFinalParamMap(Map<String, Object> paramMap)throws Exception;
}
