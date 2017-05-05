package com.ai.bdex.dataexchange;

import com.ai.bdex.dataexchange.filter.LoginAuthFilter;
import com.ai.paas.session.filter.CacheSessionFilter;
import com.ai.paas.util.MongoFileUtil;
import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
public class ManagerWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ManagerWebApplication.class, args);
		Utils.setCtx(context);
		System.out.println("-----------------恭喜你启动ManagerWeb成功了！-----------------");
	}
}
