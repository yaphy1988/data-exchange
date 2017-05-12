package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;

public interface ISortInfoSV {
	   public SortInfoRespDTO querySortInfoById(SortInfoReqDTO sortInfoReqDTO) throws Exception;

	   public List<SortInfo> querySortInfoList(SortInfo exam) throws Exception;
	   
	   public long insertSortInfo(SortInfoReqDTO sortInfoReqDTO)throws Exception;
	   
	   public long updateSortInfoById(SortInfoReqDTO sortInfoReqDTO)throws Exception;
}
