package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
 import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
 
@Service("iOrdInfoSV")
public class OrdInfoSVImpl  implements IOrdInfoSV {
	  private static final Logger log = Logger.getLogger(OrdInfoSVImpl.class);
	  @Autowired
	  private OrdInfoMapper ordInfoMapper; 
	  
	  @Override
	 public int creatsubOrderByweb(OrdInfoReqDTO ordInfoReqDTO)throws Exception{
		  OrdInfo record = new OrdInfo();	
		 BeanUtils.copyProperties(record, ordInfoReqDTO);
		  record.setSubOrder(ordInfoReqDTO.getSubOrder());
	 	 record.setCreateTime(DateUtil.getNowAsDate());
   		 return ordInfoMapper.insertSelective(record); 
   		 //插入子订单-- 放到dubbol层去做
	 }
    
	   //我的所有子订单
	   public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		      OrdInfoExample example = new OrdInfoExample();
			  OrdInfoExample.Criteria criteria = example.createCriteria(); 
				   //订单类型，我的订单中，渠道商不能查到后场创建的订单
			  initCriteria(criteria,ordInfoReqDTO);
			  List<OrdInfo> ordInfoList=ordInfoMapper.selectByExample(example);
			  List<OrdInfoRespDTO> respDTOList = new ArrayList<>();
			  if(!CollectionUtil.isEmpty(ordInfoList)){
				  for(OrdInfo ordInfo:ordInfoList){
					  OrdInfoRespDTO respDTO = new OrdInfoRespDTO();
				      ObjectCopyUtil.copyObjValue(ordInfo,respDTO,null,false);
				      respDTOList.add(respDTO);
				  }
			  }
			return respDTOList;	
	   }
       //商品投诉 -- 根据已支付的数据。并且不能在投诉表中出现过的数据进行投诉。
	   public List<OrdInfo> queryOrderByStaffGds(OrdInfo ordInfo) throws Exception{
		   return null;
	   }
	   //我的数据：为我的订单中最早生成，切支付成功的数据
	   public List<OrdInfoRespDTO> queryMyDataFirstBuy(OrdInfoReqDTO ordInfo) throws Exception{
		   OrdInfoExample example = new OrdInfoExample();
		   OrdInfoExample.Criteria criteria = example.createCriteria();
		   initCriteria(criteria, ordInfo);
		   example.setOrderByClause("ORDER_TIME asc");
		   List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
		   List<OrdInfoRespDTO> respDTOList = new ArrayList<OrdInfoRespDTO>();
		   if(!CollectionUtil.isEmpty(ordInfoList)){
			   for(OrdInfo ordInfotmp :ordInfoList){
				   OrdInfoRespDTO respDTO = new OrdInfoRespDTO();
				   ObjectCopyUtil.copyObjValue(ordInfotmp,respDTO,null,false);
				   respDTOList.add(respDTO);
				   break;
			   }
		   }
		   return respDTOList;
	   }
	public List<OrdInfoRespDTO> queryOrderInfoList(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		OrdInfoExample example = new OrdInfoExample();
		OrdInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordInfoReqDTO);
		example.setOrderByClause("ORDER_TIME desc");
		List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
		List<OrdInfoRespDTO> respDTOList = new ArrayList<OrdInfoRespDTO>();
		if(!CollectionUtil.isEmpty(ordInfoList)){
			for(OrdInfo ordInfo :ordInfoList){
				OrdInfoRespDTO respDTO = new OrdInfoRespDTO();
		        ObjectCopyUtil.copyObjValue(ordInfo,respDTO,null,false);
		        respDTOList.add(respDTO);
			}
		}
		return respDTOList;
	}
	@Override
	public PageResponseDTO<OrdInfoRespDTO> queryOrdInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception {
		Integer pageNo = ordInfoReqDTO.getPageNo();
		Integer pageSize = ordInfoReqDTO.getPageSize();
		OrdInfoExample example = new OrdInfoExample();
		OrdInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordInfoReqDTO);
		example.setOrderByClause("ORDER_TIME desc");
		PageHelper.startPage(pageNo, pageSize);
		List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(ordInfoList);
		PageResponseDTO<OrdInfoRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdInfoRespDTO.class);
		return pageResponseDTO;
	}

	private void initCriteria(OrdInfoExample.Criteria criteria, OrdInfoReqDTO ordInfoReqDTO) {
		if (StringUtil.isNotBlank(ordInfoReqDTO.getSubOrder())) {
			criteria.andSubOrderEqualTo(ordInfoReqDTO.getSubOrder());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordInfoReqDTO.getOrderId());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getShopId())) {
			criteria.andShopIdEqualTo(ordInfoReqDTO.getShopId());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getStaffId())) {
			criteria.andStaffIdEqualTo(ordInfoReqDTO.getStaffId());
		}
		if (ordInfoReqDTO.getCatFirst()!= null) {
			criteria.andCatFirstEqualTo(ordInfoReqDTO.getCatFirst());
		}
		if (ordInfoReqDTO.getCatId() != null) {
			criteria.andCatIdEqualTo(ordInfoReqDTO.getCatId());
		}
		if (ordInfoReqDTO.getGdsId() != null) {
			criteria.andGdsIdEqualTo(ordInfoReqDTO.getGdsId());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getGdsName())) {
			criteria.andGdsNameLike("%" + ordInfoReqDTO.getGdsName() + "%");
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getSkuName())) {
			criteria.andSkuNameLike("%" + ordInfoReqDTO.getSkuName() + "%");
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getProvinceCode())) {
			criteria.andProvinceCodeEqualTo(ordInfoReqDTO.getProvinceCode());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getStatus())) {
			criteria.andStatusEqualTo(ordInfoReqDTO.getStatus());
		}
		if (StringUtil.isNotBlank(ordInfoReqDTO.getPayFlag())) {
			criteria.andPayFlagEqualTo(ordInfoReqDTO.getPayFlag());
		}
	}

	/**
	 * 取消订单
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
     */
	public int cancelOrderInfo(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		int code=updateOrderStatus(ordInfoReqDTO);
		return code;
	}

	/***
	 * 修改子订单
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
     */
	public int updateOrderStatus(OrdInfoReqDTO ordInfoReqDTO) throws Exception {
		OrdInfo record = new OrdInfo();
		OrdInfoExample example = new OrdInfoExample();
		OrdInfoExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(ordInfoReqDTO.getOrderId())){
			criteria.andOrderIdEqualTo(ordInfoReqDTO.getOrderId());
		}
		ordInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordInfoReqDTO,record,null,false);
		int code=ordInfoMapper.updateByExampleSelective(record, example);
		return code;
	}
	/***
	 * 查询到期的数据，并且是已支付的数据
	 * @param ordInfoReqDTO
	 * @return
	 * @throws Exception
	 */
		public PageResponseDTO<OrdInfoRespDTO> queryOutStatusOrdMainInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception {

		Integer pageNo = ordInfoReqDTO.getPageNo();
		Integer pageSize = ordInfoReqDTO.getPageSize();
		OrdInfoExample example = new OrdInfoExample();
		OrdInfoExample.Criteria criteria = example.createCriteria();
		//是已支付状态的（1）
		criteria.andPayFlagEqualTo(Constants.Order.ORDER_PAY_FLAG_1);
		//并且 查询已支付的（正在生效中的 02），
		criteria.andStatusEqualTo(Constants.Order.ORDER_STATUS_02);
		//失效日期小于等于当前日期
		criteria.andActiveEndTimeLessThanOrEqualTo(ordInfoReqDTO.getActiveEndTime());
		//永久有效期的数据不用查询出来
		criteria.andActiveEndTimeIsNotNull();

		initCriteria(criteria, ordInfoReqDTO);
		example.setOrderByClause("ORDER_TIME asc");

		PageHelper.startPage(pageNo, pageSize);
		List<OrdInfo> ordInfoList = ordInfoMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(ordInfoList);
		PageResponseDTO<OrdInfoRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdInfoRespDTO.class);
		return pageResponseDTO;
	}
}
//