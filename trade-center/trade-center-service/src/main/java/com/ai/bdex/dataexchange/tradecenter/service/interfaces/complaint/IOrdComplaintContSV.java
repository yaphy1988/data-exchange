package com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContRespDTO;

public interface IOrdComplaintContSV {
	
	PageResponseDTO<OrdComplaintContRespDTO> queryOrdComplaintContPageInfo(OrdComplaintContReqDTO ordComplaintContReqDTO) throws Exception;
	
	OrdComplaintContRespDTO queryOrdComplaintContByExm(OrdComplaintContReqDTO complaintContReqDTO) throws Exception;
	
	public long insertOrdComplaintCont(OrdComplaintContReqDTO complaintContReqDTO) throws Exception;
	public long updateOrdComplaintCont(OrdComplaintContReqDTO complaintContReqDTO) throws Exception;
}

