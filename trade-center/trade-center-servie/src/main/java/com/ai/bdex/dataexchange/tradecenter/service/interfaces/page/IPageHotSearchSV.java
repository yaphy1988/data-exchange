package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;

public interface IPageHotSearchSV {
	public PageHotSearch queryPageHotSearchById(Integer page_id) throws Exception ;
	
	public  List<PageHotSearch>  queryPageHotSearchNavList(PageHotSearch exam) throws Exception;
	
    public  PageResponseDTO<PageHotSearchRespDTO>  queryPageHotSearchPageInfo(PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException;  
}
