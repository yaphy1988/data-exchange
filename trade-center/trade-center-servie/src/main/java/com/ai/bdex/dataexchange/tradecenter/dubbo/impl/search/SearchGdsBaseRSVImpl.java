package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.search;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.search.ISearchGdsBaseRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.search.ISearchGdsBaseSV;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月25日上午10:09:11  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("searchGdsBaseRSV")
public class SearchGdsBaseRSVImpl implements ISearchGdsBaseRSV{
    private static final Logger logger = LoggerFactory.getLogger(SearchGdsBaseRSVImpl.class.getName());
    @Resource
    private ISearchGdsBaseSV iSearchGdsBaseSV;
    
    @Override
    public SearchGdsBaseRespDTO querySearchGdsBaseInfo(SearchGdsBaseReqDTO searchGdsBaseReqDTO)
            throws BusinessException {
        
        return iSearchGdsBaseSV.querySearchGdsBaseInfo(searchGdsBaseReqDTO);
    }

    @Override
    public List<SearchGdsBaseRespDTO> querySearchGdsBaseList(
            SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException {
        return iSearchGdsBaseSV.querySearchGdsBaseList(searchGdsBaseReqDTO);
    }

    @Override
    public PageResponseDTO<SearchGdsBaseRespDTO> querySearchGdsBasePageInfo(
            SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException {
        return iSearchGdsBaseSV.querySearchGdsBasePageInfo(searchGdsBaseReqDTO);
    }

}

