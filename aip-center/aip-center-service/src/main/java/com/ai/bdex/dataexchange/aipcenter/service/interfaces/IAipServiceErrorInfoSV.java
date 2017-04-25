package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;

public interface IAipServiceErrorInfoSV {
	
	List<AipServiceErrorInfo> getbeans(String serviceId,String serviceVersion)throws Exception;
}
