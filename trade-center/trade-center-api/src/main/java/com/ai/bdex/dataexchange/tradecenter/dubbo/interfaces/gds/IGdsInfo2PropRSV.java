package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
public interface IGdsInfo2PropRSV {

    public List<GdsInfo2PropRespDTO> queryGdsInfo2PropList(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception;
}
