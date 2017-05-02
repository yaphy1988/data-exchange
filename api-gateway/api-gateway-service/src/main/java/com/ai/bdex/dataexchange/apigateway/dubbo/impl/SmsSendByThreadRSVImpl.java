package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.SmsMessage;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;
import com.ai.bdex.dataexchange.apigateway.sms.Sms2AlibabaByThread;
import com.alibaba.fastjson.JSON;

@Service("smsSendByThreadRSV")
public class SmsSendByThreadRSVImpl implements ISmsSendRSV{
	private static transient Logger log = LoggerFactory.getLogger(SmsSendByThreadRSVImpl.class);
	@Autowired
    private ISmsLogSV smsLogSV;
	@Autowired
	private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	@Override
	public void sendSmsByAlibaba(List<String> phones,
			Map<String, String> params, String templateCode, String uuid,
			String owner,String token) throws Exception {

		StringBuffer request=new StringBuffer();
		try{
			request.append("phones:"+JSON.toJSONString(phones)+"\n");
			request.append("params:"+JSON.toJSONString(params)+"\n");
			request.append("templateCode:"+templateCode+"\n");
			request.append("uuid:"+uuid+"\n");
			request.append("owner:"+owner+"\n");			
			log.info(request.toString());
			
			SmsMessage mess=new SmsMessage(SmsMessage.TOPIC.SMS2ALIBABA,uuid);
			mess.setPhoneNums(phones);
			mess.setOwner(owner);
			mess.setTemplateCode(templateCode);
			mess.setLogId(uuid);
			AipPServiceUsedLog plog=new AipPServiceUsedLog();
			plog.setAccessToken(token);
			plog.setClientId(owner);
			mess.setObject(plog);
			
			if(null!=params){
				mess.setContent(params);
			}						
			Sms2AlibabaByThread thread=new Sms2AlibabaByThread(mess);
			thread.setSmsLogSV(smsLogSV);	
			thread.setAipPServiceUsedLogSV(aipPServiceUsedLogSV);
			ThreadPoolForSms.getExecutor().execute(thread);
		}catch(Exception e){
			log.error("send sms for template:"+templateCode+" failted",e);
			throw e;
		}	
	}
	
    static class ThreadPoolForSms{
    	private static ExecutorService executor =Executors.newFixedThreadPool(2);
    	public static ExecutorService getExecutor(){
    		return executor;
    	}
    }

	@Override
	public void sendVerifyCodeByAlibaba(String phone, String verifyCode)
			throws Exception {	
		String templateCode="SMS_62495440";
		List<String> phoneList=new ArrayList<String>();
		phoneList.add(phone);
		Map<String,String> map=new HashMap<String,String>();
		map.put("number", verifyCode);
		String uuid=UUID.randomUUID().toString().replace("-", "");
		sendSmsByAlibaba(phoneList,map,templateCode,uuid,"userCenter","");
	}
    
    
}
