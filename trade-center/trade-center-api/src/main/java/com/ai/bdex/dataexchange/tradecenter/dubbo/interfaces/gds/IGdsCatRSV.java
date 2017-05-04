package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;

/**
 * Created by yx on 2017/4/20.
 */
public interface IGdsCatRSV {

    /**
     * 根据分类Id查询商品分类信息
     * @param catId
     * @return
     * @throws Exception
     */
    public GdsCatRespDTO queryGdsCatByCatId(Integer catId) throws Exception;
    public List<GdsCatRespDTO> queryGdsCatList(GdsCatReqDTO gdsCatReqDTO) throws Exception;

    /**
     * 根据当前ID获取从最低级到最高级分类异常
     * @param catId
     * @return
     * @throws Exception
     */
    public List<GdsCatRespDTO> queryLadderCatListByCatId(Integer catId) throws Exception;
    /**
     * 
     * queryCatPageInfo:(根据条件获取分类分页). <br/> 
     * 
     * @author gxq 
     * @param gdsCatReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatRespDTO> queryCatPageInfo(GdsCatReqDTO gdsCatReqDTO) throws BusinessException;
}
