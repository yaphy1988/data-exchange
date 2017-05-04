package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.List;

import javax.annotation.Resource;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;

public class OrderInfoRSVImpl  implements IOrderInfoRSV {
  
   @Resource
   private IOrdMainInfoSV   iOrdMainInfoSV;
   @Resource
   private IOrdInfoSV   iOrdInfoSV;
   
	@Override
    public int createOrderInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception
    {
		return 1;
		//需要调用主表和子表的实物
        // return   iDataCustomizationSV.saveDataCustomization(dataCustomizationRespDTO);
    }
	@Override
	  public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception
	  {
		return null;
	  }
	   //我的所有子订单
	@Override
	   public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoRespDTO ordInfo) throws Exception
	   {
		return null;
	   }
     //商品投诉
	@Override
	   public List<OrdInfoRespDTO> queryOrderByStaffGds(OrdInfoRespDTO ordInfo) throws Exception{
		return null;
	}
	   //我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	@Override
	   public List<OrdInfoRespDTO> queryAllDataByStaff(OrdInfoRespDTO ordInfo) throws Exception{
		return null; 
	} 
}
