package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Gds;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespDTO;

/**
 * Created by yx on 2017/4/18.
 */
public interface IGdsInfoRSV {

    public GdsInfoRespDTO queryGdsInfoDetails (GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
    
    /**
     * 查询商品分类
     * @param gdsCatReqDTO
     * @return
     * @throws Exception
     */
    public List<GdsCatRespDTO> queryGdsCatListDTO(GdsCatReqDTO gdsCatReqDTO) throws Exception;
}
