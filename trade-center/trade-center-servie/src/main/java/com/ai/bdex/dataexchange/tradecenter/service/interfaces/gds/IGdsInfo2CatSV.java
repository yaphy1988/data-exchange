package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Cat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2CatReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfo2CatSV {

    public GdsInfo2Cat queryGdsInfo2CatById(Integer gcId) throws Exception;

    public List<GdsInfo2Cat> queryGdsInfo2CatList(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception;

    public int insertGdsInfo2Cat(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception;

    public int updateGdsInfo2Cat(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception;

    public int deleteGdsInfo2CatById(Integer gcId) throws Exception;
}
