package com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;

/**
 * Created by yx on 2017/4/17.
 */
public interface IGdsCatSV {
    public GdsCat queryGdsCatById(Integer catId) throws Exception;

    public List<GdsCat> queryGdsCatList(GdsCatReqDTO gdsCatReqDTO) throws Exception;

    public int insertGdsCat(GdsCatReqDTO gdsCatReqDTO) throws Exception;

    public int updateGdsCat(GdsCatReqDTO gdsCatReqDTO) throws Exception;

    public int deleteGdsCatById(Integer catId) throws Exception;
    
    public List<GdsCatRespDTO> queryGdsCatListDTO(GdsCatReqDTO gdsCatReqDTO) throws Exception;
    /**
     * 
     * queryCatPageInfo:(获取分类分页). <br/> 
     * 
     * @author gxq 
     * @param gdsCatReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatRespDTO> queryCatPageInfo(GdsCatReqDTO gdsCatReqDTO) throws BusinessException;
    
    public int deleteGdsCatInfo(Integer catId) throws BusinessException;
}
