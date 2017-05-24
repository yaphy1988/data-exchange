package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pay;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfReqLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayIntfResultLogReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;

public interface IPayInfoRSV {
	  
	/**
	 * 支付请求：接收到订单发来的支付请求，直接记录原始数据
	 * @param payRequestReqDTO
	 * @return
	 * @throws Exception
	 */
	public long insertPayRequst(PayRequestReqDTO payRequestReqDTO) throws Exception;
	/**
	 * * 支付结果：只有支付成功的数据才记录在此表
	 * @param payResultReqDTO
	 * @return
	 * @throws Exception
	 */
	public long insertPayResult(PayResultReqDTO payResultReqDTO) throws Exception;
	/**
	 * 支付请求：支付接口请求报文日志表
	 * @param payIntfReqLogReqDTO
	 * @return
	 * @throws Exception
	 */
	public long insertPayIntfReqLog(PayIntfReqLogReqDTO payIntfReqLogReqDTO) throws Exception;
	/**
	 * 支付接口接收报文日志
	 * @param resultLogReqDTO
	 * @return
	 * @throws Exception
	 */
	public long insertIntfResultLog(PayIntfResultLogReqDTO resultLogReqDTO) throws Exception;
		
	
}
