package com.ai.bdex.dataexchange.job.tradecenter.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsLabelRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.search.ISearchGdsBaseRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.SolrCoreEnum;
import com.ai.paas.utils.CollectionUtil;
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
    private static int PAGE_SIZE = 300;
    @DubboConsumer
    private IDeltaIndexServiceRSV iDeltaIndexServiceRSV;
    @Autowired
    private SolrClient solrClient;
    
    @DubboConsumer
    private IGdsInfoRSV iGdsInfoRSV;
    
    @DubboConsumer
    private IGdsLabelRSV iGdsLabelRSV;
    
    public void deltaClear(){
        try {
            deltaFullImport(SolrCoreEnum.GDS.getCode(), true);
        } catch (BusinessException e) {
            logger.error("重建索引失败", e);
        }
    }
    
    public void delta(){
        try {
            deltaFullImport(SolrCoreEnum.GDS.getCode(), false);
        } catch (BusinessException e) {
            logger.error("重建索引失败", e);
        }
    }
    public void deltaFullImport(String collectionName,Boolean removeAll) throws BusinessException {
        long start = System.currentTimeMillis();
        try {
            if(removeAll){
                solrClient.deleteByQuery(collectionName,"*:*");
                solrClient.commit(collectionName);
            }
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setStatus("1");
            long count = iGdsInfoRSV.count(gdsInfoReqDTO);
            int pageCount = (int) (count/PAGE_SIZE);
            if(count%PAGE_SIZE>0){
                pageCount = pageCount + 1;
            }
            int pageNo = 1;
            /**
             * 是否清除旧数据
             */
            while(true){
                if(pageNo > pageCount){
                    break;
                }
                solrClient.add(collectionName,generDeltaFullImportGdsInfo(pageNo,PAGE_SIZE).iterator());
                solrClient.commit(collectionName);
                pageNo ++;
            }
        } catch (Exception e) {
            logger.error("全量刷索引失败！", e);
        }
        logger.info("全量刷索引消耗："+(System.currentTimeMillis() - start)+" ms");
    }
    @DubboConsumer
    private IGdsSkuRSV iGdsSkuRSV;
    @DubboConsumer
    private ISearchGdsBaseRSV iSearchGdsBaseRSV;
    @DubboConsumer
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    /**
     * 
     * generDeltaFullImportGdsInfo:(获取全量刷新的商品信息). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    public Collection<SolrInputDocument> generDeltaFullImportGdsInfo(int pageNo,int pageSize){
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        try {
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setPageNo(pageNo);
            gdsInfoReqDTO.setPageSize(pageSize);
            gdsInfoReqDTO.setStatus("1");
            PageResponseDTO<GdsInfoRespDTO> pageInfo = iGdsInfoQueryRSV.queryGdsInfoListPage(gdsInfoReqDTO);
            if(pageInfo != null && pageInfo.getResult().size() >= 1){
                SolrInputDocument solrInputDocument = null;
                for(GdsInfoRespDTO gdsInfoRespDTO : pageInfo.getResult()){
                    solrInputDocument = new SolrInputDocument();
                    solrInputDocument.addField("id", gdsInfoRespDTO.getGdsId(), 1.0f);
                    solrInputDocument.addField("gdsName", gdsInfoRespDTO.getGdsName(), 1.0f);
                    solrInputDocument.addField("gdsSubtitle", gdsInfoRespDTO.getGdsSubtitle(), 1.0f);
                    solrInputDocument.addField("catFirst", gdsInfoRespDTO.getCatFirst());
                    if(gdsInfoRespDTO.getApiId() != null){
                        solrInputDocument.addField("apiId", gdsInfoRespDTO.getApiId());
                    }
                    solrInputDocument.addField("gdsPic", gdsInfoRespDTO.getGdsPic());
                    solrInputDocument.addField("ifRecommend", gdsInfoRespDTO.getIfRecommend());
                    solrInputDocument.addField("funIntroduction", gdsInfoRespDTO.getFunIntroduction());
                    solrInputDocument.addField("status", gdsInfoRespDTO.getStatus());
                    solrInputDocument.addField("shelveTime", gdsInfoRespDTO.getShelveTime());
                    solrInputDocument.addField("createTime", gdsInfoRespDTO.getCreateTime());
                    solrInputDocument.addField("catId", gdsInfoRespDTO.getCatId());
                    solrInputDocument.addField("commpanyName", gdsInfoRespDTO.getCommpanyName());
                    solrInputDocument.addField("providerId", gdsInfoRespDTO.getProviderId());
                    //获取单品主表信息
                    GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
                    gdsSkuReqDTO.setGdsId( gdsInfoRespDTO.getGdsId());
                    List<GdsSkuRespDTO> skuInfo = iGdsSkuRSV.queryGdsSkuList(gdsSkuReqDTO);
                    if(skuInfo != null && skuInfo.size() >= 1){
                        GdsSkuRespDTO sku = skuInfo.get(0);
                        solrInputDocument.addField("skuId", sku.getSkuId());
                        solrInputDocument.addField("packPrice", sku.getPackPrice());
                        solrInputDocument.addField("packTimes", sku.getPackTimes());
                    }
                    //获取其他系数参数
                    SearchGdsBaseReqDTO searchGdsBaseReqDTO = new SearchGdsBaseReqDTO();
                    searchGdsBaseReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                    SearchGdsBaseRespDTO searchGdsBase = iSearchGdsBaseRSV.querySearchGdsBaseInfo(searchGdsBaseReqDTO);
                    if(searchGdsBase != null){
                        solrInputDocument.addField("gdsSale", searchGdsBase.getGdsSale());
                        //最热
                        solrInputDocument.addField("hotDegree", searchGdsBase.getWeiScore());
                    }
                    //标签
                    try {
                        GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
                        gdsLabelReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                        gdsLabelReqDTO.setStatus("1");
                        List<GdsLabelRespDTO> Labellist = iGdsLabelRSV.queryGdsLabelList(gdsLabelReqDTO);
                        if(!CollectionUtil.isEmpty(Labellist)){
                            List<String> gdsLabelList = new ArrayList<String>();
                            for(GdsLabelRespDTO gdsLabel : Labellist){
                                gdsLabelList.add(gdsLabel.getLabName()+"_"+gdsLabel.getLabColor());
                            }
                            solrInputDocument.addField("gdsLabel", gdsLabelList);
                        }
                    } catch (Exception e) {
                        logger.error("获取标签失败！", e);
                    }
                    docs.add(solrInputDocument);
                }
            }
        } catch (Exception e) {
            logger.error("获取全量刷新信息失败！", e);
        }
        return docs;
    }
    
}

