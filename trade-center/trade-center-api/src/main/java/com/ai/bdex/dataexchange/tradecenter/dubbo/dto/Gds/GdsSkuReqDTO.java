package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsSkuReqDTO implements Serializable {
    private Integer skuId;

    private Integer gdsId;

    private String skuName;

    private Integer packPrice;

    private Integer packTimes;

    private Integer packDay;

    private Integer showOrder;

    private String status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(Integer packPrice) {
        this.packPrice = packPrice;
    }

    public Integer getPackTimes() {
        return packTimes;
    }

    public void setPackTimes(Integer packTimes) {
        this.packTimes = packTimes;
    }

    public Integer getPackDay() {
        return packDay;
    }

    public void setPackDay(Integer packDay) {
        this.packDay = packDay;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
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
}
