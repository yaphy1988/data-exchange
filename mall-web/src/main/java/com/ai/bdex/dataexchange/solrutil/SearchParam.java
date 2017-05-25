package com.ai.bdex.dataexchange.solrutil;

import java.util.List;
import java.util.Map;

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
     * 查询字段.AND
     */
    @SuppressWarnings("rawtypes")
    private Map<String,String> searchField;
    
    /**
     * 查询字段.AND 用于比如 gdsId =1 and gdsId= 2 and gdsId = 3 多值的&&查询
     */
    private Map<String,List<String>> searchAndListField;
    
    /**
     * 查询字段.OR.这里的数据之间的查询都是or关系了
     */
    @SuppressWarnings("rawtypes")
    private Map<String,String> searchOrField;
    /**
     * 查询字段.OR 用于比如 gdsId in(1,2,3,4,5) 多值的||查询
     * 
     */
    private Map<String,List<String>> searchOrListField;
    
    /**
     * 查询字段.NOT
     */
    @SuppressWarnings("rawtypes")
    private  Map<String,String> searchNotField;
    
    /**
     * 查询字段.NOT 用于比如 gdsId not in(1,2,3,4,5) 排出多值的
     */
    private Map<String,List<String>> searchNotListField;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public SolrClient getSolrClient() {
        return solrClient;
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public Map<String, String> getSearchField() {
        return searchField;
    }

    public void setSearchField(Map<String, String> searchField) {
        this.searchField = searchField;
    }

    public Map<String, List<String>> getSearchAndListField() {
        return searchAndListField;
    }

    public void setSearchAndListField(Map<String, List<String>> searchAndListField) {
        this.searchAndListField = searchAndListField;
    }

    public Map<String, String> getSearchOrField() {
        return searchOrField;
    }

    public void setSearchOrField(Map<String, String> searchOrField) {
        this.searchOrField = searchOrField;
    }

    public Map<String, List<String>> getSearchOrListField() {
        return searchOrListField;
    }

    public void setSearchOrListField(Map<String, List<String>> searchOrListField) {
        this.searchOrListField = searchOrListField;
    }

    public Map<String, String> getSearchNotField() {
        return searchNotField;
    }

    public void setSearchNotField(Map<String, String> searchNotField) {
        this.searchNotField = searchNotField;
    }

    public Map<String, List<String>> getSearchNotListField() {
        return searchNotListField;
    }

    public void setSearchNotListField(Map<String, List<String>> searchNotListField) {
        this.searchNotListField = searchNotListField;
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

    
}
