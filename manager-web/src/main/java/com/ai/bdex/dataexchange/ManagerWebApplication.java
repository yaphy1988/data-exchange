package com.ai.bdex.dataexchange;

import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
public class ManagerWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ManagerWebApplication.class, args);
		Utils.setCtx(context);
		System.out.println("-----------------恭喜你启动ManagerWeb成功了！-----------------");
	}
}
