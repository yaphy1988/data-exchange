package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
 
public interface IPageInfoSV {
	   public PageInfo queryPageInfoById(Integer PageInfoid) throws Exception;
}
