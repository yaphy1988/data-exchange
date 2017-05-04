package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class OrdTimeLimitReqDTO extends BaseInfo{ 
   private Long timeLimitNo; 
   private String provinceCode; 
   private Long cancelTime; 
   private Long catId; 
   private Long limitTime; 
   private String isValid; 
   private String isActivity; 
   private String createStaff; 
   private Date createTime; 
   private String updateStaff; 
   private Date updateTime;
	public Long getTimeLimitNo() {
		return timeLimitNo;
	}
	public void setTimeLimitNo(Long timeLimitNo) {
		this.timeLimitNo = timeLimitNo;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public Long getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Long cancelTime) {
		this.cancelTime = cancelTime;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public Long getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(Long limitTime) {
		this.limitTime = limitTime;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}
	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateStaff() {
		return updateStaff;
	}
	public void setUpdateStaff(String updateStaff) {
		this.updateStaff = updateStaff;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	   

}
