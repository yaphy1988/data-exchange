package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;

 
public interface IOrderMainInfoRSV {
	/**
	 * 分页查询主订单数据
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception;
	
	/**
	 * 取消订单
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int cancelOrder(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception;
	/**
	 * 更新订单
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderMainInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception;
	
	/**
	 * 获取订单主表信息List
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdMainInfoRespDTO> queryOrdMainInfoList(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception;

	/***
	 * 更新订单主表和子表的状态，支付信息
	 * @param ordMainInfoReqDTO
	 * @param ordInfo
	 * @return
	 * @throws Exception
     */
	public int updateOrderAndSubOrdStatuss(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception;
	/***
	 * 查询订单的信息详情，包括主订单信息和子订单信息
	 * @param ordMainInfoReqDTO
 	 * @return
	 * @throws Exception
	 */
	public OrdMainInfoRespDTO queryOrderDetail(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception ;

	/***
	 * 定时任务处理  -将过期的数据进行失效处理
	 * 只需要 ordInfoReqDTO.setActiveEndTime = 当前系统时间
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAndSubOrderInfoByJob(OrdInfoReqDTO ordInfoReqDTO) throws Exception;


	/***
	 * 在线支付成功
	 * @param ordMainInfoReqDTO
	 * @param ordInfo
	 * @return
	 * @throws Exception
	 */
	public int setOrderAndSubOrdToPayInline(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception;


    /***
     * 在线支付成功
     * @param ordMainInfoReqDTO
     * @param ordInfo
     * @return
     * @throws Exception
     */
    public int setOrderAndSubOrdToPayByManager(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception;


}
