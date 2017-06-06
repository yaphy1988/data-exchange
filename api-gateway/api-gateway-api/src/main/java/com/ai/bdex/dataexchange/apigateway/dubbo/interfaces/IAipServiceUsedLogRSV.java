package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceUsedLogDTO;

public interface IAipServiceUsedLogRSV {
	String insertAipServiceUsedLogWithoutClientId(AipServiceUsedLogDTO dto)throws Exception;
	String insertAipServiceUsedLog(AipServiceUsedLogDTO dto)throws Exception;
	int updateAipServiceUsedLogByPK(AipServiceUsedLogDTO dto)throws Exception;
	int updateByPrimaryKeySelective(AipServiceUsedLogDTO vo)throws Exception;
}
