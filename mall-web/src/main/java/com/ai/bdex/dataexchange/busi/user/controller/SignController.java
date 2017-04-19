package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.bdex.dataexchange.busi.login.controller.LoginController;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;

@Controller
@RequestMapping(value="/sign")
public class SignController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IAuthStaffRSV iAuthStaffRSV;
	
	/**
	 * 注册页面初始化
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/pageInit")
	public String pageInit(HttpServletRequest request,
			HttpServletResponse response){
		return "sign/signInfo";
	}
	
	/**
	 * 发送激活邮件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/dosign")
	public Map<String,Object> dosign(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> rMap = new HashMap<String,Object>();
		SignInfoDTO signInfo = new SignInfoDTO();
		String staffId = request.getParameter("staffId");
		String email = request.getParameter("email");
		String signPass = request.getParameter("signPass");
		String confirmPass = request.getParameter("confirmPass");
		signInfo.setStaffId(staffId);
		signInfo.setEmail(email);
		signInfo.setSignpass(signPass);
		signInfo.setConfirmPass(confirmPass);
		try {
			iAuthStaffRSV.sendEmalForActive(signInfo);
			rMap.put("success", true);
			rMap.put("msg", "激活邮件已发送至"+email+",请及时激活！");
		} catch (BusinessException e){
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 激活操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/doactive")
	public String doactive(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		String signId = request.getParameter("signid");
		String code = request.getParameter("code");
		try {
			result = iAuthStaffRSV.doActiveByEmail(signId, code);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		request.setAttribute("success", result.get("success"));
		request.setAttribute("msg", result.get("msg"));
		return "sign/activeresult";
		
	}
}
