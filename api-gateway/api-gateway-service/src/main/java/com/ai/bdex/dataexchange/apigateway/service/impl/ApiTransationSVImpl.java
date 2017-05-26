package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipApiData;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceSpring;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipApiDataSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipClientInfoSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceSpringSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiDealTransationSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiGatewayDataAccountSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiTransationSV;
import com.ai.bdex.dataexchange.apigateway.util.ApiServiceUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.util.Utils;
import com.ai.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;

@Service("apiTransationSV")
public class ApiTransationSVImpl implements IApiTransationSV{
	
	private static final Logger log = LoggerFactory.getLogger(ApiTransationSVImpl.class);
	@Autowired
	private IAipApiDataSV aipApiDataSV;
	@Autowired
	private IApiGatewayDataAccountSV apiGatewayDataAccountSV;
	@Autowired
	private IAipServiceSpringSV aipServiceSpringSV;
	@Autowired
	private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	@Autowired
	private IAipClientInfoSV aipClientInfoSV;
	@Autowired
	private IAipServiceInfoSV aipServiceInfoSV;

	@Override
	public Object invoke(String clientId,String logId,String serviceId, String version,
			Map<String, Object> paramMap) throws Exception {
		Timestamp requestTime=new Timestamp(System.currentTimeMillis());
		Object resultMap=null;
		String result=null;
		//获取usedID			
		AipClientInfo clientInfo= aipClientInfoSV.getAipClientInfoByKey(clientId);
		String userId=clientInfo.getUserId();//??
		AipServiceInfo serviceInfo=getService(serviceId);
		Long unitPrice=serviceInfo.getUnitPrice();
		BigDecimal bg=null;
		if(null!=unitPrice){
			bg=new BigDecimal(unitPrice);
		}
		//扣除费用
		DataConsumeDTO dto=new DataConsumeDTO();
		dto.setRealServiceId(serviceId);
		dto.setInvokeSeq(logId);
		dto.setUserId(userId);
		dto.setConsumeNum(1);		
		dto.setConsumeMoney(bg==null?null:bg.intValue());
		apiGatewayDataAccountSV.dealDataCharge(dto);
		//可用时第一步，从沉淀表找数据
		//对参数进行排序处理,获取MD5值，paramMap中不允许有key相同的数据
		AipServiceSpring springConf=getServiceSpring(serviceId,version);
		String beanName=springConf.getBeanName();
		String pserviceFlag=springConf.getPserviceFlag();
		
		IApiDealTransationSV apiDealTransationSV=(IApiDealTransationSV)Utils.getBean(beanName);
		
		Map<String, Object> finalParamMap=apiDealTransationSV.getFinalParamMap(paramMap);
		
		String paramMd5=ApiServiceUtil.getParamMapMd5(finalParamMap);
		AipApiData storeData= aipApiDataSV.getAipApiDataByKey(serviceId, version, paramMd5);
		if(null==storeData){
			//沉淀表没有时,调用具体服务
			resultMap=apiDealTransationSV.deal(serviceId, version, finalParamMap);
			result=JSON.toJSONString(resultMap);
			//沉淀正确结果
			AipApiData data=new AipApiData();
			data.setCreateTime(new Timestamp(System.currentTimeMillis()));
			data.setRequestMd5(paramMd5);
			data.setRequeststr(JSON.toJSONString(paramMap));
			data.setResponsestr(result);
			data.setServiceId(serviceId);
			data.setStatus("1");
			data.setVersionId(version);
			aipApiDataSV.insertAipApiData(data);				
		}else{
			resultMap =JSON.parseObject(storeData.getResponsestr(),apiDealTransationSV.getResultClass());
		}
		//如果是提供商服务，t_aip_p_service_used_log进行记录
		if("1".equals(pserviceFlag)){
			
			String providerId=serviceInfo==null?"":serviceInfo.getProviderId();
			String pServiceId=serviceInfo==null?"":serviceInfo.getpServiceId();
			String pVersion=serviceInfo==null?"":serviceInfo.getpVersion();
			
			String accessToken=(String)paramMap.get(APIConstants.AIP_PARAM_ACCESSTOKEN);
			AipPServiceUsedLog pLog=new AipPServiceUsedLog();
			pLog.setAccessToken(accessToken);
			pLog.setClientId(clientId);
			pLog.setCreateTime(requestTime );
			pLog.setProviderId(providerId);
			pLog.setRequestMsg(JSON.toJSONString(finalParamMap));
			pLog.setResponseMsg(JSON.toJSONString(resultMap));
			pLog.setResponseTime(new Timestamp(System.currentTimeMillis()));
			pLog.setServiceId(pServiceId);
			pLog.setStatus("1");
			pLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			pLog.setUsedId(getPLogId());
			pLog.setVersion(pVersion);
			aipPServiceUsedLogSV.insertLog(pLog);
		}
		return resultMap;
	}
	
	private AipServiceSpring getServiceSpring(String serviceId,String version){
		return aipServiceSpringSV.getAipServiceSpringByKey(serviceId, version);		
	}
	private String getPLogId(){    
		return DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(APIConstants.AipSeqName.SEQ_AIP_P_SERVICE_USED_LOG);
	}
	private AipServiceInfo getService(String serviceId){
		AipServiceInfo info=null;
		try{
			info=aipServiceInfoSV.selectServiceByServiceIdWithInitversion(serviceId);
		}catch(Exception e){
			log.error("query failed",e);
		}
		return info;
	}
}
