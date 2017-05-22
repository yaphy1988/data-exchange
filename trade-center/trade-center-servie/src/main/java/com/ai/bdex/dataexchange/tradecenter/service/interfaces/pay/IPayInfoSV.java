package com.ai.bdex.dataexchange.tradecenter.service.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;

public interface IPayInfoSV {
	  
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception;

	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception;
	
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception;
	
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception;
	

}
