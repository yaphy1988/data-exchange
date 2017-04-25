package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;

public interface IAipServiceInfoSV {
	AipServiceInfo getAipServiceInfo(String serviceId,String serviceVersion)throws Exception;
}
