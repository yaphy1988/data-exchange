package com.ai.bdex.dataexchange.tradecenter.service.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;

public interface IOrdMainInfoSV { 
	 //创建订单
	 public int creatOrderByweb(OrdMainInfoRespDTO ordMainInfoRespDTO)throws Exception;
	 //根据ID查询订单的详细信息
	 public OrdMainInfo queryOrderById(OrdMainInfo ordInfo) throws Exception;
	 //我的订单 （可以根据状态查询是否已经申请开票了）
	 //渠道商申请开票
	 //发票管理--管理员是否要查询出要开票的订单
	 public List<OrdMainInfo> queryMyOrders(OrdMainInfo ordInfo) throws Exception; 
	 //订单管理（创建后台订单）
	 public int creatOrderByManager(OrdMainInfo ordInfo)throws Exception;
	 //订单管理（失效订单数据）
	 public int invativeOrderByManager(OrdMainInfo ordInfo)throws Exception;
      

}
