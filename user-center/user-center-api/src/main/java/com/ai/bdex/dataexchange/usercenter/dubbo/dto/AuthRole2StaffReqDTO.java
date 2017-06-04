package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by yx on 2017/6/5.
 */
public class AuthRole2StaffReqDTO extends BaseInfo {

    private String staffId;

    private Integer roleId;

    private String status;

    private String updateId;

    private Date updateDate;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
