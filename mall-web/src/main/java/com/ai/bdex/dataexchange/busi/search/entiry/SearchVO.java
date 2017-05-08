package com.ai.bdex.dataexchange.busi.search.entiry;

import java.io.Serializable;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月21日上午11:14:20  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchVO extends BaseInfo implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3329595398889197798L;
    /**
     * 商品id
     */
    private int gdsId;
    /**
     * 商品列表id
     */
    private List<Integer> gdsIds;
    /**
     * 单品id
     */
    private int skuId;
    /**
     * 关键词
     */
    private String keyWord;
    /**
     * 分类id
     */
    private int catId;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序值。ASC 升序，DES 降序
     */
    private String sortValue;
    /**
     * 一级分类
     */
    private int catFirst;
    /**
     * 分类map集合
     */
    private String selectedCondition;
    
    
    public String getKeyWord() {
        return keyWord;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public int getCatId() {
        return catId;
    }
    public void setCatId(int catId) {
        this.catId = catId;
    }
    public String getSortField() {
        return sortField;
    }
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
    public String getSortValue() {
        return sortValue;
    }
    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
    public int getCatFirst() {
        return catFirst;
    }
    public void setCatFirst(int catFirst) {
        this.catFirst = catFirst;
    }
    public String getSelectedCondition() {
        return selectedCondition;
    }
    public void setSelectedCondition(String selectedCondition) {
        this.selectedCondition = selectedCondition;
    }
    public int getGdsId() {
        return gdsId;
    }
    public void setGdsId(int gdsId) {
        this.gdsId = gdsId;
    }
    public int getSkuId() {
        return skuId;
    }
    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }
    public List<Integer> getGdsIds() {
        return gdsIds;
    }
    public void setGdsIds(List<Integer> gdsIds) {
        this.gdsIds = gdsIds;
    }
    
}

