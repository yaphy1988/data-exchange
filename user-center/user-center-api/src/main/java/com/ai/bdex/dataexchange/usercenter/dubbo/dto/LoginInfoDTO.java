package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import java.io.Serializable;

public class LoginInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	
	private String inputVerifyCode;
	
	private String loginIp;
	
	private String remark;
	
	private String loginPwd;

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getInputVerifyCode() {
		return inputVerifyCode;
	}

	public void setInputVerifyCode(String inputVerifyCode) {
		this.inputVerifyCode = inputVerifyCode;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
