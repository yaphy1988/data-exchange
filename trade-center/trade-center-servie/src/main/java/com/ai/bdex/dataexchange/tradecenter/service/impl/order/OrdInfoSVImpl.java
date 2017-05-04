package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
 
@Service("iOrdInfoSV")
public class OrdInfoSVImpl  implements IOrdInfoSV {
	  private static final Logger log = Logger.getLogger(OrdInfoSVImpl.class);
	  @Autowired
	  private OrdInfoMapper ordInfoMapper; 
	  
	  @Override
	 public int creatsubOrderByweb(OrdInfoReqDTO ordInfoReqDTO)throws Exception{
		  OrdInfo record = new OrdInfo();	
		 BeanUtils.copyProperties(record, ordInfoReqDTO);
		 record.setOrderId(Long.toString(SeqUtil.getLong("SEQ_ORD_INFO"))); 
	 	 record.setCreateTime(DateUtil.getNowAsDate()); 
   		 return ordInfoMapper.insertSelective(record); 
   		 //插入子订单-- 放到dubbol层去做
	 }
      // 查询子订单详情
	  @Override
	   public OrdInfo queryOrderDetail(OrdInfo ordInfo) throws Exception{
  		    String suborderid = ordInfo.getSubOrder();
  		    OrdInfo ordInforeturn =  ordInfoMapper.selectByPrimaryKey(suborderid);
			return ordInforeturn;	 
	   }
	   //我的所有子订单
	   public List<OrdInfo> queryOrderByStaff(OrdInfo ordInfo) throws Exception{
		      OrdInfoExample example = new OrdInfoExample();
			  OrdInfoExample.Criteria criteria = example.createCriteria(); 
				   //订单类型，我的订单中，渠道商不能查到后场创建的订单
			  if(ordInfo.getOrderId() != "0"){
					criteria.andOrderIdEqualTo(ordInfo.getOrderId());
			  }  
					//订单状态，是否支付
			   if(ordInfo.getStatus() != null){
						criteria.andStatusEqualTo(ordInfo.getSubOrder());
			   } 
				return ordInfoMapper.selectByExample(example);	
	   }
       //商品投诉 -- 根据已支付的数据。并且不能在投诉表中出现过的数据进行投诉。
	   public List<OrdInfo> queryOrderByStaffGds(OrdInfo ordInfo) throws Exception{
		   return null;
	   }
	   //我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	   public List<OrdInfo> queryAllDataByStaff(OrdInfo ordInfo) throws Exception{
		   return null;
		   //distict SKU_ID status = 1 的数据，统计总次数
	   }
}
