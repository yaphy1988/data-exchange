package com.ai.bdex.dataexchange.report.entity;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class UserBildingQueryInfo extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date startTime;

    private Date endTime;
    
    private String userId;
    
    private String serviceId;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
