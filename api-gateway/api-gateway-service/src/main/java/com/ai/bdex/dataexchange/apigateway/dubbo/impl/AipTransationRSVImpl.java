package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientAccesstoken;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipTransationRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipClientAccesstokenSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiTransationSV;

@Service("aipTransationRSV")
public class AipTransationRSVImpl implements IAipTransationRSV{
	private static final Logger log = LoggerFactory.getLogger(AipTransationRSVImpl.class);

	@Autowired
	private IApiTransationSV apiTransationSV;
	@Autowired
	private IAipClientAccesstokenSV aipClientAccesstokenSV;

	@Override
	public ApiTransationRespDTO createTransation(String logId,String serviceId,
			String version, Map<String, Object> paramMap) throws Exception {	
		
		ApiTransationRespDTO invokeMap=null;					
		String clientId=null;
		String accessToken=null;
		try{	
			accessToken=(String)paramMap.get(APIConstants.AIP_PARAM_ACCESSTOKEN);
			clientId=getClientId(accessToken);
			invokeMap=apiTransationSV.createTransation(clientId, logId, serviceId, version, paramMap);	
		}catch(Exception e){
			log.error("createTransation error",e);
			throw e;
		}
		return invokeMap;
	}
	
	private String getClientId(String accessToken)throws Exception{
	    AipClientAccesstoken token=aipClientAccesstokenSV.getAipClientAccesstokenByKey(accessToken);
		if(null!=token){
			return token.getClientId();
		}
		return null;
	}
}
