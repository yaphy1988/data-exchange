package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientLoginLog;

public interface IAipClientLoginLogSV {
	int insertAipClientLoginLog(AipClientLoginLog log)throws Exception;
}
