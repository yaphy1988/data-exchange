package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.search;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;

public interface ISearchGdsBaseRSV {
    /**
     * 
     * querySearchGdsBaseInfo:(根据条件获取单条记录信息). <br/> 
     * 
     * @author gxq 
     * @param searchGdsBaseReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public SearchGdsBaseRespDTO querySearchGdsBaseInfo (SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException;
    /**
     * 
     * querySearchGdsBaseInfo:(根据条件获取多条记录信息.不分页). <br/> 
     * 
     * @author gxq 
     * @param searchGdsBaseReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public List<SearchGdsBaseRespDTO> querySearchGdsBaseList (SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException;
    /**
     * 
     * querySearchGdsBasePageInfo:(根据条件获取多条记录信息.分页). <br/> 
     * 
     * @author gxq 
     * @param searchGdsBaseReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public PageResponseDTO<SearchGdsBaseRespDTO> querySearchGdsBasePageInfo (SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException;
}

