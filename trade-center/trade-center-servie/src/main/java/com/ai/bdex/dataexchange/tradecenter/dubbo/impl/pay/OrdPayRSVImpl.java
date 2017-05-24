package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.pay;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pay.IOrdPayRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay.IPayInfoSV;
import com.ai.paas.utils.DateUtil;
@Service("iOrdPaySV")
public class OrdPayRSVImpl implements IOrdPayRSV {

	@Resource
    private IPayInfoSV iPayInfoSV;
	@Override
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception {
		return iPayInfoSV.insertPayRequst(payRequestReqDTO);
	}

	@Override
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception {
		return iPayInfoSV.insertPayResult(payResultReqDTO);
	}

	@Override
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception {
		return iPayInfoSV.insertPayIntfReqLog(payIntfReqLogReqDTO);
	}

	@Override
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception {
		return iPayInfoSV.insertIntfResultLog(resultLogReqDTO);
	}

}
