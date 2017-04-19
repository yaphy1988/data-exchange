package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Prop;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfo2PropSV {
    public GdsInfo2Prop queryGdsInfo2PropById(Integer gpId) throws Exception;

    public List<GdsInfo2Prop> queryGdsInfo2PropList(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception;

    public int insertGdsInfo2Prop(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception;

    public int updateGdsInfo2Prop(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception;

    public int deleteGdsInfo2PropById(Integer gpId) throws Exception;
}
