package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;

public interface IAipProviderInfoSV {
	AipProviderInfo getAipProviderInfo(String providerId)throws Exception;
}
