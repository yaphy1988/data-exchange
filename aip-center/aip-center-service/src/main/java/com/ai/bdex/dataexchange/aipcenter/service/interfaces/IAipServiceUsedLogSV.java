package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceUsedLog;

public interface IAipServiceUsedLogSV {
	String insertAipServiceUsedLog(AipServiceUsedLog vo)throws Exception;
	int updateAipServiceUsedLogByPK(AipServiceUsedLog vo)throws Exception;
}
