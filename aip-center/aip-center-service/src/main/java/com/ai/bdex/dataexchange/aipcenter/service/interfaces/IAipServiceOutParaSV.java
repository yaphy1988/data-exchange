package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutPara;

public interface IAipServiceOutParaSV {
	List<AipServiceOutPara> getbeans(String serviceId,String serviceVersion)throws Exception;
}
