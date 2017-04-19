package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsCatSV {
    public GdsCat queryGdsCatById(Integer catId) throws Exception;

    public List<GdsCat> queryGdsCatList(GdsCatReqVO gdsCatReqVO) throws Exception;

    public int insertGdsCat(GdsCatReqVO gdsCatReqVO) throws Exception;

    public int updateGdsCat(GdsCatReqVO gdsCatReqVO) throws Exception;

    public int deleteGdsCatById(Integer catId) throws Exception;

}
