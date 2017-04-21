package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;

/**
 * Created by yx on 2017/4/20.
 */
public interface IGdsCatRSV {

    /**
     * 根据分类Id查询商品分类信息
     * @param catId
     * @return
     * @throws Exception
     */
    public GdsCatRespDTO queryGdsCatByCatId(Integer catId) throws Exception;
    public List<GdsCatRespDTO> queryGdsCatList(GdsCatReqDTO gdsCatReqDTO) throws Exception;
}
