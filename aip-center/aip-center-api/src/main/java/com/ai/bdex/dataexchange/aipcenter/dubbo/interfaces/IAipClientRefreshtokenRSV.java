package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientRefreshtokenDTO;

public interface IAipClientRefreshtokenRSV {
	AipClientRefreshtokenDTO getAipClientRefreshtokenByKey(String refreshToken)throws Exception;
	int insertAipClientRefreshtoken(AipClientRefreshtokenDTO token)throws Exception;
	int updateAipClientRefreshtokenExpireTime(AipClientRefreshtokenDTO token)throws Exception;
}
