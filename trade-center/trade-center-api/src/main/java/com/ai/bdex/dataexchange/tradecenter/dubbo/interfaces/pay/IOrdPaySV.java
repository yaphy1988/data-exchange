package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pay;

import java.util.Map;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;

public interface IOrdPaySV {
	  
	//调用支付宝支付
	public Map<String,Object> orderAlipay(OrdInfoReqDTO ordInfoReqDTO) throws Exception;

	
	
}
