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
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipPServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.ai.bdex.dataexchange.apigateway.util.AipCacheUtil;
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
		Object result=null;
		//调用fcfa基础服务
		try{
			//包含有head和body
			resultMap=sendRequest(logBean,serviceId, version, paramMap);
			Map headMap=(Map)resultMap.get("head");
			if(null!=headMap){
				String headCode=(String)headMap.get("code");
				if("00000".equals(headCode)){
					Map bodyMap=(Map)resultMap.get("body");
					if(null!=bodyMap){
						result=bodyMap.get("result");
					}
					dto.setRespCode(SystemErrorCode.CODE_00000);
					dto.setRespDesc("success");	
				}else{
					if("20001".equals(headCode)){
						//cfca 账号不存在，进行告警
						log.error("cfca account not exists,please contract administrators");
					}
					if("20002".equals(headCode)){
						//cfca 账号被冻结，进行告警
						log.error("cfca account has been frozen,please contract administrators");
					}
					if("20003".equals(headCode)){
						//cfca 账号余额不足，进行告警
						log.error("cfca account has not enough money,please contract administrators");
					}
					if("20004".equals(headCode)){
						//cfca 非法交易类型，进行告警
						log.error("serviceId:"+serviceId+",cfca Illegal transaction type,please contract administrators");
					}
					if("20004".equals(headCode)){
						//cfca 尚未开通此业务，进行告警
						log.error("serviceId:"+serviceId+",cfca Not yet opened this business,please contract administrators");
					}
					dto.setRespCode(ApiTransationCode.CODE_20002);
					dto.setRespDesc("occured error");
					
				}
			}else{
				dto.setRespCode(ApiTransationCode.CODE_20002);
				dto.setRespDesc("no data");
			}
			
			dto.setResult(result);
						
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
	
	@SuppressWarnings({ "rawtypes" })
	private Map sendRequest(AipPServiceUsedLog logBean,String serviceId, String version,Map<String, Object> paramMap)throws Exception{
		Map resultMap=null;
		resultMap=cFCATransactionSV.getResult(serviceId, version, getParamMap(paramMap));
		Map headMap=(Map)resultMap.get("head");
		if(null!=headMap){
			String headCode=(String)headMap.get("code");
			if("10008".equals(headCode)){
				//密钥失效，重新申请
				AipPServiceUsedLogDTO plogBean=new AipPServiceUsedLogDTO();
				plogBean.setUsedId(getPLogId());
				plogBean.setAccessToken("");
				plogBean.setClientId("");
				plogBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
				plogBean.setProviderId("2");
				plogBean.setServiceId("2");
				plogBean.setStatus("1");
				plogBean.setVersion("1.0");
				plogBean.setFromUsedId(logBean.getUsedId());
				String lastKey=(String)AipCacheUtil.getConfigFromCache("CFCA", "USER_ACCOUNT_KEY");
				String key=cFCATransactionSV.applyKey(lastKey, plogBean);
				if(StringUtil.isBlank(key)){
					//告警
					throw new Exception("使用"+lastKey+"申请新的用户密钥失败");
				}
				//重新发次请求
				resultMap=cFCATransactionSV.getResult(serviceId, version, getParamMap(paramMap));
			}
		}
		return resultMap;
	}


	@Override
	public void sendWarnSms(String warnContent) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void sendWarnEmail(String title, String warnContent) {
		// TODO 自动生成的方法存根
		
	}
	
}
