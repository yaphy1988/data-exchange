package com.ai.bdex.dataexchange.busi.base.entity;

/**
 * Created by yx on 2017/5/9.
 */
public class BaseAdminAreaInfoVO {
    private String areaCode;

    private String areaName;

    private String parentAreaCode;

    private String areaLevel;

    private Long areaOrder;

    private String status;

    private String areaCodeShort;

    private String centerFlag;

    private String adminAreaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Long getAreaOrder() {
        return areaOrder;
    }

    public void setAreaOrder(Long areaOrder) {
        this.areaOrder = areaOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAreaCodeShort() {
        return areaCodeShort;
    }

    public void setAreaCodeShort(String areaCodeShort) {
        this.areaCodeShort = areaCodeShort;
    }

    public String getCenterFlag() {
        return centerFlag;
    }

    public void setCenterFlag(String centerFlag) {
        this.centerFlag = centerFlag;
    }

    public String getAdminAreaCode() {
        return adminAreaCode;
    }

    public void setAdminAreaCode(String adminAreaCode) {
        this.adminAreaCode = adminAreaCode;
    }
}
