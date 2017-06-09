package com.ai.bdex.dataexchange.busi.page.controller;

import com.ai.bdex.dataexchange.busi.page.entity.PageModuleGoodsVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageNewsInfoVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@DubboConsumer(timeout = 30000)
	IPageDisplayRSV iPageDisplayRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	
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
		String moduleIdstr = request.getParameter("moduleId");
		
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			int moduleId = Integer.parseInt(moduleIdstr);
			//查询楼层
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			pageModuleReqDTO.setStatus(STATUS_VALID);
			PageModuleRespDTO moduleResp = iPageDisplayRSV.queryPageModuleById(moduleId);
			Integer count = 10;
			if (moduleResp != null) {
				count = moduleResp.getModuleCount();
			}
			
			PageModuleGoodsReqDTO pageModuleGoodsReqDTO = new PageModuleGoodsReqDTO();
			pageModuleGoodsReqDTO.setModuleId(moduleId);
			pageModuleGoodsReqDTO.setStatus(STATUS_VALID);  
			pageModuleGoodsReqDTO.setPageSize(count);
			PageResponseDTO<PageModuleGoodsRespDTO> moduleGoodsList = iPageDisplayRSV.queryPageModuleGoodsList(pageModuleGoodsReqDTO);
			List<PageModuleGoodsVO> resultList = new ArrayList<PageModuleGoodsVO>();
			if(moduleGoodsList !=null && moduleGoodsList.getCount()>0)
			{
				try{
					   for(PageModuleGoodsRespDTO source: moduleGoodsList.getResult()) {
						   PageModuleGoodsVO target = new PageModuleGoodsVO();
						   BeanUtils.copyProperties(source, target);
						   resultList.add(target);
							int gdsid =  source.getGdsId();
							//通过商品id去搜索商品信息，获取到商品的图片ID
							GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
							GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
							gdsInfoReqDTO.setGdsId(gdsid);
							gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
							if(gdsInfoRespDTO!= null && !StringUtil.isBlank(gdsInfoRespDTO.getGdsPic()) )
							{
								  String vfsid= ImageUtil.getImageUrl(gdsInfoRespDTO.getGdsPic() + "_86x86!");
								  target.setVfsId(vfsid); 
								  target.setGdsName(gdsInfoRespDTO.getGdsName());
							}
					   }
					}
					catch(Exception e1)
					{
						log.error("查询推荐商品的图片信息："+e1.getMessage());
					}
			}
		
			rMap.put("moduleGoodsList",resultList);
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
					.queryPageModuleAdPageInfo(pageModuleAdResDTO);
			List<PageModuleAdRespDTO> moduleAResult = moduleAdPageInfo.getResult();
			if (!CollectionUtils.isEmpty(moduleAResult)) {
				for (PageModuleAdRespDTO moduleAdDTO : moduleAResult) {
					if (moduleAdDTO.getVfsId() != null) {
						if(moduleId.equals("103")){
							//数据定制
							moduleAdDTO.setVfsId(ImageUtil.getImageUrl(moduleAdDTO.getVfsId()+ "_555x350!"));
						}else if(moduleId.equals("101")){
								//轮播广告图
								moduleAdDTO.setVfsId(ImageUtil.getImageUrl(moduleAdDTO.getVfsId()+ "_940x400!"));
						}else if(moduleId.equals("109")){
						   //合作伙伴
							moduleAdDTO.setVfsId(ImageUtil.getImageUrl(moduleAdDTO.getVfsId()+ "_217x116!"));

						}

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
	public String querySortInfo(Model model, HttpServletRequest request) {
		String sortParentId = request.getParameter("sortParentId");
		String sortLever = request.getParameter("sortLever");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
			sortInfoRespDTO.setStatus(STATUS_VALID);
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
		return getJsonCallback(rMap,request);
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
	@ResponseBody
	private  Map<String, Object> saveMadeData(Model model,  HttpServletRequest request,  HttpServletResponse response) {
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
			StaffInfoDTO staffInfoDTO  = StaffUtil.getStaffVO(hpptsesion);
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
			else
			{
				log.error("请先登录" );
				rMap.put("erroinfo", "NOLOGIN");
				rMap.put("success", false);
				return rMap;
			}
			//未认证
			if(!"1".equals(staffInfoDTO.getAuthenFlag()))
			{
				rMap.put("erroinfo", "NOAUTHFLAG");
				rMap.put("success", false);
				return rMap;
			}

			dataCustomizationRespDTO.setStatus(CUSTOMDATA_STATUS_VALID);
			int count = iPageDisplayRSV.saveDataCustomizationRsv(dataCustomizationRespDTO);
			model.addAttribute("savecount", count);
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
		String moduleId = request.getParameter("moduleId");
		modelAndView.addObject("moduleId", moduleId);
		return modelAndView;
	}

	// 新闻资讯详情
	@RequestMapping(value = "/newsDetail")
	public ModelAndView newsDetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("info_details");
		String infoId = request.getParameter("infoId");
		try {
			if (!StringUtils.isBlank(infoId)) {
				PageNewsInfoRespDTO pageNewsInfo = iPageDisplayRSV.queryPageNewsInfoById(Integer.valueOf(infoId));
				if (pageNewsInfo != null) {
					 String docUrl = ImageUtil.getStaticDocUrl(pageNewsInfo.getInfoUrl(),"html");
					 pageNewsInfo.setInfoUrl(docUrl);
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
	public String queryNewsPageInfo(HttpServletRequest request,Model model){
		String pageNo = request.getParameter("pageNo");
		String moduleId = request.getParameter("moduleId");
		PageResponseDTO<PageNewsInfoRespDTO> pageInfoList = new PageResponseDTO<>();
		PageModuleRespDTO moduleRespDTO = null;
		try {
			if (!StringUtils.isBlank(moduleId)) {
				// 查询楼层信息
				PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
				pageModuleReqDTO.setModuleId(Integer.valueOf(moduleId));
				pageModuleReqDTO.setStatus(STATUS_VALID);
				List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
				moduleRespDTO = pageModuleList.get(0);
				if(moduleRespDTO != null){
					PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
					newsInfoReqDTO.setStatus(STATUS_VALID);
					if(!StringUtils.isBlank(pageNo)){
						newsInfoReqDTO.setPageNo(Integer.valueOf(pageNo));
					}
					newsInfoReqDTO.setPageSize(3);
					newsInfoReqDTO.setModuleId(moduleRespDTO.getModuleId());
					pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(newsInfoReqDTO);
					List<PageNewsInfoRespDTO> result = pageInfoList.getResult();
					if(!CollectionUtils.isEmpty(result)){
						for(PageNewsInfoRespDTO infoRespDTO : result){
							infoRespDTO.setInfoType(moduleRespDTO.getModuleName());
							model.addAttribute("moduleName", moduleRespDTO.getModuleName());
							infoRespDTO.setInfoUrl(ImageUtil.getStaticDocUrl(infoRespDTO.getInfoUrl(), "html"));
						}
						
					}
				
				}
			}else{
				PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
				newsInfoReqDTO.setStatus(STATUS_VALID);
				if(!StringUtils.isBlank(pageNo)){
					newsInfoReqDTO.setPageNo(Integer.valueOf(pageNo));
				}
				newsInfoReqDTO.setPageSize(3);
				pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(newsInfoReqDTO);
				List<PageNewsInfoRespDTO> result = pageInfoList.getResult();
				if(!CollectionUtils.isEmpty(result)){
					for(PageNewsInfoRespDTO infoRespDTO : result){
						// 查询楼层信息
						PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
						pageModuleReqDTO.setModuleId(infoRespDTO.getModuleId());
						pageModuleReqDTO.setStatus(STATUS_VALID);
						PageModuleRespDTO moduleDTO = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO).get(0);
						infoRespDTO.setInfoType(moduleDTO.getModuleName());
						//设置文本路劲
						String docUrl = ImageUtil.getStaticDocUrl(infoRespDTO.getInfoUrl(), "html");
						infoRespDTO.setInfoUrl(docUrl);
					}
				}
			}
			model.addAttribute("pageInfo", pageInfoList);
		} catch (Exception e) {
			log.error("查询新闻资讯列表异常：" + e.getMessage());
		}
		return "info_list :: #info_listitem";
	}
	/**
	 * 公司简介
	 */
	@RequestMapping(value="/company")
	private String company(){ 
		String viewName = "companyprofile"; 
		return viewName;	 
	} 
	/**
	 * 联系我们
	 */
	@RequestMapping(value="/contactouer")
	private String contactouer(){ 
		String viewName = "contactouer"; 
		return viewName;	 
	}
	/**
	 * 帮助中心
	 */
	@RequestMapping(value="/commonHelp")
	private String commonHelp(){ 
		String viewName = "commonHelp"; 
		return viewName;	 
	}
	
	/**
	 * 查询新闻资讯列表（页面右侧模块）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryRightNewslist")
	private String queryRightNewslist(HttpServletRequest request,Model model) {
		String moduleType = request.getParameter("moduleType");
		String modulePid = request.getParameter("modulePid");
		List<PageNewsInfoVO> resultList = new ArrayList<>();
		try {
			
			// 查询楼层信息
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			if (!StringUtils.isBlank(modulePid)) {
				pageModuleReqDTO.setModulePid(Integer.valueOf(modulePid));
			}
			if(!StringUtils.isBlank(moduleType)){
				pageModuleReqDTO.setModuleType(moduleType);
			}
			pageModuleReqDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> pageModuleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			PageModuleRespDTO moduleRespDTO = pageModuleList.get(0);
			List<PageModuleRespDTO> subPageModuleList = new ArrayList<>();
			if(moduleRespDTO !=null){
				subPageModuleList = moduleRespDTO.getSubPageModuleList();
			}
			
			if(!CollectionUtils.isEmpty(subPageModuleList)){
				for(PageModuleRespDTO pageModuleRespDTO: subPageModuleList){
					PageNewsInfoVO newsInfoVO = new PageNewsInfoVO();
					PageModuleVO pageModuleVO = new PageModuleVO();
					BeanUtils.copyProperties(pageModuleRespDTO, pageModuleVO);
					newsInfoVO.setPageModuleVO(pageModuleVO);
					
					PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
					newsInfoReqDTO.setStatus(STATUS_VALID);
					if (pageModuleRespDTO.getModuleCount() != null) {
						newsInfoReqDTO.setPageSize(Integer.valueOf(pageModuleRespDTO.getModuleCount()));
					}
					if (pageModuleRespDTO.getModuleId() != null) {
						newsInfoReqDTO.setModuleId(Integer.valueOf(pageModuleRespDTO.getModuleId()));
					}
					PageResponseDTO<PageNewsInfoRespDTO> pageInfoList = iPageDisplayRSV.queryPageNewsInfoList(newsInfoReqDTO);
					if(!CollectionUtils.isEmpty(pageInfoList.getResult())){
						List<PageNewsInfoVO> subNewsInfoList = new ArrayList<>();
						for(PageNewsInfoRespDTO infoRespDTO : pageInfoList.getResult()){
							PageNewsInfoVO infoVO = new PageNewsInfoVO();
							BeanUtils.copyProperties(infoRespDTO, infoVO);
							subNewsInfoList.add(infoVO);
							//设置文本路劲
							String docUrl = ImageUtil.getStaticDocUrl(infoRespDTO.getVfsId(), "html");
							infoRespDTO.setInfoUrl(docUrl);
						}
						newsInfoVO.setNewsInfoList(subNewsInfoList);
					}
					resultList.add(newsInfoVO);
				}
			}
			model.addAttribute("resultList", resultList);
		} catch (Exception e) {
			log.error("查询首页导航信息信息异常：" + e.getMessage());
		}
		return "home/rightNewslist :: #news_list";
	}
	protected String getJsonCallback(Map<String, Object> map,HttpServletRequest request){
		String callback = request.getParameter("jsonpCallback");

		try{
			if(StringUtils.isBlank(callback)){
				return new ObjectMapper().writeValueAsString(map);
			}else{
				return callback+"("+new ObjectMapper().writeValueAsString(map)+")";
			}
		}catch (Exception e) {
			throw new RuntimeException("JsonUtil.toJSONString发生错误", e);
		}
	}

	/**
	 * 帮助中心
	 */
	@RequestMapping(value="/ihaveData")
	private String IhaveData(){
		String viewName = "ihaveData";
		return viewName;
	}
}