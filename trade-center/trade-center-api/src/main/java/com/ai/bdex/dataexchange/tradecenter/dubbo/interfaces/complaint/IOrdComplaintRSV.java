package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.complaint;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;

public interface IOrdComplaintRSV {
	
	PageResponseDTO<OrdComplaintRespDTO> queryOrdComplaintPageInfo(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception;
	
	PageResponseDTO<OrdComplaintContRespDTO> queryOrdComplaintContPageInfo(OrdComplaintContReqDTO ordComplaintContReqDTO) throws Exception;

	public long insertOrdComplaintCont(OrdComplaintContReqDTO complaintContReqDTO) throws Exception;
	
	public long insertOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception;
	
	public long updateOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception;

	public long updateOrdComplaintCont(OrdComplaintContReqDTO ordComplaintContReqDTO) throws Exception;
}
