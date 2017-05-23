package com.ai.bdex.dataexchange.busi.order.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
//临时用，支付回调的接口在另外的位置处理
 import  com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

/**
 * 
 * Description:广交所首页 <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author landeng
 */


@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private final static String STATUS_VALID = "1";// 有效
	private final static String CUSTOMDATA_STATUS_VALID = "1";// 有效
	private final static String SESSION_UNION_SHOPCART = "_shopcart";//缓存数据
    private final static String API_SERVICE_NAME_TMP = "API_SERVICE_NAME_TMP";//API服务名称接口获取不到数据
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@DubboConsumer(timeout = 30000)
	IOrderInfoRSV iOrderInfoRSV;
	@DubboConsumer(timeout = 30000)
	IOrderMainInfoRSV iOrderMainInfoRSV ;
	
	@DubboConsumer(timeout = 30000)
	IGdsSkuRSV iGdsSkuRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	@DubboConsumer(timeout = 30000)
	IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer(timeout = 30000)
   IAipCenterDataAccountRSV iAipCenterDataAccountRSV;
    //预定的数据展现
	@RequestMapping(value = "/gdshopcart")
	public ModelAndView saveTosession(Model model, HttpServletRequest request) {
		//取用户ID
		boolean bshowCreatebt = true;//是否顯示確認訂單
		HttpSession hpptsesion = request.getSession(); 
		String staff_id = StaffUtil.getStaffId(hpptsesion);
 	   if(StringUtil.isBlank(staff_id))
		{
			//没有登录的，不顯示確認訂單按鈕。
			bshowCreatebt = false;
			staff_id = SESSION_UNION_SHOPCART;
		}


		StaffInfoDTO staffInfoDTO = StaffUtil.getStaffVO(hpptsesion);
		//将商品的名称，gdsID，套餐id，skuID，带过来，存储到session中。价
	    //图片ID格必须从商品服务从新回去
		//商品名称和ID
		int gdsid = Integer.parseInt(request.getParameter("gdsId"));
		String gdsname = "";
		//套餐信息
		int skusid =  Integer.parseInt(request.getParameter("skuId"));
		String skuname = "";
		String gdsvfsid =  "";
		String gdsvfsurl = "";
		int inaviDay =  Constants.Order.ORDER_AIP_ACTIVE_DAY; // 100年
		//获取商品的价格和图
		GdsSkuRespDTO gdsSkuRespDTO = new GdsSkuRespDTO(); 
		GdsSkuReqDTO dsSkuReqDTO = new GdsSkuReqDTO();
		dsSkuReqDTO.setGdsId(gdsid);
		dsSkuReqDTO.setSkuId(skusid);
		dsSkuReqDTO.setStatus(STATUS_VALID);
		List<GdsSkuRespDTO>  listGdsSku =new ArrayList<>();
		try {
			//价格
			listGdsSku = iGdsSkuRSV.queryGdsSkuList(dsSkuReqDTO);
		} catch (Exception e) {
		    e.printStackTrace();
		}   	
		OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
		if(!CollectionUtil.isEmpty(listGdsSku) )
		{
			gdsSkuRespDTO = listGdsSku.get(0);
			skuname = gdsSkuRespDTO.getSkuName();
			//图片 
			gdsvfsid = gdsSkuRespDTO.getGdsPic(); 
			if(!StringUtil.isBlank(gdsvfsid)){
				gdsvfsurl= ImageUtil.getImageUrl(gdsvfsid + "_80x80");  
			} 
			//每份的次数
			ordInfoReqDTO.setEachCount(gdsSkuRespDTO.getPackTimes());
			//购买总数
			int orderAmount = 1;//默认进来就只是购买一个
			ordInfoReqDTO.setOrderAmount(orderAmount);
			//单价
			long lprice = (long)gdsSkuRespDTO.getPackPrice();
			ordInfoReqDTO.setOrderPrice(lprice);
			//订单金额
			long lorderMoney = lprice * orderAmount;
			ordInfoReqDTO.setOrderMoney(lorderMoney); 
			//使用次数
			long lbuyAllCount = gdsSkuRespDTO.getPackTimes()*orderAmount;
			ordInfoReqDTO.setBuyAllCount(lbuyAllCount);

			//不是空，就有有效天数，否则为无期西安
			if(gdsSkuRespDTO.getPackDay() != null)
			{
				//有效天数
				inaviDay = gdsSkuRespDTO.getPackDay();
			}
			Date orderTime = DateUtil.getNowAsDate();
			Calendar calendar   =   new   GregorianCalendar();
			calendar.setTime(orderTime);
			calendar.add(calendar.DATE,inaviDay);//把日期往后增加一年.整数往后推,负数往前移动
			Date activeEndTime =  calendar.getTime();   //这个时间就是日期往后推一天的结果
			ordInfoReqDTO.setActiveEndTime(activeEndTime);

			long lgdsid =  (long)gdsid;
			ordInfoReqDTO.setGdsId(lgdsid);
			long lskuid = (long)skusid;
			ordInfoReqDTO.setSkuId(lskuid);
			//获取服务ID，服务名称，商品名称
			//获取名称
			try { 
				GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
				GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
	 			gdsInfoReqDTO.setGdsId(gdsid);
				gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
				if(gdsInfoRespDTO != null)
				{ 
					    ordInfoReqDTO.setAipServiceId(Integer.toString(gdsInfoRespDTO.getApiId()));
                        try{
                            List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(gdsInfoRespDTO.getApiId()));
                            if(!CollectionUtil.isEmpty(apiServiceList))
                            {
                                ordInfoReqDTO.setServiceName(apiServiceList.get(0).getServiceName());
                             }
                        }
                        catch(Exception e1){
                            //服务名称取不到
                            ordInfoReqDTO.setServiceName(API_SERVICE_NAME_TMP);
                        }

						ordInfoReqDTO.setCatFirst(gdsInfoRespDTO.getCatFirst());
						ordInfoReqDTO.setCatId(gdsInfoRespDTO.getCatId());
						gdsname = gdsInfoRespDTO.getGdsName();
						ordInfoReqDTO.setGdsName(gdsname);
						ordInfoReqDTO.setSkuName(skuname);
				}
			} catch (Exception e) {
			    e.printStackTrace();
			}
			//如果已经有数据了，那就先删除，然后再写入
			Object  ordInfoDTO = CacheUtil.getItem(staff_id+SESSION_UNION_SHOPCART);
			if(ordInfoDTO != null)
			{
				CacheUtil.delItem(staff_id+SESSION_UNION_SHOPCART);
			}
			//每次进来都是讲session赋值为新的数据
			 CacheUtil.addItem(staff_id+SESSION_UNION_SHOPCART, ordInfoReqDTO);
		}  
	    request.setAttribute("skuInfo",gdsSkuRespDTO);
	    request.setAttribute("gdsname",gdsname);
	    request.setAttribute("skuname",skuname);
	    request.setAttribute("gdsvfsurl",gdsvfsurl); 
	    request.setAttribute("authenflag",staffInfoDTO.getAuthenFlag());
		request.setAttribute("bshowCreatebt",bshowCreatebt);
		//返回预购界面
		ModelAndView modelAndView = new ModelAndView("shopcart/shoppint_cart");
		return modelAndView; 
	}
	  //修改预定的数据的数量，增加或者减少
	@RequestMapping(value = "/updategdstmpsave")
	@ResponseBody
	public  Map<String, Object> updategdstmpsave(Model model, HttpServletRequest request) {
		Map<String, Object> rMap = new HashMap<String, Object>();
       try{
		String updatenum = request.getParameter("updatenum"); 
		int iorderamount = Integer.parseInt(updatenum);
		if(iorderamount < 1)
		{
			log.error("更新次数出错：" + "不能提交小于1的数量");
			rMap.put("success", false);
		}
		else{
			HttpSession hpptsesion = request.getSession(); 
			String staff_id = StaffUtil.getStaffId(hpptsesion); 
			if(StringUtil.isBlank(staff_id))
			{
				rMap.put("success", false);
				rMap.put("ERRORINFO", "亲，请先登录哦");
				return rMap;
			}
			OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+"_shopcart");
			//原始单品次数 每个套餐的次数
			long skutimes = ordInfoReqDTO.getEachCount();
			ordInfoReqDTO.setOrderAmount(iorderamount);
			ordInfoReqDTO.setOrderMoney(iorderamount*ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setBuyAllCount(iorderamount*skutimes);
			//重置
			CacheUtil.delItem(staff_id+"_shopcart"); 
			CacheUtil.addItem(staff_id+"_shopcart", ordInfoReqDTO);  
			
		 	double dmoney = iorderamount*ordInfoReqDTO.getOrderPrice();
			//js没有做元的转换
	     	double dmoneytmp = dmoney/100;
	     	BigDecimal b = new BigDecimal(dmoneytmp);
	     	double dmoneyback =  b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
			rMap.put("money", "￥"+dmoneyback); 
			rMap.put("iorderamount", iorderamount); 
 			rMap.put("success", true);
	      } 
	} 
       catch (Exception e) {
   				log.error("更新次数出错：" + e.getMessage());
   				rMap.put("success", false);
   			}
		return rMap;
	} 
	/**
	 * 订单生成
	 */
	@RequestMapping(value = "/creatOrder")
	@ResponseBody
	private  Map<String, Object> creatOrder(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			HttpSession hpptsesion = request.getSession();
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			
  			StaffInfoDTO staffInfoDTO = StaffUtil.getStaffVO(hpptsesion);
 			//是否已认证：1 认证，0未认证
 			if(staffInfoDTO!=null)
 			{
 				  if("0".equals(staffInfoDTO.getAuthenFlag()))
 				  {
 					  //未认证用户，不能购买
 			         rMap.put("success", false);
 					 rMap.put("ERRORINFO", "未认证用户，不能购买商品，请先去实名认证");
 					 return rMap; 
 				  }
  					if(StringUtil.isBlank(staff_id))
 					{
						rMap.put("success", false);
						rMap.put("ERRORINFO", "亲，请先登录哦");
						return rMap;
					}
 					OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+SESSION_UNION_SHOPCART);
 					ordInfoReqDTO.setCreateStaff(staff_id);
			    	ordInfoReqDTO.setStaffId(staff_id);
 					GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
 					GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
 					int igdsid =   new Long(ordInfoReqDTO.getGdsId()).intValue();  
 					gdsInfoReqDTO.setGdsId(igdsid);
 					gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
 					if(gdsInfoRespDTO != null)
 					{
 						ordInfoReqDTO.setCatFirst(gdsInfoRespDTO.getCatFirst());
 						ordInfoReqDTO.setCatId(gdsInfoRespDTO.getCatId());
 					}
				    ordInfoReqDTO.setShopId(Constants.Shop.GZDATA_SHOP_ID);
                    ordInfoReqDTO.setOrdertype(Constants.Order.ORDER_TYPE_10);
                    ordInfoReqDTO.setSource(Constants.Order.ORDER_SOURCE_0);
			    	OrdInfoReqDTO rdInfoReqDTOResp =  iOrderInfoRSV.createOrderInfo(ordInfoReqDTO);
				    rMap.put("orderid", rdInfoReqDTOResp.getOrderId());
			    	rMap.put("suborderid", rdInfoReqDTOResp.getSubOrder());
 			} 
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("生成订单异常：" + e.getMessage());
			rMap.put("ERRORINFO", "亲，生成订单异常");
			rMap.put("success", false);
		}
		return rMap;
	}

	/***
	 * 支付的日誌記錄
	 * @param model
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value = "/savepayLog")
	@ResponseBody
	private  Map<String, Object> savepayLog(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try { 
			HttpSession hpptsesion = request.getSession(); 
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			
  			StaffInfoDTO staffInfoDTO = StaffUtil.getStaffVO(hpptsesion);
 			//是否已认证：1 认证，0未认证
 			if(staffInfoDTO!=null)
 			{
 				  if("0".equals(staffInfoDTO.getAuthenFlag()))
 				  {
 					  //未认证用户，不能购买
 			         rMap.put("success", false);
 					 rMap.put("ERRORINFO", "未认证用户，不能购买商品，请先去实名认证");
 					 return rMap; 
 				  }
  					if(StringUtil.isBlank(staff_id))
 					{
 						staff_id = "tmpuser";
 					}
 					OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+SESSION_UNION_SHOPCART);
 					ordInfoReqDTO.setCreateStaff(staff_id);
 					
 					GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
 					GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
 					int igdsid =   new Long(ordInfoReqDTO.getGdsId()).intValue();  
 					gdsInfoReqDTO.setGdsId(igdsid);
 					gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
 					if(gdsInfoRespDTO != null)
 					{
 						ordInfoReqDTO.setAipServiceId(Integer.toString(gdsInfoRespDTO.getApiId()));
 						List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(gdsInfoRespDTO.getApiId()));
 						if(!CollectionUtil.isEmpty(apiServiceList))
 						{
 							ordInfoReqDTO.setServiceName(apiServiceList.get(0).getServiceName());
 						}
 					}  
 					iOrderInfoRSV.createOrderInfo(ordInfoReqDTO);
 					//要去获取一下商品的大类
 			} 
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("生成订单异常：" + e.getMessage());
			rMap.put("success", false);
		}
		return rMap;
	}


	private String uRLDecoderStr(String strinfo) {
		String newstrinfo = "";
		try {
			newstrinfo = URLDecoder.decode(strinfo, "utf-8");
		} catch (Exception e) {
		}
		return newstrinfo;
	}
	    //预线下支付的静态界面展示
		@RequestMapping(value = "/offline_remittance")
		public ModelAndView offline_remittance(Model model, HttpServletRequest request) { 
			//返回预购界面
			ModelAndView modelAndView = new ModelAndView("offline_remittance");
			return modelAndView; 
		}
	//支付成功模拟界面
	@RequestMapping(value = "/pay_test")
	public ModelAndView pay_test(Model model, HttpServletRequest request) {
		//返回预购界面
		String orderid = request.getParameter("orderid");
		String subordid = request.getParameter("suborderid");
		ModelAndView modelAndView = new ModelAndView("pay_test");
		model.addAttribute("orderid",orderid);
		model.addAttribute("subordid",subordid);
		return modelAndView;
	}
	/**
	 * 异步通知：获取支付宝POST过来反馈信息
	 * 支付成功模拟修改后台数据
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/alipayNotify")
	@ResponseBody
	public String alipayNotify(Model model, HttpServletRequest request) {
		Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = request.getParameterMap();
    	Map<String, Object> rMap = new HashMap<String, Object>();
    	try {
	    	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    		String name = (String) iter.next();
	    		String[] values = (String[]) requestParams.get(name);
	    		String valueStr = "";
	    		for (int i = 0; i < values.length; i++) {
	    			valueStr = (i == values.length - 1) ? valueStr + values[i]
	    					: valueStr + values[i] + ",";
	    		}
	    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	    		log.error("支付宝通知：key="+name+";value="+valueStr);
	    		params.put(name, valueStr);
	    	}
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			log.error("商户订单号：out_trade_no="+out_trade_no);
			
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			log.error("支付宝交易号：trade_no="+trade_no);
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			log.error("交易状态：trade_status="+trade_status);
			//子订单编号
			String subOrderId = new String(request.getParameter("passback_params").getBytes("ISO-8859-1"),"UTF-8");
			log.error("子订单编号：out_trade_no="+subOrderId);
			
			//模拟成功
			HttpSession hpptsesion = request.getSession();
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			
			String orderid = trade_no;
			String subordid = subOrderId;
			//模拟成功
			
			Date orderTime = DateUtil.getNowAsDate();
			OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
			ordMainInfoReqDTO.setOrderId(orderid);
			ordMainInfoReqDTO.setUpdateStaff(staff_id);
			ordMainInfoReqDTO.setPayWay(Constants.Order.ORDER_PAY_WAY_ZHIFUBAO);
			ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_02);
			ordMainInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);
			ordMainInfoReqDTO.setPayTime(orderTime);
	
			OrdInfoReqDTO ordInfo = new OrdInfoReqDTO();
			ordInfo.setUpdateStaff(staff_id);
			ordInfo.setOrderId(orderid);
			ordInfo.setSubOrder(subordid);
			ordInfo.setStatus(Constants.Order.ORDER_STATUS_02);
			ordInfo.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);
			ordInfo.setPayTime(orderTime);
		
			OrdMainInfoRespDTO ordMainInfoRespDTO=	iOrderMainInfoRSV.queryOrderDetail(ordMainInfoReqDTO);
			if(ordMainInfoRespDTO.getOrderStatus().equals(Constants.Order.ORDER_STATUS_02))
			{
				rMap.put("success", true);
				return  "1";
			}
			OrdInfoRespDTO ordInfoRespDTO = ordMainInfoRespDTO.getOrdInfoRespDTO();

			if (ordInfoRespDTO!= null) {
				//此处为API支付回调
				RechargeReqDTO rechargeDTO = new RechargeReqDTO();
      /*            * rechargeUserId
                    * subOrder
                    * rechargeType (1-次数，2-金额)
                    * periodType (1-有有效期，2-永久有效)
                    * totalNum  (当rechargeType="1")
                    * totalMoney(当rechargeType="2")
                    * serviceId (当rechargeType="1")
                    *
                    * startDate(当periodType="1")
                    * endDate(当periodType="1")*/
				int igdsid = new Long(ordInfoRespDTO.getGdsId()).intValue();
				rechargeDTO.setRechargeUserId(ordMainInfoRespDTO.getStaffId());
				if(Constants.Order.ORDER_TYPE_30.equals(ordMainInfoRespDTO.getOrderType()))
				{
					/*int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
					rechargeDTO.setTotalNum(itotalcount);*/
				}
				else if(Constants.Order.ORDER_TYPE_10.equals(ordMainInfoRespDTO.getOrderType()) || Constants.Order.ORDER_TYPE_20.equals(ordMainInfoRespDTO.getOrderType()) ){
					if(Constants.Order.ORDER_TYPE_10.equals(ordMainInfoRespDTO.getOrderType()) ){
						int iskuid = new Long(ordInfoRespDTO.getSkuId()).intValue();
						rechargeDTO.setSkuId(iskuid);
					}

					int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
					rechargeDTO.setTotalNum(itotalcount);
					rechargeDTO.setCatId(ordInfoRespDTO.getCatId());
					rechargeDTO.setCatFirst(ordInfoRespDTO.getCatFirst());
					if(ordInfoRespDTO.getAipServiceId() != null)
					{
						rechargeDTO.setServiceId(ordInfoRespDTO.getAipServiceId() );
					}
				}
				rechargeDTO.setRechargeUserId(ordInfoRespDTO.getStaffId());
				rechargeDTO.setOrderId(orderid);
				rechargeDTO.setSubOrder(ordInfoRespDTO.getSubOrder());
				rechargeDTO.setGdsId(igdsid);

				String periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				String  inavidate =  Constants.Order.ORDER_API_NODATE;
				if (  ordInfoRespDTO.getActiveEndTime() == null  ) {
					//大于50年,就是无限期了
					periodType = Constants.Order.ORDER_API_PERIODTYPE_2;
				}
				else	{
					periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				}
				rechargeDTO.setStartDate(ordInfoRespDTO.getCreateTime());
				rechargeDTO.setEndDate(ordInfoRespDTO.getActiveEndTime());
				rechargeDTO.setPeriodType(periodType);
				String packageType = ordMainInfoRespDTO.getOrderType();
				rechargeDTO.setPackageType(packageType);
				int iordermoney = new Long(ordInfoRespDTO.getOrderMoney()).intValue();
				rechargeDTO.setTotalMoney(iordermoney);
				try {
					iAipCenterDataAccountRSV.dealRecharge(rechargeDTO);
					iOrderMainInfoRSV.updateOrderAndSubOrdStatuss(ordMainInfoReqDTO, ordInfo);
					rMap.put("success", true);
				} catch (Exception e) {
					System.out.print("更新AipCenter失败：" + e.getMessage());
					//需要通知运维，去处理数据
					rMap.put("ERRORINFO", "更新AipCenter失败");
					rMap.put("success", false);
				}
			}
			else
			{
				System.out.print("更新AipCenter失败：查不到对于的子订单" );
				//需要通知运维，去处理数据
				rMap.put("success", false);
				rMap.put("ERRORINFO", "更新AipCenter失败");
			}
		} catch (Exception er) {
			System.out.print("更新失败：" + er.getMessage());
			rMap.put("success", false);
			rMap.put("ERRORINFO", "更新订单失败");
		}
		return "1";
	}
    /**
     * 获取无限期的比较日期
     * @param
     * @return date
     */
    public   Date getNodatelength() {
        String strdate =Constants.Order.ORDER_API_NODATE;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}