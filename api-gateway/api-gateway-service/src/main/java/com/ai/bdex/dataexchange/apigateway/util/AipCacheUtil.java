package com.ai.bdex.dataexchange.apigateway.util;

import org.eclipse.jetty.util.StringUtil;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimple;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaSimpleSV;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.Utils;

public class AipCacheUtil {
	
	public static Object getConfigFromCache(String paraLinkKey,String spCode){
		Object configValue=null;
    	String valueKey=APIConstants.AipCache.AIP_CACHE_NAME_PREFIX+paraLinkKey+"_"+spCode;
    	configValue=CacheUtil.getItem(valueKey);
    	//if null,query database
    	if(configValue==null){
    		IAipParaSimpleSV sv=(IAipParaSimpleSV)Utils.getBean("gatewayAipParaSimpleSV");
    		AipParaSimple configBean=sv.getValidAipParaSimple(paraLinkKey, spCode);
    		if(configBean!=null){    			
    			if(StringUtil.isNotBlank(configBean.getSpValue())){
    				configValue=configBean.getSpValue();
    				//add to cache
    				CacheUtil.addItem(valueKey, configValue);
    			}
    		}
    	}
    	return configValue;
	}
}	
