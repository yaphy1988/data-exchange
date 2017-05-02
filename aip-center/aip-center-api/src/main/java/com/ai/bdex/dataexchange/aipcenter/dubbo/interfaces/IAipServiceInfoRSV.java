package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipServiceInfoRSV {

	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req)throws Exception;
	List<AipServiceInfoDTO> selectServiceByServiceId(String serviceId)throws Exception;
	AipServiceInfoDTO selectServiceByPk(String serviceId,String version)throws Exception;
}
