package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientAccesstoken;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.SystemErrorCode;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipTransationRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipApiDataSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipClientAccesstokenSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipClientInfoSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IApiTransationSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;

@Service("aipTransationRSV")
public class AipTransationRSVImpl implements IAipTransationRSV{
	private static final Logger log = LoggerFactory.getLogger(AipTransationRSVImpl.class);
	@Autowired
	private IAipApiDataSV aipApiDataSV;
	@Autowired
	private IApiTransationSV apiTransationSV;
	@Autowired
	private IAipServiceUsedLogSV aipServiceUsedLogSV;
	@Autowired
	private IAipClientInfoSV aipClientInfoSV;
	@Autowired
	private IAipClientAccesstokenSV aipClientAccesstokenSV;


	@Override
	public Map<String, String> createTransation(String serviceId,
			String version, Map<String, Object> paramMap) throws Exception {		
		Map<String, String> resultMap=new HashMap<String, String>();
		String result=null;
		String logId=null;
		Timestamp requestTime=new Timestamp(System.currentTimeMillis());
		String status=null;
		String clientId=null;
		String accessToken=null;
		try{
			accessToken=(String)paramMap.get(APIConstants.AIP_PARAM_ACCESSTOKEN);
			clientId=getClientId(accessToken);
			//获取T_AIP_SERVICE_USED_LOG的logid
			logId=getLogId();

			Object invokeMap=apiTransationSV.invoke(clientId,logId,serviceId, version, paramMap);
			result=JSON.toJSONString(invokeMap);		
			resultMap.put("error_code", SystemErrorCode.CODE_00000);
			resultMap.put("reason", "success");
			resultMap.put("serialNo", logId);
			resultMap.put("result", result);
			status="1";
		}catch(Exception e ){
			log.error("", e);
			resultMap.put("error_code", SystemErrorCode.ERRORCODE_00001);
			resultMap.put("reason", "原因未明");		
			status="2";
		}finally{
			//T_AIP_SERVICE_USED_LOG进行记录
			AipServiceUsedLog vo=new AipServiceUsedLog();
			
			vo.setAccessToken(accessToken);
			vo.setClientId(clientId);
			vo.setCreateTime(requestTime);
			vo.setRequestMsg(JSON.toJSONString(paramMap));
			vo.setResponseMsg(JSON.toJSONString(resultMap));
			vo.setResponseTime(new Timestamp(System.currentTimeMillis()));
			vo.setServiceId(serviceId);
			vo.setStatus(status);
			vo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			vo.setUsedId(logId);
			vo.setVersion(version);
			aipServiceUsedLogSV.insertAipServiceUsedLog(vo);	
		}
		return resultMap;
	}
	
	private String getLogId(){		
		return DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(APIConstants.AipSeqName.SEQ_AIP_SERVICE_USED_LOG);
	}
	private String getClientId(String accessToken)throws Exception{
	    AipClientAccesstoken token=aipClientAccesstokenSV.getAipClientAccesstokenByKey(accessToken);
		if(null!=token){
			return token.getClientId();
		}
		return null;
	}
}
