package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLog;


public interface IAipServiceUsedLogSV {
	String insertAipServiceUsedLog(AipServiceUsedLog vo)throws Exception;
	int updateAipServiceUsedLogByPK(AipServiceUsedLog vo)throws Exception;
	int updateByPrimaryKeySelective(AipServiceUsedLog vo)throws Exception;
	int updateByPrimaryKeySelectiveUnRollback(AipServiceUsedLog vo)throws Exception;
}
