package com.ai.bdex.dataexchange.config;

import com.ai.paas.config.ModuleInfo;
import com.ai.paas.config.SystemConfiguration;
import com.ai.paas.config.SystemInfo;
import com.ai.paas.config.autoconfigure.SystemSvAutoConfiguration;
import com.ai.paas.util.SystemConfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaphy on 2017/5/21.
 */
@Configuration
@AutoConfigureAfter(SystemSvAutoConfiguration.class)
@ConditionalOnBean(SystemConfiguration.class)
public class CustomCorsConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemConfiguration systemConfiguration;

    public CustomCorsConfiguration() {
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                ArrayList<String> allModule = new ArrayList();
                List<String> sysCodes = systemConfiguration.getSystemCodes();
                for (String sysCode : sysCodes) {
                    SystemInfo systemInfo = systemConfiguration.getSystemInfo(sysCode);
                    for (ModuleInfo moduleInfo : systemInfo.getModuleList()) {
                        allModule.add(moduleInfo.getDomainUrl());
                    }
                }

                String[] origins;
                if (allModule.size() > 0) {
                    String[] strings = new String[allModule.size()];
                    origins = allModule.toArray(strings);
                } else {
                    origins = new String[]{"*"};
                }

                registry.addMapping("/reportdata/**")
                        .allowedOrigins(origins)
                        .allowedHeaders("Cache-Control", "Pragma", "Accept", "Origin", "Authorization", "Content-Type", "X-Requested-With")
                        .allowedMethods("GET","POST","OPTIONS","PUT")
                        .allowCredentials(true)
                        .maxAge(3600);

                logger.info("CustomCorsConfiguration addCorsMappings success!!!");
                logger.info("CorsRegistry config: allowedOrigins={}", Arrays.toString(origins));
            }
        };
    }
}
