package com.ai.bdex.dataexchange.solrutil;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrSearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(SolrSearchUtil.class.getName());
    /* 
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield 
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示 
     */  
    public static SolrDocumentList Search(String[] field, String[] key, int start,  
            int count, String[] sortfield, Boolean[] flag, Boolean hightlight,SolrClient solrClient) {  
        // 检测输入是否合法  
        if (null == field || null == key || field.length != key.length) {
            return null;
        }  
        if (null == sortfield || null == flag  
                || sortfield.length != flag.length) {  
            return null;  
        }  
  
        SolrQuery query = null;  
        try {  
            // 初始化查询对象  
            query = new SolrQuery(field[0] + ":" + key[0]);  
            for (int i = 0; i < field.length; i++) {  
                query.addFilterQuery(field[i] + ":" + key[i]);  
            }  
            // 设置起始位置与返回结果数  
            query.setStart(start);  
            query.setRows(count);  
            // 设置排序  
            for (int i = 0; i < sortfield.length; i++) {  
                if (flag[i]) { 
                    query.addSort(sortfield[i], SolrQuery.ORDER.asc);  
                } else {  
                    query.addSort(sortfield[i], SolrQuery.ORDER.desc);  
                }  
            }  
            // 设置高亮  
            if (null != hightlight) {  
                query.setHighlight(true); // 开启高亮组件  
                query.addHighlightField("gdsName");// 高亮字段  
//                query.addHighlightField("gdsSubtitle");// 高亮字段  
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
            rsp = solrClient.query("gdscollection",query);
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

