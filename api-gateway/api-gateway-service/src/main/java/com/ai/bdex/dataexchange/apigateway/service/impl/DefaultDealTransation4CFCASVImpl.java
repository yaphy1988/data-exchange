package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.cfca.service.interfaces.ICFCATransactionSV;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.ApiTransationCode;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.alibaba.fastjson.JSON;

@Service("defaultDealTransation4CFCASV")
public class DefaultDealTransation4CFCASVImpl implements IApiDealTransationSV{
	private static final Logger log = LoggerFactory.getLogger(DefaultDealTransation4CFCASVImpl.class);
	@Autowired
	private ICFCATransactionSV cFCATransactionSV;
	@Autowired
	private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map<String,Object> deal(AipPServiceUsedLog logBean,String serviceId, String version,
			Map<String, Object> paramMap){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		Map resultMap=null;
		logBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		logBean.setRequestMsg(JSON.toJSONString(getParamMap(paramMap)));
	
		//调用fcfa基础服务
		try{
			resultMap=cFCATransactionSV.getResult(serviceId, version, getParamMap(paramMap));
			Object result=resultMap==null?null:resultMap.get("result");
			
			returnMap.put("resp_code",ApiTransationCode.CODE_0000);
			returnMap.put("resp_desc","success");
			returnMap.put("result", result);
			
			logBean.setResponseMsg(resultMap==null?null:JSON.toJSONString(resultMap));
			logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
			logBean.setStatus("1");
		}catch(Exception e){
			log.error(serviceId+":"+version+",调用外部系统异常", e);
			
			returnMap.put("resp_code",ApiTransationCode.CODE_0009);
			returnMap.put("resp_desc","unknow reason");
			returnMap.put("result", null);
			
			logBean.setResponseMsg(e.getMessage());
			logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
			logBean.setStatus("2");
		}
		
		if(null!=logBean){
			//调用了外部接口需要日志记录
			try{	
				logBean.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				aipPServiceUsedLogSV.insertLog(logBean);
			}catch(Exception e){
				log.error(serviceId+":"+version+",保存日志异常", e);
			}
		}
		return returnMap;
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


	@Override
	public Class<?> getResultClass() {
		return Map.class;
	}
	
}
