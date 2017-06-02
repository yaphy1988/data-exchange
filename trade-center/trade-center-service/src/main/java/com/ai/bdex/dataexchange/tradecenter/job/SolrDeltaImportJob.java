package com.ai.bdex.dataexchange.tradecenter.job;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.SolrCoreEnum;
import com.ai.paas.util.Utils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by yaphy on 2017/6/2.
 */
public class SolrDeltaImportJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        IDeltaIndexServiceRSV deltaIndexService = Utils.getInstance(IDeltaIndexServiceRSV.class);
        try {
            logger.info("SolrDeltaImportJob fired at " + new Date());

            deltaIndexService.deltaFullImport(SolrCoreEnum.GDS.getCode(), false);
        } catch (BusinessException e) {
            logger.error("执行 SolrDeltaImportJob 失败： ",e);
        }
    }
}
