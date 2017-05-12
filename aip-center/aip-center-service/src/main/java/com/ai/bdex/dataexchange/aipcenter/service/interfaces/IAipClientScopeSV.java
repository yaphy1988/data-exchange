package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientScope;

public interface IAipClientScopeSV {
	AipClientScope getAipClientScopeByKey(String clientId,String scopeId)throws Exception;
}
