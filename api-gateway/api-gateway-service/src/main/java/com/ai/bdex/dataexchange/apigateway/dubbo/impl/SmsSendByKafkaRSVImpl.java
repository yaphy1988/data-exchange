package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.SmsMessage;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;
import com.ai.paas.message.impl.MessageSender;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;


@Service("smsSendByKafkaRSV")
public class SmsSendByKafkaRSVImpl implements ISmsSendRSV{
	private static transient Logger log = LoggerFactory.getLogger(SmsSendByKafkaRSVImpl.class);
	private static String sequenceName="SEQ_AIP_SMS_REV_LOG";
    private MessageSender messageSender = null;
    private ISmsLogSV smsLogSV;
    
	@Override
	public void sendSmsByAlibaba(List<String> phones,
			Map<String, String> params, String templateCode,String uuid,String owner) throws Exception {
		Timestamp requestTime=null;
		Timestamp responseTime=null;
		StringBuffer request=new StringBuffer();
		StringBuffer response=new StringBuffer();
		AipSmsRevLog logBean=new AipSmsRevLog(); 
		String logId=null;
		try{
			request.append("phones:"+JSON.toJSONString(phones)+"\n");
			request.append("params:"+JSON.toJSONString(params)+"\n");
			request.append("templateCode:"+templateCode+"\n");
			request.append("uuid:"+uuid+"\n");
			request.append("owner:"+owner+"\n");			
			requestTime=new Timestamp(System.currentTimeMillis());
			logId=SeqUtil.getNextValueLong(sequenceName)+DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss");
			
			SmsMessage mess=new SmsMessage(SmsMessage.TOPIC.SMS2ALIBABA,uuid);
			mess.setPhoneNums(phones);
			mess.setOwner(owner);
			mess.setTemplateCode(templateCode);
			mess.setLogId(logId);
			if(null!=params){
				mess.setContent(params);
			}
			sendMessageToKafka(mess);	
			response.append("success");
		}catch(Exception e){
			response.append(e.getMessage());
			log.error("send sms for template:"+templateCode+" failted",e);
			throw e;
		}finally{
			responseTime=new Timestamp(System.currentTimeMillis());
			logBean.setBatchId(uuid);
			logBean.setOwner(owner);
			logBean.setRequest(request.toString());
			logBean.setRequestTime(requestTime);
			logBean.setResponse(response.toString());
			logBean.setResponseTime(responseTime);
			logBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
			try{
				smsLogSV.insertSmsRevLog(logBean);
			}catch(Exception e){
				log.error("insert failted",e);
			}
		}
		
			
	}
	
    private void sendMessageToKafka(SmsMessage smsMessage){
        String message=smsMessage.toString();
        String topic=smsMessage.getTopic();
        messageSender.sendMessage(message, topic);
        log.debug("发送短信信息到kafka。topic="+topic+"。message="+message);
    }
}
