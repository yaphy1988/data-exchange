package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
 
@Service("iOrdInfoSV")
public class OrdInfoSVImpl  implements IOrdInfoSV {
	   //我的所有子订单
	   public OrdInfo queryOrderById(Integer OrderById) throws Exception{
		   return null;
	   }
	   public List<OrdInfo> queryOrderByStaff(OrdInfo ordInfo) throws Exception{
		   return null;
	   }
    //商品投诉
	   public List<OrdInfo> queryOrderByStaffGds(OrdInfo ordInfo) throws Exception{
		   return null;
	   }
	   //我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	   public List<OrdInfo> queryAllDataByStaff(OrdInfo ordInfo) throws Exception{
		   return null;
	   }
}
