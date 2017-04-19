package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuik;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsLabelQuikSV {
    public GdsLabelQuik queryGdsLabelQuikById(Integer labId) throws Exception;

    public List<GdsLabelQuik> queryGdsLabelQuikList(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;

    public int insertGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;

    public int updateGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;

    public int deleteGdsLabelQuikById(Integer labId) throws Exception;
    public List<GdsLabelQuikRespDTO> queryGdsLabelQuikListDTO(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception;
}
