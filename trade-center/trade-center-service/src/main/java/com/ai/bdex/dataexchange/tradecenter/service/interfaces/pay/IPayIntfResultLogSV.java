package com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;

public interface IPayIntfResultLogSV {
	
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception;

}
