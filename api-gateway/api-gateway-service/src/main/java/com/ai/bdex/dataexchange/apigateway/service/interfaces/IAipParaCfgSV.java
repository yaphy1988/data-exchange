package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfg;

public interface IAipParaCfgSV {
	List<AipParaCfg> getValidAipParaCfg();
	AipParaCfg getValidAipParaCfgByKey(String paraCode);
	int updateAipParaCfgByKey(AipParaCfg cfg);
}
