package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年4月25日上午9:58:40  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchGdsBaseRespDTO extends BaseResponseDTO{
   /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2190064617344198319L;

    private Integer gdsId;

    private Integer catFirst;

    private Integer catId;

    private String ifRecommend;

    private String status;

    private Date shelveTime;

    private Date createTime;

    private Date updateTime;

    private Integer gdsSale;

    private Integer gdsMoney;

    private Double kpiTime;

    private Double kpiNum;

    private Double kpiMoney;

    private Double weiScore;

    private Date dataTime;
    
    public Integer getGdsId() {
        return gdsId;
    }
    
    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }
    
    public Integer getCatFirst() {
        return catFirst;
    }
    
    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
    }
    
    public Integer getCatId() {
        return catId;
    }
    
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    
    public String getIfRecommend() {
        return ifRecommend;
    }
    
    public void setIfRecommend(String ifRecommend) {
        this.ifRecommend = ifRecommend;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getShelveTime() {
        return shelveTime;
    }
    
    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getGdsSale() {
        return gdsSale;
    }
    
    public void setGdsSale(Integer gdsSale) {
        this.gdsSale = gdsSale;
    }
    
    public Integer getGdsMoney() {
        return gdsMoney;
    }
    
    public void setGdsMoney(Integer gdsMoney) {
        this.gdsMoney = gdsMoney;
    }
    
    public Double getKpiTime() {
        return kpiTime;
    }
    
    public void setKpiTime(Double kpiTime) {
        this.kpiTime = kpiTime;
    }
    
    public Double getKpiNum() {
        return kpiNum;
    }
    
    public void setKpiNum(Double kpiNum) {
        this.kpiNum = kpiNum;
    }
    
    public Double getKpiMoney() {
        return kpiMoney;
    }
    
    public void setKpiMoney(Double kpiMoney) {
        this.kpiMoney = kpiMoney;
    }
    
    public Double getWeiScore() {
        return weiScore;
    }
    
    public void setWeiScore(Double weiScore) {
        this.weiScore = weiScore;
    }
    
    public Date getDataTime() {
        return dataTime;
    }
    
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }
       
}

