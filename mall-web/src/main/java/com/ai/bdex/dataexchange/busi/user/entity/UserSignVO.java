package com.ai.bdex.dataexchange.busi.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserSignVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private String staffId;
   private String email;
   private String password;
   private String activeFlag;
   private Date createTime;
   private Date updateTime;
   
   private String confirmPass;//确认密码
   private String signpass;//输入密码
   private String smsCode;//短信验证码
   private String tocken;//短信验证的tocken
   private String phoneNo;
   private String serialNumber;
   public String getSerialNumber() {
	return serialNumber;
}

public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public String getTocken() {
	return tocken;
}

public void setTocken(String tocken) {
	this.tocken = tocken;
}

public String getSmsCode() {
	return smsCode;
}

public void setSmsCode(String smsCode) {
	this.smsCode = smsCode;
}

public String getConfirmPass() {
	return confirmPass;
}

public void setConfirmPass(String confirmPass) {
	this.confirmPass = confirmPass;
}

public String getSignpass() {
	return signpass;
}

public void setSignpass(String signpass) {
	this.signpass = signpass;
}

public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.STAFF_ID
    *
    * @param staffId the value for t_auth_staff_sign.STAFF_ID
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff_sign.EMAIL
    *
    * @return the value of t_auth_staff_sign.EMAIL
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getEmail() {
       return email;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.EMAIL
    *
    * @param email the value for t_auth_staff_sign.EMAIL
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setEmail(String email) {
       this.email = email == null ? null : email.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff_sign.PASSWORD
    *
    * @return the value of t_auth_staff_sign.PASSWORD
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getPassword() {
       return password;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.PASSWORD
    *
    * @param password the value for t_auth_staff_sign.PASSWORD
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setPassword(String password) {
       this.password = password == null ? null : password.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff_sign.ACTIVE_FLAG
    *
    * @return the value of t_auth_staff_sign.ACTIVE_FLAG
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getActiveFlag() {
       return activeFlag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.ACTIVE_FLAG
    *
    * @param activeFlag the value for t_auth_staff_sign.ACTIVE_FLAG
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setActiveFlag(String activeFlag) {
       this.activeFlag = activeFlag == null ? null : activeFlag.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff_sign.CREATE_TIME
    *
    * @return the value of t_auth_staff_sign.CREATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.CREATE_TIME
    *
    * @param createTime the value for t_auth_staff_sign.CREATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff_sign.UPDATE_TIME
    *
    * @return the value of t_auth_staff_sign.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff_sign.UPDATE_TIME
    *
    * @param updateTime the value for t_auth_staff_sign.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }
}
