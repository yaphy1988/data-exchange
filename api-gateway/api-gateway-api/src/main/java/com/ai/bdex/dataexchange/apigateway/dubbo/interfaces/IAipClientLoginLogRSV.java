package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientLoginLogDTO;

public interface IAipClientLoginLogRSV {
	int insertAipClientLoginLog(AipClientLoginLogDTO log)throws Exception;
}
