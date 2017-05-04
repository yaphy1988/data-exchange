package com.ai.bdex.dataexchange;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.ai.bdex.dataexchange.security.AccessTokenCheckHandler;
import com.ai.bdex.dataexchange.security.PermissionCheckHandler;
import com.ai.bdex.dataexchange.security.ServiceCheckChain;
import com.ai.bdex.dataexchange.web.DispatcherServlet;
import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
public class ApiGatewayWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiGatewayWebApplication.class, args);
		Utils.setCtx(context);
		System.out.println("api-gateway-web 启动成功");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO 自动生成的方法存根
//		registry.addInterceptor(new VerifyAccesstokenInterceptor()).addPathPatterns("/rest/**");
//	}
	
	/* 代码注入：
	 *  此种方式需注意：ServletRegistrationBean的配置，及最终的请求路径。
	 * 注解注入：
	 *  JerseyConfig.java中用@Configuration
	 */
//    @Bean  
//    public ServletRegistrationBean jerseyServlet() {  
//        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");  
//        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, RestApplication.class.getName());  
//        return registration;  
//    } 
	@Bean
	public ServiceCheckChain serviceCheckChain(){
		ServiceCheckChain chain=new ServiceCheckChain();
		List<PermissionCheckHandler> list=new ArrayList<PermissionCheckHandler>();
		list.add((AccessTokenCheckHandler)Utils.getCtx().getBean(AccessTokenCheckHandler.class));
		chain.setCheckHandlers(list);
		return chain;
	}
	
	//自定义DispatcherServlet
	@Bean
	public ServletRegistrationBean apiDispatcherServlet(){
	  //注解扫描上下文
	  AnnotationConfigWebApplicationContext applicationContext
	      = new AnnotationConfigWebApplicationContext();
	  //base package
	  applicationContext.scan("com.ai.bdex.dataexchange");
	  
	  //通过构造函数指定dispatcherServlet的上下文
	  DispatcherServlet rest_dispatcherServlet
	      = new DispatcherServlet(applicationContext);
	 
	  //用ServletRegistrationBean包装servlet
	  ServletRegistrationBean registrationBean
	      = new ServletRegistrationBean(rest_dispatcherServlet);
	  registrationBean.setLoadOnStartup(1);
	  //指定urlmapping
	  registrationBean.addUrlMappings("/");
	  //指定name，如果不指定默认为dispatcherServlet
//	  registrationBean.setName("rest");
	  return registrationBean;
	}
    
}
