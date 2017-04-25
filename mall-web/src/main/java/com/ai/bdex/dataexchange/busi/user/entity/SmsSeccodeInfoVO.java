package com.ai.bdex.dataexchange.busi.user.entity;

import java.io.Serializable;
import java.util.Date;

public class SmsSeccodeInfoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String phoneNo;
	
	private String lastTocken;
	
	private String busiType;
	
	private Date sendTime;
	
	private String securityCode;
	
	private String inputSecurityCode;
	
	private String sessionId;
	
	private Long staffId;
	
	private String createStaff;
	
	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getInputSecurityCode() {
		return inputSecurityCode;
	}
	public void setInputSecurityCode(String inputSecurityCode) {
		this.inputSecurityCode = inputSecurityCode;
	}
	private String tocken;
	public String getTocken() {
		return tocken;
	}
	public void setTocken(String tocken) {
		this.tocken = tocken;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getLastTocken() {
		return lastTocken;
	}
	public void setLastTocken(String lastTocken) {
		this.lastTocken = lastTocken;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

}
