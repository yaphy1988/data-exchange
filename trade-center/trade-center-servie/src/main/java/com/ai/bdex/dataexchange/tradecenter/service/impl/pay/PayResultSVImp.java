package com.ai.bdex.dataexchange.tradecenter.service.impl.pay;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayResultMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayResult;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayResultSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;

@Service("iPayResultSV")
public class PayResultSVImp implements IPayResultSV {
	@Autowired
    private PayResultMapper payResultMapper;
	@Override
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception {
		PayResult record = new PayResult();
		BeanUtils.copyProperties(payResultReqDTO, record);
		Long seq =  SeqUtil.getLong("SEQ_PAY_RESULT");
	    record.setPayResultSn(seq);
 	    record.setCreateTime(DateUtil.getNowAsDate());
		return payResultMapper.insert(record);
	}

}
