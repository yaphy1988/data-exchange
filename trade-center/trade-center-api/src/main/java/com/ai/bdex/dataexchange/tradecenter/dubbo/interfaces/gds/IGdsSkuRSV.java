package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
public interface IGdsSkuRSV {

    /**
     * 查询单品信息列表
     * @param gdsSkuReqDTO
     * @return
     * @throws Exception
     */
    public List<GdsSkuRespDTO> queryGdsSkuList(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;
    /**
     * 保存单品信息
     * @param gdsSkuReqDTO
     * @return
     * @throws Exception
     */
    public int insertGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;
}
