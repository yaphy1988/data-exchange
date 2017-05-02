package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsResultVO;
import com.github.pagehelper.Page;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsInfoSV {

    public GdsInfo queryGdsInfoById(Integer gdsId) throws Exception;

    public List<GdsInfo> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;

    public int insertGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;

    public int updateGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;

    public int deleteGdsInfoById(Integer gdsId) throws Exception;
    
	public GdsResultVO addGds(GdsInfoReqDTO reqDTO) throws Exception ;
	
	public GdsResultVO editGds(GdsInfoReqDTO reqDTO) throws Exception;
	
	public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception;
	
	public long count(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;

    /**
     *
     * @param gdsInfoReqDTO
     * @return
     */
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoPage(GdsInfoReqDTO gdsInfoReqDTO);
}
