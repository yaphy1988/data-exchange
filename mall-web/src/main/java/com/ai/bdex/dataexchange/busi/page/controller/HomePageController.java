package com.ai.bdex.dataexchange.busi.page.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageInfoRSV;


/**
 * 
 * Description:广交所首页 <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
@Controller
@RequestMapping(value="/homePage")
public class HomePageController {
	
	private final static String STATUS_VALID="1";//有效
	
	private static final Logger log = LoggerFactory.getLogger(HomePageController.class);
	
	@DubboConsumer
	IPageInfoRSV iPageInfoRSV;
	@RequestMapping(value="/pageInit")
	public String pageInit(){
		
		return "/index";
	}
	/**
	 * 查询楼层信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryPageModue")
	public Map<String,Object> queryPageModue(Model model){
		
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			PageModuleRespDTO pageModuleRespDTO = new PageModuleRespDTO();
			pageModuleRespDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageInfoRSV.queryPageModuleList(pageModuleRespDTO);
			rMap.put("pageModuleList", pageModuleList);
			rMap.put("success", true);
		} catch (Exception e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	/**
	 * 通过楼层ID查询商品信息
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="/queryPageModuleGoods")
	public String queryPageModuleGoods(Model model,@PathVariable Integer moduleId){
		
		try {
			PageModuleGoodsRespDTO pageModuleGoodsRespDTO = new PageModuleGoodsRespDTO();
			pageModuleGoodsRespDTO.setModuleId(moduleId);
			pageModuleGoodsRespDTO.setStatus(STATUS_VALID);
			List<PageModuleGoodsRespDTO> moduleGoodsList = iPageInfoRSV.queryPageModuleGoodsList(pageModuleGoodsRespDTO);
			model.addAllAttributes(moduleGoodsList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}
	/**
	 * 通过楼层ID查询广告信息
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="/queryPageModuleAd")
	public String queryPageModuleAd(Model model,@PathVariable Integer moduleId){
		try {
			PageModuleAdRespDTO pageModuleAdRespDTO = new PageModuleAdRespDTO();
			pageModuleAdRespDTO.setModuleId(moduleId);
			pageModuleAdRespDTO.setStatus(STATUS_VALID);
			List<PageModuleAdRespDTO> moduleAdList = iPageInfoRSV.queryPageModuleAdList(pageModuleAdRespDTO);
			model.addAllAttributes(moduleAdList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}
}
