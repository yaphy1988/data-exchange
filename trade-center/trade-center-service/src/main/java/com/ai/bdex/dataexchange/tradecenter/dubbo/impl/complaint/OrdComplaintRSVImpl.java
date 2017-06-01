package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.complaint;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.complaint.IOrdComplaintRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint.IOrdComplaintContSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint.IOrdComplaintSV;
@Service("iOrdComplaintRSV")
public class OrdComplaintRSVImpl implements IOrdComplaintRSV {
	@Resource
    private IOrdComplaintSV iordComplaintSV;
	@Resource
    private IOrdComplaintContSV iordComplaintContSV;

	@Override
	public PageResponseDTO<OrdComplaintContRespDTO> queryOrdComplaintContPageInfo(
			OrdComplaintContReqDTO ordComplaintContReqDTO) throws Exception {
		return iordComplaintContSV.queryOrdComplaintContPageInfo(ordComplaintContReqDTO);
	}


	@Override
	public PageResponseDTO<OrdComplaintRespDTO> queryOrdComplaintPageInfo(OrdComplaintReqDTO ordComplaintReqDTO)
			throws Exception {
		return iordComplaintSV.queryOrdComplaintPageInfo(ordComplaintReqDTO);
	}


	@Override
	public long insertOrdComplaintCont(OrdComplaintContReqDTO complaintContReqDTO) throws Exception {
		if(complaintContReqDTO == null){
			throw new BusinessException("新增complaintContReqDTO对象不能为空！");
		}
		return iordComplaintContSV.insertOrdComplaintCont(complaintContReqDTO);
	}


	@Override
	public long insertOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception {
		if(ordComplaintReqDTO == null){
			throw new BusinessException("新增ordComplaintReqDTO对象不能为空！");
		}
		return iordComplaintSV.insertOrdComplaint(ordComplaintReqDTO);
	}


	@Override
	public long updateOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception {
		if(ordComplaintReqDTO == null){
			throw new BusinessException("更新ordComplaintReqDTO对象不能为空！");
		}
		return iordComplaintSV.updateOrdComplaint(ordComplaintReqDTO);
	}
	@Override
	public long updateOrdComplaintCont(OrdComplaintContReqDTO ordComplaintContReqDTO) throws Exception{
		if(ordComplaintContReqDTO == null){
			throw new BusinessException("更新ordComplaintContReqDTO对象不能为空！");
		}
		return iordComplaintContSV.updateOrdComplaintCont(ordComplaintContReqDTO);
	}
}
