package com.ai.bdex.dataexchange.busi.order.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.constants.Constants;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.CollectionUtil;
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
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@DubboConsumer(timeout = 30000)
	IOrderInfoRSV iOrderInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IGdsSkuRSV iGdsSkuRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	@DubboConsumer(timeout = 30000)
	IAipServiceInfoRSV iAipServiceInfoRSV;
	
    //预定的数据展现
	@RequestMapping(value = "/gdshopcart")
	public ModelAndView saveTosession(Model model, HttpServletRequest request) {
		//取用户ID
		HttpSession hpptsesion = request.getSession(); 
		String staff_id = StaffUtil.getStaffId(hpptsesion); 
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
						List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(gdsInfoRespDTO.getApiId()));
						if(!CollectionUtil.isEmpty(apiServiceList))
						{
							ordInfoReqDTO.setServiceName(apiServiceList.get(0).getServiceName());
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
			Object  ordInfoDTO = CacheUtil.getItem(staff_id+"_shopcart");
			if(ordInfoDTO != null)
			{
				CacheUtil.delItem(staff_id+"_shopcart");
			}
			//每次进来都是讲session赋值为新的数据
			if(StringUtil.isBlank(staff_id))
			{
				staff_id = "tmpuser";
			}
			 CacheUtil.addItem(staff_id+"_shopcart", ordInfoReqDTO);
		}  
	    request.setAttribute("skuInfo",gdsSkuRespDTO);
	    request.setAttribute("gdsname",gdsname);
	    request.setAttribute("skuname",skuname);
	    request.setAttribute("gdsvfsurl",gdsvfsurl); 
	    request.setAttribute("authenflag",staffInfoDTO.getAuthenFlag());  
	    
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
				staff_id = "tmpuser";
			}
			OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+"_shopcart");
			//原始单品次数
			long skutimes = ordInfoReqDTO.getBuyAllCount()/ordInfoReqDTO.getOrderAmount();			
			ordInfoReqDTO.setOrderAmount(iorderamount);
			ordInfoReqDTO.setOrderMoney(iorderamount*ordInfoReqDTO.getOrderPrice());
			ordInfoReqDTO.setBuyAllCount(iorderamount*skutimes);
			//重置
			CacheUtil.delItem(staff_id+"_shopcart"); 
			CacheUtil.addItem(staff_id+"_shopcart", ordInfoReqDTO);  
			
		 	double dmoney = iorderamount*ordInfoReqDTO.getOrderPrice();
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
 						staff_id = "tmpuser";
 					}
 					OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+"_shopcart");
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
 						ordInfoReqDTO.setCatFirst(gdsInfoRespDTO.getCatFirst());
 						ordInfoReqDTO.setCatId(gdsInfoRespDTO.getCatId());
 					}
				    ordInfoReqDTO.setShopId(Constants.Shop.GZDATA_SHOP_ID);
  					iOrderInfoRSV.createOrderInfo(ordInfoReqDTO);
 			} 
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("生成订单异常：" + e.getMessage());
			rMap.put("success", false);
		}
		return rMap;
	}
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
 					OrdInfoReqDTO  ordInfoReqDTO = (OrdInfoReqDTO)CacheUtil.getItem(staff_id+"_shopcart");
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
		  

	 
}