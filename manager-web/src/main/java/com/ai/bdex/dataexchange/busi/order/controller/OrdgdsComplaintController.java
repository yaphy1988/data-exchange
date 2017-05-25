package com.ai.bdex.dataexchange.busi.order.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/pageInit")
	public ModelAndView pageInit(){
		ModelAndView modelAndView = new ModelAndView("goods_complaint");
		return modelAndView;
	}
}
