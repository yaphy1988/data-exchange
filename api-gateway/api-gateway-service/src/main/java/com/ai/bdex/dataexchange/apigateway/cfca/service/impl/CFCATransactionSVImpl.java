package com.ai.bdex.dataexchange.apigateway.cfca.service.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.cfca.CFCATransactionBase;
import com.ai.bdex.dataexchange.apigateway.cfca.service.interfaces.ICFCATransactionSV;
import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipCfcaConfMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConf;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipCfcaConfKey;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimple;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipPServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaSimpleSV;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;

@Service("cFCATransactionSV")
public class CFCATransactionSVImpl implements ICFCATransactionSV{
	private static final Logger log = LoggerFactory.getLogger(CFCATransactionSVImpl.class);
	private static final String providerId="2";
	 @Autowired
	 private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	 @Autowired
	 private AipCfcaConfMapper aipCfcaConfMapper;
	@Autowired
	private IAipParaSimpleSV gatewayAipParaSimpleSV;
	 
	 /**
	  * CF00000001,加密同步接口,密钥申请接口
	  * @param lastKey
	  * @param dto
	  * @return
	  */
	 public String applyKey(String lastKey,AipPServiceUsedLogDTO dto)throws Exception{
		 String key=null;
		 String	serviceId=dto.getServiceId();
		 String version=dto.getVersion();
		 try{
			 key=CFCATransactionBase.applyKey(lastKey);
			 if(!StringUtil.isBlank(key)){
				//写入缓存
				String cacheKey=APIConstants.AipCache.AIP_CACHE_NAME_PREFIX+"CFCA_USER_ACCOUNT_KEY";
				CacheUtil.delItem(cacheKey);
				CacheUtil.addItem(cacheKey, key);
				//回写配置表
				AipParaSimple simpleConf=new AipParaSimple();
				simpleConf.setParaLinkKey("CFCA");
				simpleConf.setSpCode("USER_ACCOUNT_KEY");
				simpleConf.setSpValue(key);
				simpleConf.setUpdateStaff("admin");
				simpleConf.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				gatewayAipParaSimpleSV.updateAipParaSimpleByKey(simpleConf);
			 }
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
	 public Map  getResult(String serviceId,String version,Map<String,String> map)throws Exception{
		 Map resultMap=null;

		 String transactionCode=null;
		 String urlSuffix=null;
		
		 try{
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
			 throw e;
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
