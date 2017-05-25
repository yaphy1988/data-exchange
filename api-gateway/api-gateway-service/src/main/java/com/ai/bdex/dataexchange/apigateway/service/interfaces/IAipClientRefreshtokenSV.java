package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientRefreshtoken;

public interface IAipClientRefreshtokenSV {
	AipClientRefreshtoken getAipClientRefreshtokenByKey(String refreshToken)throws Exception;
	int insertAipClientRefreshtoken(AipClientRefreshtoken token)throws Exception;
	int updateAipClientRefreshtokenExpireTime(AipClientRefreshtoken token)throws Exception;
}
