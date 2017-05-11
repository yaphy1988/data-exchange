package com.ai.bdex.dataexchange.filter;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ILoginRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.session.impl.SessionManager;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.SystemConfUtil;
import com.ai.paas.util.Utils;
import com.ai.paas.utils.InetTool;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fangyunfeng on 2017/4/12.
 * Update by xiongqian
 */
@WebFilter(urlPatterns = "/*")
@Order(2)
public class LoginAuthFilter implements Filter {

    @Value("${application.filter.enabled:true}")
    private boolean filterEnabled;

    @Value("${application.filter.ignoreSuffix:.ico,.swf,.flv,.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm,.eot,.svg,.ttf,.woff,.mp4,.woff2,.map}")
    private String ignoreSuffix;

    @Value("${application.filter.unloginflag:false}")
    private boolean unLoginFlag;

    public String[] IGNORE_SUFFIX = {};// 忽略的访问后缀
    public String loginPage = "/login/pageInit";
    public String logoutPage = "/login/doLogout";

    public static String remember_Paas_CookieKey = "REMEMBER_BUSY_COOKIE";
    public static String remember_PaasKey_Pre = "REM_PAAS_";

    @DubboConsumer
    private ILoginRSV iLoginRSV;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //忽略的访问后缀
        if(StringUtils.isBlank(ignoreSuffix) == false) {
            IGNORE_SUFFIX = ignoreSuffix.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LogFactory.getLog(LoginAuthFilter.class).info("receive url:"+request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 过滤不需要权限控制的请求后缀
        if (shouldFilter(request) || filterEnabled == false) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //当前登录用户
        StaffInfoDTO staffInfo = StaffUtil.getStaffVO(session);
        String mallDomain = SystemConfUtil.getSystemModuleInfo("01","1").genFullUrl();
        if(staffInfo != null && staffInfo.isLoginIn()){
            //已登陆
            if("1".equals(staffInfo.getLoginType())) {
                //如果是用户手动登陆的，则允许访问
                filterChain.doFilter(request, response);
                return;
            }else{
                //如果是记住密码系统自动登陆的，且请求的url需要确认登陆，则跳转到登陆界面
                filterChain.doFilter(request, response);
                return;
            }

        }else{
            //未登陆
            // 如果是AJAX请求，则返回json格式提示未登陆
            if (isAjaxRequest(request)) {
                if(this.unLoginFlag == true) {
                    filterChain.doFilter(request, response);
                    return;
                }else{
                    //没开免登陆
                    response.getWriter().write("{errorCode:\"999999\",\"errorMsg\":\"unlogin\"}");
                    return;
                }
            }

            //非AJAX请求，则校验是否记住密码，记住的话，做登陆操作
            //如果记住密码，则跳转到自动登陆操作
            if(isRememberPaas(request,response) == true){
                filterChain.doFilter(request, response);
                return;
            }

            //url 配置在表 t_base_login_url
            if(this.unLoginFlag == true){
                //如果开启免登陆，则只校验需要登录的URL，
                //请求的url在t_base_login_url中则跳转到登陆界面
                //否则允许访问
                filterChain.doFilter(request, response);
                return;

            }else{
                //如果没有开启免登陆，则只校验不需要登录的URL
                //请求的url在t_base_login_url中且标识为免登陆则允许访问
                //否则不允许访问，跳转到登陆界面
            }
        }

        //其他返回登陆界面
        response.sendRedirect(mallDomain+this.loginPage+"?toPage="+ LoginAuthFilter.getRequestUrl(request));
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

        //如果是登录界面
        if (uri.endsWith(this.loginPage)) {
            return true;
        }
        //如果是登出界面
        if (uri.endsWith(this.logoutPage)) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否记住密码
     * @param request
     * @param response
     * @return
     */
    private boolean isRememberPaas(HttpServletRequest request,HttpServletResponse response){
        //获取记住密码的Cookie值
        List<String> cookieValues = getRequestedCookieValues(request,remember_Paas_CookieKey);

        if(cookieValues != null && cookieValues.size()>0){
            boolean ifLogin = false;
            try {

                String ip = InetTool.getClientAddr(request);
                for (String cookieVal:cookieValues) {
                    //根据Cookie值去redis查找用户id和加密的密码
                    Object staffStr = CacheUtil.getItem(LoginAuthFilter.remember_PaasKey_Pre+cookieVal);
                    if(staffStr == null || StringUtils.isBlank(staffStr.toString())) continue;
                    //获取用户id和加密的密码
                    String[] staffAuthStr = staffStr.toString().split(":");

                    if(staffAuthStr != null && staffAuthStr.length==2){
                        //校验登陆
                        String staffId = staffAuthStr[0];
                        String staffPaas = staffAuthStr[1];

                        LoginInfoDTO loginInfo = new LoginInfoDTO();
                        loginInfo.setLoginName(staffId);
                        loginInfo.setLoginPwd(staffPaas);
                        loginInfo.setLoginIp(ip);
                        //校验登录
                        StaffInfoDTO staffInfoVO = LoginAuthFilter.loginVerify(request,response,loginInfo,iLoginRSV,"2");
                        if(staffInfoVO != null && staffInfoVO.isLoginIn()){
                            ifLogin = true;
                            break;
                        }
                    }
                }
            }catch (Exception e){}

            if(ifLogin == true){
                return true;
            }else{
                //移除记住密码
                unRememberPaas(request,response);
                return false;
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

    /**
     * 根据cookie kye 获取cookie value
     * @param request
     * @param cookieKey
     * @return
     */
    public static List<String> getRequestedCookieValues(HttpServletRequest request,String cookieKey) {
        Cookie[] cookies = request.getCookies();
        List<String> cookieValues = new ArrayList<>();
        if ((cookies == null) || (cookies.length == 0))
            return null;
        for (Cookie cookie : cookies) {
            if (cookieKey.equals(cookie.getName()) && StringUtils.isBlank(cookie.getValue()) == false) {
                cookieValues.add(cookie.getValue());
            }
        }
        return cookieValues;
    }

    /**
     * 获取请求全路径
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request){
        String requestUrl = request.getRequestURL().toString();
        //请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<String> paramList = new ArrayList<String>();
        for (String key : parameterMap.keySet()) {
            String param = StringUtils.join(parameterMap.get(key), ",");
            try {
                String value = URLEncoder.encode(param, "UTF-8");
                paramList.add(key + "=" + value);
            } catch (Exception err) {
                LogFactory.getLog(LoginAuthFilter.class).error("encoding url :" + param + "; error;msg" + err.getMessage());
            }
        }

        String paramStr = "";
        if (paramList.size() != 0) {
            paramStr = StringUtils.join(paramList, "&");
        }
        if (StringUtils.isNotEmpty(paramStr)) {
            paramStr = "?"+paramStr;
        }

        try {
            requestUrl += URLEncoder.encode(paramStr, "UTF-8");
        } catch (Exception err) {
            LogFactory.getLog(LoginAuthFilter.class).error("encoding url :" + paramStr + "; error;msg" + err.getMessage());
        }

        return requestUrl;
    }

    /**
     *登陆校验
     * @param loginInfo
     * @return
     */
    public static StaffInfoDTO loginVerify(HttpServletRequest request,HttpServletResponse response,LoginInfoDTO loginInfo,ILoginRSV iLoginRSV,String loginType) throws Exception{

        //校验登陆
        StaffInfoDTO staffInfoVO = iLoginRSV.loginVerify(loginInfo);

        if(staffInfoVO != null && staffInfoVO.isLoginIn()){
            //登陆成功
            staffInfoVO.setLoginType(loginType);//登陆类型（1：用户手动登陆，2：记住密码系统自动登陆）

            //更新登陆时间
            iLoginRSV.updateLastLogin(staffInfoVO.getStaffId());

            //保存用户ID带cookie
            StaffUtil.addStaffCookie(request, response, staffInfoVO.getStaffId());

            //存入用户信息到session
            StaffUtil.setStaffInfo(request.getSession(), staffInfoVO);

            return staffInfoVO;
        }else{
            throw new Exception("登陆失败");
        }
    }

    /**
     * 记住密码
     * @param request
     * @param response
     * @param loginInfo
     */
    public static void rememberPaas(HttpServletRequest request, HttpServletResponse response,LoginInfoDTO loginInfo){
        //是否记住密码
        String rememberPaas = request.getParameter("rememberPaas");
        SessionManager sessionManager = Utils.getInstance(SessionManager.class);

        if("true".equals(rememberPaas)){
            int expiryTime = 10*24*60*60;//10天
            //cookie值
            String rememberPaasCookieValue = UUID.randomUUID().toString().replaceAll("-", "");
            //保存cookie到客户端
            LoginAuthFilter.addCookie(response, LoginAuthFilter.remember_Paas_CookieKey, rememberPaasCookieValue,expiryTime);
            //保存在服务端的cookie值对应的用户信息
            CacheUtil.addItem(LoginAuthFilter.remember_PaasKey_Pre+rememberPaasCookieValue,loginInfo.getLoginName()+":"+ loginInfo.getLoginPwd(),expiryTime);
        }else{
            //移除服务端记住密码的信息
            LoginAuthFilter.unRememberPaas(request,response);
        }
    }

    /**
     * 移除记住密码
     * @param request
     * @param response
     */
    public static void unRememberPaas(HttpServletRequest request, HttpServletResponse response){
        //移除服务端记住密码的信息
        List<String> cookieValues = LoginAuthFilter.getRequestedCookieValues(request,LoginAuthFilter.remember_Paas_CookieKey);
        if(cookieValues != null && cookieValues.size()>0) {
            for (String cookieVal : cookieValues) {
                CacheUtil.delItem(LoginAuthFilter.remember_PaasKey_Pre+cookieVal);
            }
            //移除客户端cookie
            LoginAuthFilter.addCookie(response, LoginAuthFilter.remember_Paas_CookieKey, "",0);
        }
    }

    /**
     * 写Cookie
     * @param response
     * @param key
     * @param value
     * @param expiry 过期时间
     */
    public static void addCookie(HttpServletResponse response, String key, String value ,int expiry) {

        String domain = Utils.getInstance(SessionManager.class).getDomain();

        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);

        StringBuilder cookie = new StringBuilder();
        cookie.append(key+"="+value+";");
        cookie.append("Path=/;");
        if (!StringUtils.isBlank(domain)){
            cookie.append("Domain="+domain+";");
        }
        if(expiry>0) {
            //IE 浏览器需要加这个
            cookie.append("Expires=" + df.format(new Date(System.currentTimeMillis() + expiry * 1000)) + ";");
        }
        cookie.append("Max-Age="+expiry);

        response.addHeader("Set-Cookie",cookie.toString());
    }

    @Override
    public void destroy() {

    }
}
