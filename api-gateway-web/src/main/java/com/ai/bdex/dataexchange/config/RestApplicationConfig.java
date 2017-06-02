package com.ai.bdex.dataexchange.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/* 想要开始使用Jersey 2.x只需要加入spring-boot-starter-jersey依赖，
 * 然后你需要一个ResourceConfig类型的@Bean，用于注册所有的端点（endpoints,demo为JerseyController）。
 */
//@Component
@Configuration
//Jersey servlet将被注册，并默认映射到/*。可将@ApplicationPath添加到ResourceConfig来改变该映射。
@ApplicationPath("/rest")
public class RestApplicationConfig extends ResourceConfig{
//public class RestApplication {

	public RestApplicationConfig() {
		// 资源类所在的包路径
		packages("com.ai.bdex.dataexchange.busi");
		// 注册 MultiPart
//    		register(MultiPartFeature.class);
		}
   
}
