package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class AipClientInfoReqDTO  extends BaseInfo{
	private static final long serialVersionUID = 1L;
	private String clientId;
	private String clientSecret;
	private String userId;
	private String username;
	private String status;
	private Date createTime;
	private Date updateTime;
	private String updateStaff;
	private Date effectiveTime;
	private String password;
	private List<String> statusList;
	private Date fromCreateTime;
	private Date toCreateTime;
	private String createStaff;
	private Date fromEffectiveTime;
	private Date toEffectiveTime;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}
	public Date getFromCreateTime() {
		return fromCreateTime;
	}
	public void setFromCreateTime(Date fromCreateTime) {
		this.fromCreateTime = fromCreateTime;
	}
	public Date getToCreateTime() {
		return toCreateTime;
	}
	public void setToCreateTime(Date toCreateTime) {
		this.toCreateTime = toCreateTime;
	}
	public Date getFromEffectiveTime() {
		return fromEffectiveTime;
	}
	public void setFromEffectiveTime(Date fromEffectiveTime) {
		this.fromEffectiveTime = fromEffectiveTime;
	}
	public Date getToEffectiveTime() {
		return toEffectiveTime;
	}
	public void setToEffectiveTime(Date toEffectiveTime) {
		this.toEffectiveTime = toEffectiveTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
