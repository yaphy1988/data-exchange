package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;

public interface IAipPServiceUsedLogSV {
	String insertLog(AipPServiceUsedLog log)throws Exception;
}
