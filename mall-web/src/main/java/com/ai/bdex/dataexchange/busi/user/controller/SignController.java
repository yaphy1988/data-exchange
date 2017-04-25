package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.bdex.dataexchange.busi.user.entity.UserSignVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SignInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ISmsSeccodeRSV;
import com.ai.paas.util.CacheUtil;

@Controller
@RequestMapping(value="/regist")
public class SignController {
	private static final Logger log = LoggerFactory.getLogger(SignController.class);
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
	@DubboConsumer
	private IAuthStaffRSV iAuthStaffRSV;
	
	@DubboConsumer
	private ISmsSeccodeRSV iSmsSeccodeRSV;
	
	/**
	 * 注册页面初始化
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/pageInit")
	public String pageInit(Model model){
		return "register";
	}
	
	/**
	 * 发送激活邮件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/dosign")
	public Map<String,Object> dosign(Model model,UserSignVO signvo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		SignInfoDTO signInfo = new SignInfoDTO();
		BeanUtils.copyProperties(signvo, signInfo);
		try {
			iAuthStaffRSV.sendEmalForActive(signInfo);
			rMap.put("success", true);
			rMap.put("msg", "激活邮件已发送至"+signvo.getEmail()+",请及时激活！");
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
	public String doactive(Model model,String signId,String code){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			result = iAuthStaffRSV.doActiveByEmail(signId, code);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		model.addAttribute("success", result.get("success"));
		model.addAttribute("msg", result.get("msg"));
		return "sign/activeresult";
		
	}
	
	/**
	 * 短信验证码注册
	 */
	@RequestMapping(value="/savesign",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> savesign(Model model,UserSignVO signvo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		//校验短信验证码
		SmsSeccodeReqDTO smsDto = new SmsSeccodeReqDTO();
		smsDto.setTocken(signvo.getTocken());
		smsDto.setPhoneNo(signvo.getPhoneNo());
		smsDto.setInputSecurityCode(signvo.getSmsCode());
		boolean codeFlag = true;
		try {
//			codeFlag = iSmsSeccodeRSV.checkSmsSecCode(smsDto);
			if(codeFlag){
				//查询用户名是否重复
				AuthStaffDTO input1 = new AuthStaffDTO();
				input1.setStaffId(signvo.getStaffId());
				AuthStaffDTO phoneResult1 = iAuthStaffRSV.findAuthStaffInfo(input1);
				if(phoneResult1!=null){
					rMap.put("success", false);
					rMap.put("msg", "用户ID已经存在");
					return rMap;
				}
				//查询手机号是否重复
				AuthStaffDTO input = new AuthStaffDTO();
				input.setSerialNumber(signvo.getSerialNumber());
				AuthStaffDTO phoneResult = iAuthStaffRSV.findAuthStaffInfo(input);
				if(phoneResult!=null){
					rMap.put("success", false);
					rMap.put("msg", "手机号已经存在");
					return rMap;
				}
				SignInfoDTO info = new SignInfoDTO();
				BeanUtils.copyProperties(signvo, info);
				iAuthStaffRSV.saveSignInfoBysms(info);
				rMap.put("success", true);
				rMap.put("msg", "注册成功");
			}else{
				rMap.put("success", false);
				rMap.put("msg", "验证码不正确");
			}
		} catch (BusinessException e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
}
