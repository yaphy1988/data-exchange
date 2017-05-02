package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageHotSearchRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;

@Service("pageHotSearchRSV")
public class PageHotSearchRSVImpl implements IPageHotSearchRSV{
    @Resource
    private IPageHotSearchSV iPageHotSearchSV;
    
    @Override
    public List<PageHotSearchRespDTO> queryPageHotSearchList(
            PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException {
        return null;
    }

    @Override
    public PageResponseDTO<PageHotSearchRespDTO> queryPageHotSearchPageInfo(
            PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException {
        return iPageHotSearchSV.queryPageHotSearchPageInfo(pageHotSearchReqDTO);
    }

}

