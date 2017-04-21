package com.ai.bdex.dataexchange.busi.page.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageInfoRSV;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;


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
	public String pageInit(Model model){
		queryHotSearch(model);
		queryHeaderNav(model);
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
			log.error("查询楼层信息出错："+e.getMessage());
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
			model.addAttribute("moduleGoodsList",moduleGoodsList);
		} catch (Exception e) {
			log.error("查询商品楼层信息："+e.getMessage());
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
			model.addAttribute("moduleAdList",moduleAdList);
		} catch (Exception e) {
			log.error("查询广告楼层信息出错："+e.getMessage());
		}
		return "";
	}
	/**
	 * 查询商品分类信息
	 * @param model
	 */
	@RequestMapping(value="/querySortInfo")
	public String querySortInfo(Model model,@PathVariable Integer sortId,@PathVariable Integer sortParentId) {
		try {
			SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
			sortInfoRespDTO.setStatus(STATUS_VALID);
			if(sortId != null && sortId != 0){
				sortInfoRespDTO.setSortId(sortId);
			}
			List<SortInfoRespDTO> sortInfos = iPageInfoRSV.querySortInfos(sortInfoRespDTO);
			model.addAttribute("sortInfos",sortInfos);
		} catch (Exception e) {
			log.error("查询商品分类信息异常："+e.getMessage());
		}
		return "";
	}
	/**
	 * 查询首页热门搜索信息
	 */
	private void queryHotSearch(Model model){
		try {
			PageHotSearchRespDTO pageHotSearchRespDTO = new PageHotSearchRespDTO();
			pageHotSearchRespDTO.setStatus(STATUS_VALID);
			List<PageHotSearchRespDTO> hotSearchList = iPageInfoRSV.queryPageHotSearchList(pageHotSearchRespDTO);
			model.addAttribute("hotSearchList",hotSearchList);
		} catch (Exception e) {
			log.error("查询首页热门搜索信息异常："+e.getMessage());
		}
	}
	/**
	 * 查询首页导航信息
	 */
	private void queryHeaderNav(Model model){
		try {
			PageHeaderNavRespDTO pageHeaderNavRespDTO = new PageHeaderNavRespDTO();
			pageHeaderNavRespDTO.setStatus(STATUS_VALID);
			List<PageHeaderNavRespDTO> searchNavList = iPageInfoRSV.queryPageHeaderNavList(pageHeaderNavRespDTO);
			model.addAttribute("searchNavList",searchNavList);
		} catch (Exception e) {
			log.error("查询首页导航信息信息异常："+e.getMessage());
		}
	}
}
