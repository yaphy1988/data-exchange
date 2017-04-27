package com.ai.bdex.dataexchange.solrutil;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月25日下午1:46:55  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchParam {
    /**
     * 关键词
     */
    private String keyWord;
    /**
     * solr实例
     */
    private SolrClient solrClient;
    /**
     * 集合名称
     */
    private String collectionName;

    /**
     * 检索是否高亮（覆盖索引配置）
     */
    private boolean ifHightlight=true;

    /**
     * 查询字段.and
     */
    @SuppressWarnings("rawtypes")
    private List<SearchField> searchField;
    
    /**
     * 查询字段.or
     */
    private List<SearchField> searchOrField;
    /**
     * 字段排序List
     */
    private List<SortField> sortField;

    /**
     * 当前页
     */
    private int pageNo=1;

    /**
     * 搜索结果pageSize
     */
    private int pageSize = 20;
    
    public String getCollectionName() {
        return collectionName;
    }

    public boolean isIfHightlight() {
        return ifHightlight;
    }

    public void setIfHightlight(boolean ifHightlight) {
        this.ifHightlight = ifHightlight;
    }

    @SuppressWarnings("rawtypes")
    public List<SearchField> getSearchField() {
        return searchField;
    }

    @SuppressWarnings("rawtypes")
    public void setSearchField(List<SearchField> searchField) {
        this.searchField = searchField;
    }

    public List<SortField> getSortField() {
        return sortField;
    }

    public void setSortField(List<SortField> sortField) {
        this.sortField = sortField;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public SolrClient getSolrClient() {
        return solrClient;
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<SearchField> getSearchOrField() {
        return searchOrField;
    }

    public void setSearchOrField(List<SearchField> searchOrField) {
        this.searchOrField = searchOrField;
    }
    
}
