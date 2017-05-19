package com.ai.bdex.dataexchange.tradecenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.SolrCoreEnum;
import com.ai.paas.util.Utils;

public class SolrDeltaImportJob {
    private static Logger logger = LoggerFactory.getLogger(SolrDeltaImportJob.class);
    public static void main(String[] args){
        try {
             Utils.getCtx().getBean("deltaIndexServiceRSV",IDeltaIndexServiceRSV.class).deltaFullImport(SolrCoreEnum.GDS.getCode(), false);
             logger.error("====================================重建索引成功！====================================");
         } catch (BeansException | BusinessException e) {
             logger.error("重建索引失败！",e);
         }
     }
}

