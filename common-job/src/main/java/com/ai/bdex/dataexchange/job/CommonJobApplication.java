package com.ai.bdex.dataexchange.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ai.paas.util.Utils;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;

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
@SpringBootApplication
@EnableDubboConfiguration
public class CommonJobApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CommonJobApplication.class, args);
        Utils.setCtx(context);
        System.out.println("--------------------恭喜你启动common-job成功了！！！-----------------");
    }
}
