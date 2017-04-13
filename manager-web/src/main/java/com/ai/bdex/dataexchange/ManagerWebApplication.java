package com.ai.bdex.dataexchange;

import com.ai.bdex.dataexchange.filter.LoginAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ImportResource({"classpath:/dubbo/dubboContext.xml"})
public class ManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerWebApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		LoginAuthFilter authFilter = new LoginAuthFilter();
		filterRegistrationBean.setFilter(authFilter);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/**");
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		return filterRegistrationBean;
	}
}
