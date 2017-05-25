package com.ai.bdex.dataexchange.tradecenter.service.impl.pay;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayIntfResultLogMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfResultLogWithBLOBs;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayIntfResultLogSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
@Service("iPayIntfResultLogSV")
public class PayIntfResultLogSVImpl implements IPayIntfResultLogSV {

	@Autowired
    private PayIntfResultLogMapper payIntfResultLogMapper;

	@Override
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception {
		PayIntfResultLogWithBLOBs record = new PayIntfResultLogWithBLOBs();
		BeanUtils.copyProperties(record, resultLogReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_INTF_RESULT_LOG");
	    record.setId(seq);
		record.setStaffId(resultLogReqDTO.getStaffId());
		//record.setStaffId(resultLogReqDTO.getStaffId());
 	    record.setRequestTime(DateUtil.getNowAsDate());
 	    record.setResponseTime(DateUtil.getNowAsDate());
		return payIntfResultLogMapper.insert(record);
	}

}
