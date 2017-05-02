package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipServiceInfoSV {
	
	AipServiceInfo getAipServiceInfo(String serviceId,String serviceVersion)throws Exception;
	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req) throws Exception;
	
	List<AipServiceInfo> selectServiceByServiceId(String serviceId)throws Exception;
}
