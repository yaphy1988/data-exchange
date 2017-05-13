package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdLogMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdMainInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.DataCustomization;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdLog;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
 
@Service("iOrdMainInfoSV")
public class OrdMainInfoSVImpl  implements IOrdMainInfoSV {
	  private static final Logger log = Logger.getLogger(OrdMainInfoSVImpl.class);
	  @Autowired
	  private OrdMainInfoMapper ordMainInfoMapper; 
	  @Autowired
	  private OrdLogMapper ordLogMapper; 
	 //创建订单
	  @Override
	 public long creatOrderByweb(OrdMainInfoReqDTO ordMainInfoRespDTO)throws Exception{
		 OrdMainInfo record = new OrdMainInfo();	
		 BeanUtils.copyProperties(record, ordMainInfoRespDTO);
		 long lorderid = SeqUtil.getLong("SEQ_ORD_MAIN_INFO");
		 record.setOrderId(Long.toString(lorderid)); 
	 	 record.setCreateTime(DateUtil.getNowAsDate()); 
   		  ordMainInfoMapper.insertSelective(record); 
   		  return lorderid;
 	 }
	 //根据ID查询订单的详细信息
/*	  @Override
	 public OrdMainInfo queryOrderById(OrdMainInfo ordInfo) throws Exception{
		   
 	     if (ordInfo==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
 	    String orderid = ordInfo.getOrderId();
 	    OrdMainInfo ordMainInfo =  ordMainInfoMapper.selectByPrimaryKey(orderid);
	    return ordMainInfo;
	 }*/
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

	// 订单日志
	public int saveOrderlog(OrdLog record) throws Exception {
		long seq = SeqUtil.getLong("SEQ_ORD_LOG");
		record.setSysLogId(seq);
		record.setCreateTime(DateUtil.getNowAsDate());
		return ordLogMapper.insertSelective(record);
	}

	@Override
	public PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainInfoReqDTO)
			throws Exception {
		Integer pageNo = ordMainInfoReqDTO.getPageNo();
		Integer pageSize = ordMainInfoReqDTO.getPageSize();
		OrdMainInfoExample example = new OrdMainInfoExample();
		OrdMainInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordMainInfoReqDTO);
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getStaffId())) {
			criteria.andStaffIdEqualTo(ordMainInfoReqDTO.getStaffId());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getShopId())) {
			criteria.andShopIdEqualTo(ordMainInfoReqDTO.getShopId());
		}
		example.setOrderByClause("ORDER_TIME desc");
		PageHelper.startPage(pageNo, pageSize);
		List<OrdMainInfo> ordMainInfoList = ordMainInfoMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(ordMainInfoList);
		PageResponseDTO<OrdMainInfoRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdMainInfoRespDTO.class);
		return pageResponseDTO;
	}

	@Override
	public List<OrdMainInfoRespDTO> queryOrdMainInfoList(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		OrdMainInfoExample example = new OrdMainInfoExample();
		OrdMainInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria,ordMainInfoReqDTO);
		List<OrdMainInfo> ordMainInfoList = ordMainInfoMapper.selectByExample(example);
		List<OrdMainInfoRespDTO> respDTOList = new ArrayList<OrdMainInfoRespDTO>();
		if (!CollectionUtil.isEmpty(ordMainInfoList)) {
			for (OrdMainInfo ordMainInfo : ordMainInfoList) {
				OrdMainInfoRespDTO ordMainInfoRespDTO = new OrdMainInfoRespDTO();
				ObjectCopyUtil.copyObjValue(ordMainInfo, ordMainInfoRespDTO, null, false);
				respDTOList.add(ordMainInfoRespDTO);
			}
		}

		return respDTOList;
	}
	private void initCriteria(OrdMainInfoExample.Criteria criteria, OrdMainInfoReqDTO ordMainInfoReqDTO) {
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordMainInfoReqDTO.getOrderId());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getShopId())) {
			criteria.andShopIdEqualTo(ordMainInfoReqDTO.getShopId());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getStaffId())) {
			criteria.andStaffIdEqualTo(ordMainInfoReqDTO.getStaffId());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getProvinceCode())) {
			criteria.andProvinceCodeEqualTo(ordMainInfoReqDTO.getProvinceCode());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getOrderType())) {
			criteria.andOrderTypeEqualTo(ordMainInfoReqDTO.getOrderType());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getPayFlag())) {
			criteria.andPayFlagEqualTo(ordMainInfoReqDTO.getPayFlag());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getPayWay())) {
			criteria.andPayWayEqualTo(ordMainInfoReqDTO.getPayWay());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getInvoiceModType())) {
			criteria.andInvoiceModTypeEqualTo(ordMainInfoReqDTO.getInvoiceModType());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getInvoiceStatus())) {
			criteria.andInvoiceStatusEqualTo(ordMainInfoReqDTO.getInvoiceStatus());
		}
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getSource())) {
			criteria.andSourceEqualTo(ordMainInfoReqDTO.getSource());
		}
		if (!CollectionUtil.isEmpty(ordMainInfoReqDTO.getInvoiceModTypeList())) {
			criteria.andInvoiceModTypeIn(ordMainInfoReqDTO.getInvoiceModTypeList());
		}
	}
	public int cancelOrderMainInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		OrdMainInfo ordMainInfo = new OrdMainInfo();
		OrdMainInfoExample example = new OrdMainInfoExample();
		OrdMainInfoExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordMainInfoReqDTO.getOrderId());
		}
		ordMainInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordMainInfoReqDTO, ordMainInfo, null, false);
		int code = ordMainInfoMapper.updateByExampleSelective(ordMainInfo, example);
		return code;
	}
	public int updateOrderMainInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		OrdMainInfo ordMainInfo = new OrdMainInfo();
		OrdMainInfoExample example = new OrdMainInfoExample();
		OrdMainInfoExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(ordMainInfoReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordMainInfoReqDTO.getOrderId());
		}
		ordMainInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordMainInfoReqDTO, ordMainInfo, null, false);
		int code = ordMainInfoMapper.updateByExampleSelective(ordMainInfo, example);
		return code;
	}
	@Override
	public  OrdMainInfoRespDTO  queryOrdMainInfoOne(OrdMainInfoReqDTO ordMainInfoReqDTO)
			throws Exception {
		OrdMainInfoExample example = new OrdMainInfoExample();
		if (StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())) {
			throw new Exception("查询订单信息时，订单ID入参为空");
		}
		OrdMainInfoRespDTO resorderMaininfo = new OrdMainInfoRespDTO();
		OrdMainInfo ordMainInfo  = ordMainInfoMapper.selectByPrimaryKey(ordMainInfoReqDTO.getOrderId());
 		BeanUtils.copyProperties(resorderMaininfo, ordMainInfo);
		return resorderMaininfo;
	}
}
