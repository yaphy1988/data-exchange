package com.ai.bdex.dataexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

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
	
    
}
