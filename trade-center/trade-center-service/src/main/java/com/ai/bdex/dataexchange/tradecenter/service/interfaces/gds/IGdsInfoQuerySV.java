package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;

public interface IGdsInfoQuerySV {
    /**
     * 
     * queryGdsInfoListPage:(分页获取商品列表). <br/> 
     * 
     * @author gxq 
     * @param gdsInfoReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
}

