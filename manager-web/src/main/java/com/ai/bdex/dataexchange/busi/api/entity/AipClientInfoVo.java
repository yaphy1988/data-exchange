package com.ai.bdex.dataexchange.busi.api.entity;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class AipClientInfoVo extends BaseInfo{
	private String clientId;
	private String clientSecret;
	private String userName;
	private String password;
	private String status;

	private String createStaff;
	private String updateStaff;
	private Date effectiveTime;

	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
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
	
	
}
