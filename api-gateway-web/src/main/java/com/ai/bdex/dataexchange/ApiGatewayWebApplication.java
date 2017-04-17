package com.ai.bdex.dataexchange;

import com.ai.paas.util.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:/dubbo/dubboContext.xml")
@SpringBootApplication
public class ApiGatewayWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiGatewayWebApplication.class, args);
		Utils.setCtx(context);
	}
}
