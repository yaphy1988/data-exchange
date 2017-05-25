package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientLoginLog;

public interface IAipClientLoginLogSV {
	int insertAipClientLoginLog(AipClientLoginLog log)throws Exception;
}
