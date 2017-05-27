package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimple;

public interface IAipParaSimpleSV {
	AipParaSimple getValidAipParaSimple(String paraLinkKey,String spCode);
	List<AipParaSimple> getValidAipParaSimpleListByLinkKey(String paraLinkKey);
	int updateAipParaSimpleByKey(AipParaSimple cfg);
}
