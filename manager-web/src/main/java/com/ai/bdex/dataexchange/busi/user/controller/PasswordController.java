package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.user.entity.PassWordVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.system.StaffUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffPassRSV;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping(value="/password")
public class PasswordController {
	
	@DubboConsumer
	private IAuthStaffPassRSV iAuthStaffPassRSV;

	@RequestMapping(value="/modify")
	public String modify(Model model,HttpSession session){
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			model.addAttribute("islogin", "0");
		}else{
			model.addAttribute("staffId",staffId);
			model.addAttribute("islogin", "1");
		}
		return "personalCenter/password_setting";
	}
	
	/**
	 * 修改密码
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/domodify",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> domodify(Model model,PassWordVO passWordVO){
		Map<String,Object> rMap = new HashMap<String,Object>();
		//校验原密码是否正确
		String staffId = passWordVO.getStaffId();
		String oldpass = passWordVO.getOldpasswd();
		AuthStaffPassDTO pass = new AuthStaffPassDTO();
		pass.setStaffId(staffId);
		pass.setStaffPasswd(oldpass);
		try {
			boolean validResult = this.iAuthStaffPassRSV.validPasswd(pass);
			if(validResult){
				//修改密码
				if(passWordVO.getNewpasswd().equals(passWordVO.getConfirmPass())){
					AuthStaffPassDTO input = new AuthStaffPassDTO();
					input.setStaffId(staffId);
					input.setStaffPasswd(passWordVO.getNewpasswd());
					iAuthStaffPassRSV.updatePasswd(input);
					rMap.put("success", true);
					rMap.put("msg", "修改成功");
				}else{
					rMap.put("success", false);
					rMap.put("msg", "两次输入的密码不一致！");
				}				
			}else{
				rMap.put("success", false);
				rMap.put("msg", "原密码错误！");
			}
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
}
