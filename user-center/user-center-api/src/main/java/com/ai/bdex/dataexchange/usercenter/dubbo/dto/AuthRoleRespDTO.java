package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.Date;

/**
 * Created by yx on 2017/6/2.
 */
public class AuthRoleRespDTO extends BaseResponseDTO {

    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String status;

    private String updateId;

    private Date updateDate;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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
