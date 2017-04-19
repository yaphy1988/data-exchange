package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsSkuReqDTO;

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
}
