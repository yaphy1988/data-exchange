package com.ai.bdex.dataexchange.apigateway.sms;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;
import com.ai.bdex.dataexchange.apigateway.util.HttpUtils;
import com.ai.paas.message.AsyncMessageProcessor;
import com.ai.paas.message.MessageStatus;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class Sms2AlibabaComsumer extends AsyncMessageProcessor{
	 private static final Logger log = LoggerFactory.getLogger(Sms2AlibabaComsumer.class);
	 @Autowired
	 private ISmsLogSV smsLogSV;
	 @Autowired
	 private IAipPServiceUsedLogSV aipPServiceUsedLogSV;
	 
	 private  static final String SeqName_AipSmsSendLog="SEQ_AIP_SMS_SEND_LOG";
	 private  static final String SeqName_AipPServiceUsedLog="SEQ_P_SERVICE_USED_LOG";
	 private static final  String host = "http://sms.market.alicloudapi.com";
	 private static final   String path = "/singleSendSms";
	 private static final   String method = "GET";
	 private static final   String appcode = "83fc5ae6ab1d4b67ab998c960feb8601";
	 private static final int maxNumPerSend=100;
	 
	@Override
	public void receiveMessage(Object jo, MessageStatus status)
			throws Exception {
		JSONObject json=JSON.parseObject((String)jo);
		String topic=json.getString("topic");
		String owner=json.getString("owner");
		String batchId=json.getString("batchId");
		String logId=json.getString("logId");
		String templateCode=json.getString("templateCode");
		String content=json.getString("content");
		JSONArray phones=json.getJSONArray("phoneNums");
		JSONObject pUserLog= JSON.parseObject(json.getString("object"));
		
		AipSmsSendLog logBean=new AipSmsSendLog();
		logBean.setBatchId(batchId);
		logBean.setOwner(owner);
		logBean.setRevLogId(logId);		

		AipPServiceUsedLog pUserLogBean=new AipPServiceUsedLog();
		pUserLogBean.setAccessToken(pUserLog.getString("accessToken"));
		pUserLogBean.setClientId(owner);
		pUserLogBean.setProviderId("1");
		pUserLogBean.setServiceId("1");
		pUserLogBean.setStatus("1");
		pUserLogBean.setVersion("1.0");
		
		try{
			if(ISmsSendRSV.SMS2ALIBABA.equals(topic)){
				if(null!=phones){
					String[] phoneArr=phones.toArray(new String[phones.size()]);
				    Map<String, String> querys = new HashMap<String, String>();
				    querys.put("ParamString", content);				   
				    querys.put("SignName", "广州数据交易");
				    querys.put("TemplateCode", templateCode);
				    int count=0;
				    StringBuffer phonebf=new StringBuffer();
				    String comitString=null;
					for(String phone:phoneArr){
						phonebf.append(phone+",");
						count++;
						if(count==maxNumPerSend){
							comitString=phonebf.toString();
							comitString=comitString.substring(0,comitString.lastIndexOf(","));
							sendSms(querys, logBean,comitString, pUserLogBean);
							count=0;
							phonebf=new StringBuffer();
							comitString=null;
						}
					}
					//最后一次
					if(StringUtils.isNotBlank(phonebf.toString())){
						comitString=phonebf.toString();
						comitString=comitString.substring(0,comitString.lastIndexOf(","));
						sendSms(querys, logBean,comitString ,pUserLogBean);
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
//		    Map<String, String> querys = new HashMap<String, String>();
//		    querys.put("ParamString", "%7B%22no%22%3A%22123456%22%7D");
//		    querys.put("RecNum", "RecNum");
//		    querys.put("SignName", "SignName");
//		    querys.put("TemplateCode", "TemplateCode");
	
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
		   return response;
	 }
	 
	 private void sendSms(Map<String, String> querys,AipSmsSendLog logBean,String phones,AipPServiceUsedLog pUserLogBean ){
			HttpResponse response=null;
			String responseString=null;
			logBean.setRequestTime(new Timestamp(System.currentTimeMillis()));
			pUserLogBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
			String usedLogId=DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(SeqName_AipPServiceUsedLog);
			String sendLogId=DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(SeqName_AipSmsSendLog);
			
			querys.put("RecNum",phones);
			 try{
				 response=send2Alibaba(querys);
				 responseString=EntityUtils.toString(response.getEntity());
			 }catch(Exception e){
				 log.error(sendLogId+" send failted",e);
				 responseString=e.getMessage();
			 }finally{
				 logBean.setRequest("host:"+host+"\n"+"path:"+path+"\n"+"method:"+method+"\n"+JSON.toJSONString(querys));
				 logBean.setLogId(sendLogId);
				 logBean.setResponse(responseString);
				 logBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
				 logBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
				 
				 pUserLogBean.setRequestMsg(logBean.getRequest());
				 pUserLogBean.setUsedId(usedLogId);
				 pUserLogBean.setResponseMsg(responseString);
				 pUserLogBean.setResponseTime(new Timestamp(System.currentTimeMillis()));
				 pUserLogBean.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				 try{
					 smsLogSV.insertSmsSendLog(logBean);
				 }catch(Exception e){
					log.error("insert error",e); 
				 }
				 try{
					 aipPServiceUsedLogSV.insertLog(pUserLogBean);
				 }catch(Exception e){
					 log.error("insert error",e); 
				 }
			 }
	 }
}
