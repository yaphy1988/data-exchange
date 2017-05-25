package com.ai.bdex.dataexchange.tradecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfiguration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@Configuration
/**
 * 
 * Title: ECP <br>
 * Project Name:common-job <br>
 * Description: <br>
 * Date:2017年5月23日下午3:40:00  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class CommonJobStart {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CommonJobStart.class, args);
        Utils.setCtx(context);
        System.out.println("--------------------恭喜你启动common-job成功了！！！-----------------");
    }
}
