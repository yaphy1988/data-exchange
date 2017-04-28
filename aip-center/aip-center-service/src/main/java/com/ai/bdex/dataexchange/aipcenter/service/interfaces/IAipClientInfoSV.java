package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfo;

public interface IAipClientInfoSV {
	AipClientInfo getAipClientInfoByKey(String clientId)throws Exception;
	int insertAipClientInfo(AipClientInfo info)throws Exception;
}
