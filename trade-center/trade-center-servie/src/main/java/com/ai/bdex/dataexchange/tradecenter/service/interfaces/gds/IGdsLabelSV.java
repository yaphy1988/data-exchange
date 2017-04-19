package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqVO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsLabelSV {

    public GdsLabel queryGdsLabelById(Integer labId) throws Exception;

    public List<GdsLabel> queryGdsLabelList(GdsLabelReqVO gdsLabelReqVO) throws Exception;

    public int insertGdsLabel(GdsLabelReqVO gdsLabelReqVO) throws Exception;

    public int updateGdsLabel(GdsLabelReqVO gdsLabelReqVO) throws Exception;

    public int deleteGdslabelById(Integer labId) throws Exception;
}
