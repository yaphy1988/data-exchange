package com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;

public interface IPayRequestSV {
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception;
}
