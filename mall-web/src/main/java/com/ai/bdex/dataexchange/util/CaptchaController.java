package com.ai.bdex.dataexchange.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ai.paas.captcha.CaptchaServlet;
  
@Controller  
@RequestMapping(value="/captcha")
public class CaptchaController{
  
	@RequestMapping(value="captcha",method=RequestMethod.GET)
    public ModelAndView viewKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaptchaServlet captcha = new CaptchaServlet();
		captcha.genCaptcha(request, response);
		return null;
	}  
  
}  