package com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;

public interface IPayResultSV {
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception;

}
