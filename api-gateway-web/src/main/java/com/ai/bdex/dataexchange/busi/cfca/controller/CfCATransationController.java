package com.ai.bdex.dataexchange.busi.cfca.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
	 
	 @Path("/query/{serviceId}")
	 @POST
	 @Security(authorCheckType=ServiceCheckChain.class)
	 @Produces({MediaType.APPLICATION_FORM_URLENCODED+";charset=utf-8"})
	 public String getResultFromCFCA(@Context HttpServletRequest request,@PathParam("serviceId") String serviceId)throws Exception{
		 String result=null;
		 String version="1.0";
		 Map<String ,Object> resultMap=new HashMap<String ,Object>();
		 try{
			 if(!StringUtil.isBlank(serviceId)){
				 //参数封装
				 Map<String,Object> paramMap=ApiWebHttpUtil.getParamString(request);
				 //调用服务
				 resultMap= aipTransationRSV.createTransation(serviceId, version, paramMap);
			 }
		 }catch(Exception e){
			 logger.error("", e);
		 }
		 if(null!=resultMap){
			 result=JSON.toJSONString(resultMap);
		 }
		 return result;
	 }

}
