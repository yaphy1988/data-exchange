package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdLog;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
import com.ai.paas.utils.DateUtil;
@Service("iOrderInfoRSV")
public class OrderInfoRSVImpl  implements IOrderInfoRSV {
	private static final Logger log = LoggerFactory.getLogger(OrderInfoRSVImpl.class);

	@Resource
	private IOrdMainInfoSV iOrdMainInfoSV;
	@Resource
	private IOrdInfoSV iOrdInfoSV;
 
	/**
	 * 创建订单  -- 事物还需要处理
	 */
	@Override
    public int createOrderInfo(OrdInfoReqDTO ordInfoReqDTO) throws Exception
    {
		Date orderTime = DateUtil.getNowAsDate();
		OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO ();
		ordMainInfoReqDTO.setOrderAmount(ordInfoReqDTO.getOrderAmount());
		ordMainInfoReqDTO.setOrderMoney(ordInfoReqDTO.getOrderMoney());
		ordMainInfoReqDTO.setRealMoney(ordInfoReqDTO.getOrderMoney());
		ordMainInfoReqDTO.setStaffId(ordInfoReqDTO.getCreateStaff());
		ordMainInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
 		ordMainInfoReqDTO.setOrderTime(orderTime);
		ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_01);
		ordMainInfoReqDTO.setOrderType(Constants.Order.ORDER_TYPE_10);
		ordMainInfoReqDTO.setSource(Constants.Order.ORDER_SOURCE_0);
 		ordMainInfoReqDTO.setInvoiceModType(Constants.Order.ORDER_invoiceModType_1);
		ordMainInfoReqDTO.setInvoiceStatus(Constants.Order.ORDER_INVOICE_STATUS_0);
		ordMainInfoReqDTO.setCreateStaff(ordInfoReqDTO.getCreateStaff()); 
		long orderid = iOrdMainInfoSV.creatOrderByweb(ordMainInfoReqDTO);
		
		ordInfoReqDTO.setOrderId(Long.toString(orderid));
		ordInfoReqDTO.setAgentPrice(ordInfoReqDTO.getOrderPrice());
 		ordInfoReqDTO.setDiscountPrice(ordInfoReqDTO.getOrderPrice());
		ordInfoReqDTO.setOrderTime(orderTime);
		ordInfoReqDTO.setStaffId(ordInfoReqDTO.getCreateStaff());
		ordInfoReqDTO.setProductType(ordInfoReqDTO.getProductType());//一级大类
		ordInfoReqDTO.setAipServiceId(ordInfoReqDTO.getAipServiceId());
		ordInfoReqDTO.setServiceName(ordInfoReqDTO.getServiceName());
 		ordInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
 		
  	    Calendar   calendar   =   new   GregorianCalendar(); 
 	    calendar.setTime(orderTime); 
 	    calendar.add(calendar.DATE,Constants.Order.ORDER_AIP_ACTIVE_MON*365);//把日期往后增加一年.整数往后推,负数往前移动 
 		Date activeEndTime =  calendar.getTime();   //这个时间就是日期往后推一天的结果  
		ordInfoReqDTO.setActiveEndTime(activeEndTime);
		ordInfoReqDTO.setBuyAllCount(ordInfoReqDTO.getBuyAllCount());
		ordInfoReqDTO.setStatus(Constants.Order.ORDER_STATUS_01);
		iOrdInfoSV.creatsubOrderByweb(ordInfoReqDTO);
		
		//写日志
		OrdLog ordLog = new OrdLog();
		ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
		ordLog.setOrderId(Long.toString(orderid));
		ordLog.setNode(Constants.Order.LOG_CODE_01);
		ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_01);
		return iOrdMainInfoSV.saveOrderlog(ordLog); 
    }
	@Override
	public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 我的所有子订单
	@Override
	public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoReqDTO ordInfo) throws Exception {
		List<OrdInfoRespDTO> ordInfoList = new ArrayList<>();
		try {
			ordInfoList = iOrdInfoSV.queryOrderByStaff(ordInfo);
		} catch (Exception e) {
			log.error("分页获取子订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoList;
	}

	// 商品投诉
	@Override
	public List<OrdInfoRespDTO> queryOrderByStaffGds(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	@Override
	public List<OrdInfoRespDTO> queryAllDataByStaff(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	@Override
	public PageResponseDTO<OrdInfoRespDTO> queryOrdInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception {
		PageResponseDTO<OrdInfoRespDTO> ordInfoPage = new PageResponseDTO<>();
		try {
			ordInfoPage = iOrdInfoSV.queryOrdInfoPage(ordInfoReqDTO);
		} catch (Exception e) {
			log.error("分页获取子订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoPage;
	}
	public List<OrdInfoRespDTO> queryOrderInfoList(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		List<OrdInfoRespDTO> ordInfoList = new ArrayList<OrdInfoRespDTO>();
		try {
			ordInfoList = iOrdInfoSV.queryOrderInfoList(ordInfoReqDTO);
		} catch (Exception e) {
			log.error("获取子订单List信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoList;
	}
}
