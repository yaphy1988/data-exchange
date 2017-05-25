package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.Map;

public interface IAipProviderSV {
	 Map<String, String> deal(String serviceId, String version,Map<String, Object> paramMap) throws Exception; 
}
