package com.ai.bdex.dataexchange.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import com.ai.bdex.dataexchange.busi.demo.controller.DemoRestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.stream.Collectors;

/* 想要开始使用Jersey 2.x只需要加入spring-boot-starter-jersey依赖，
 * 然后你需要一个ResourceConfig类型的@Bean，用于注册所有的端点（endpoints,demo为JerseyController）。
 */
//@Component
@Configuration
//Jersey servlet将被注册，并默认映射到/*。可将@ApplicationPath添加到ResourceConfig来改变该映射。
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
//public class RestApplication {

    public JerseyConfig() {
        // 资源类所在的包路径
//		packages("com.ai.bdex.dataexchange.busi");
        // 注册 MultiPart
//      register(DemoRestController.class);
        customScan("com.ai.bdex.dataexchange.busi");

    }
    private void customScan(String scanPackage) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        // add more annotation filters if you need
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
        this.registerClasses(scanner.findCandidateComponents(scanPackage).stream()
                .map(beanDefinition -> ClassUtils
                        .resolveClassName(beanDefinition.getBeanClassName(), this.getClassLoader()))
                .collect(Collectors.toSet()));
    }


}
