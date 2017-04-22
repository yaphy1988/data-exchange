package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipSmsRevLogMapper;
import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipSmsSendLogMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLogExample;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLogExample.Criteria;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLogExample;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;

@Service("smsLogSV")
public class SmsLogSVImpl implements ISmsLogSV{
	private static transient Logger log = LoggerFactory.getLogger(SmsLogSVImpl.class);
	@Autowired
	private AipSmsRevLogMapper aipSmsRevLogMapper;
	@Autowired
	private AipSmsSendLogMapper aipSmsSendLogMapper;
	@Override
	public String insertSmsRevLog(AipSmsRevLog log) throws Exception {
		aipSmsRevLogMapper.insert(log);
		return log.getLogId();
	}

	@Override
	public int updateSmsRevLogByPK(String logId, String response,
			Timestamp responseTime) throws Exception {
		AipSmsRevLog logBean=new AipSmsRevLog();
		logBean.setLogId(logId);
		logBean.setResponse(response);
		logBean.setResponseTime(responseTime);
		AipSmsRevLogExample ex=new AipSmsRevLogExample();
		AipSmsRevLogExample.Criteria cry=ex.createCriteria();	
		cry.andLogIdEqualTo(logId);
		int cnt=aipSmsRevLogMapper.updateByExampleSelective(logBean, ex);
		return cnt;
	}

	@Override
	public String insertSmsSendLog(AipSmsSendLog log) throws Exception {
		aipSmsSendLogMapper.insert(log);
		return log.getLogId();
	}

	@Override
	public int updateSmsSendLogByPK(String logId, String response,
			Timestamp responseTime) throws Exception {
		AipSmsSendLog logBean=new AipSmsSendLog();
		logBean.setLogId(logId);
		logBean.setResponse(response);
		logBean.setResponseTime(responseTime);
		AipSmsSendLogExample ex=new AipSmsSendLogExample();
		AipSmsSendLogExample.Criteria cry=ex.createCriteria();	
		cry.andLogIdEqualTo(logId);
		int cnt=aipSmsSendLogMapper.updateByExampleSelective(logBean, ex);
		return cnt;
	}
	
}
