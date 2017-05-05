package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;

 
public interface IOrderInfoRSV {
	public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception;

	// 我的所有子订单
	public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoRespDTO ordInfo) throws Exception;

	// 商品投诉
	public List<OrdInfoRespDTO> queryOrderByStaffGds(OrdInfoRespDTO ordInfo) throws Exception;

	// 我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	public List<OrdInfoRespDTO> queryAllDataByStaff(OrdInfoRespDTO ordInfo) throws Exception;

	// 订单生成
	public int createOrderInfo(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfoReqDTO) throws Exception;
 	/**
	 * 分页查询子订单数据
	 * 
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<OrdInfoRespDTO> queryOrdInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception;
	/**
	 * 查询子订单List
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdInfoRespDTO> queryOrderInfoList(OrdInfoReqDTO ordInfoReqDTO) throws Exception;

}
