package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientLoginLogDTO;

public interface IAipClientLoginLogRSV {
	int insertAipClientLoginLog(AipClientLoginLogDTO log)throws Exception;
}
