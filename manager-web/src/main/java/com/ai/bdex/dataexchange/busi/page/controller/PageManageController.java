package com.ai.bdex.dataexchange.busi.page.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
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
@RequestMapping(value = "/pageManage")
public class PageManageController {

	private final static String STATUS_VALID = "1";// 有效
	private static final Logger log = LoggerFactory.getLogger(PageManageController.class);

	@DubboConsumer(timeout = 30000)
	IPageDisplayRSV iPageDisplayRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	
	@RequestMapping(value = "/pageInit")
	public ModelAndView pageInit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("info_details");
		return modelAndView;
	}

	/**
	 * 查询新闻资讯信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryNewsPageInfo")
	@ResponseBody
	public Map<String, Object> queryNewsPageInfo(Model model, HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String infoTitle = request.getParameter("infoTitle");
		String status = request.getParameter("status");
		String infotype = request.getParameter("infotype");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageNewsInfoReqDTO sortInfoDTO = new PageNewsInfoReqDTO();
			if(!StringUtils.isBlank(pageNo)){
				sortInfoDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(!StringUtils.isBlank(pageSize)){
				sortInfoDTO.setPageSize(Integer.valueOf(pageSize));
			}
			if(!StringUtils.isBlank(infoTitle)){
				sortInfoDTO.setInfoTitle(infoTitle);
			}
			if(!StringUtils.isBlank(status)){
				sortInfoDTO.setStatus(status);
			}
			if(!StringUtils.isBlank(infotype)){
				sortInfoDTO.setInfoType(infotype);
			}
			if(!StringUtils.isBlank(pageSize)){
				sortInfoDTO.setPageSize(Integer.valueOf(pageSize));
			}else{
				sortInfoDTO.setPageSize(10);
			}
			sortInfoDTO.setStatus(STATUS_VALID);
			PageResponseDTO<PageNewsInfoRespDTO> newsInfoPageInfo = iPageDisplayRSV.queryPageNewsInfoList(sortInfoDTO);
			if(!CollectionUtils.isEmpty(newsInfoPageInfo.getResult())){
				for(PageNewsInfoRespDTO newsInfoRespDTO: newsInfoPageInfo.getResult()){
					if("1".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("行业资讯");
					}else if("2".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("平台公告");
					}else if("3".equals(newsInfoRespDTO.getInfoType())){
						newsInfoRespDTO.setInfoType("平台活动");
					}else{
						newsInfoRespDTO.setInfoType("常见问题");
					}
				}
			}
			rMap.put("pageInfo", newsInfoPageInfo);
			rMap.put("infoTitle", infoTitle);
			rMap.put("status", status);
			rMap.put("infotype", infotype);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("查询新闻资讯信息出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/addNewsPageInfo")
	public String addNewsPageInfo(Model model, HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		try {
			if(!StringUtils.isBlank(infoId)){
				PageNewsInfoRespDTO newsInfo = iPageDisplayRSV.queryPageNewsInfoById(Integer.valueOf(infoId));
				request.setAttribute("newsInfo", newsInfo);
			}
		} catch (Exception e) {
			log.error("新闻资讯信息新增出错：" + e.getMessage());
		}
		return "page/newsInfo-add";
	} 
	@RequestMapping(value="/updateNewsPageInfo")
	@ResponseBody
	public Map<String,Object> updateNewsPageInfo(HttpServletRequest request){
		Map<String,Object>  rMap = new HashMap<>();
		String infoId = request.getParameter("infoId");
		String status = request.getParameter("status");
		try {
			PageNewsInfoReqDTO newsInfoReqDTO = new PageNewsInfoReqDTO();
			if(!StringUtils.isBlank(status)){
				newsInfoReqDTO.setStatus(status);
			}
			if(!StringUtils.isBlank(infoId)){
				newsInfoReqDTO.setInfoId(Integer.valueOf(infoId));
			}
			long infoByKey = iPageDisplayRSV.updatePageNewsInfoByKey(newsInfoReqDTO);
			rMap.put("infoByKey", infoByKey);
		} catch (Exception e) {
			log.error("新闻资讯信息删除出错：" + e.getMessage());
		}
		return rMap;
	}
	@RequestMapping(value = "/moduleManage")
	public ModelAndView moduleManage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("module_manager");
		return modelAndView;
	}
	@RequestMapping(value = "/queryModuleList")
	@ResponseBody
	public Map<String, Object> queryModuleList(Model model, HttpServletRequest request) {
		String moduleName = request.getParameter("moduleName");
		Map<String, Object> rMap = new HashMap<String, Object>();
		try {
			PageModuleReqDTO pageModuleReqDTO = new PageModuleReqDTO();
			pageModuleReqDTO.setStatus(STATUS_VALID);
			List<PageModuleRespDTO> moduleList = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO);
			if(!CollectionUtils.isEmpty(moduleList)){
				for(PageModuleRespDTO moduleRespDTO : moduleList){
					if(moduleRespDTO.getModulePid() != -1){
						pageModuleReqDTO.setModuleId(moduleRespDTO.getModulePid());
						PageModuleRespDTO respDTO = iPageDisplayRSV.queryPageModuleList(pageModuleReqDTO).get(0);
						if(respDTO != null){
							moduleRespDTO.setRemark(respDTO.getModuleName());
						}
					}
				}
			}
			rMap.put("moduleList", moduleList);
			rMap.put("moduleName", moduleName);
			rMap.put("success",true);
		} catch (Exception e) {
			rMap.put("success",false);
			log.error("查询楼层信息出错：" + e.getMessage());
		}
		return rMap;
	}
}