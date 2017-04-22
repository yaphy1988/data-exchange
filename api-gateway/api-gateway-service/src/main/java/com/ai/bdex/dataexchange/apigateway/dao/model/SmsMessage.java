package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.alibaba.fastjson.JSON;

public class SmsMessage {

    private String batchId;
    private String logId;
    private String topic;
    private List<String> phoneNums = new ArrayList<>();
    private Map<String ,String> content=new HashMap<String ,String>();
    private long stamp;
    private String sign;//短信签名
    private String owner;
    private String templateCode;

    public static enum TOPIC {
    	SMS2ALIBABA
    }

    public SmsMessage(TOPIC type, String batchId) {
        switch (type) {
            case SMS2ALIBABA:
                topic = ISmsSendRSV.SMS2ALIBABA;
        }
        this.batchId = batchId;
        stamp = System.currentTimeMillis();/// - 60 * 60 * 1000;
    }

    public void clear() {
        phoneNums.clear();
        content.clear();
    }

    public List<String> getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(List<String> phoneNums) {
        this.phoneNums = phoneNums;
    }

    
    public Map<String, String> getContent() {
		return content;
	}

	public void setContent(Map<String, String> content) {
		this.content = content;
	}

	public String getBatchId() {
        return batchId;
    }
	
    public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getTopic() {
        return topic;
    }

    public long getStamp() {
        return stamp;
    }
    
    public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	

    public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    
}
