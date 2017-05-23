package com.ai.bdex.dataexchange.apigateway.cfca;

import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipCfcaConfMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConf;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConfKey;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipPServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

public class CFCATransactionSVImpl {
	private static final Logger log = LoggerFactory.getLogger(CFCATransactionSVImpl.class);
	private static final String providerId="";
	 @Autowired
	 private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	 @Autowired
	 private AipCfcaConfMapper aipCfcaConfMapper;
	 
	 /**
	  * CF00000001,加密同步接口,密钥申请接口
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
	  * 根据配置进行查询
	  * @param map
	  * @param dto not null,serviceId,version non-null
	  * @return
	  */
	 @SuppressWarnings({ "rawtypes" })
	 public Map  getCompanyName(Map<String,String> map,AipPServiceUsedLogDTO dto){
		 Map resultMap=null;
		 String	serviceId=null;
		 String version=null;
		 String transactionCode=null;
		 String urlSuffix=null;
		
		 try{
			 serviceId=dto.getServiceId();
			 version=dto.getVersion();
			 if(StringUtil.isBlank(serviceId)||StringUtil.isBlank(version)){
				 throw new Exception("serviceId,version non-null");
			 }
			 AipCfcaConf conf= getCfcaConf(serviceId,version);
			 if(null!=conf){
				 transactionCode=conf.getTransactionCode();
				 urlSuffix=conf.getUrlSuffix();
				 String encryptFlag=conf.getEncryptFlag();
				 String asyncFlag=conf.getAsyncFlag();				 
				 if(null!=map){	
					 if("1".equals(encryptFlag)){
						 //加密
						 if("1".equals(asyncFlag)){
							//异步 
							 resultMap=CFCATransactionBase.encryptAsync(transactionCode, urlSuffix, map);
						 }else if("0".equals(asyncFlag)){
							 //同步
							 resultMap=CFCATransactionBase.encryptSync(transactionCode, urlSuffix, map);
						 }
					 }else if("0".equals(encryptFlag)){
						 //明文
						 if("1".equals(asyncFlag)){
							//异步 
							 resultMap=CFCATransactionBase.notEncryptedAsync(transactionCode, urlSuffix, map);
						 }else if("0".equals(asyncFlag)){
							 //同步
							 resultMap=CFCATransactionBase.notEncryptSync(transactionCode, urlSuffix, map);
						 }
					 }					 
				 }
			 }
		 }catch(Exception e){
			 log.error("query  failted", e);
		 }finally{
			 //记录日志
			 dto.setRequestMsg(JSON.toJSONString(map));
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
	 
	 private AipCfcaConf getCfcaConf(String pServiceId,String version)throws Exception{
		 AipCfcaConfKey key=new AipCfcaConfKey();
		 key.setpServiceId(pServiceId);
		 key.setVersion(version);
		 return aipCfcaConfMapper.selectByPrimaryKey(key);
	 }
}
