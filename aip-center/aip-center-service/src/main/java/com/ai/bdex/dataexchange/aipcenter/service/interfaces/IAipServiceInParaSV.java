package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;

public interface IAipServiceInParaSV {
	List<AipServiceInPara> getBeans(String serviceId,String serviceVersion)throws Exception;
}
