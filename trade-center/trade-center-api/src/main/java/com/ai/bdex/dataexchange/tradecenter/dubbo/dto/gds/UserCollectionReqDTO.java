package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年5月8日上午10:54:29  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class UserCollectionReqDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
   private static final long serialVersionUID = 2931580473057695516L;

   private Integer colId;

   private Integer catFirst;

   private Integer gdsId;

   private Integer skuId;

   private String staffId;

   private String status;

   private String createUser;

   private Date createTime;

   private String updateUser;

   private Date updateTime;
   
   /**
    * 商品列表id
    */
   private List<Integer> gdsIds;

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
    
    public String getCreateUser() {
        return createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateUser() {
        return updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getGdsIds() {
        return gdsIds;
    }

    public void setGdsIds(List<Integer> gdsIds) {
        this.gdsIds = gdsIds;
    }
   
}

