package com.ai.bdex.dataexchange.apigateway.sms;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.SmsMessage;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;
import com.ai.bdex.dataexchange.apigateway.util.HttpUtils;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;

public class Sms2AlibabaByThread implements Runnable{
	 private static final Logger log = LoggerFactory.getLogger(Sms2AlibabaByThread.class);	
	 private ISmsLogSV smsLogSV;
	 private  static final String SeqName_AipSmsSendLog="SEQ_AIP_SMS_SEND_LOG";
	 private static final  String host = "http://sms.market.alicloudapi.com";
	 private static final   String path = "/singleSendSms";
	 private static final   String method = "GET";
	 private static final   String appcode = "83fc5ae6ab1d4b67ab998c960feb8601";	 
	 private static final int maxNumPerSend=100;
	 private SmsMessage mess;
	 
	 public Sms2AlibabaByThread(SmsMessage mess){
		 this.mess=mess;
	 }
	 
	@Override
	public void run() {
		sendToAlibaba(this.mess)	;
	}
	public ISmsLogSV getSmsLogSV() {
		return smsLogSV;
	}
	public void setSmsLogSV(ISmsLogSV smsLogSV) {
		this.smsLogSV = smsLogSV;
	}
	
	public void sendToAlibaba(SmsMessage mess){
		
		String topic=mess.getTopic();
		String owner=mess.getOwner();
		String batchId=mess.getBatchId();
		String logId=mess.getLogId();
		String templateCode=mess.getTemplateCode();
		Map<String,String> content=mess.getContent();
		List<String> phones=mess.getPhoneNums();
		AipSmsSendLog logBean=new AipSmsSendLog();
		logBean.setBatchId(batchId);
		logBean.setOwner(owner);		
		logBean.setRevLogId(logId);
		logBean.setRequestTime(new Timestamp(System.currentTimeMillis()));

		
		try{
			if(ISmsSendRSV.SMS2ALIBABA.equals(topic)){
				if(!CollectionUtil.isEmpty(phones)){
					
				    Map<String, String> querys = new HashMap<String, String>();
				    querys.put("ParamString", URLEncoder.encode(JSON.toJSONString(content), "utf-8"));				   
				    querys.put("SignName", "广州数据交易");
				    querys.put("TemplateCode", templateCode);
				    int count=0;
				    StringBuffer phonebf=new StringBuffer();
				    String comitString=null;
					for(String phone:phones){
						phonebf.append(phone+",");
						count++;
						if(count==maxNumPerSend){
							comitString=phonebf.toString();
							comitString=comitString.substring(0,comitString.lastIndexOf(","));
							sendSms(querys, logBean,comitString );
							count=0;
							phonebf=new StringBuffer();
							comitString=null;
						}
					}
					//最后一次
					if(StringUtils.isNotBlank(phonebf.toString())){
						comitString=phonebf.toString();
						comitString=comitString.substring(0,comitString.lastIndexOf(","));
						sendSms(querys, logBean,comitString );
					}
				}
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
	
	 private HttpResponse send2Alibaba(Map<String, String> querys)throws Exception{

		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);

	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
		   return response;
	 }
	 
	 private void sendSms(Map<String, String> querys,AipSmsSendLog logBean,String phones){
			HttpResponse response=null;
			String responseString=null;
			String sendLogId=SeqUtil.getNextValueLong(SeqName_AipSmsSendLog)+DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss");
			 querys.put("RecNum",phones);
			 try{
				 response=send2Alibaba(querys);
				 responseString=response.toString();
			 }catch(Exception e){
				 log.error(sendLogId+" send failted",e);
				 responseString=e.getMessage();
			 }finally{
				 logBean.setRequest("host:"+host+"\n"+"path:"+path+"\n"+"method:"+method+"\n"+JSON.toJSONString(querys));
				 logBean.setLogId(sendLogId);
				 logBean.setResponse(responseString);
				 logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
				 logBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
				 try{
					 smsLogSV.insertSmsSendLog(logBean);
				 }catch(Exception e){
					log.error("insert error",e); 
				 }
			 }
	 }
	
}
