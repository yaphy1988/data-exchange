package com.ai.bdex.dataexchange.tradecenter.service.impl.pay;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayIntfReqLogMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayIntfResultLogMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayRequestMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PayResultMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfReqLogWithBLOBs;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayIntfResultLogWithBLOBs;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayRequest;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PayResult;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayInfoSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
@Service("iPayInfoSV")
public class PayInfoSVImpl implements IPayInfoSV {
	@Resource
    private PayRequestMapper payRequestMapper;
	@Resource
    private PayResultMapper payResultMapper;
	@Resource
    private PayIntfReqLogMapper payIntfReqLogMapper;
	@Resource
    private PayIntfResultLogMapper payIntfResultLogMapper;
	@Override
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception {
		PayRequest record = new PayRequest();
		BeanUtils.copyProperties(record, payRequestReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_REQUEST");
	    record.setId(seq);
		record.setStaffId(payRequestReqDTO.getStaffId());
 	    record.setCreateTime(DateUtil.getNowAsDate());
 	    record.setRequestTime(DateUtil.getNowAsDate());
 	    record.setCreateStaff(payRequestReqDTO.getCreateStaff());
 	    
		return payRequestMapper.insert(record);
				
	}

	@Override
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception {
		PayResult record = new PayResult();
		BeanUtils.copyProperties(record, payResultReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_RESULT");
	    record.setPayResultSn(seq);
		record.setStaffId(payResultReqDTO.getStaffId());
 	    record.setCreateTime(DateUtil.getNowAsDate());
 	    record.setRequestTime(DateUtil.getNowAsDate());
		return payResultMapper.insert(record);
	}

	@Override
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception {
		PayIntfReqLogWithBLOBs record = new PayIntfReqLogWithBLOBs();
		BeanUtils.copyProperties(record, payIntfReqLogReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_INTF_REQ_LOG");
	    record.setId(seq);
		record.setStaffId(payIntfReqLogReqDTO.getStaffId());
 	    record.setRequestTime(DateUtil.getNowAsDate());
 	    record.setResponseTime(DateUtil.getNowAsDate());
		return payIntfReqLogMapper.insert(record);
	}

	@Override
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception {
		PayIntfResultLogWithBLOBs record = new PayIntfResultLogWithBLOBs();
		BeanUtils.copyProperties(record, resultLogReqDTO);
		Long seq =  SeqUtil.getLong("SEQ_PAY_INTF_RESULT_LOG");
	    record.setId(seq);
		record.setStaffId(resultLogReqDTO.getStaffId());
 	    record.setRequestTime(DateUtil.getNowAsDate());
 	    record.setResponseTime(DateUtil.getNowAsDate());
		return payIntfResultLogMapper.insert(record);
	}

}
