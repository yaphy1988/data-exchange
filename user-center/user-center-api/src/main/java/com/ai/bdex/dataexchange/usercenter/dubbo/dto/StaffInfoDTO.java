package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.List;

public class StaffInfoDTO extends BaseResponseDTO {
	
	private static final long serialVersionUID = 1L;
	private String staffId;
	private String staffName;
	private String staffType;
	private String aliasName;
	private String contactInfo;
	private String officeId;
	private String serialNumber;
	private String email;
	private String authenFlag;
	private String isFirst;
	private boolean loginIn;
	private String loginType; //登陆类型（1：用户手动登陆，2：记住密码系统自动登陆）
	private List<String> menuUrls;

	public List<String> getMenuUrls() {
		return menuUrls;
	}
	public void setMenuUrls(List<String> menuUrls) {
		this.menuUrls = menuUrls;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public boolean isLoginIn() {
		return loginIn;
	}
	public void setLoginIn(boolean loginIn) {
		this.loginIn = loginIn;
	}
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthenFlag() {
		return authenFlag;
	}
	public void setAuthenFlag(String authenFlag) {
		this.authenFlag = authenFlag;
	}
}
