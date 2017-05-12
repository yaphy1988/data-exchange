package com.ai.bdex.dataexchange.tradecenter.service.interfaces.page;

 

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;

public interface ISortContentSV {
	
	   public SortContentRespDTO querysortContenById(SortContentReqDTO contentReqDTO) throws Exception;
	
	   public List<SortContent> querysortContenList(SortContent sortContent) throws Exception;
	   
	   public long insertSortContent(SortContentReqDTO sortContentReqDTO)throws Exception;
	   
	   public long updateSortContent(SortContentReqDTO sortContentReqDTO)throws Exception;
}
