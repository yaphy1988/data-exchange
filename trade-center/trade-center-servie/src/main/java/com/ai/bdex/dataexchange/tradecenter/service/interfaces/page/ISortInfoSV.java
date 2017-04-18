package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;

public interface ISortInfoSV {
	   public SortInfo querySortInfoById(Integer SortInfoid) throws Exception;
}
