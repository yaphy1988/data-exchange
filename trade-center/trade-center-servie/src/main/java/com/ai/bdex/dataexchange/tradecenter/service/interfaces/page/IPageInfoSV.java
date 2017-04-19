package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
 
public interface IPageInfoSV {
	   public PageInfo queryPageInfoById(Integer PageInfoid) throws Exception;

	   public List<PageInfo> queryPageInfoList(PageInfo exam) throws Exception;
}
