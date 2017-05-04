package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.user.entity.AuthStaffVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.system.StaffUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping(value="/infomanager")
public class UserInfoManagerController {

	@DubboConsumer
	private IAuthStaffRSV iAuthStaffRSV;
	
	@RequestMapping(value="/pageInit")
	public String pageInit(Model model,HttpSession session) throws Exception{
		if(StringUtil.isBlank(StaffUtil.getStaffId(session))){
			return "redirect:/login/pageInit";
		}
		String staffId = StaffUtil.getStaffVO(session).getStaffId();
		//获取用户信息
		AuthStaffDTO input = new AuthStaffDTO();
		input.setStaffId(staffId);
		AuthStaffDTO authInfo = iAuthStaffRSV.findAuthStaffInfo(input);
		if(authInfo!=null){
			AuthStaffVO data = new AuthStaffVO();
			BeanUtils.copyProperties(authInfo, data);
			String vfsId = data.getHeadVfsid();
			if(!StringUtil.isBlank(vfsId)){
				data.setHeadSrc(ImageUtil.getImageUrl(vfsId+"_80x80!"));
			}
			model.addAttribute("userinfo", data);
		}
		return "personalCenter/userinfo";
	}
	
	/**
	 * 修改用户信息
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> modify(Model model,AuthStaffVO vo,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();
		AuthStaffDTO input = new AuthStaffDTO();
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		try {
			//校验用户名是否存在
			boolean nameFlag = this.iAuthStaffRSV.checkInfoByName(vo.getStaffName(), staffId);
			if(nameFlag){
				rMap.put("success", false);
				rMap.put("msg", "用户名已被使用！");
				return rMap;
			}
			nameFlag = this.iAuthStaffRSV.checkInfoByName(vo.getAliasName(), staffId);
			if(nameFlag){
				rMap.put("success", false);
				rMap.put("msg", "别名已被使用！");
				return rMap;
			}
			BeanUtils.copyProperties(vo, input);
			input.setStaffId(staffId);
			iAuthStaffRSV.updateAuthStaffInfo(input);
			rMap.put("success", true);
			rMap.put("msg", "修改成功！");
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 获取手机号
	 */
	@RequestMapping(value="/getphone",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updatephone(Model model,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		AuthStaffDTO input = new AuthStaffDTO();
		input.setStaffId(staffId);
		try {
			AuthStaffDTO result = this.iAuthStaffRSV.findAuthStaffInfo(input);
			if(result!=null){
				rMap.put("success", true);
				rMap.put("phoneNo", result.getSerialNumber());
			}
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 修改用户手机号
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/updatephone",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updatephone(Model model,String phoneNo,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();
		AuthStaffDTO input = new AuthStaffDTO();
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		if(!isMobile(phoneNo)){
			rMap.put("success", false);
			rMap.put("msg", "手机号格式不正确！");
			return rMap;
		}
		try {
			input.setSerialNumber(phoneNo);
			input.setStaffId(staffId);
			iAuthStaffRSV.updateMobilePhone(input);
			rMap.put("success", true);
			rMap.put("msg", "修改成功！");
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 校验手机号码
	 * @param mobileNo
	 * @return
	 */
	private boolean isMobile(String mobileNo) {
		if (!StringUtil.isBlank(mobileNo)) {
			 String reg = "^1[34578]\\d{9}$";//13.14.15.17.18这些号段
		     return Pattern.compile(reg).matcher(mobileNo).find();
		}

		return false;
	}
}
