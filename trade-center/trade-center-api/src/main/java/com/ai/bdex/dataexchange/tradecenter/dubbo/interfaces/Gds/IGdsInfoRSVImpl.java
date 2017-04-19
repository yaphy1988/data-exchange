package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespDTO;

/**
 * Created by yx on 2017/4/18.
 */
public interface IGdsInfoRSVImpl {

    public GdsInfoRespDTO queryGdsInfoDetails (GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
}
