package com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;

public interface IOrdComplaintSV {
	
	PageResponseDTO<OrdComplaintRespDTO> queryOrdComplaintPageInfo(OrdComplaintReqDTO complaintReqDTO) throws Exception;

	public long insertOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception;
	
	public long updateOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception;
}
