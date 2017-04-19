package com.ai.bdex.dataexchange.busi.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/pageInit")
	public String pageInit(HttpServletRequest request,
			HttpServletResponse response){
		return "/login/login";
	}
	
	@RequestMapping(value="/dologin")
	public void dologin(HttpServletRequest request,
			HttpServletResponse response){
		
	}

}
