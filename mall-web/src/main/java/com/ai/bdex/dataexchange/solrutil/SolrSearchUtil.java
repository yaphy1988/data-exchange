package com.ai.bdex.dataexchange.solrutil;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.utils.StringUtil;

public class SolrSearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(SolrSearchUtil.class.getName());
    /* 
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield 
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示 
     */  
    @SuppressWarnings("rawtypes")
    public static SolrDocumentList Search(SearchParam searchParam) {
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        List<SearchField> searchfield = searchParam.getSearchField(); 
        List<SortField> sortfield = searchParam.getSortField();
        SolrQuery query = null;  
        try {  
            // 初始化查询对象  
            query = new SolrQuery("*:*");  
            for (SearchField searchField : searchfield) {  
                if(searchField.getValue() == null){
                    continue;
                }
                query.addFilterQuery(searchField.getName() + ":" + searchField.getValue().toString());  
            }  
            // 设置起始位置与返回结果数  
            query.setStart(searchParam.getPageNo());  
            query.setRows(searchParam.getPageSize());  
            // 设置排序  
            if (null != sortfield || sortfield.size() >= 1) {  
                for (SortField sortField : sortfield) { 
                    query.addSort(sortField.getName(),  SolrQuery.ORDER.valueOf(sortField.getValue().toString()));  
                }  
            }
            
            // 设置高亮  
            if (searchParam.isIfHightlight()) {  
                query.setHighlight(true); // 开启高亮组件  
                query.addHighlightField("gdsName");// 高亮字段  
                query.addHighlightField("gdsSubtitle");// 高亮字段  
                query.setHighlightSimplePre("<font color=\"red\">");// 标记  
                query.setHighlightSimplePost("</font>");  
                query.setHighlightSnippets(1);// 结果分片数，默认为1  
                query.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100  
  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;
        SolrDocumentList docs = new SolrDocumentList();
        try {  
            rsp = searchParam.getSolrClient().query(searchParam.getCollectionName(),query);
            docs = rsp.getResults();
            logger.info("查询内容:" + query);
            logger.info("文档数量：" + docs.getNumFound());
            logger.info("查询花费时间:" + rsp.getQTime());
        } catch (Exception e) {  
            logger.error("查询失败！", e);
            return null;  
        }  
        // 返回查询结果  
        return docs;  
    }  
    
    @SuppressWarnings("rawtypes")
    public static SolrDocumentList suggest(SearchParam searchParam) {
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        SolrQuery query = null;  
        try {  
            // 初始化查询对象  
            query = new SolrQuery();  
            query.set("qt", "/suggest");
            query.set("spellcheck.q", searchParam.getKeyWord());
            query.set("spellcheck", "true");
            query.set("spellcheck.build", "true");
            query.set("spellcheck.count", searchParam.getPageSize());
            query.set("spellcheck.extendedResults", "false");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;
        SolrDocumentList docs = new SolrDocumentList();
        try {  
            rsp = searchParam.getSolrClient().query(searchParam.getCollectionName(),query);
            docs = rsp.getResults();
            logger.info("查询内容:" + query);
            logger.info("文档数量：" + docs.getNumFound());
            logger.info("查询花费时间:" + rsp.getQTime());
        } catch (Exception e) {  
            logger.error("查询失败！", e);
            return null;  
        }  
        // 返回查询结果  
        return docs;  
    }  
}

