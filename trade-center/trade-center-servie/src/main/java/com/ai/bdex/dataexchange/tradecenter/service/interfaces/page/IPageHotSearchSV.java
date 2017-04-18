package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;

public interface IPageHotSearchSV {
	public PageHotSearch queryPageHotSearchById(Integer page_id) throws Exception ;
}
