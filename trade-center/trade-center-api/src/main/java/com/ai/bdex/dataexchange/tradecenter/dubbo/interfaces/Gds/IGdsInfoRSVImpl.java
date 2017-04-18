package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespVO;

/**
 * Created by yx on 2017/4/18.
 */
public interface IGdsInfoRSVImpl {

    public GdsInfoRespVO queryGdsInfoDetails (GdsInfoReqVO gdsInfoReqVO) throws Exception;
}
