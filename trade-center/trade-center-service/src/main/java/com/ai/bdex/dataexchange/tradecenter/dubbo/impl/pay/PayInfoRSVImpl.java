package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.pay;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pay.IPayInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayIntfReqLogSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayIntfResultLogSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayRequestSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayResultSV;
@Service("iPayInfoRSV")
public class PayInfoRSVImpl implements IPayInfoRSV {

	@Resource
	private IPayRequestSV iPayRequestSV;
	@Resource
    private IPayIntfReqLogSV iPayIntfReqLogSV;
	@Resource
    private IPayResultSV iPayResultSV;
	@Resource
	private IPayIntfResultLogSV iPayIntfResultLogSV;
	@Override
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception {
		return iPayRequestSV.insertPayRequst(payRequestReqDTO);
	}

	@Override
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception {
		return iPayResultSV.insertPayResult(payResultReqDTO);
	}

	@Override
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception {
		return iPayIntfReqLogSV.insertPayIntfReqLog(payIntfReqLogReqDTO);
	}

	@Override
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception {
		return iPayIntfResultLogSV.insertIntfResultLog(resultLogReqDTO);
	}

}
