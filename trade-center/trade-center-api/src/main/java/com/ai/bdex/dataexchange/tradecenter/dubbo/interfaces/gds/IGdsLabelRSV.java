package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
public interface IGdsLabelRSV {

    public List<GdsLabelRespDTO> queryGdsLabelList(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;
	
    public int insertGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception ;
    public int updateGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception ;    
    public int deleteGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;
    public int updataGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception;
}
