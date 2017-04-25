package com.ai.bdex.dataexchange.tradecenter.service.interfaces.search;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月25日上午10:09:48  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public interface ISearchGdsBaseSV {
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

