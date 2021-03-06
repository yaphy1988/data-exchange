package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class LoginInfoDTO extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	
	private String inputVerifyCode;
	
	private String loginIp;
	
	private String remark;
	
	private String loginPwd;
	
	private String sessionVerifyCode;

	public String getSessionVerifyCode() {
		return sessionVerifyCode;
	}

	public void setSessionVerifyCode(String sessionVerifyCode) {
		this.sessionVerifyCode = sessionVerifyCode;
	}

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
