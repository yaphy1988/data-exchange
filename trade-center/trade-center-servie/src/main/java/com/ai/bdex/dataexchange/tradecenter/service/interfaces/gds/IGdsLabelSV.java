package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsLabelSV {

    public GdsLabel queryGdsLabelById(Integer labId) throws Exception;

    public List<GdsLabel> queryGdsLabelList(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;

    public int insertGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;

    public int updateGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;

    public int deleteGdslabelById(Integer labId) throws Exception;
    public List<GdsLabelRespDTO> queryGdsLabelListDTO(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;
    public int deleteGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception ;
}
