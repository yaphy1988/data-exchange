package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;

public interface IApiDealTransationSV {
	Map<String,Object> deal(AipPServiceUsedLog logBean,String serviceId, String version,Map<String, Object> paramMap);
	Map<String, Object> getFinalParamMap(Map<String, Object> paramMap)throws Exception;
	Class<?> getResultClass();
}
