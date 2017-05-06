package com.ai.bdex.dataexchange.busi.order.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.CacheUtil;
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
	IGdsInfoRSV iGdsInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IGdsSkuRSV iGdsSkuRSV;
	
    //预定的数据展现
	@RequestMapping(value = "/gdstmpsavesession")
	public ModelAndView saveTosession(Model model, HttpServletRequest request) {
		//取用户ID
		HttpSession hpptsesion = request.getSession(); 
		String staff_id = StaffUtil.getStaffId(hpptsesion); 
		//将商品的名称，gdsID，套餐id，skuID，带过来，存储到session中。价格必须从商品服务从新回去
        //商品名称和ID
		int gdsid = Integer.parseInt(request.getParameter("gdsid"));
		String gdsname =  request.getParameter("gdsname"); 
		//套餐信息
		int skusid =  Integer.parseInt(request.getParameter("skuid"));
		String skuname =  request.getParameter("skuname"); 
		//获取商品的价格和图
		GdsSkuRespDTO gdsSkuRespDTO = new GdsSkuRespDTO(); 
		GdsSkuReqDTO dsSkuReqDTO = new GdsSkuReqDTO();
		dsSkuReqDTO.setGdsId(gdsid);
		dsSkuReqDTO.setSkuId(skusid);
		dsSkuReqDTO.setStatus(STATUS_VALID);
		List<GdsSkuRespDTO>  listGdsSku =new ArrayList<>();
		try { 
			listGdsSku = iGdsSkuRSV.queryGdsSkuList(dsSkuReqDTO);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		if(!CollectionUtil.isEmpty(listGdsSku) )
		{
			gdsSkuRespDTO = listGdsSku.get(0);
		}
	   /*	long gdsprice = 1;//单位分
		String vsfid = "";//图片ID 
       */        
		Object obj = new Object();
		//每次进来都是讲session赋值为新的数据
		// CacheUtil.addItem(staff_id, gdsSkuRespDTO);
	    request.setAttribute("skuInfo",gdsSkuRespDTO);
	    request.setAttribute("gdsname",gdsname);
	    request.setAttribute("skuname",skuname);
		//返回预购界面
		ModelAndView modelAndView = new ModelAndView("shoppint_cart");
		return modelAndView; 
	}
	  //修改预定的数据的数量，增加或者减少
	@RequestMapping(value = "/updategdstmpsave")
	@ResponseBody
	public ModelAndView updategdstmpsave() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
/*			//将商品的名称，gdsID，套餐id，skuID，带过来，存储到session中。
			//每次进来都是讲session赋值为新的数据
		String moduleType = request.getParameter("moduleType");
		String moduleId = request.getParameter("moduleId");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if (!StringUtils.isBlank(moduleType)) {
				pageModuleReqDTO.setModuleType(moduleType);
			}
			if(!StringUtils.isBlank(moduleId)){
				pageModuleReqDTO.setModuleId(Integer.valueOf(moduleId));
			}
			pageModuleReqDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			rMap.put("pageModuleList", pageModuleList);
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("查询楼层信息出错：" + e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;*/
	}
 
 
	/**
	 * 首页数据定制
	 */
	@RequestMapping(value = "/saveMadeData")
	@ResponseBody
	private  Map<String, Object> saveMadeData(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try { 
			String needTieltmp = request.getParameter("needTiel");
			String needTiel = uRLDecoderStr(needTieltmp);
			String needcontent = uRLDecoderStr(request.getParameter("needcontent"));
			String lnkposen = uRLDecoderStr(request.getParameter("lnkposen"));
			String lnkphone = request.getParameter("lnkphone");
			String lnkemail = request.getParameter("lnkemail");
			HttpSession hpptsesion = request.getSession(); 
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			DataCustomizationRespDTO dataCustomizationRespDTO = new DataCustomizationRespDTO();
			dataCustomizationRespDTO.setCustomName(needTiel);
			dataCustomizationRespDTO.setCustomDescrip(needcontent);
			dataCustomizationRespDTO.setLinkPerson(lnkposen);
			dataCustomizationRespDTO.setLinkPhnoe(lnkphone);
			dataCustomizationRespDTO.setCustomMail(lnkemail);
			if(!StringUtil.isBlank(staff_id) )
			{
				dataCustomizationRespDTO.setCreateStaffId(staff_id); 
			} 
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("保存数据定制异常：" + e.getMessage());
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

	 
}