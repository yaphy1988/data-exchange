package com.ai.bdex.dataexchange.busi.user.entity;

import java.io.Serializable;

public class PassWordVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String staffId;
	
	private String oldpasswd;
	
	private String newpasswd;
	
	private String confirmPass;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
}
