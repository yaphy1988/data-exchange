package com.ai.bdex.dataexchange.apigateway.cfca;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipPServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

public class CFCATransactionSVImpl {
	private static final Logger log = LoggerFactory.getLogger(CFCATransactionSVImpl.class);
	private static final String providerId="";
	 @Autowired
	 private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	 
	 /**
	  * CF00000001,加密同步接口
	  * @param lastKey
	  * @param dto
	  * @return
	  */
	 public String applyKey(String lastKey,AipPServiceUsedLogDTO dto){
		 String key=null;
		 String	serviceId="";
		 String version="";
		 try{
			 key=CFCATransactionBase.applyKey(lastKey);
		 }catch(Exception e){
			 log.error("apply serect failted", e);
		 }finally{
			 //记录日志
			 dto.setRequestMsg(lastKey);
			 dto.setResponseMsg(key);
			 dto.setServiceId(serviceId);
			 dto.setVersion(version);			 
			 saveLog(dto);
		 }
		 
		 return key;
	 }
	 /**
	  * CF209a0005,明文异步接口
	  * @param map
	  * @param dto
	  * @return
	  */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public Map  getCompanyName(Map<String,String> map,AipPServiceUsedLogDTO dto){
		 Map resultMap=null;
		 String	serviceId="";
		 String version="";
		 String transactionCode="CF209a0005";
		 String urlSuffix="check-company-name.json";
		 Map requestMap=null;
		 try{
			 requestMap=getFromCF209a0005toCF209a0010(map);
			 if(null!=requestMap){		 
				 resultMap=CFCATransactionBase.notEncryptedAsync(transactionCode, urlSuffix, requestMap);
			 }
		 }catch(Exception e){
			 log.error("query  failted", e);
		 }finally{
			 //记录日志
			 dto.setRequestMsg(JSON.toJSONString(requestMap));
			 dto.setResponseMsg(JSON.toJSONString(resultMap));
			 dto.setServiceId(serviceId);
			 dto.setVersion(version);			 
			 saveLog(dto);
		 }
		 return resultMap;
	 }
	 /**
	  * CF209a0006,明文异步接口
	  * @param map
	  * @param dto
	  * @return
	  */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public Map  getCompanyType(Map<String,String> map,AipPServiceUsedLogDTO dto){
		 Map resultMap=null;
		 String	serviceId="";
		 String version="";
		 String transactionCode="CF209a0006";
		 String urlSuffix="check-company-type.json";
		 Map requestMap=null;
		 try{		 
			 requestMap=getFromCF209a0005toCF209a0010(map);
			 if(null!=requestMap){				 
				 resultMap=CFCATransactionBase.notEncryptedAsync(transactionCode, urlSuffix, requestMap);
			 }
		 }catch(Exception e){
			 log.error("query  failted", e);
		 }finally{
			 //记录日志
			 dto.setRequestMsg(JSON.toJSONString(requestMap));
			 dto.setResponseMsg(JSON.toJSONString(resultMap));
			 dto.setServiceId(serviceId);
			 dto.setVersion(version);			 
			 saveLog(dto);
		 }
		 return resultMap;
	 }	 
	 /**
	  * CF209a0007,明文异步接口,每月最大交易金额接口
	  * @param map
	  * @param dto
	  * @return
	  */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public Map  getMaxPosBalancePerMonth(Map<String,String> map,AipPServiceUsedLogDTO dto){
		 Map resultMap=null;
		 String	serviceId="";
		 String version="";
		 String transactionCode="CF209a0007";
		 String urlSuffix="check-POS-balance-max.json";
		 Map requestMap=null;
		 try{
			 requestMap=getFromCF209a0005toCF209a0010(map);
			 if(null!=requestMap){				 
				 resultMap=CFCATransactionBase.notEncryptedAsync(transactionCode, urlSuffix, requestMap);
			 }
		 }catch(Exception e){
			 log.error("query  failted", e);
		 }finally{
			 //记录日志
			 dto.setRequestMsg(JSON.toJSONString(requestMap));
			 dto.setResponseMsg(JSON.toJSONString(resultMap));
			 dto.setServiceId(serviceId);
			 dto.setVersion(version);			 
			 saveLog(dto);
		 }
		 return resultMap;
	 }	 
	 
	 
	 private void saveLog(AipPServiceUsedLogDTO dto){
		 try{
			 AipPServiceUsedLog logBean=new AipPServiceUsedLog();
			 ObjectCopyUtil.copyObjValue(dto, logBean, null, false);
			 dto.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			 logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));		 
			 logBean.setProviderId(providerId);
			 aipPServiceUsedLogSV.insertLog(logBean);
		 }catch(Exception e){
			 log.error("save record failted", e);
		 }
	 }
	 private Map<String,String> getFromCF209a0005toCF209a0010(Map<String,String> map){
		 Map<String,String> requestMap=null;
		 if(null!=map){
			 requestMap=new HashMap<String,String>();
			 requestMap.put("companyMID", map.get("companyMID"));
			 requestMap.put("companyName", map.get("companyName"));
			 requestMap.put("registrationNumber", map.get("registrationNumber"));
			 requestMap.put("personName", map.get("personName"));			 
		 }
		 return requestMap;
	 }
}
