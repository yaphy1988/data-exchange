<<<<<<< Upstream, based on origin/develop
package com.ai.bdex.dataexchange.busi.page.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;


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
	IPageDisplayRSV iPageDisplayRSV;
	@DubboConsumer
	IGdsInfoRSV iGdsInfoRSV;
	
	@RequestMapping(value="/pageInit")
	public ModelAndView  pageInit(){
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	/**
	 * 查询楼层信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryPageModue")
	@ResponseBody
	public Map<String,Object> queryPageModue(Model model){
		
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			PageModuleRespDTO pageModuleRespDTO = new PageModuleRespDTO();
			pageModuleRespDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleRespDTO);
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
	 * 通过楼层ID查询商品信息--数据推荐
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="/queryPageModuleGoods")
	@ResponseBody
	public  Map<String,Object> queryPageModuleGoods(Model model, HttpServletRequest request){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			String moduleIdstr = request.getParameter("moduleId");
			int moduleId = Integer.parseInt(moduleIdstr);
			PageModuleGoodsRespDTO pageModuleGoodsRespDTO = new PageModuleGoodsRespDTO();
			pageModuleGoodsRespDTO.setModuleId(moduleId);
			pageModuleGoodsRespDTO.setStatus(STATUS_VALID);  
			PageResponseDTO<PageModuleGoodsRespDTO> moduleGoodsList = iPageDisplayRSV.queryPageModuleGoodsList(pageModuleGoodsRespDTO);
			if(moduleGoodsList !=null && moduleGoodsList.getCount()>0)
			{
				try{
					   for(int i = 0 ; i < moduleGoodsList.getResult().size();i++) {
							int gdsid =  moduleGoodsList.getResult().get(i).getGdsId();
							//通过商品id去搜索商品信息，获取到商品的图片ID
							GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
							GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
							gdsInfoReqDTO.setGdsId(gdsid);
							gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
							if(gdsInfoRespDTO!= null && !StringUtil.isBlank(gdsInfoRespDTO.getGdsPic()) )
							{
								  String vfsid= ImageUtil.getImageUrl(gdsInfoRespDTO.getGdsPic() + "_150x150.jpg");
								// String vfsid= "http://112.74.163.29:14751/ImageServer/image/"+gdsInfoRespDTO.getGdsPic()+"_150x150.jpg";
								  moduleGoodsList.getResult().get(i).setVfsid(vfsid); 
							}
					   }
					}
					catch(Exception e1)
					{
						log.error("查询推荐商品的图片信息："+e1.getMessage());
					}
			}
		
			rMap.put("moduleGoodsList",moduleGoodsList);
 			rMap.put("success", true);
 		
		} catch (Exception e) {
			log.error("查询商品楼层信息："+e.getMessage());
		}
		return rMap;
	}
	/**
	 * 通过楼层ID查询广告信息
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="/queryPageModuleAd")
	@ResponseBody
	public Map<String,Object> queryPageModuleAd(Model model,HttpServletRequest request){
		String moduleId = request.getParameter("moduleId");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			//查询楼层信息
			PageModuleRespDTO pageModuleRespDTO = new PageModuleRespDTO();
			if(!StringUtils.isBlank(moduleId)){
				pageModuleRespDTO.setModuleId(Integer.valueOf(moduleId));
			}
			pageModuleRespDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleRespDTO);
			PageModuleRespDTO moduleRespDTO = new PageModuleRespDTO();
			if(!CollectionUtils.isEmpty(pageModuleList)){
				moduleRespDTO = pageModuleList.get(0);
			}
			Integer count = moduleRespDTO.getModuleCount();
			PageModuleAdRespDTO pageModuleAdRespDTO = new PageModuleAdRespDTO();
			if(!StringUtils.isBlank(moduleId)){
				pageModuleAdRespDTO.setModuleId(Integer.valueOf(moduleId));
			}
			if(moduleRespDTO.getModuleCount() != null){
				pageModuleAdRespDTO.setPageSize(count);
			}
			pageModuleAdRespDTO.setStatus(STATUS_VALID);
			PageResponseDTO<PageModuleAdDTO> moduleAdPageInfo = iPageDisplayRSV.queryPageModulePageInfo(pageModuleAdRespDTO);
			List<PageModuleAdDTO> moduleAResult = moduleAdPageInfo.getResult();
			if(!CollectionUtils.isEmpty(moduleAResult)){
				for(PageModuleAdDTO moduleAdDTO : moduleAResult){
					if(moduleAdDTO.getVfsId()!=null){
 						moduleAdDTO.setVfsId(ImageUtil.getImageUrl(moduleAdDTO.getVfsId()));
					}
				}
			}
			rMap.put("moduleAdList", moduleAdPageInfo.getResult());
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("查询广告楼层信息出错：楼层ID="+moduleId+","+e.getMessage());
		}
		return rMap;
	}
	/**
	 * 查询商品分类信息
	 * @param model
	 */
	@RequestMapping(value="/querySortInfo")
	@ResponseBody
	public Map<String,Object> querySortInfo(Model model,HttpServletRequest request) {
		String sortId = request.getParameter("sortId");
		String sortParentId = request.getParameter("sortParentId");
		String sortLever = request.getParameter("sortLever");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
			sortInfoRespDTO.setStatus(STATUS_VALID);
			if(!StringUtils.isBlank(sortId)){
				sortInfoRespDTO.setSortId(Integer.valueOf(sortId));
			}
			if(!StringUtils.isBlank(sortParentId)){
				sortInfoRespDTO.setParentSortId(Integer.valueOf(sortParentId));
			}
			if(!StringUtils.isBlank(sortLever)){
				sortInfoRespDTO.setSortLevel(sortLever);
			}
			List<SortInfoRespDTO> sortInfos = iPageDisplayRSV.querySortInfos(sortInfoRespDTO);
			rMap.put("success", true);
			rMap.put("sortInfos",sortInfos);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询商品分类信息异常："+e.getMessage());
		}
		return rMap;
	}
	/**
	 * 查询首页热门搜索信息
	 */
	@RequestMapping(value="/queryHotSearch")
	@ResponseBody
	private Map<String,Object> queryHotSearch(){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			PageHotSearchRespDTO pageHotSearchRespDTO = new PageHotSearchRespDTO();
			pageHotSearchRespDTO.setStatus(STATUS_VALID);
			List<PageHotSearchRespDTO> hotSearchList = iPageDisplayRSV.queryPageHotSearchList(pageHotSearchRespDTO);
			rMap.put("hotSearchList", hotSearchList);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页热门搜索信息异常："+e.getMessage());
		}
		return rMap;
	}
	/**
	 * 查询首页导航信息
	 */
	@RequestMapping(value="/queryHeaderNav")
	@ResponseBody
	private Map<String,Object>  queryHeaderNav(){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			PageHeaderNavRespDTO pageHeaderNavRespDTO = new PageHeaderNavRespDTO();
			pageHeaderNavRespDTO.setStatus(STATUS_VALID);
			List<PageHeaderNavRespDTO> searchNavList = iPageDisplayRSV.queryPageHeaderNavList(pageHeaderNavRespDTO);
			rMap.put("searchNavList", searchNavList);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页导航信息信息异常："+e.getMessage());
		}
		return rMap;
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
			int count = iPageDisplayRSV.saveDataCustomizationRsv(dataCustomizationRespDTO);
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
	/**
	 * 查询平台公告信息
	 * @return
	 */
	@RequestMapping(value="/queryPageInfoList")
	@ResponseBody
	private Map<String,Object>  queryPageInfoList(HttpServletRequest request){
		String pageNo =request.getParameter("pageNo");
		String moduleId =request.getParameter("moduleId");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			//查询楼层信息
			PageModuleRespDTO pageModuleRespDTO = new PageModuleRespDTO();
			if(!StringUtils.isBlank(moduleId)){
				pageModuleRespDTO.setModuleId(Integer.valueOf(moduleId));
			}
			pageModuleRespDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleRespDTO);
			PageModuleRespDTO moduleRespDTO = new PageModuleRespDTO();
			if(!CollectionUtils.isEmpty(pageModuleList)){
				moduleRespDTO = pageModuleList.get(0);
			}
			Integer count = moduleRespDTO.getModuleCount();
			PageNewsInfoRespDTO sortInfoRespDTO = new PageNewsInfoRespDTO();
			sortInfoRespDTO.setStatus(STATUS_VALID);
			if(!StringUtils.isBlank(pageNo)){
				sortInfoRespDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(count != null){
				sortInfoRespDTO.setPageSize(Integer.valueOf(count));
			}
			if(!StringUtils.isBlank(moduleId)){
				sortInfoRespDTO.setModuleId(Integer.valueOf(moduleId));
			}
			PageResponseDTO<PageNewsInfoDTO> pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(sortInfoRespDTO);
			rMap.put("pageInfoList", pageInfoList.getResult());
			rMap.put("moduleRespDTO", moduleRespDTO);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页导航信息信息异常："+e.getMessage());
		}
		return rMap;
	}
	/**
	 * 公司简介
	 */
	@RequestMapping(value="/company")
	private String company(){ 
		String viewName = "/companyprofile"; 
		return viewName;	 
	}
	/**
	 * 新闻动态
	 */
	@RequestMapping(value="/companynews")
	private String companynews(){ 
		String viewName = "/companynews"; 
		return viewName;	 
	}
	/**
	 * 常见问题
	 */
	@RequestMapping(value="/commonproblem")
	private String commonproblem(){ 
		String viewName = "/commonproblem"; 
		return viewName;	 
	}
	/**
	 * 帮助中心
	 */
	@RequestMapping(value="/commonHelp")
	private String commonHelp(){ 
		String viewName = "/commonHelp"; 
		return viewName;	 
	}
	

}
=======
package com.ai.bdex.dataexchange.busi.page.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 
 * Description:广交所首页 <br>
 * Date: 2017年4月19日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
@Controller
@RequestMapping(value = "/homePage")
public class HomePageController {

	private final static String STATUS_VALID = "1";// 有效
	private final static String CUSTOMDATA_STATUS_VALID = "1";// 有效
	private static final Logger log = LoggerFactory.getLogger(HomePageController.class);

	@DubboConsumer
	IPageDisplayRSV iPageDisplayRSV;

	@RequestMapping(value = "/pageInit")
	public ModelAndView pageInit() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	/**
	 * 查询楼层信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPageModue")
	@ResponseBody
	public Map<String, Object> queryPageModue(Model model, HttpServletRequest request) {
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
		return rMap;
	}

	/**
	 * 通过楼层ID查询商品信息
	 * 
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value = "/queryPageModuleGoods")
	@ResponseBody
	public Map<String, Object> queryPageModuleGoods(Model model, HttpServletRequest request) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			String moduleIdstr = request.getParameter("moduleId");
			int moduleId = Integer.parseInt(moduleIdstr);
			PageModuleGoodsRespDTO pageModuleGoodsRespDTO = new PageModuleGoodsRespDTO();
			pageModuleGoodsRespDTO.setModuleId(moduleId);
			pageModuleGoodsRespDTO.setStatus(STATUS_VALID);
			// 通过商品id去搜索商品信息，获取到商品的图片ID
			/*
			 * IGdsInfoRSV.queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO);
			 * IGdsInfoRSV.queryGdsInfo(GdsInfoReqDTO gdsInfoReqDTO);
			 */
			PageResponseDTO<PageModuleGoodsRespDTO> moduleGoodsList = iPageDisplayRSV
					.queryPageModuleGoodsList(pageModuleGoodsRespDTO);
			rMap.put("moduleGoodsList", moduleGoodsList);
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("查询商品楼层信息：" + e.getMessage());
		}
		return rMap;
	}

	/**
	 * 通过楼层ID查询广告信息
	 * 
	 * @param model
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value = "/queryPageModuleAd")
	@ResponseBody
	public Map<String, Object> queryPageModuleAd(Model model, HttpServletRequest request) {
		String moduleId = request.getParameter("moduleId");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			// 查询楼层信息
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if (!StringUtils.isBlank(moduleId)) {
				pageModuleReqDTO.setModuleId(Integer.valueOf(moduleId));
			}
			pageModuleReqDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			PageModuleRespDTO moduleRespDTO = new PageModuleRespDTO();
			if (!CollectionUtils.isEmpty(pageModuleList)) {
				moduleRespDTO = pageModuleList.get(0);
			}
			Integer count = moduleRespDTO.getModuleCount();
			PageModuleAdReqDTO pageModuleAdResDTO = new PageModuleAdReqDTO();
			if (!StringUtils.isBlank(moduleId)) {
				pageModuleAdResDTO.setModuleId(Integer.valueOf(moduleId));
			}
			if (moduleRespDTO.getModuleCount() != null) {
				pageModuleAdResDTO.setPageSize(count);
			}
			pageModuleAdResDTO.setStatus(STATUS_VALID);
			PageResponseDTO<PageModuleAdRespDTO> moduleAdPageInfo = iPageDisplayRSV
					.queryPageModulePageInfo(pageModuleAdResDTO);
			List<PageModuleAdRespDTO> moduleAResult = moduleAdPageInfo.getResult();
			if (!CollectionUtils.isEmpty(moduleAResult)) {
				for (PageModuleAdRespDTO moduleAdDTO : moduleAResult) {
					if (moduleAdDTO.getVfsId() != null) {
						// moduleAdDTO.setVfsId(ImageUtil.getImageUrl(moduleAdDTO.getVfsId()));
					}
				}
			}
			rMap.put("moduleAdList", moduleAdPageInfo.getResult());
			rMap.put("success", true);
		} catch (Exception e) {
			log.error("查询广告楼层信息出错：楼层ID=" + moduleId + "," + e.getMessage());
		}
		return rMap;
	}

	/**
	 * 查询商品分类信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/querySortInfo")
	@ResponseBody
	public Map<String, Object> querySortInfo(Model model, HttpServletRequest request) {
		String sortId = request.getParameter("sortId");
		String sortParentId = request.getParameter("sortParentId");
		String sortLever = request.getParameter("sortLever");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
			sortInfoRespDTO.setStatus(STATUS_VALID);
			if (!StringUtils.isBlank(sortId)) {
				sortInfoRespDTO.setSortId(Integer.valueOf(sortId));
			}
			if (!StringUtils.isBlank(sortParentId)) {
				sortInfoRespDTO.setParentSortId(Integer.valueOf(sortParentId));
			}
			if (!StringUtils.isBlank(sortLever)) {
				sortInfoRespDTO.setSortLevel(sortLever);
			}
			List<SortInfoRespDTO> sortInfos = iPageDisplayRSV.querySortInfos(sortInfoRespDTO);
			rMap.put("success", true);
			rMap.put("sortInfos", sortInfos);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询商品分类信息异常：" + e.getMessage());
		}
		return rMap;
	}

	/**
	 * 查询首页热门搜索信息
	 */
	@RequestMapping(value = "/queryHotSearch")
	@ResponseBody
	private Map<String, Object> queryHotSearch() {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageHotSearchRespDTO pageHotSearchRespDTO = new PageHotSearchRespDTO();
			pageHotSearchRespDTO.setStatus(STATUS_VALID);
			List<PageHotSearchRespDTO> hotSearchList = iPageDisplayRSV.queryPageHotSearchList(pageHotSearchRespDTO);
			rMap.put("hotSearchList", hotSearchList);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页热门搜索信息异常：" + e.getMessage());
		}
		return rMap;
	}

	/**
	 * 查询首页导航信息
	 */
	@RequestMapping(value = "/queryHeaderNav")
	@ResponseBody
	private Map<String, Object> queryHeaderNav() {
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageHeaderNavRespDTO pageHeaderNavRespDTO = new PageHeaderNavRespDTO();
			pageHeaderNavRespDTO.setStatus(STATUS_VALID);
			List<PageHeaderNavRespDTO> searchNavList = iPageDisplayRSV.queryPageHeaderNavList(pageHeaderNavRespDTO);
			rMap.put("searchNavList", searchNavList);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页导航信息信息异常：" + e.getMessage());
		}
		return rMap;
	}

	/**
	 * 首页数据定制
	 */
	@RequestMapping(value = "/saveMadeData")
	private void saveMadeData(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {

			String needTieltmp = request.getParameter("needTiel");
			String needTiel = uRLDecoderStr(needTieltmp);
			String needcontent = uRLDecoderStr(request.getParameter("needcontent"));
			String lnkposen = uRLDecoderStr(request.getParameter("lnkposen"));
			String lnkphone = request.getParameter("lnkphone");
			String lnkemail = request.getParameter("lnkemail");

			DataCustomizationRespDTO dataCustomizationRespDTO = new DataCustomizationRespDTO();
			dataCustomizationRespDTO.setCustomName(needTiel);
			dataCustomizationRespDTO.setCustomDescrip(needcontent);
			dataCustomizationRespDTO.setLinkPerson(lnkposen);
			dataCustomizationRespDTO.setLinkPhnoe(lnkphone);
			dataCustomizationRespDTO.setCustomMail(lnkemail);
			dataCustomizationRespDTO.setCreateStaffId("chuangjianren");
			dataCustomizationRespDTO.setStatus(CUSTOMDATA_STATUS_VALID);
			int count = iPageDisplayRSV.saveDataCustomizationRsv(dataCustomizationRespDTO);
			model.addAttribute("savecount", count);
		} catch (Exception e) {
			log.error("查询首页导航信息信息异常：" + e.getMessage());
		}
	}

	private String uRLDecoderStr(String strinfo) {
		String newstrinfo = "";
		try {
			newstrinfo = URLDecoder.decode(strinfo, "utf-8");
		} catch (Exception e) {
		}
		return newstrinfo;
	}

	/**
	 * 查询平台公告信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryPageInfoList")
	@ResponseBody
	private Map<String, Object> queryPageInfoList(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String moduleId = request.getParameter("moduleId");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			// 查询楼层信息
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if (!StringUtils.isBlank(moduleId)) {
				pageModuleReqDTO.setModuleId(Integer.valueOf(moduleId));
			}
			pageModuleReqDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			PageModuleRespDTO moduleRespDTO = new PageModuleRespDTO();
			if (!CollectionUtils.isEmpty(pageModuleList)) {
				moduleRespDTO = pageModuleList.get(0);
			}
			Integer count = moduleRespDTO.getModuleCount();
			PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
			newsInfoReqDTO.setStatus(STATUS_VALID);
			if (!StringUtils.isBlank(pageNo)) {
				newsInfoReqDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if (count != null) {
				newsInfoReqDTO.setPageSize(Integer.valueOf(count));
			}
			if (!StringUtils.isBlank(moduleId)) {
				newsInfoReqDTO.setModuleId(Integer.valueOf(moduleId));
			}
			PageResponseDTO<PageNewsInfoRespDTO> pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(newsInfoReqDTO);
			rMap.put("pageInfoList", pageInfoList.getResult());
			rMap.put("moduleRespDTO", moduleRespDTO);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询首页导航信息信息异常：" + e.getMessage());
		}
		return rMap;
	}

	// 新闻资讯列表页
	@RequestMapping(value = "/pageNewsInfolist")
	public ModelAndView pageNewsInfoList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("info_list");
		String infoType = request.getParameter("infoType");
		modelAndView.addObject("infoType", infoType);
		return modelAndView;
	}

	// 新闻资讯详情
	@RequestMapping(value = "/pageNewsDetail")
	public ModelAndView pageNewsDetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("info_details");
		String infoId = request.getParameter("infoId");
		try {
			if (!StringUtils.isBlank(infoId)) {
				PageNewsInfoRespDTO pageNewsInfo = iPageDisplayRSV.queryPageNewsInfoById(Integer.valueOf(infoId));
				if (pageNewsInfo != null) {
//					 String docUrl = ImageUtil.getStaticDocUrl(pageNewsInfo.getVfsId(),"html");
//					 pageNewsInfo.setInfoUrl(docUrl);
				}
				modelAndView.addObject("pageNewsInfo", pageNewsInfo);
			}

		} catch (Exception e) {
			log.error("查询首页新闻资讯详情异常：" + e.getMessage());
		}
		return modelAndView;
	}
	//分页查询新闻资讯列表
	@RequestMapping(value="/queryNewsPageInfo")
	@ResponseBody
	public Map<String,Object> queryNewsPageInfo(HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		String moduleId = request.getParameter("moduleId");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			
			PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
			newsInfoReqDTO.setStatus(STATUS_VALID);
			if (!StringUtils.isBlank(pageNo)) {
				newsInfoReqDTO.setPageNo(Integer.valueOf(pageNo));
			}
			newsInfoReqDTO.setPageSize(5);
			if (!StringUtils.isBlank(moduleId)) {
				newsInfoReqDTO.setModuleId(Integer.valueOf(moduleId));
			}
			PageResponseDTO<PageNewsInfoRespDTO> pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(newsInfoReqDTO);
			List<PageNewsInfoRespDTO> newsList = pageInfoList.getResult();
			if(!CollectionUtils.isEmpty(pageInfoList.getResult())){
				for(PageNewsInfoRespDTO infoRespDTO : newsList){
					if (infoRespDTO.getVfsId() != null) {
//					 String docUrl = ImageUtil.getStaticDocUrl(pageNewsInfo.getVfsId(),"html");
//					 pageNewsInfo.setInfoUrl(docUrl);
					}
					DateUtil.format(infoRespDTO.getPubTime(), "yyyy-MM-dd");
					PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
					if(infoRespDTO.getModuleId() != null){
						pageModuleReqDTO.setModuleId(Integer.valueOf(infoRespDTO.getModuleId()));
					}
					pageModuleReqDTO.setStatus(STATUS_VALID);
					List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
					PageModuleRespDTO moduleRespDTO = pageModuleList.get(0);
					infoRespDTO.setInfoType(moduleRespDTO.getModuleName());
					
				}
			}
			rMap.put("pageInfo", pageInfoList);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			log.error("查询新闻资讯列表异常：" + e.getMessage());
		}
		return rMap;
	}
}
>>>>>>> b7b6d59 新闻资讯详情页、列表页
