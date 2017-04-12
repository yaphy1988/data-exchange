package com.ai.bdex.dataexchange.filter;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public class LoginAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        LogFactory.getLog(LoginAuthFilter.class).info("receive url:"+httpServletRequest.getRequestURI());
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
