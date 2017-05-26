package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import java.util.Map;

public interface IAipTransationRSV {
	
	Map<String,Object> createTransation(String serviceId,String version,Map<String,Object> paramMap)throws Exception;
}
