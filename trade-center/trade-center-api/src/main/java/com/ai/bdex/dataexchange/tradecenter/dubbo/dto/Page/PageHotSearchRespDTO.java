package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yx on 2017/4/18.
 */
public class PageHotSearchRespDTO implements Serializable { 
   private Integer searchId; 
   private String searchKey; 
   private String status; 
   private Integer infoOrder; 
   private String remark; 
   private String createStaffId; 
   private Date createTime; 
   private String updateStaffId; 
   private Date updateTime; 
   private String searchUrl;
	public Integer getSearchId() {
		return searchId;
	}
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getInfoOrder() {
		return infoOrder;
	}
	public void setInfoOrder(Integer infoOrder) {
		this.infoOrder = infoOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

}
