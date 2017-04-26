package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr;

import java.util.List;

import com.ai.bdex.dataexchange.exception.BusinessException;

public interface IDeltaIndexServiceRSV {
    /**
     * 
     * deltaImport:(增量刷索引). <br/> 
     * 
     * @author gxq 
     * @param core
     * @param gdsId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deltaImport(String collectionName,String gdsId) throws BusinessException;
    /**
     * 
     * deltaFullImport:(全量刷索引). <br/> 
     * 
     * @author gxq 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deltaFullImport() throws BusinessException;
    /**
     * 
     * delteDelta:(删除单条索引记录). <br/> 
     * 
     * @author gxq 
     * @param gdsId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void delteDelta(String gdsId) throws BusinessException;
    /**
     * 
     * deleteDeltaBatch:(批量删除索引记录). <br/> 
     * 
     * @author gxq 
     * @param gdsIds
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteDeltaBatch(List<String> gdsIds) throws BusinessException;
    /**
     * 
     * deleteAll:(清楚全部索引). <br/> 
     * 
     * @author gxq 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAll() throws BusinessException;
    
}

