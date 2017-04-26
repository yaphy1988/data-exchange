package com.ai.bdex.dataexchange.tradecenter.service.impl.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoQuerySV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.search.ISearchGdsBaseSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.solr.IDeltaIndexServiceSV;
import com.ai.bdex.dataexchange.util.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月26日下午3:04:55  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("iDeltaIndexServiceSV")
public class DeltaIndexServiceSVImpl implements IDeltaIndexServiceSV{
    private static final Logger logger = LoggerFactory.getLogger(DeltaIndexServiceSVImpl.class.getName());
    private static int PAGE_SIZE = 300;
    @Autowired
    private SolrClient solrClient;
    
    @Autowired
    private IGdsInfoSV iGdsInfoSV;
    
    @Override
    public void deltaImport(String collectionName, String gdsId) throws BusinessException {
        try {
            solrClient.add("gdscollection",generDeltaImportGdsInfo(gdsId));
            solrClient.commit();
        } catch (Exception e) {
            logger.error("增量刷索引失败！", e);
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deltaFullImport() throws BusinessException {
        long start = System.currentTimeMillis();
        try {
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setStatus("1");
            long count = iGdsInfoSV.count(gdsInfoReqDTO);
            int pageCount = (int) (count/PAGE_SIZE);
            if(count%PAGE_SIZE>0){
                pageCount = pageCount + 1;
            }
            int pageNo = 1;
            while(true){
                if(pageNo > pageCount){
                    break;
                }
                solrClient.add("gdscollection",generDeltaFullImportGdsInfo(pageNo,PAGE_SIZE).iterator());
                solrClient.commit();
                pageNo ++;
            }
        } catch (Exception e) {
            logger.error("全量刷索引失败！", e);
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("全量刷索引消耗："+(System.currentTimeMillis() - start)+" ms");
    }

    @Override
    public void delteDelta(String gdsId) throws BusinessException {
        try {
//            solrClient.deleteById("gdscollection",gdsId);
            solrClient.deleteByQuery("gdscollection", "id:"+gdsId);
            solrClient.commit();
        } catch (Exception e) {
            logger.error("删除索引记录失败！", e);
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteDeltaBatch(List<String> gdsIds) throws BusinessException {
        try {
            solrClient.deleteById("gdscollection",gdsIds);
            solrClient.commit();
        } catch (Exception e) {
            logger.error("批量删除索引记录失败！", e);
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() throws BusinessException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        try {
            solrClient.deleteByQuery("gdscollection",query.toString());
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Resource
    private IGdsSkuSV iGdsSkuSV;
    @Resource
    private ISearchGdsBaseSV iSearchGdsBaseSV;
    @Resource
    private IGdsInfoQuerySV iGdsInfoQuerySV;
    /**
     * 
     * generDeltaImportGdsInfo:(获取增量刷新的商品信息). <br/> 
     * 
     * @author gxq 
     * @param gdsId
     * @return 
     * @since JDK 1.6
     */
    public SolrInputDocument generDeltaImportGdsInfo(String gdsId){
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        if(StringUtil.isBlank(gdsId)){
            return solrInputDocument;
        }
        try {
            //获取商品主表信息
            GdsInfo gdsInfo = iGdsInfoSV.queryGdsInfoById(Integer.parseInt(gdsId));
            if(gdsInfo != null){
                solrInputDocument.addField("id", gdsInfo.getGdsId(), 1.0f);
                solrInputDocument.addField("gdsName", gdsInfo.getGdsName(), 1.0f);
                solrInputDocument.addField("gdsSubtitle", gdsInfo.getGdsSubtitle(), 1.0f);
                solrInputDocument.addField("catFirst", gdsInfo.getGdsSubtitle());
                solrInputDocument.addField("apiId", gdsInfo.getApiId());
                solrInputDocument.addField("gdsPic", gdsInfo.getGdsPic());
                solrInputDocument.addField("ifRecommend", gdsInfo.getIfRecommend());
                solrInputDocument.addField("funIntroduction", gdsInfo.getFunIntroduction());
                solrInputDocument.addField("status", gdsInfo.getStatus());
                solrInputDocument.addField("shelveTime", gdsInfo.getShelveTime());
                solrInputDocument.addField("createTime", gdsInfo.getCreateTime());
                solrInputDocument.addField("catId", gdsInfo.getCatId());
                solrInputDocument.addField("commpanyName", gdsInfo.getCommpanyName());
            }
            //获取单品主表信息
            GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
            gdsSkuReqDTO.setGdsId(Integer.parseInt(gdsId));
            List<GdsSku> skuInfo = iGdsSkuSV.queryGdsSkuList(gdsSkuReqDTO);
            if(skuInfo != null && skuInfo.size() >= 1){
                GdsSku sku = skuInfo.get(0);
                solrInputDocument.addField("packPrice", sku.getPackPrice());
                solrInputDocument.addField("packTimes", sku.getPackTimes());
            }
           
            //获取其他系数参数
            SearchGdsBaseReqDTO searchGdsBaseReqDTO = new SearchGdsBaseReqDTO();
            searchGdsBaseReqDTO.setGdsId(Integer.parseInt(gdsId));
            SearchGdsBaseRespDTO searchGdsBase = iSearchGdsBaseSV.querySearchGdsBaseInfo(searchGdsBaseReqDTO);
            if(searchGdsBase != null){
                solrInputDocument.addField("gdsSale", searchGdsBase.getGdsSale());
                //最热
                solrInputDocument.addField("hotDegree", searchGdsBase.getWeiScore());
            }
        } catch (Exception e) {
            logger.error("获取增量信息失败！", e);
        }
        return solrInputDocument;
    }
    
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
            PageResponseDTO<GdsInfoRespDTO> pageInfo = iGdsInfoQuerySV.queryGdsInfoListPage(gdsInfoReqDTO);
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
                    //获取单品主表信息
                    GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
                    gdsSkuReqDTO.setGdsId( gdsInfoRespDTO.getGdsId());
                    List<GdsSku> skuInfo = iGdsSkuSV.queryGdsSkuList(gdsSkuReqDTO);
                    if(skuInfo != null && skuInfo.size() >= 1){
                        GdsSku sku = skuInfo.get(0);
                        solrInputDocument.addField("packPrice", sku.getPackPrice());
                        solrInputDocument.addField("packTimes", sku.getPackTimes());
                    }
                    //获取其他系数参数
                    SearchGdsBaseReqDTO searchGdsBaseReqDTO = new SearchGdsBaseReqDTO();
                    searchGdsBaseReqDTO.setGdsId( gdsInfoRespDTO.getGdsId());
                    SearchGdsBaseRespDTO searchGdsBase = iSearchGdsBaseSV.querySearchGdsBaseInfo(searchGdsBaseReqDTO);
                    if(searchGdsBase != null){
                        solrInputDocument.addField("gdsSale", searchGdsBase.getGdsSale());
                        //最热
                        solrInputDocument.addField("hotDegree", searchGdsBase.getWeiScore());
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
