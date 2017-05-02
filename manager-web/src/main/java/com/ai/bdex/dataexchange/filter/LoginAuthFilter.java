package com.ai.bdex.dataexchange.filter;

import com.ai.bdex.dataexchange.system.StaffUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public class LoginAuthFilter implements Filter {

    public String[] IGNORE_SUFFIX = {};// 忽略的访问后缀
    public static  String loginPage = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String ignore_suffix = filterConfig.getInitParameter("ignore_suffix");
        String login_page = filterConfig.getInitParameter("login_page");
        if(!"".equals(ignore_suffix)) {
            IGNORE_SUFFIX = filterConfig.getInitParameter("ignore_suffix").split(",");
        }

        if(!"".equals(login_page)){
            loginPage = login_page;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LogFactory.getLog(LoginAuthFilter.class).info("receive url:"+request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 过滤不需要权限控制的Url
        if (shouldFilter(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //当前登录用户
        StaffInfoDTO staffInfo = StaffUtil.getStaffVO(session);

        if(staffInfo != null && staffInfo.isLoginIn()){
            //已登陆
            filterChain.doFilter(request, response);
            return;
        }

        //未登陆的跳转到登陆界面
        // 如果是AJAX请求，则返回json格式提示未登陆
        if (isAjaxRequest(request)) {
            response.getWriter().write("{errorCode:\"999999\",\"errorMsg\":\"未登陆\"}");
            return;
        }

        //否则返回登陆界面
        response.sendRedirect(this.loginPage);
    }

    /**
     * 过滤指定后缀文件不走Filter
     *
     * @param request
     * @return
     */
    private boolean shouldFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        //过滤后缀
        for (String suffix : IGNORE_SUFFIX) {
            if (uri!= null && uri.toLowerCase().endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否是Ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {

        String requestedWith = request.getHeader("x-requested-with");
        // 表示是一个AJAX POST请求
        if ("XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
