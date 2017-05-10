package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;

public interface IAipClientInfoRSV {
	AipClientInfoDTO getAipClientInfoByKey(String clientId)throws Exception;
	int insertAipClientInfo(AipClientInfoDTO info)throws Exception;
}
