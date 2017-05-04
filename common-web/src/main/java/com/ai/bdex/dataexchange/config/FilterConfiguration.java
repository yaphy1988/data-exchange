package com.ai.bdex.dataexchange.config;

import com.ai.bdex.dataexchange.filter.LoginAuthFilter;
import com.ai.paas.config.ConfigurationCenter;
import com.ai.paas.config.SystemConfiguration;
import com.ai.paas.config.SystemConfigurationImpl;
import com.ai.paas.config.autoconfigure.ConfigCenterAutoConfiguration;
import com.ai.paas.session.filter.CacheSessionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

/**
 * @author xiongqian
 * @since 2017/5/4
 */
@Configuration
public class FilterConfiguration {
    private static Logger logger = LoggerFactory.getLogger(FilterConfiguration.class);

    @Value("${application.filter.enabled:true}")
    private boolean filterEnabled;

    @Value("${application.filter.ignoreSuffix}")
    private String ignoreSuffix = ".ico,.swf,.flv,.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm,.eot,.svg,.ttf,.woff,.mp4,.woff2,.map";

    public FilterConfiguration() {
    }

    /**
     * session过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean SessionFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CacheSessionFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("ignore_suffix", ignoreSuffix);
        return filterRegistrationBean;
    }

    /**
     * 登陆过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new LoginAuthFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setEnabled(filterEnabled);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("ignore_suffix", ignoreSuffix);
        return filterRegistrationBean;
    }
}