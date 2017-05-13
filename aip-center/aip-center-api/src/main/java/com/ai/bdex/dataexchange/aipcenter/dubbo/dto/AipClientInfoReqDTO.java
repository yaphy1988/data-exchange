package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class AipClientInfoReqDTO  extends BaseInfo{
	private static final long serialVersionUID = 1L;
	private String clientId;
	private String username;
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

	
	
}
