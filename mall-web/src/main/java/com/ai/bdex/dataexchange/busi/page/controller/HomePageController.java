package com.ai.bdex.dataexchange.busi.page.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageInfoRSV;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

import javax.servlet.http.HttpServletResponse;


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
	private final static String CUSTOMDATA_STATUS_VALID="1";//有效
	private static final Logger log = LoggerFactory.getLogger(HomePageController.class);

	@DubboConsumer
	IPageInfoRSV iPageInfoRSV;
	@RequestMapping(value="/pageInit")
	public ModelAndView  pageInit(){
		ModelAndView modelAndView = new ModelAndView("index");
		queryHotSearch(modelAndView);
//		queryHeaderNav(modelAndView);
		return modelAndView;
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
	public String queryPageModuleGoods(Model model,@PathVariable("moduleId") Integer moduleId){
		
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
	public String queryPageModuleAd(Model model,@PathVariable("moduleId") Integer moduleId){
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
	public String querySortInfo(Model model,@PathVariable("sortId") Integer sortId,@PathVariable("sortParentId") Integer sortParentId) {
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
	private void queryHotSearch(ModelAndView modelAndView){
		try {
			PageHotSearchRespDTO pageHotSearchRespDTO = new PageHotSearchRespDTO();
			pageHotSearchRespDTO.setStatus(STATUS_VALID);
			List<PageHotSearchRespDTO> hotSearchList = iPageInfoRSV.queryPageHotSearchList(pageHotSearchRespDTO);
			modelAndView.addObject("hotSearchList",hotSearchList);
		} catch (Exception e) {
			log.error("查询首页热门搜索信息异常："+e.getMessage());
		}
	}
	/**
	 * 查询首页导航信息
	 */
	private void queryHeaderNav(ModelAndView modelAndView){
		try {
			PageHeaderNavRespDTO pageHeaderNavRespDTO = new PageHeaderNavRespDTO();
			pageHeaderNavRespDTO.setStatus(STATUS_VALID);
			List<PageHeaderNavRespDTO> searchNavList = iPageInfoRSV.queryPageHeaderNavList(pageHeaderNavRespDTO);
			modelAndView.addObject("searchNavList",searchNavList);
		} catch (Exception e) {
			log.error("查询首页导航信息信息异常："+e.getMessage());
		}
	}

	/**
	 * 首页数据定制
	 */
	@RequestMapping(value="/saveMadeData")
	private void saveMadeData(Model model, HttpServletRequest request, HttpServletResponse response){
		try {

            String needTieltmp = request.getParameter("needTiel");
			String needTiel = uRLDecoderStr(needTieltmp);
			String needcontent = uRLDecoderStr(request.getParameter("needcontent"));
			String lnkposen = uRLDecoderStr(request.getParameter("lnkposen"));
			String lnkphone = request.getParameter("lnkphone");
			String lnkemail = request.getParameter("lnkemail");

			DataCustomizationRespDTO dataCustomizationRespDTO   = new DataCustomizationRespDTO();
			dataCustomizationRespDTO.setCustomName(needTiel);
			dataCustomizationRespDTO.setCustomDescrip(needcontent);
			dataCustomizationRespDTO.setLinkPerson(lnkposen);
			dataCustomizationRespDTO.setLinkPhnoe(lnkphone);
			dataCustomizationRespDTO.setCustomMail(lnkemail);
			dataCustomizationRespDTO.setCreateStaffId("chuangjianren");
 			dataCustomizationRespDTO.setStatus(CUSTOMDATA_STATUS_VALID);
			int count = iPageInfoRSV.saveDataCustomizationRsv(dataCustomizationRespDTO);
			model.addAttribute("savecount",count);
		} catch (Exception e) {
			log.error("查询首页导航信息信息异常："+e.getMessage());
		}
	}
	private String uRLDecoderStr(String strinfo)
	{
		String newstrinfo  ="";
		try{
			newstrinfo = URLDecoder.decode(strinfo , "utf-8");
		}catch(Exception e){
		}
		return newstrinfo;
	}


}
