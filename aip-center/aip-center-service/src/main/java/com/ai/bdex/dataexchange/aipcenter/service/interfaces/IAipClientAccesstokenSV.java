package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientAccesstoken;

public interface IAipClientAccesstokenSV {
	AipClientAccesstoken getAipClientAccesstokenByKey(String accesstoken)throws Exception;
	int insertAipClientAccesstoken(AipClientAccesstoken token)throws Exception;
}
