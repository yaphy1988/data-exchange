package com.ai.bdex.dataexchange.busi.gds.entity;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:manager-web <br>
 * Description: <br>
 * Date:2017年5月9日下午7:03:08  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class UserFootPrintVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2415683715987608972L;

    /**
    *
    * 浏览记录id
    */
    private Integer fpId;

   /**
    * 一级分类
    */
    private Integer catFirst;
    
   /**
    *
    * 商品id
    */
    private Integer gdsId;

   /**
    * 浏览次数
    */
    private Integer seeNum;

   /**
    * 用户id
    */
    private String staffId;

   /**
    * 状态
    */
    private String status;
    
    private String gdsName;
    
    private int pageNo;
    
    private int pageSize;

    public Integer getFpId() {
        return fpId;
    }
    
    public void setFpId(Integer fpId) {
        this.fpId = fpId;
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
    
    public Integer getSeeNum() {
        return seeNum;
    }
    
    public void setSeeNum(Integer seeNum) {
        this.seeNum = seeNum;
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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
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

