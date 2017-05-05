package com.ai.bdex.dataexchange.util;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.paas.session.impl.SessionManager;
import com.ai.paas.util.Utils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaffUtil {
	private static Logger logger = Logger.getLogger(StaffUtil.class);
	/**
	 * 用户登录名Cookie
	 */
	private final static String STAFF_ID = "StaffLoginInfo";
	
	/**
	 * Cookie有效期--一周
	 */
	private final static int EXPIRY = 60*60*24*7;
	
	/**人员信息*/
	public final static String STAFF_INFO = "staffInfoDTO";
	
	/**
	 * 存入staffId到cookie中，有效时间一周
	 * @param response
	 * @param staffId
	 */
	public static void addStaffCookie(HttpServletRequest request, HttpServletResponse response,String staffId){
		
		SessionManager sessionManager=(SessionManager)(Utils.getBean("sessionManager"));
    	sessionManager.addCookie(request, response, STAFF_ID, staffId);
    
    	
		/*Cookie cookie = new Cookie(STAFF_ID, staffId);
		cookie.setMaxAge(EXPIRY);
		cookie.setPath("/");
		response.addCookie(cookie);*/
	}
	
	/**
	 * 存入用户信息到session中
	 * @param session
	 * @param staff
	 */
	public static void setStaffInfo(HttpSession session,StaffInfoDTO staff){
		session.setAttribute(STAFF_INFO,staff);
	}
	
	/**
	 * 删除用户登录的session信息
	 * @param session
	 */
	public static void removeStaffInfo(HttpSession session){
		session.removeAttribute(STAFF_INFO);
		//统一设置：session失效；
		session.invalidate();
	}
	
	/**
	 * 登录人员VO in session
	 * @param session
	 * @return
	 */
	public static StaffInfoDTO getStaffVO(HttpSession session){
		Object obj = session.getAttribute(STAFF_INFO);
		if(obj == null){
			return new StaffInfoDTO();
		}
		return (StaffInfoDTO)obj;
	}
	
	public static String getStaffId(HttpSession session){
		return getStaffVO(session).getStaffId();
	}

}
