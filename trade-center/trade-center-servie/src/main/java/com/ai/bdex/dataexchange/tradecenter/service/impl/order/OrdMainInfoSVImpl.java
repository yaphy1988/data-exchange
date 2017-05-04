package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdMainInfoMapper;
 import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
 import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
 
@Service("iOrdMainInfoSV")
public class OrdMainInfoSVImpl  implements IOrdMainInfoSV {
	  private static final Logger log = Logger.getLogger(OrdMainInfoSVImpl.class);
	  @Autowired
	  private OrdMainInfoMapper ordMainInfoMapper; 
	 //创建订单
	  @Override
	 public int creatOrderByweb(OrdMainInfoReqDTO ordMainInfoRespDTO)throws Exception{
		 OrdMainInfo record = new OrdMainInfo();	
		 BeanUtils.copyProperties(record, ordMainInfoRespDTO);
		 record.setOrderId(Long.toString(SeqUtil.getLong("SEQ_ORD_MAIN_INFO"))); 
	 	 record.setCreateTime(DateUtil.getNowAsDate()); 
   		 return ordMainInfoMapper.insertSelective(record); 
   		 //插入子订单-- 放到dubbol层去做
	 }
	 //根据ID查询订单的详细信息
	  @Override
	 public OrdMainInfo queryOrderById(OrdMainInfo ordInfo) throws Exception{
		   
 	     if (ordInfo==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
 	    String orderid = ordInfo.getOrderId();
 	    OrdMainInfo ordMainInfo =  ordMainInfoMapper.selectByPrimaryKey(orderid);
	    return ordMainInfo;
	 }
	 //我的订单 （可以根据状态查询是否已经申请开票了）
	 //渠道商申请开票
	 //发票管理--管理员是否要查询出要开票的订单
	  @Override
	 public List<OrdMainInfo> queryMyOrders(OrdMainInfo ordInfo) throws Exception{
		  OrdMainInfoExample example = new OrdMainInfoExample();
		  OrdMainInfoExample.Criteria criteria = example.createCriteria(); 
		   //订单类型，我的订单中，渠道商不能查到后场创建的订单
		   if(ordInfo.getOrderType() != "0"){
				criteria.andOrderTypeEqualTo(ordInfo.getOrderType());
			} 
		   //是否开票信息
			if(ordInfo.getInvoiceStatus() != null){
				criteria.andInvoiceStatusEqualTo(ordInfo.getInvoiceStatus());
			} 
			//订单状态，是否支付
			if(ordInfo.getOrderStatus() != null){
				criteria.andOrderStatusEqualTo(ordInfo.getOrderStatus());
			} 
			return ordMainInfoMapper.selectByExample(example);	 
	 }
	 //订单管理（创建后台订单）
	  @Override
	 public int creatOrderByManager(OrdMainInfo ordInfo)throws Exception{
		  ordInfo.setOrderType("20");
		  return ordMainInfoMapper.insertSelective(ordInfo); 
	 }
	 //订单管理（失效订单数据）
	  @Override
	 public int invativeOrderByManager(OrdMainInfo ordInfo)throws Exception{
		  
		 // update t-ord-main-info
		  return 1;
		  // record.setTaxId(SeqUtil.getLong("SEQ_DATA_CUSTOMIZATION")); 
 		 
	 }
}
