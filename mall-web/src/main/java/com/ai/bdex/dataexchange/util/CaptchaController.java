package com.ai.bdex.dataexchange.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.paas.captcha.CaptchaServlet;
import com.ai.paas.util.CacheUtil;
  
@Controller  
@RequestMapping(value="/captcha")
public class CaptchaController{
  
	@RequestMapping(value="/captcha",method=RequestMethod.GET)
    public ModelAndView viewKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		CaptchaUtil captcha = new CaptchaUtil();
//		captcha.genCaptcha(request, response);
		HttpSession session = request.getSession();
		StaffInfoDTO info = StaffUtil.getStaffVO(session);
		String code = CaptchaServlet.getCaptchaCode(request);
		CacheUtil.addItem("123", "ozou",60);
		System.out.println(code);
		return null;
	}  
  
}  