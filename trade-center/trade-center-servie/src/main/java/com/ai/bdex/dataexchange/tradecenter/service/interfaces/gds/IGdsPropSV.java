package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsPropReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsPropSV {

    public GdsProp queryGdsPropById(Integer proId) throws Exception;

    public List<GdsProp> queryGdsPropList(GdsPropReqVO gdsPropReqVO) throws Exception;

    public int insertGdsProp(GdsPropReqVO gdsPropReqVO) throws Exception;

    public int updateGdsProp(GdsPropReqVO gdsPropReqVO) throws Exception;

    public int deleteGdsPropById(Integer proId) throws Exception;
}
