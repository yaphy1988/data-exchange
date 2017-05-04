package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceUsedLogDTO;

public interface IAipServiceUsedLogRSV {
	String insertAipServiceUsedLog(AipServiceUsedLogDTO dto)throws Exception;
	int updateAipServiceUsedLogByPK(AipServiceUsedLogDTO dto)throws Exception;
}
