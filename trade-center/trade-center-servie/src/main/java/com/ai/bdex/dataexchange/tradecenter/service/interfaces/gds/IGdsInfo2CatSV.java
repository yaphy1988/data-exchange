package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Cat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2CatReqDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfo2CatSV {

    public GdsInfo2Cat queryGdsInfo2CatById(Integer gcId) throws Exception;

    public List<GdsInfo2Cat> queryGdsInfo2CatList(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception;

    public int insertGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception;

    public int updateGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception;

    public int deleteGdsInfo2CatById(Integer gcId) throws Exception;
}
