package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/5/12.
 */
@Service("iAipProviderServiceMgrRSV")
public class AipProviderServiceMgrRSVImpl implements IAipProviderServiceMgrRSV {

    private final static Logger log = LoggerFactory.getLogger(AipProviderServiceMgrRSVImpl.class);

    @Autowired
    private IAipProviderServiceInfoSV iAipProviderServiceInfoSV;

    @Override
    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception {

        PageResponseDTO<AipProviderServiceInfoRespDTO> pageResponseDTO = null;
        try {
            pageResponseDTO = iAipProviderServiceInfoSV.pagePServiceInfo(aipProviderServiceInfoReqDTO);
        }catch (Exception e){
            log.error("查询供应商服务分页列表异常：",e);
        }

        return pageResponseDTO;
    }
}
