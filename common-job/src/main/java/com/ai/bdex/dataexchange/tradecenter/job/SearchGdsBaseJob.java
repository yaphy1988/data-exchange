package com.ai.bdex.dataexchange.tradecenter.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.tradecenter.service.SearchGdsBaseService;
import com.ai.paas.util.Utils;
/**
 * 
 * Title: ECP <br>
 * Project Name:common-job <br>
 * Description: <br>
 * Date:2017年5月23日下午5:06:32  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Configuration  
@Component // 此注解必加  
@EnableScheduling // 此注解必加  
public class SearchGdsBaseJob {
    private static Logger logger = LoggerFactory.getLogger(SearchGdsBaseJob.class);
    //每天凌晨3点执行
    @Scheduled(cron = "0 0 1 * * ? ")
    public void callProcedureJob(){
        Utils.getCtx().getBean(SearchGdsBaseService.class).callProcedure();
    }
}

