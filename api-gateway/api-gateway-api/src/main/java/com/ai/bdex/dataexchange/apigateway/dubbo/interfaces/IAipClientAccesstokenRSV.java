package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientAccesstokenDTO;

public interface IAipClientAccesstokenRSV {
	AipClientAccesstokenDTO getAipClientAccesstokenByKey(String accesstoken)throws Exception;
	int insertAipClientAccesstoken(AipClientAccesstokenDTO token)throws Exception;
}
