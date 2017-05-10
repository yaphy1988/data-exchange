package com.ai.bdex.dataexchange.busi.page.entity;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class SortInfoVO  extends BaseInfo{ 
    private Integer sortId; 
    private String sortType; 
    private String sortLevel;
    private String sortName;
    private Integer parentSortId; 
    private String orderNo;
    private String status; 
    private String createStaffId; 
    private Date createTime;
    private String updateStaffId;
    private Date updateTime;
    private SortContentVO sortContentRespDTO;

public SortContentVO getSortContentRespDTO() {
		return sortContentRespDTO;
	}

	public void setSortContentRespDTO(SortContentVO sortContentRespDTO) {
		this.sortContentRespDTO = sortContentRespDTO;
	}

public Integer getSortId() {
	return sortId;
}

public void setSortId(Integer sortId) {
	this.sortId = sortId;
}

public String getSortType() {
	return sortType;
}

public void setSortType(String sortType) {
	this.sortType = sortType;
}

public String getSortLevel() {
	return sortLevel;
}

public void setSortLevel(String sortLevel) {
	this.sortLevel = sortLevel;
}

public String getSortName() {
	return sortName;
}

public void setSortName(String sortName) {
	this.sortName = sortName;
}

public Integer getParentSortId() {
	return parentSortId;
}

public void setParentSortId(Integer parentSortId) {
	this.parentSortId = parentSortId;
}

public String getOrderNo() {
	return orderNo;
}

public void setOrderNo(String orderNo) {
	this.orderNo = orderNo;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getCreateStaffId() {
	return createStaffId;
}

public void setCreateStaffId(String createStaffId) {
	this.createStaffId = createStaffId;
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
	this.updateStaffId = updateStaffId;
}

public Date getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
   
}
