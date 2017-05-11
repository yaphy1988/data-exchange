package com.ai.bdex.dataexchange.busi.login.controller;

import com.ai.bdex.dataexchange.filter.LoginAuthFilter;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ILoginRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.captcha.CaptchaServlet;
import com.ai.paas.utils.InetTool;
import com.ai.paas.utils.SignUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@DubboConsumer
	private ILoginRSV iLoginRSV;

	@RequestMapping(value="/pageInit")
	public String pageInit(HttpServletRequest request) {
		return "login";
	}

	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dologin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();

		String ip = InetTool.getClientAddr(request);
        //请求参数
		String staffId = request.getParameter("staffId");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");

		//验证码注释
		if (!CaptchaServlet.verifyCaptcha(request, verifyCode)) {
			rMap.put("success", false);
			rMap.put("errorMsg", "验证码输入错误");
			return rMap;
		}

		//登陆校验
		LoginInfoDTO loginInfo = new LoginInfoDTO();
		loginInfo.setLoginName(staffId);
		loginInfo.setLoginPwd(SignUtil.SHA1(password));
		loginInfo.setLoginIp(ip);

		try {
			//先校验登录,重要重要重要：所有登陆后要写session的请在LoginAuthFilter.loginVerify写
			StaffInfoDTO staffInfoVO = LoginAuthFilter.loginVerify(request,response,loginInfo,iLoginRSV,"1");
			//记住密码
			LoginAuthFilter.rememberPaas(request, response,loginInfo);

			rMap.put("success", true);
			rMap.put("data", staffInfoVO);
		} catch (Exception e) {
			log.error("登录异常", e);
			rMap.put("success", false);
			rMap.put("errorMsg",e.getMessage());
		}
		return rMap;
	}

	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "/doLogout")
	public String doLogout(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		try {
			//移除用户
			StaffUtil.removeStaffInfo(session);
			//移除服务端记住密码的信息
			LoginAuthFilter.unRememberPaas(request,response);
		} catch (Exception e) {
			log.error("退出异常", e.getMessage());
		}
		return "redirect:/login/pageInit";
	}
}
