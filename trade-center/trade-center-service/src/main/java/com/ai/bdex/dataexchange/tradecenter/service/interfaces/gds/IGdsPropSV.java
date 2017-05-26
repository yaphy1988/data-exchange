package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsPropSV {

    public GdsProp queryGdsPropById(Integer proId) throws Exception;

    public List<GdsProp> queryGdsPropList(GdsPropReqDTO gdsPropReqDTO) throws Exception;

    public int insertGdsProp(GdsPropReqDTO gdsPropReqDTO) throws Exception;

    public int updateGdsProp(GdsPropReqDTO gdsPropReqDTO) throws Exception;

    public int deleteGdsPropById(Integer proId) throws Exception;
}
