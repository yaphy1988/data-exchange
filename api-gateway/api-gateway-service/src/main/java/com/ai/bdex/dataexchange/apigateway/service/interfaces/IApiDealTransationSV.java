package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;

public interface IApiDealTransationSV {
	ApiTransationRespDTO deal(AipPServiceUsedLog logBean,String serviceId, String version,Map<String, Object> paramMap);
	Map<String, Object> getFinalParamMap(Map<String, Object> paramMap)throws Exception;
	Class<?> getResultClass();
	ApiTransationRespDTO checkParams(Map<String,Object> others,Map<String, Object> paramMap);
}
