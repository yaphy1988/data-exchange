package com.ai.bdex.dataexchange.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.bdex.dataexchange.thymeleaf.ToolExpressionDialect;
import com.ai.paas.session.filter.CacheSessionFilter;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * @author xiongqian
 * @since 2017/5/4
 */
@Configuration
public class FilterConfiguration {
    private static Logger logger = LoggerFactory.getLogger(FilterConfiguration.class);

    @Value("${application.filter.enabled:true}")
    private boolean filterEnabled;

    @Value("${application.filter.ignoreSuffix:.ico,.swf,.flv,.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm,.eot,.svg,.ttf,.woff,.mp4,.woff2,.map}")
    private String ignoreSuffix;

    @Value("${application.alipay.aliGateway:'https://openapi.alipay.com/gateway.do'}")
    private  String GATE_WAY_URL;
    
    @Value("${application.alipay.appId:}")
    private  String APPID;
    
    @Value("${application.alipay.privateKey:}")
    private  String APP_PRIVATE_KEY;
    
    @Value("${application.alipay.format:}")
    private  String FORMAT;
    
    @Value("${application.alipay.charset:}")
    private  String CHARSET;
    
    @Value("${application.alipay.alipayPulicKey:}")
    private  String ALIPAY_PUBLIC_KEY;
    
    @Value("${application.alipay.signType:}")
    private  String SIGNTYPE;
    
    
    
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

    @Bean
    @ConditionalOnMissingBean
    public ToolExpressionDialect toolExpressionDialect() {
        return new ToolExpressionDialect();
    }
    @Bean(name="alipayClient")
    public AlipayClient getAlipayClient() {
    	DefaultAlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY_URL, APPID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,SIGNTYPE);
    	return alipayClient;
    }
}