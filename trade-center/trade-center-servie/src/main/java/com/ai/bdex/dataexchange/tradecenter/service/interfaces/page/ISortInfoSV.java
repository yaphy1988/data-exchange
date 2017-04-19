package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;

public interface ISortInfoSV {
	   public SortInfo querySortInfoById(Integer SortInfoid) throws Exception;

	   public List<SortInfo> querySortInfoList(SortInfo exam) throws Exception;
}
