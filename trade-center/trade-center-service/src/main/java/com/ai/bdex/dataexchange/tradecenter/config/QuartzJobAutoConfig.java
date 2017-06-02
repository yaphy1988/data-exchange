package com.ai.bdex.dataexchange.tradecenter.config;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.job.model.QuartzTaskInfo;
import com.ai.bdex.dataexchange.job.service.QuartzScheduleService;
import com.ai.bdex.dataexchange.tradecenter.job.SearchGdsBaseJob;
import com.ai.bdex.dataexchange.tradecenter.job.SolrDeltaImportJob;
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
        searchGdsBaseJobInfo.setCronExpression("0 0/1 * * * ? ");
        searchGdsBaseJobInfo.setJobName(SearchGdsBaseJob.class.getName());
        searchGdsBaseJobInfo.setJobGroup("solrJobGroup");
        initJobs(searchGdsBaseJobInfo);


        QuartzTaskInfo solrDeltaImportJobInfo = new QuartzTaskInfo();
        solrDeltaImportJobInfo.setCronExpression("0 0/1 * * * ? ");
        solrDeltaImportJobInfo.setJobName(SolrDeltaImportJob.class.getName());
        solrDeltaImportJobInfo.setJobGroup("solrJobGroup");
        initJobs(solrDeltaImportJobInfo);

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
