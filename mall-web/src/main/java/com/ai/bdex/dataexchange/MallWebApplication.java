package com.ai.bdex.dataexchange;

import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
public class MallWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallWebApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean(){
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////		LoginAuthFilter authFilter = new LoginAuthFilter();
////		filterRegistrationBean.setFilter(authFilter);
//		List<String> urlPatterns = new ArrayList<>();
//		urlPatterns.add("/**");
//		filterRegistrationBean.setUrlPatterns(urlPatterns);
//		return filterRegistrationBean;
//	}
}
