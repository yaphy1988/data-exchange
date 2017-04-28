package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.solr;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.solr.IDeltaIndexServiceSV;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月26日下午3:05:08  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("deltaIndexServiceRSV")
public class DeltaIndexServiceRSVImpl implements IDeltaIndexServiceRSV{
    private static final Logger logger = LoggerFactory.getLogger(DeltaIndexServiceRSVImpl.class.getName());
    @Resource
    private IDeltaIndexServiceSV iDeltaIndexServiceSV;
    @Override
    public void deltaImport(String collectionName, String gdsId) throws BusinessException {
        iDeltaIndexServiceSV.deltaImport(collectionName, gdsId);
    }
    @Override
    public void deltaFullImport(String collectionName) throws BusinessException {
        iDeltaIndexServiceSV.deltaFullImport(collectionName);
    }
    @Override
    public void delteDelta(String collectionName,String gdsId) throws BusinessException {
        iDeltaIndexServiceSV.delteDelta(collectionName,gdsId);
    }
    @Override
    public void deleteDeltaBatch(String collectionName,List<String> gdsIds) throws BusinessException {
        iDeltaIndexServiceSV.deleteDeltaBatch(collectionName,gdsIds);
    }
    @Override
    public void deleteAll(String collectionName) throws BusinessException {
        iDeltaIndexServiceSV.deleteAll(collectionName);
    }
}

