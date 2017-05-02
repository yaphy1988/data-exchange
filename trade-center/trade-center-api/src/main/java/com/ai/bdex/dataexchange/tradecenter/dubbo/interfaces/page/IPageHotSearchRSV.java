package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;

public interface IPageHotSearchRSV {
    public  List<PageHotSearchRespDTO>  queryPageHotSearchList(PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException;
    
    public  PageResponseDTO<PageHotSearchRespDTO>  queryPageHotSearchPageInfo(PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException;
}

