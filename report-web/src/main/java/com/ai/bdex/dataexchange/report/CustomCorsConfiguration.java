package com.ai.bdex.dataexchange.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yaphy on 2017/5/21.
 */
@Configuration
public class CustomCorsConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomCorsConfiguration() {
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/reportdata/**")
                        .allowedOrigins("http://localhost:8082")
                        .allowedHeaders("Cache-Control", "Pragma", "Accept", "Origin", "Authorization", "Content-Type", "X-Requested-With")
                        .allowedMethods("POST")
                        .allowCredentials(true)
                        .maxAge(3600);
                logger.info("CustomCorsConfiguration addCorsMappings success!!!");
                logger.info("allowedOrigins");
            }
        };
    }
}
