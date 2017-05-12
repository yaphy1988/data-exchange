package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientAccesstokenDTO;

public interface IAipClientAccesstokenRSV {
	AipClientAccesstokenDTO getAipClientAccesstokenByKey(String accesstoken)throws Exception;
	int insertAipClientAccesstoken(AipClientAccesstokenDTO token)throws Exception;
}
