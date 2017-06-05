package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;

public interface IAipTransationRSV {
	
	ApiTransationRespDTO createTransation(String serviceId,String version,Map<String,Object> paramMap)throws Exception;
}
