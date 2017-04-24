package com.ai.bdex.dataexchange.solrutil;

import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 自定义查询条件<br>
 * Date:2015年9月7日下午7:52:32  <br>
 * Copyright (c) 2015 asia All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class ExtraQueryInfo {
    
    public final static String FIELD_CATEGORIES="categories";
    public final static String FIELD_PROPERTYCODES="propertycodes";
    
    /**
     * 查询的分类列表，不受索引配置中的默认搜索字段约束
     */
    private List<QueryCategory> queryCategoryList;
    
    /**
     * 查询的属性列表，不受索引配置中的默认搜索字段约束
     */
    private List<QueryProperty> queryPropertyList;
    
    /**
     * 属性查询字段
     */
    private String propertyFieldName=FIELD_PROPERTYCODES;
    
    public List<QueryCategory> getQueryCategoryList() {
        return queryCategoryList;
    }

    public void setQueryCategoryList(List<QueryCategory> queryCategoryList) {
        this.queryCategoryList = queryCategoryList;
    }

    public List<QueryProperty> getQueryPropertyList() {
        return queryPropertyList;
    }

    public void setQueryPropertyList(List<QueryProperty> queryPropertyList) {
        this.queryPropertyList = queryPropertyList;
    }
    
    public String getPropertyFieldName() {
        return propertyFieldName;
    }

    public void setPropertyFieldName(String propertyFieldName) {
        this.propertyFieldName = propertyFieldName;
    }
    
    public static class QueryCategory{
        
        /**
         * 查询的分类编码，不受索引配置中的默认搜索字段约束
         */
        private List<String> categoryCodeList;
        
        /**
         * 检索分类字段名，主分类字段名mainCategoryCode，顶级分类字段名topCategoryCode，全分类字段名categories。
         */
        private String categoryFieldName=FIELD_CATEGORIES;
        
        /**
         * 默认分类关系是与OR
         */
        private boolean categoryOrRelation=true;
        
        public QueryCategory(List<String> categoryCodeList, String categoryFieldName) {
            this(categoryCodeList, categoryFieldName, true);
        }
        
        public QueryCategory(List<String> categoryCodeList, String categoryFieldName,
                boolean categoryOrRelation) {
            super();
            this.categoryCodeList = categoryCodeList;
            this.categoryFieldName = categoryFieldName;
            this.categoryOrRelation = categoryOrRelation;
        }

        public List<String> getCategoryCodeList() {
            return categoryCodeList;
        }

        public void setCategoryCodeList(List<String> categoryCodeList) {
            this.categoryCodeList = categoryCodeList;
        }

        public String getCategoryFieldName() {
            return categoryFieldName;
        }

        public void setCategoryFieldName(String categoryFieldName) {
            this.categoryFieldName = categoryFieldName;
        }

        public boolean isCategoryOrRelation() {
            return categoryOrRelation;
        }

        public void setCategoryOrRelation(boolean categoryOrRelation) {
            this.categoryOrRelation = categoryOrRelation;
        }
        
    }

    public static class QueryProperty{
        
        /**
         * 属性编码
         */
        private String propertyCode;
        
        /**
         * 属性值编码列表
         */
        private List<String> propertyValueCodeList;

        public String getPropertyCode() {
            return propertyCode;
        }

        public void setPropertyCode(String propertyCode) {
            this.propertyCode = propertyCode;
        }

        public List<String> getPropertyValueCodeList() {
            return propertyValueCodeList;
        }

        public void setPropertyValueCodeList(List<String> propertyValueCodeList) {
            this.propertyValueCodeList = propertyValueCodeList;
        }

    }

}

