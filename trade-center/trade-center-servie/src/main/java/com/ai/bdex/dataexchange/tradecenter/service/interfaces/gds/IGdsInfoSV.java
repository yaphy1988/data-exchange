package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfoSV {

    public GdsInfo queryGdsInfoById(Integer gdsId) throws Exception;

    public List<GdsInfo> queryGdsInfoList(GdsInfoReqVO gdsInfoReqVO) throws Exception;

    public int insertGdsInfo(GdsInfoReqVO gdsInfoReqVO) throws Exception;

    public int updateGdsInfo(GdsInfoReqVO gdsInfoReqVO) throws Exception;

    public int deleteGdsInfoById(Integer gdsId) throws Exception;
}
