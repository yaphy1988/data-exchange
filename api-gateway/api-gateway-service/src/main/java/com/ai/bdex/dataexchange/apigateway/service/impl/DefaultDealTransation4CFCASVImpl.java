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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.bdex.dataexchange.apigateway.cfca.service.interfaces.ICFCATransactionSV;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.ApiTransationCode;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.SystemErrorCode;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;
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
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ApiTransationRespDTO deal(AipPServiceUsedLog logBean,String serviceId, String version,
			Map<String, Object> paramMap){
		ApiTransationRespDTO dto=new ApiTransationRespDTO();
		Map resultMap=null;
		logBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		logBean.setRequestMsg(JSON.toJSONString(getParamMap(paramMap)));
	
		//调用fcfa基础服务
		try{
			resultMap=cFCATransactionSV.getResult(serviceId, version, getParamMap(paramMap));
			
			Object result=resultMap==null?null:resultMap.get("result");
			dto.setResult(result);
			if(null!=resultMap){
				dto.setRespCode(SystemErrorCode.CODE_00000);
				dto.setRespDesc("success");				
			}else{
				dto.setRespCode(ApiTransationCode.CODE_20002);
				dto.setRespDesc("no data");
			}
						
			logBean.setResponseMsg(resultMap==null?null:JSON.toJSONString(resultMap));
			logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
			logBean.setStatus("1");
		}catch(Exception e){
			log.error(serviceId+":"+version+",调用外部系统异常", e);
			
			dto.setRespCode(ApiTransationCode.CODE_20009);
			dto.setRespDesc("unkown reason");
			dto.setResult(null);
			
			logBean.setResponseMsg(e.getMessage());
			logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
			logBean.setStatus("2");
		}
		
		if(null!=logBean){
			//调用了外部接口需要日志记录
			try{	
				if(StringUtil.isBlank(logBean.getUsedId())){
					logBean.setUsedId(getPLogId());
				}
				logBean.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				aipPServiceUsedLogSV.insertLog(logBean);
			}catch(Exception e){
				log.error(serviceId+":"+version+",保存日志异常", e);
			}
		}
		return dto;
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


	@Override
	public ApiTransationRespDTO checkParams(Map<String,Object> others,Map<String, Object> paramMap) {
		return null;
	}
	private String getPLogId(){    
		return DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(APIConstants.AipSeqName.SEQ_AIP_P_SERVICE_USED_LOG);
	}	
}
