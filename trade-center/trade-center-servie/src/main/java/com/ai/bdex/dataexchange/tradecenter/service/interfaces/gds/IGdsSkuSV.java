package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsSkuReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsSkuSV {

    public GdsSku queryGdsSkuById(Integer skuId) throws Exception;

    public List<GdsSku> queryGdsSkuList(GdsSkuReqVO gdsSkuReqVO) throws Exception;

    public int insertGdsSku(GdsSkuReqVO gdsSkuReqVO) throws Exception;

    public int updateGdsSku(GdsSkuReqVO gdsSkuReqVO) throws Exception;

    public int deleteGdsSku(Integer skuId) throws Exception;
}
