package com.ai.bdex.dataexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:/dubbo/client/*.xml"})
@SpringBootApplication
public class MallWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallWebApplication.class, args);
	}
}
