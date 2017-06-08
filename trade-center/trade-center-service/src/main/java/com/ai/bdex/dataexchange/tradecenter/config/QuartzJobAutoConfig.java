package com.ai.bdex.dataexchange.tradecenter.config;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.job.model.QuartzTaskInfo;
import com.ai.bdex.dataexchange.job.service.QuartzScheduleService;
import com.ai.bdex.dataexchange.tradecenter.job.SearchGdsBaseJob;
import com.ai.bdex.dataexchange.tradecenter.job.SolrDeltaImportJob;
import com.ai.bdex.dataexchange.tradecenter.job.OrderStatusUpdateBaseJob;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaphy on 2017/6/2.
 */
@Configuration
public class QuartzJobAutoConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QuartzScheduleService quartzScheduleService;

    @Bean
    public Object jobDetailGenerates() {
        QuartzTaskInfo searchGdsBaseJobInfo = new QuartzTaskInfo();
        searchGdsBaseJobInfo.setCronExpression("0 0 0/1 * * ? ");
        searchGdsBaseJobInfo.setJobName(SearchGdsBaseJob.class.getName());
        searchGdsBaseJobInfo.setJobGroup("solrJobGroup");
        initJobs(searchGdsBaseJobInfo);


        QuartzTaskInfo solrDeltaImportJobInfo = new QuartzTaskInfo();
        solrDeltaImportJobInfo.setCronExpression("0 0 3 * * ? ");
        solrDeltaImportJobInfo.setJobName(SolrDeltaImportJob.class.getName());
        solrDeltaImportJobInfo.setJobGroup("solrJobGroup");
        initJobs(solrDeltaImportJobInfo);

        //订单设置为完成的定时任务 每隔10分钟执行一次
        QuartzTaskInfo orderStatusUpdateBaseJob = new QuartzTaskInfo();
        orderStatusUpdateBaseJob.setCronExpression("0 0 0/1 * * ? ");
        //0 0/30 9-17 * * ?
        orderStatusUpdateBaseJob.setJobName(OrderStatusUpdateBaseJob.class.getName());
        orderStatusUpdateBaseJob.setJobGroup("orderJobGroup");
        initJobs(orderStatusUpdateBaseJob);

        return new Object();
    }

    private void initJobs(QuartzTaskInfo taskInfo) {
        try {
            if (quartzScheduleService.checkExists(taskInfo.getJobName(), taskInfo.getJobGroup())) {
                quartzScheduleService.edit(taskInfo);
            } else {
                quartzScheduleService.addJob(taskInfo);
            }
            logger.info("=======自动初始job成功：{}============", taskInfo.getJobName());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
