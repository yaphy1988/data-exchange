package com.ai.bdex.dataexchange.tradecenter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.SolrCoreEnum;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
/**
 * 
 * Title: ECP <br>
 * Project Name:common-job <br>
 * Description: <br>
 * Date:2017年5月23日下午3:39:55  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service
public class SolrDeltaImportService {
    private static Logger logger = LoggerFactory.getLogger(SolrDeltaImportService.class);
    @DubboConsumer
    private IDeltaIndexServiceRSV iDeltaIndexServiceRSV;
    public void deltaClear(){
        try {
            iDeltaIndexServiceRSV.deltaFullImport(SolrCoreEnum.GDS.getCode(), true);
        } catch (BusinessException e) {
            logger.error("重建索引失败", e);
        }
    }
    
    public void delta(){
        try {
            iDeltaIndexServiceRSV.deltaFullImport(SolrCoreEnum.GDS.getCode(), false);
        } catch (BusinessException e) {
            logger.error("重建索引失败", e);
        }
    }
}

