package com.ai.bdex.dataexchange.busi.gds.entity;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:manager-web <br>
 * Description: <br>
 * Date:2017年5月9日上午10:49:38  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class UserCollectionVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1810069908187984669L;

    /**
     * 收藏id
    */
   private Integer colId;

   /**
    *一级分类
    */
   private Integer catFirst;

   /**
    *商品id
    */
   private Integer gdsId;

   /**
    *单品id
    */
   private Integer skuId;

   /**
    *用户id
    */
   private String staffId;

   /**
    *状态，1：有效，0失效
    */
   private String status;
   
   private int pageNo;
   
   private int pageSize;

    public Integer getColId() {
        return colId;
    }
    
    public void setColId(Integer colId) {
        this.colId = colId;
    }
    
    public Integer getCatFirst() {
        return catFirst;
    }
    
    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
    }
    
    public Integer getGdsId() {
        return gdsId;
    }
    
    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }
    
    public Integer getSkuId() {
        return skuId;
    }
    
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
    
    public String getStaffId() {
        return staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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

   
}

