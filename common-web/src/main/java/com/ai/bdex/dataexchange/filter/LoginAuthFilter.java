package com.ai.bdex.dataexchange.filter;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthBusiRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ILoginRSV;
import com.ai.bdex.dataexchange.util.StaffLocaleUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.config.ModuleInfo;
import com.ai.paas.session.impl.SessionManager;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.SystemConfUtil;
import com.ai.paas.util.Utils;
import com.ai.paas.utils.InetTool;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(LoginAuthFilter.class);

    @Value("${application.filter.enabled:true}")
    private boolean filterEnabled;

    @Value("${application.filter.ignoreSuffix:.ico,.swf,.flv,.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm,.eot,.svg,.ttf,.woff,.mp4,.woff2,.map}")
    private String ignoreSuffix;

    @Value("${application.filter.unloginflag:false}")
    private boolean unLoginFlag;

    public String[] IGNORE_SUFFIX = {};// 忽略的访问后缀
    public String loginPage = "/login/pageInit";
    public String logoutPage = "/login/doLogout";
    public String noAuthPage = "/login/noAuth";
    public String capthcaImage = "/captcha/CapthcaImage";
    public String homePage = "/homePage/pageInit";

    public static String remember_Paas_CookieKey = "REMEMBER_BUSY_COOKIE";
    public static String remember_PaasKey_Pre = "REM_PAAS_";

    @DubboConsumer
    private ILoginRSV iLoginRSV;

    @DubboConsumer
    private IAuthBusiRSV iAuthBusiRSV;

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //忽略的访问后缀
        if(StringUtils.isBlank(ignoreSuffix) == false) {
            IGNORE_SUFFIX = ignoreSuffix.split(",");
        }
    }

    /**
     * 处理请求
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        //CORS预检请求
        String reqMethod = request.getMethod();
        if("OPTIONS".equalsIgnoreCase(reqMethod) && request.getHeader("Origin") != null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //请求uri
        String uri =request.getServletPath();
        log.info("receive url:"+uri);
        //商城根路径
        String mallDomain = SystemConfUtil.getSystemModuleInfo("01","1").genFullUrl();

        //跳转首页
        if(StringUtil.isBlank(uri) || "/".equals(uri.trim())){
            response.sendRedirect(mallDomain+this.homePage);
            return;
        }

        // 过滤不需要权限控制的请求后缀,或者配置免登陆的url
        if (shouldFilter(request,uri) || filterEnabled == false) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //当前登录用户
        StaffInfoDTO staffInfo = StaffUtil.getStaffVO(session);
        if(staffInfo != null && staffInfo.isLoginIn()){
            //设置Staffid到ThreadLocal
            StaffLocaleUtil.setCurrentStaffId(staffInfo.getStaffId());

            //通过记住密码登陆，且url配置成需确认登陆的，则跳转到登陆界面
            if("2".equals(staffInfo.getLoginType()) && unLoginUrl(uri,"2")) {
                response.sendRedirect(mallDomain+this.loginPage+"?toPage="+ LoginAuthFilter.getRequestUrl(request));
                return;
            }

            //剩下的看是否有菜单权限
            boolean hasMenuAuth = false;
            if(this.unLoginFlag == false) {
                String reqUrl = getRequestUrl(request);
                String refererUrl = request.getHeader("referer");
                List<String> staffAuthMenus = staffInfo.getMenuUrls();
                if (staffAuthMenus != null && staffAuthMenus.size() > 0) {
                    for (String menuUrl : staffAuthMenus) {
                        if (reqUrl.startsWith(menuUrl) || (isAjaxRequest(request) && refererUrl.startsWith(menuUrl))) {
                            hasMenuAuth = true;
                            break;
                        }
                    }
                }

                //没有菜单权限，且不是免登陆配置的url，则返回没有权限
                if(hasMenuAuth == false && unLoginUrl(uri,"1") == false){
                    if(isAjaxRequest(request)){
                        //ajax请求的referer 需要登录，则不允许ajax访问
                        response.getWriter().write("{errorCode:\"999999\",\"errorMsg\":\"noAuth\"}");
                        return;
                    }else{
                        response.sendRedirect(mallDomain+this.noAuthPage);
                        return;
                    }
                }
            }
        }else{
            //校验是否记住密码且当前请求无需确认登陆
            boolean isRememberPaas = isRememberPaas(request,response);
            boolean needLogin = false;
            boolean noAuth = false;

            String authUrl = "";
            // AJAX请求，校验的是它的referer URL
            if (isAjaxRequest(request)) {
                //获取referer URL
                String refererUrl = request.getHeader("referer");
                String requestUrl = request.getRequestURL().toString();
                String host = requestUrl.replace(uri,"");
                String refererUri = refererUrl.replace(host,"");

                authUrl = refererUri;
            }else{
                authUrl = uri;
            }

            //已记住密码登陆
            if(isRememberPaas == true){
                if(unLoginUrl(authUrl,"2")) {
                    //需要强制登陆
                    needLogin = true;
                }else {
                    //如果是管理界面，则需要看是否配置了登陆可访问，如果没有配置则提示无权限
                    if (this.unLoginFlag == false && unLoginUrl(authUrl, "1") == false) {
                        noAuth = true;
                    }
                }
            }else{
                if(unLoginUrl(authUrl, "1") == true){
                    needLogin = true;
                }

                if(this.unLoginFlag == false && unLoginUrl(authUrl, "0") == false){
                    //  重新编译                 
				   needLogin = true;
                }
            }

            //没有权限
            if(noAuth){
                if(isAjaxRequest(request)){
                    response.getWriter().write("{errorCode:\"999999\",\"errorMsg\":\"noAuth\"}");
                    return;
                }else{
                    response.sendRedirect(mallDomain+this.noAuthPage);
                    return;
                }
            }
            //需要登录
            if(needLogin){
                if(isAjaxRequest(request)){
                    response.getWriter().write("{errorCode:\"999999\",\"errorMsg\":\"needLogin\"}");
                    return;
                }else{
                    response.sendRedirect(mallDomain+this.loginPage+"?toPage="+ LoginAuthFilter.getRequestUrl(request));
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
        return;
    }

    /**
     * 过滤指定后缀文件不走Filter
     *
     * @param request
     * @return
     */
    private boolean shouldFilter(HttpServletRequest request,String uri) {
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

        //提示没有权限
        if (uri.endsWith(this.noAuthPage)) {
            return true;
        }
        //验证码
        if(uri.endsWith(capthcaImage)){
            return true;
        }

        //免登陆
        if(unLoginUrl(uri,"0") == true) {
            return true;
        }

        return false;
    }

    /**
     * 免登陆控制
     * @param unloginAccess （0：免登陆，1：登录访问，2：记住密码登录时还需要登录）
     * @return
     */
    private boolean unLoginUrl(String uri,String unloginAccess){

        //获取当前子系统编码
        SessionManager sessionManager = Utils.getInstance(SessionManager.class);
        String systemCode = sessionManager.getSystemCode();

        //保存url的key
        String key = Constants.Cache.UN_LOGIN_URL_PRE + systemCode + "_" + uri.trim();

        String redisloginAccess = CacheUtil.getMapItem(Constants.Cache.UN_LOGIN_URL_MAP,key);

        if(unloginAccess.equals(redisloginAccess)){
            return true;
        }

        //如果是登录访问，那么1,2都满足条件
        if("1".equals(unloginAccess)){
            if("1".equals(redisloginAccess) || "2".equals(redisloginAccess)){
                return true;
            }
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
                        StaffInfoDTO staffInfoVO = LoginAuthFilter.loginVerify(request,response,loginInfo,iLoginRSV,iAuthBusiRSV,"2");
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
    public static StaffInfoDTO loginVerify(HttpServletRequest request,
                                           HttpServletResponse response,
                                           LoginInfoDTO loginInfo,
                                           ILoginRSV iLoginRSV,
                                           IAuthBusiRSV iAuthBusiRSV,
                                           String loginType) throws Exception{

        //校验登陆
        StaffInfoDTO staffInfoVO = iLoginRSV.loginVerify(loginInfo);

        if(staffInfoVO != null && staffInfoVO.isLoginIn()){
            //登陆成功
            staffInfoVO.setLoginType(loginType);//登陆类型（1：用户手动登陆，2：记住密码系统自动登陆）

            //更新登陆时间
            iLoginRSV.updateLastLogin(staffInfoVO.getStaffId());

            //保存用户ID带cookie
            StaffUtil.addStaffCookie(request, response, staffInfoVO.getStaffId());

            //设置菜单
            try {
                List<MenuDisPlayDTO> staffAuthMenus = iAuthBusiRSV.getStaffAuthMenus(staffInfoVO.getStaffId());
                if(staffAuthMenus == null){
                    staffAuthMenus = new ArrayList<MenuDisPlayDTO>();
                }

                //设置菜单url,用于权限校验
                staffInfoVO.setMenuUrls(new ArrayList<String>());
                setAuthUrls(staffAuthMenus,staffInfoVO);
                //设置首页点击进去的时候打开的url
                if(staffInfoVO.getMenuUrls() != null && staffInfoVO.getMenuUrls().size()>0){
                    request.getSession().setAttribute("menuDisPlayFirstUrl",staffInfoVO.getMenuUrls().get(0));
                }
                //设置展示的菜单
                StaffUtil.setStaffMenus(request.getSession(), staffAuthMenus);

            }catch (BusinessException bex){
                LogFactory.getLog(LoginAuthFilter.class).error("获取菜单异常:"+bex.getMessage());
                bex.printStackTrace();
            }

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

    /**
     * 递归获取菜单urls
     * @param menus
     * @param staffInfoVO
     */
    private static void setAuthUrls(List<MenuDisPlayDTO> menus, StaffInfoDTO staffInfoVO){
        if(menus != null && menus.size()>0){

            for (MenuDisPlayDTO menu:menus) {

                //设置菜单全路径
                if(StringUtils.isBlank(menu.getRelativeMenuUrl()) == false) {
                    String absoluteMenuUrl = menu.getRelativeMenuUrl();
                    ModuleInfo moduleInfo = SystemConfUtil.getSystemModuleInfo(menu.getSysCode(), menu.getMenuModule());
                    if (moduleInfo != null) {
                        absoluteMenuUrl = moduleInfo.genFullUrl() + absoluteMenuUrl;
                    }
                    menu.setAbsoluteMenuUrl(absoluteMenuUrl);

                    //加入list 用于判断权限
                    staffInfoVO.getMenuUrls().add(menu.getAbsoluteMenuUrl());
                }

                //递归处理
                if(menu.getSubMenus() != null && menu.getSubMenus().size()>0){
                    setAuthUrls(menu.getSubMenus(),staffInfoVO);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
