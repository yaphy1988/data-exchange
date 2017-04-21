package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

public class SortContentRespDTO {
 
   private Integer sortContentId;
 
   private String contentName; 
   private String contentLink; 
   private String vfsId;
 
   private String orderNo;
 
   private String moreUrl;
 
   private String status;
 
   private String createStaffId;
 
   private Date createTime;
 
   private String updateStaffId;
 
   private Date updateTime;

	public Integer getSortContentId() {
		return sortContentId;
	}
	
	public void setSortContentId(Integer sortContentId) {
		this.sortContentId = sortContentId;
	}
	
	public String getContentName() {
		return contentName;
	}
	
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	public String getContentLink() {
		return contentLink;
	}
	
	public void setContentLink(String contentLink) {
		this.contentLink = contentLink;
	}
	
	public String getVfsId() {
		return vfsId;
	}
	
	public void setVfsId(String vfsId) {
		this.vfsId = vfsId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getMoreUrl() {
		return moreUrl;
	}
	
	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
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
