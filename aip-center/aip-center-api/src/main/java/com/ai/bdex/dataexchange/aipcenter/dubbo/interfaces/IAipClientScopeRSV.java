package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientScopeDTO;

public interface IAipClientScopeRSV {
	AipClientScopeDTO getAipClientScopeByKey(String clientId,String scopeId)throws Exception;
}
