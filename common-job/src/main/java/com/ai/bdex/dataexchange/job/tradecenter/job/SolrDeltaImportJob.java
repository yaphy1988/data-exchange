package com.ai.bdex.dataexchange.job.tradecenter.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.job.tradecenter.service.SolrDeltaImportService;
import com.ai.paas.util.Utils;

/**
 * 
 * Title: ECP <br>
 * Project Name:common-job <br>
 * Description: <br>
 * Date:2017年5月23日下午3:40:07  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class SolrDeltaImportJob {
    private static Logger logger = LoggerFactory.getLogger(SolrDeltaImportJob.class);
    //每天凌晨3点执行
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void doJob(){
        Utils.getCtx().getBean(SolrDeltaImportService.class).delta();
    }
}

