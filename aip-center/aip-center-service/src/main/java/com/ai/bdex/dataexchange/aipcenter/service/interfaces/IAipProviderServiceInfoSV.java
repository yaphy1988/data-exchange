package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by yx on 2017/5/12.
 */
public interface IAipProviderServiceInfoSV {

    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception;
}
