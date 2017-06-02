package com.ai.bdex.dataexchange.tradecenter.job;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.search.ISearchGdsBaseRSV;
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
public class SearchGdsBaseJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ISearchGdsBaseRSV searchGdsBaseService = Utils.getInstance(ISearchGdsBaseRSV.class);
        try {
            logger.info("SearchGdsBaseJob fired at " + new Date());

            searchGdsBaseService.callProcedure();
        } catch (BusinessException e) {
            logger.error("执行 SearchGdsBaseJob 失败： ",e);
        }
    }
}
