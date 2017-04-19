package com.ai.bdex.dataexchange.system.filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenjy on 2017/4/17.
 */
public class LoginFilter implements Filter{

    private String loginPage = "/login/pageInit";

    private String mainPage = "/main/pageInit";

    private static String[] IGNORE_SUFFIX = {};

    private static String[] IGNORE_PAGES = {};

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {




    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
