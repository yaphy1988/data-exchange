package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLog;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.ISmsLogSV;

@Service("smsLogSV")
public class SmsLogSVImpl implements ISmsLogSV{

	@Override
	public String insertSmsRevLog(AipSmsRevLog log) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int updateSmsRevLogByPK(String logId, String response,
			Timestamp responseTime) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public String insertSmsSendLog(AipSmsSendLog log) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int updateSmsSendLogByPK(String logId, String response,
			Timestamp responseTime) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
