package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/5/12.
 */
@Service("aipProviderServiceSV")
public class AipProviderServiceInfoSVImpl implements IAipProviderServiceInfoSV {
    @Override
    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception {
        return null;
    }
}
