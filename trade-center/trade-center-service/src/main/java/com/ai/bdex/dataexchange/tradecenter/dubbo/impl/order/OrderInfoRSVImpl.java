package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	 * 创建订单  -- 事物还需要处理 新的获取订单ID
	 */
	@Override
    public OrdInfoReqDTO createOrderInfo(OrdInfoReqDTO ordInfoReqDTO) throws Exception
    {
    	try{
			Date orderTime = DateUtil.getNowAsDate();
			String tmorderid =  SeqUtil.getString("SEQ_ORD_MAIN_INFO",8);
			String strOrderid  =  createMainOrderId(tmorderid);
			OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO ();
			ordMainInfoReqDTO.setOrderId(strOrderid);
			ordMainInfoReqDTO.setOrderAmount(ordInfoReqDTO.getOrderAmount());
			ordMainInfoReqDTO.setOrderMoney(ordInfoReqDTO.getOrderMoney());
			ordMainInfoReqDTO.setRealMoney(ordInfoReqDTO.getOrderMoney());
			ordMainInfoReqDTO.setStaffId(ordInfoReqDTO.getStaffId());
			ordMainInfoReqDTO.setShopId(ordInfoReqDTO.getShopId());
			ordMainInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
			ordMainInfoReqDTO.setOrderTime(orderTime);
			ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_01);
			ordMainInfoReqDTO.setOrderType(ordInfoReqDTO.getOrdertype());
			ordMainInfoReqDTO.setSource(Constants.Order.ORDER_SOURCE_0);
			ordMainInfoReqDTO.setInvoiceModType(Constants.Order.ORDER_invoiceModType_1);
			ordMainInfoReqDTO.setInvoiceStatus(Constants.Order.ORDER_INVOICE_STATUS_0);
			ordMainInfoReqDTO.setCreateStaff(ordInfoReqDTO.getCreateStaff());
			iOrdMainInfoSV.creatOrderByweb(ordMainInfoReqDTO);

			ordInfoReqDTO.setOrderId(strOrderid);
			String tmpsuborderid =  SeqUtil.getString("SEQ_ORD_INFO",8);
			String subOrderid  =  createMainOrderId(tmpsuborderid);

			ordInfoReqDTO.setSubOrder(subOrderid);
			ordInfoReqDTO.setAgentPrice(ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setDiscountPrice(ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setOrderTime(orderTime);
			ordInfoReqDTO.setStaffId(ordInfoReqDTO.getStaffId());
			ordInfoReqDTO.setProductType(ordInfoReqDTO.getProductType());//一级大类
			ordInfoReqDTO.setAipServiceId(ordInfoReqDTO.getAipServiceId());
			ordInfoReqDTO.setServiceName(ordInfoReqDTO.getServiceName());
			ordInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
			ordInfoReqDTO.setBuyAllCount(ordInfoReqDTO.getBuyAllCount());
			ordInfoReqDTO.setStatus(Constants.Order.ORDER_STATUS_01);
			iOrdInfoSV.creatsubOrderByweb(ordInfoReqDTO);

			//写日志
			OrdLog ordLog = new OrdLog();
			ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
			ordLog.setOrderId(strOrderid);
			ordLog.setNode(Constants.Order.LOG_CODE_01);
			ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_01);
			iOrdMainInfoSV.saveOrderlog(ordLog);
		}catch (Exception ee)
		{
			log.error("创建订单信息异常createOrderInfo:", ee);
			throw  new Exception(ee);
		}
		return ordInfoReqDTO;
    }
	/**
	 * 跨类创建订单  -- 事物还需要处理
	 */
	@Override
	public OrdInfoReqDTO createOrderByallClass(OrdInfoReqDTO ordInfoReqDTO) throws Exception
	{
		try{
			Date orderTime = DateUtil.getNowAsDate();
			OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO ();
			String tmorderid =  SeqUtil.getString("SEQ_ORD_MAIN_INFO",8);
			String strOrderid  =  createMainOrderId(tmorderid);
			ordMainInfoReqDTO.setOrderId(strOrderid);
			ordMainInfoReqDTO.setOrderAmount(ordInfoReqDTO.getOrderAmount());
			ordMainInfoReqDTO.setOrderMoney(ordInfoReqDTO.getOrderMoney());
			ordMainInfoReqDTO.setRealMoney(ordInfoReqDTO.getOrderMoney());
			ordMainInfoReqDTO.setStaffId(ordInfoReqDTO.getStaffId());
			ordMainInfoReqDTO.setShopId(ordInfoReqDTO.getShopId());
			ordMainInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
			ordMainInfoReqDTO.setOrderTime(orderTime);
			ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_01);
			ordMainInfoReqDTO.setOrderType(Constants.Order.ORDER_TYPE_30);
			ordMainInfoReqDTO.setSource(Constants.Order.ORDER_SOURCE_1);
			ordMainInfoReqDTO.setInvoiceModType(Constants.Order.ORDER_invoiceModType_1);
			ordMainInfoReqDTO.setInvoiceStatus(Constants.Order.ORDER_INVOICE_STATUS_0);
			ordMainInfoReqDTO.setCreateStaff(ordInfoReqDTO.getCreateStaff());
			iOrdMainInfoSV.creatOrderByweb(ordMainInfoReqDTO);

			ordInfoReqDTO.setOrderId(strOrderid);
			String tmpsuborderid =  SeqUtil.getString("SEQ_ORD_INFO",8);
			String subOrderid  =  createMainOrderId(tmpsuborderid);
			ordInfoReqDTO.setSubOrder(subOrderid);

			ordInfoReqDTO.setAgentPrice(ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setDiscountPrice(ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setOrderTime(orderTime);
			ordInfoReqDTO.setStaffId(ordInfoReqDTO.getStaffId());
			ordInfoReqDTO.setProductType(ordInfoReqDTO.getProductType());//一级大类
			ordInfoReqDTO.setAipServiceId(ordInfoReqDTO.getAipServiceId());
			ordInfoReqDTO.setServiceName(ordInfoReqDTO.getServiceName());
			ordInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_0);
 			ordInfoReqDTO.setStatus(Constants.Order.ORDER_STATUS_01);
			iOrdInfoSV.creatsubOrderByweb(ordInfoReqDTO);

			//写日志
			OrdLog ordLog = new OrdLog();
			ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
			ordLog.setOrderId(strOrderid);
			ordLog.setNode(Constants.Order.LOG_CODE_01);
			ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_01);
			 iOrdMainInfoSV.saveOrderlog(ordLog);
		}catch (Exception e)
		{
			log.error("创建订单信息异常:", e);
			throw  new Exception(e);
		}
		return ordInfoReqDTO;

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

	// 我的数据：为我的订单中最早申请且支付成功的数据
	@Override
	public List<OrdInfoRespDTO> queryMyDataFirstBuy(OrdInfoReqDTO ordInfo) throws Exception {
		List<OrdInfoRespDTO> ordInfoList = new ArrayList<>();
		try {
			ordInfoList = iOrdInfoSV.queryMyDataFirstBuy(ordInfo);
		} catch (Exception e) {
			log.error("分页获取子订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoList;
	}
	/**
	 * 对主订单编号的封装
	 */
	public static String createMainOrderId(String mainOrdSeqId ) {
		// 主订单ID生成规则：省份编码2位+年月日YYmmdd+序列号8位补齐 ，一共16位
		DateFormat dfmt = new SimpleDateFormat("yyyyMMdd");
		String nowDate = dfmt.format(new Date());
		String mainOrdId = "";
		mainOrdId =  nowDate + mainOrdSeqId;
		return mainOrdId;
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
