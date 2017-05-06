package com.ai.bdex.dataexchange.tradecenter.service.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;

public interface IOrdInfoSV {
	  
	// 创建子订单
	public int creatsubOrderByweb(OrdInfoReqDTO ordInfoReqDTO) throws Exception;

	public OrdInfo queryOrderDetail(OrdInfo ordInfo) throws Exception;

	// 我的所有子订单-提供给商品调用
	public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoReqDTO ordInfoReqDTO) throws Exception;

	// 商品投诉
	public List<OrdInfo> queryOrderByStaffGds(OrdInfo ordInfo) throws Exception;

	// 我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	public List<OrdInfo> queryAllDataByStaff(OrdInfo ordInfo) throws Exception;

	/**
	 * 分页查询订单
	 * 
	 * @param ordInfoReqDTO
	 * @return
	 */
	public PageResponseDTO<OrdInfoRespDTO> queryOrdInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception;

	/**
	 * 查询子订单List
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdInfoRespDTO> queryOrderInfoList(OrdInfoReqDTO ordInfoReqDTO) throws Exception;
	/**
	 * 取消订单
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int cancelOrderInfo(OrdInfoReqDTO ordInfoReqDTO) throws Exception;
}
