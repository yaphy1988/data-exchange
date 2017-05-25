package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by yx on 2017/5/12.
 */
public class AipProviderServiceInfoReqDTO extends BaseInfo {

    private String pServiceId;

    private String version;

    private String status;

    private String serviceName;

    private String serviceDesc;

    private String type;

    private String implMethod;

    private String providerId;

    private Date createTime;

    private String createStaff;

    private Date updateTime;

    private String updateStaff;

    private String pServiceCode;

    public String getpServiceId() {
        return pServiceId;
    }

    public void setpServiceId(String pServiceId) {
        this.pServiceId = pServiceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImplMethod() {
        return implMethod;
    }

    public void setImplMethod(String implMethod) {
        this.implMethod = implMethod;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getpServiceCode() {
        return pServiceCode;
    }

    public void setpServiceCode(String pServiceCode) {
        this.pServiceCode = pServiceCode;
    }
}
