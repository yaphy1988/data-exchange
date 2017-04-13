package com.ai.bdex.dataexchange.usercenter;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.ai.bdex.dataexchange.usercenter.service.interfaces.IDemoSV;

/**
 * @author yafei
 * @since 2017/3/31
 */
@ImportResource({"classpath:/mybatis-config.xml"})
@SpringBootApplication
public class HelloDemoRSVImplTest {
	
    @Test
    public void helloWorld() throws Exception {
    	
    }

    public static void main(String[] args) {
		
    	ApplicationContext context = SpringApplication.run(UserCenterServiceStart.class, args);
    	
    	System.out.println(context.getBean(IDemoSV.class).callDemo(null));
    	
	}
}