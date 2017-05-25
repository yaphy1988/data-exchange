package com.ai.bdex.dataexchange.apigateway.util;

import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.ai.paas.util.Utils;

public class TransactionServiceFactory {
	
	public static IApiDealTransationSV getService(String serviceId,String version){
		
		return (IApiDealTransationSV)Utils.getBean("");
	}
}
