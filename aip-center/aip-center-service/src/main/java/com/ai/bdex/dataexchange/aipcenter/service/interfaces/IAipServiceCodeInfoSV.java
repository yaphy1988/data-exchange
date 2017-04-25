package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;

public interface IAipServiceCodeInfoSV {
	List<AipServiceCodeInfo> getBeans(String serviceId,String serviceVersion)throws Exception;
}
