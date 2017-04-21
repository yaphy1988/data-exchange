package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsSkuSV {

    public GdsSku queryGdsSkuById(Integer skuId) throws Exception;

    public List<GdsSku> queryGdsSkuList(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;

    public int insertGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;

    public int updateGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;

    public int deleteGdsSku(Integer skuId) throws Exception;
    /**
     * 根据商品信息删除单品
     * @param gdsSkuReqDTO
     * @return
     * @throws Exception
     */
    public int deleteGdsSkuByGdsId(GdsSkuReqDTO gdsSkuReqDTO) throws Exception;
}
