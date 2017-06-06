package com.ai.bdex.dataexchange.busi.cfca.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.ApiTransationCode;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants.SystemErrorCode;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipServiceUsedLogRSV;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipTransationRSV;
import com.ai.bdex.dataexchange.security.ServiceCheckChain;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.bdex.dataexchange.utils.ApiWebHttpUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.fastjson.JSON;

@Component
@Path("cfca")
public class CfCATransationController {
	 private final Log logger = LogFactory.getLog(getClass());
	 @DubboConsumer(timeout=30000)
	 private IAipTransationRSV aipTransationRSV;
	 @DubboConsumer(timeout=10000)
	 private IAipServiceUsedLogRSV gatewayAipServiceUsedLogRSV;
	 
	 @Path("/query/{serviceId}")
	 @POST
	 @Security(authorCheckType=ServiceCheckChain.class)
	 @Produces({MediaType.APPLICATION_FORM_URLENCODED+";charset=utf-8"})
	 public String getResultFromCFCA(@Context HttpServletRequest request,@PathParam("serviceId") String serviceId)throws Exception{
		 String result=null;
		 String version="1.0";
		 ApiTransationRespDTO resultMap=null;
		 AipServiceUsedLogDTO logVo=new AipServiceUsedLogDTO();
		 String logId=null;
		 try{
			 if(!StringUtil.isBlank(serviceId)){
				 //参数封装
				 Map<String,Object> paramMap=ApiWebHttpUtil.getParamString(request);
				 //保存日志。T_AIP_SERVICE_USED_LOG进行记录
				AipServiceUsedLogDTO vo=new AipServiceUsedLogDTO();		
				vo.setAccessToken((String)paramMap.get(APIConstants.AIP_PARAM_ACCESSTOKEN));					
				vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
				vo.setRequestMsg(JSON.toJSONString(paramMap));
				vo.setServiceId(serviceId);
				vo.setStatus("0");
				vo.setUpdateTime(new Timestamp(System.currentTimeMillis()));					
				vo.setVersion(version);
				logId=gatewayAipServiceUsedLogRSV.insertAipServiceUsedLogWithoutClientId(vo);
					
				 //调用服务
				 resultMap= aipTransationRSV.createTransation(logId,serviceId, version, paramMap);
				 if(null!=resultMap){
					resultMap.setSerialNo(logId);
					if(SystemErrorCode.CODE_00000.equals(resultMap.getRespCode())){
						logVo.setStatus("1");
					}else{
						logVo.setStatus("2");
					}
					logVo.setResponseMsg(JSON.toJSONString(resultMap));
				 }
			 }

		 }catch(Exception e){
			 logger.error("getResultFromCFCA error", e);
			 resultMap=new ApiTransationRespDTO();
			 resultMap.setSerialNo(logId);
			 resultMap.setRespCode(ApiTransationCode.CODE_20009);
			 resultMap.setRespDesc("unknow reason");
			 resultMap.setResult(null);
				
			 logVo.setResponseMsg(e.getMessage());
			 logVo.setStatus("2");
		 }
		logVo.setUsedId(logId);
		logVo.setResponseTime(new Timestamp(System.currentTimeMillis()));
		logVo.setUpdateTime(new Timestamp(System.currentTimeMillis()));		
		gatewayAipServiceUsedLogRSV.updateByPrimaryKeySelective(logVo);
		 if(null!=resultMap){
			 result=JSON.toJSONString(resultMap);
		 }
		 return result;
	 }
}
