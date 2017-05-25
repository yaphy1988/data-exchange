package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientAccesstoken;


public interface IAipClientAccesstokenSV {
	AipClientAccesstoken getAipClientAccesstokenByKey(String accesstoken)throws Exception;
	int insertAipClientAccesstoken(AipClientAccesstoken token)throws Exception;
}
