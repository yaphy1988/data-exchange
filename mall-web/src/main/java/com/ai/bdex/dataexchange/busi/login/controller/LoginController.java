package com.ai.bdex.dataexchange.busi.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ILoginRSV;
import com.ai.bdex.dataexchange.util.CaptchaUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.utils.InetTool;
import com.ai.paas.utils.SignUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@DubboConsumer
	private ILoginRSV iLoginRSV;
	
	@RequestMapping(value="/pageInit")
	public String pageInit(Model model){
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
	public Map<String,Object> dologin(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();
		LoginInfoDTO loginInfo = null;
		String staffId = null;
		String ip = InetTool.getClientAddr(request);

		staffId = request.getParameter("staffId");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		String veriCodeInSession = CaptchaUtil.getCaptchaCode(request);
//		验证码注释
		if (!CaptchaUtil.verifyCaptcha(request, verifyCode)) {
			rMap.put("success", false);
			rMap.put("errorMsg", "验证码输入错误");
			return rMap;
		}

		loginInfo = new LoginInfoDTO();
		loginInfo.setLoginName(staffId);
		loginInfo.setLoginPwd(SignUtil.SHA1(password));
		loginInfo.setInputVerifyCode(verifyCode);
		loginInfo.setSessionVerifyCode(veriCodeInSession);
		loginInfo.setLoginIp(ip);
		try {
			//先校验登录，登录成功再进行其他业务逻辑判断
            Map<String,Object> map = loginVerify_new(loginInfo,response,request,session);
            StaffInfoDTO staffInfoVO = (StaffInfoDTO) map.get("staffInfoDTO");
			rMap.put("success", true);
			rMap.put("data", staffInfoVO);
		} catch (Exception e) {
			rMap.put("success", false);
			rMap.put("errorMsg",e.getMessage());
		}
		return rMap;
	}
	
	
	/**
	 * 调用登录接口校验是否能登录
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> loginVerify_new(LoginInfoDTO loginInfo,HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception{
		StaffInfoDTO staffInfoVO = null;
		staffInfoVO = iLoginRSV.loginVerify(loginInfo);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
//			result = saveStaffInfotoSession(staffInfoVO, response,request,session);
			iLoginRSV.updateLastLogin(staffInfoVO.getStaffId());
		}catch (Exception e){
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 将staffInfoDTO存入session
	 * @return
	 * @throws Exception 
	 */
	
	public Map<String,Object> saveStaffInfotoSession(StaffInfoDTO staffInfoDTO,HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//存入用户上次登录信息Cookie，时间为一周
		StaffUtil.addStaffCookie(request, response, staffInfoDTO.getStaffId());
		
		log.debug("staffInfoDTO:"+staffInfoDTO.toString());
		//存入session
		StaffUtil.setStaffInfo(session, staffInfoDTO);
		map.put("staffInfoDTO", staffInfoDTO);
		return map;
	}
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "/doLogout")
	@ResponseBody
	public Map<String,Object> doLogout(HttpServletResponse response,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			StaffUtil.removeStaffInfo(session);
			map.put("success", true);
			map.put("msg", "注销成功");
		} catch (Exception e) {
			log.error("系统异常");
			map.put("success", false);
			map.put("msg", "系统异常");
		}
		return map;
	}

}
