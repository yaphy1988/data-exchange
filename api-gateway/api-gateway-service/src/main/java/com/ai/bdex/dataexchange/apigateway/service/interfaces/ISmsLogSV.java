package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.sql.Timestamp;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsRevLog;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipSmsSendLog;

public interface ISmsLogSV {
	String insertSmsRevLog(AipSmsRevLog log)throws Exception;
	int updateSmsRevLogByPK(String logId,String response,Timestamp responseTime)throws Exception;  
	
	String insertSmsSendLog(AipSmsSendLog log)throws Exception;
	int updateSmsSendLogByPK(String logId,String response,Timestamp responseTime)throws Exception;
}
