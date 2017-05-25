package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.cfca.service.interfaces.ICFCATransactionSV;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.alibaba.fastjson.JSON;

@Service("defaultDealTransation4CFCASV")
public class DefaultDealTransation4CFCASVImpl implements IApiDealTransationSV{
	private static final Logger log = LoggerFactory.getLogger(DefaultDealTransation4CFCASVImpl.class);
	@Autowired
	private ICFCATransactionSV cFCATransactionSV;

	@Override
	public Object deal(String serviceId, String version,
			Map<String, Object> paramMap) throws Exception {
		Map resultMap=null;
		//调用fcfa基础服务
		resultMap=cFCATransactionSV.getResult(serviceId, version, getParamMap(paramMap));
		Object result=resultMap==null?null:resultMap.get("result");
		
		return result;
	}


	@Override
	public Map<String, Object> getFinalParamMap(Map<String, Object> paramMap)
			throws Exception {
		//去掉非业务参数，去掉accessToken
		Map<String,Object> map=null;
		if(null!=paramMap&&paramMap.size()>0){
			map=new HashMap<String,Object>();
			Set<Entry<String, Object>>set=paramMap.entrySet();
			for(Entry<String, Object> en:set){
				if(!APIConstants.AIP_PARAM_ACCESSTOKEN.equalsIgnoreCase(en.getKey())){
					map.put(en.getKey(), en.getValue());
				}
			}
		}
		return map;
	}
	
	private Map<String,String> getParamMap(Map<String, Object> paramMap){
		Map<String,String> map=null;
		if(null!=paramMap&&paramMap.size()>0){
			map=new HashMap<String,String>();
			Set<Entry<String, Object>>set=paramMap.entrySet();
			for(Entry<String, Object> en:set){
				if(!APIConstants.AIP_PARAM_ACCESSTOKEN.equalsIgnoreCase(en.getKey())){
					map.put(en.getKey(), (String)en.getValue());
				}
			}
		}
		return map;
	}
}
