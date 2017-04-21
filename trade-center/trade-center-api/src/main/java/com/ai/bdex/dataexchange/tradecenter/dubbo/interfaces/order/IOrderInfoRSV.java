package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;

 
public interface IOrderInfoRSV {
	  public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception;
	   //我的所有子订单
	   public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoRespDTO ordInfo) throws Exception;
      //商品投诉
	   public List<OrdInfoRespDTO> queryOrderByStaffGds(OrdInfoRespDTO ordInfo) throws Exception;
	   //我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	   public List<OrdInfoRespDTO> queryAllDataByStaff(OrdInfoRespDTO ordInfo) throws Exception;
}
