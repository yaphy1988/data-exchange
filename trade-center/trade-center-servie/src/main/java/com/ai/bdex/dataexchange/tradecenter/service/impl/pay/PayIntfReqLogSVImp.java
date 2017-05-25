package com.ai.bdex.dataexchange.tradecenter.service.impl.pay;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayIntfReqLogMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfReqLogWithBLOBs;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayIntfReqLogSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
@Service("iPayIntfReqLogSV")
public class PayIntfReqLogSVImp implements IPayIntfReqLogSV {
	@Autowired
    private PayIntfReqLogMapper payIntfReqLogMapper;
	
	@Override
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception {
		PayIntfReqLogWithBLOBs record = new PayIntfReqLogWithBLOBs();
		BeanUtils.copyProperties(record, payIntfReqLogReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_INTF_REQ_LOG");
	    record.setId(seq);
		record.setStaffId(payIntfReqLogReqDTO.getStaffId());
		//record.setStaffId(payIntfReqLogReqDTO.getStaffId());
 	    record.setRequestTime(DateUtil.getNowAsDate());
 	    record.setResponseTime(DateUtil.getNowAsDate());
		return payIntfReqLogMapper.insert(record);
	}


}
