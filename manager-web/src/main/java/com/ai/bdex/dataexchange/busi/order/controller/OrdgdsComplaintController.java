package com.ai.bdex.dataexchange.busi.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.complaint.IOrdComplaintRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

/**
 * 订单商品投诉
 * Description: <br>
 * Date: 2017年5月25日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
@Controller
@RequestMapping(value = "/ordgdsComplaint")
public class OrdgdsComplaintController {
	@DubboConsumer(timeout=3000)
	private IOrdComplaintRSV iOrdComplaintRSV;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/pageInit")
	public ModelAndView pageInit(){
		ModelAndView modelAndView = new ModelAndView("goods_complaint");
		return modelAndView;
	}
	/**
	 * 查询我的订单投诉列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryComplaint")
	public String complaintPageInfo(HttpServletRequest request,Model model){
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
		try {
			if(!StringUtil.isBlank(pageNo)){
				ordComplaintReqDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(!StringUtil.isBlank(pageSize)){
				ordComplaintReqDTO.setPageSize(Integer.valueOf(pageSize));
			}else{
				ordComplaintReqDTO.setPageSize(10);
			}
			PageResponseDTO<OrdComplaintRespDTO> pageInfo = iOrdComplaintRSV.queryOrdComplaintPageInfo(ordComplaintReqDTO);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("[查询我的订单投诉异常]，异常信息："+e.getMessage());
		}
		return "goods_complaint :: #tab02";
	}
}
