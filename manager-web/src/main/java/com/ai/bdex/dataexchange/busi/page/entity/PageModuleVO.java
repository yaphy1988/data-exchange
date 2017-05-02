package com.ai.bdex.dataexchange.busi.page.entity;

import java.util.Date;

public class PageModuleVO {

    private Integer moduleId;

    private String moduleName;

    private String moduleType;
    private String adPlace;

    private Integer modulePid;

    private Integer moduleCount;

    private Integer orderNo;

    private String remark;

    private String status;

    private String createStaffId;

    private Date createTime;
    private String updateStaffId;

    private Date updateTime;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getAdPlace() {
        return adPlace;
    }

    public void setAdPlace(String adPlace) {
        this.adPlace = adPlace == null ? null : adPlace.trim();
    }

    public Integer getModulePid() {
        return modulePid;
    }

    public void setModulePid(Integer modulePid) {
        this.modulePid = modulePid;
    }

    public Integer getModuleCount() {
        return moduleCount;
    }
    public void setModuleCount(Integer moduleCount) {
        this.moduleCount = moduleCount;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getUpdateStaffId() {
        return updateStaffId;
    }


    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}