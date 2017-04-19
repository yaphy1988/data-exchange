package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthStaffPassDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String staffId;
	
	private String staffPasswd;
	
	private Date invalidTime;
	
	private String isFirst;
	
	private String createStaff;
	
	private Date createTime;
	
	private String updateStaff;
	
	private Date updateTime;
	
	private String passwdFlag;//0:未加密，1：已加密

	public String getPasswdFlag() {
		return passwdFlag;
	}

	public void setPasswdFlag(String passwdFlag) {
		this.passwdFlag = passwdFlag;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffPasswd() {
		return staffPasswd;
	}

	public void setStaffPasswd(String staffPasswd) {
		this.staffPasswd = staffPasswd;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
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
