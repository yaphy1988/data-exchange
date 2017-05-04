package com.ai.bdex.dataexchange;

 import com.ai.paas.config.ConfigurationCenter;
 import com.ai.paas.config.SystemConfiguration;
 import com.ai.paas.config.SystemConfigurationImpl;
 import com.ai.paas.session.impl.SessionManager;
 import com.ai.paas.util.SystemConfUtil;
 import com.ai.paas.util.Utils;
 import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.annotation.Bean;

 import java.io.IOException;
 import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
public class MallWebApplication {

	@Value("${application.system.confpath:/com/ai/paas/system/conf}")
	private String systemConfPath;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MallWebApplication.class, args);
		Utils.setCtx(context);
		System.out.println("--------------------恭喜你启动Mail-web成功了！！！-----------------");
	}
}
