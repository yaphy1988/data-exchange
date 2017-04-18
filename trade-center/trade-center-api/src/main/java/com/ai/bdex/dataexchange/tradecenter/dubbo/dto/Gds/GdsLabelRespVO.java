package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsLabelRespVO implements Serializable {
    private Integer labId;

    private Integer gdsId;

    private String labName;

    private String labColor;

    private Integer showOrder;

    private String ifEdit;

    private String status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabColor() {
        return labColor;
    }

    public void setLabColor(String labColor) {
        this.labColor = labColor;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getIfEdit() {
        return ifEdit;
    }

    public void setIfEdit(String ifEdit) {
        this.ifEdit = ifEdit;
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
