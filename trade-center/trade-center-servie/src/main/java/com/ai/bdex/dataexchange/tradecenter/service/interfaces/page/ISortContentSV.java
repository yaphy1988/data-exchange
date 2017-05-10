package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

 

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentReqDTO;

public interface ISortContentSV {
	   public SortContent querysortContenById(Integer dcza_id) throws Exception;
	   public List<SortContent> querysortContenList(SortContent sortContent) throws Exception;
	   
	   public long insertSortContent(SortContentReqDTO sortContentReqDTO)throws Exception;
	   
	   public long updateSortContentById(SortContentReqDTO sortContentReqDTO)throws Exception;
}
