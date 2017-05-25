package com.ai.bdex.dataexchange.tradecenter.service.impl.pay;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayRequestMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayRequest;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayRequestSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;

@Service("iPayRequestSV")
public class PayRequestSVImp implements IPayRequestSV {
	
	@Autowired
    private PayRequestMapper payRequestMapper;
	@Override
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception {
		PayRequest record = new PayRequest();
		BeanUtils.copyProperties(payRequestReqDTO, record);
		Long seq =  SeqUtil.getLong("SEQ_PAY_REQUEST");
	    record.setId(seq);
		record.setStaffId(payRequestReqDTO.getStaffId());
 	    record.setCreateTime(DateUtil.getNowAsDate());
 	    record.setRequestTime(DateUtil.getNowAsDate());
		record.setStaffId(payRequestReqDTO.getCreateStaff());
 	    record.setCreateStaff(payRequestReqDTO.getCreateStaff());
 	    
		return payRequestMapper.insert(record);
				
	}

}
