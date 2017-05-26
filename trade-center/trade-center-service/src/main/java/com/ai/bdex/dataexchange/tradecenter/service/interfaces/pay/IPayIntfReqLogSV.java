package com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;

public interface IPayIntfReqLogSV {
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception;

}
