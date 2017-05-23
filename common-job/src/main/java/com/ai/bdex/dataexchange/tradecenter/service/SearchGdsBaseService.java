package com.ai.bdex.dataexchange.tradecenter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.search.ISearchGdsBaseRSV;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
/**
 * 
 * Title: ECP <br>
 * Project Name:common-job <br>
 * Description: <br>
 * Date:2017年5月23日下午5:04:35  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service
public class SearchGdsBaseService {
    private static Logger logger = LoggerFactory.getLogger(SearchGdsBaseService.class);
    /**
     * 调用存储函数PRO_GDS_SEARCH_DATA
     */
    @DubboConsumer
    private ISearchGdsBaseRSV iSearchGdsBaseRSV;
    public void callProcedure(){
        try {
            iSearchGdsBaseRSV.callProcedure();
        } catch (BusinessException e) {
            logger.error("调用PRO_GDS_SEARCH_DATA存储函数失败", e);
        }
    }
}

