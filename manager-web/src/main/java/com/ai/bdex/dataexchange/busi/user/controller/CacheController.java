package com.ai.bdex.dataexchange.busi.user.controller;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseLoginUrlDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthBusiRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value="/cache")
public class CacheController {

	private static final Logger log = LoggerFactory.getLogger(CacheController.class);

	private static String UN_LOGIN_URL = "UN_LOGIN_URL";
	private static String STAFF_AUTH_MENU = "STAFF_AUTH_MENU";

	@DubboConsumer
	private IAuthBusiRSV iAuthBusiRSV;

	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/init")
	public String init(HttpServletRequest request,Model model) throws Exception{

		String busiType = request.getParameter("busiType");
		//刷新免登陆URL缓存
		if(UN_LOGIN_URL.equals(busiType)){
			this.loadUnLoginUrlToCache(model);
		}
		//刷新用户角色菜单权限
		if(STAFF_AUTH_MENU.equals(busiType)){
			this.loadRoleAuthMenuCache(model);
		}

		if(StringUtil.isBlank(busiType)){
			return "cache";
		}else{
			return "cache :: #loadResult";
		}
	}

	/**
	 * 刷新免登陆URL缓存
	 * @param model
     */
	private void loadUnLoginUrlToCache(Model model){
		try {
			//刷新免登陆Urls缓存
			int size = iAuthBusiRSV.loadUnLoginUrls();

			model.addAttribute("success",true);
			model.addAttribute("size",size);
		} catch (BusinessException e) {
			model.addAttribute("success",false);
			model.addAttribute("msg","加载失败："+ e.getMessage());
		}
	}

	/**
	 * 刷新用户角色菜单权限
	 * @param model
	 */
	private void loadRoleAuthMenuCache(Model model){
		try {
			//刷新用户角色菜单权限缓存
			int size = iAuthBusiRSV.loadRoleMenus();

			model.addAttribute("success",true);
			model.addAttribute("size",size);
		} catch (BusinessException e) {
			model.addAttribute("success",false);
			model.addAttribute("msg","加载失败："+ e.getMessage());
		}
	}

//	/**
//	 * 获取用户菜单
//	 * @param model
//	 * @param session
//	 * @return
//	 * @throws Exception
//     */
//	@RequestMapping(value="/loadsysmenu")
//	public String loadsysmenu(Model model,HttpSession session) throws Exception{
//
//		//从session 获取
//		List<MenuDisPlayDTO> staffAuthMenus = StaffUtil.getStaffMenus(session);
//		if(staffAuthMenus == null){
//			//从缓存获取
//			String staffId = StaffUtil.getStaffId(session);
//			staffAuthMenus = iAuthBusiRSV.getStaffAuthMenus(staffId);
//			StaffUtil.setStaffMenus(session,staffAuthMenus);
//		}
//
//		model.addAttribute("staffAuthMenus",staffAuthMenus);
//
//		return "fragments/nav_left :: #systemmenu";
//	}
}