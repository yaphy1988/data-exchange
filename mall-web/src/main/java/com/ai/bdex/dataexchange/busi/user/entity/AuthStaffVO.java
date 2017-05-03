package com.ai.bdex.dataexchange.busi.user.entity;

import java.util.Date;

public class AuthStaffVO {
	
   private String staffId;
   private String staffName;
   private String staffType;
   private String aliasName;
   private String contactInfo;
   private String officeId;
   private String createStaff;
   private Date createTime;
   private String updateStaff;
   private Date updateTime;
   private String serialNumber;
   private String email;
   private String authenFlag;
   private Date birthday;
   private String gender;
   private String job;
   private String qq;
   private String weChat;
   private String disturbFlag;
   private Date startDate;
   private Date endDate;
   private String createFrom;
   private String lockStatus;
   private Date lockTime;
   private Date lastLogin;
   private String headVfsid;
   private String staffFlag;
   
   public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.STAFF_ID
    *
    * @param staffId the value for t_auth_staff.STAFF_ID
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.STAFF_NAME
    *
    * @return the value of t_auth_staff.STAFF_NAME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getStaffName() {
       return staffName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.STAFF_NAME
    *
    * @param staffName the value for t_auth_staff.STAFF_NAME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setStaffName(String staffName) {
       this.staffName = staffName == null ? null : staffName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.STAFF_TYPE
    *
    * @return the value of t_auth_staff.STAFF_TYPE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getStaffType() {
       return staffType;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.STAFF_TYPE
    *
    * @param staffType the value for t_auth_staff.STAFF_TYPE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setStaffType(String staffType) {
       this.staffType = staffType == null ? null : staffType.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.ALIAS_NAME
    *
    * @return the value of t_auth_staff.ALIAS_NAME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getAliasName() {
       return aliasName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.ALIAS_NAME
    *
    * @param aliasName the value for t_auth_staff.ALIAS_NAME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setAliasName(String aliasName) {
       this.aliasName = aliasName == null ? null : aliasName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.CONTACT_INFO
    *
    * @return the value of t_auth_staff.CONTACT_INFO
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getContactInfo() {
       return contactInfo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.CONTACT_INFO
    *
    * @param contactInfo the value for t_auth_staff.CONTACT_INFO
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setContactInfo(String contactInfo) {
       this.contactInfo = contactInfo == null ? null : contactInfo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.OFFICE_ID
    *
    * @return the value of t_auth_staff.OFFICE_ID
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getOfficeId() {
       return officeId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.OFFICE_ID
    *
    * @param officeId the value for t_auth_staff.OFFICE_ID
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setOfficeId(String officeId) {
       this.officeId = officeId == null ? null : officeId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.CREATE_STAFF
    *
    * @return the value of t_auth_staff.CREATE_STAFF
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getCreateStaff() {
       return createStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.CREATE_STAFF
    *
    * @param createStaff the value for t_auth_staff.CREATE_STAFF
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setCreateStaff(String createStaff) {
       this.createStaff = createStaff == null ? null : createStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.CREATE_TIME
    *
    * @return the value of t_auth_staff.CREATE_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.CREATE_TIME
    *
    * @param createTime the value for t_auth_staff.CREATE_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.UPDATE_STAFF
    *
    * @return the value of t_auth_staff.UPDATE_STAFF
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getUpdateStaff() {
       return updateStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.UPDATE_STAFF
    *
    * @param updateStaff the value for t_auth_staff.UPDATE_STAFF
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setUpdateStaff(String updateStaff) {
       this.updateStaff = updateStaff == null ? null : updateStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.UPDATE_TIME
    *
    * @return the value of t_auth_staff.UPDATE_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.UPDATE_TIME
    *
    * @param updateTime the value for t_auth_staff.UPDATE_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.SERIAL_NUMBER
    *
    * @return the value of t_auth_staff.SERIAL_NUMBER
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getSerialNumber() {
       return serialNumber;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.SERIAL_NUMBER
    *
    * @param serialNumber the value for t_auth_staff.SERIAL_NUMBER
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setSerialNumber(String serialNumber) {
       this.serialNumber = serialNumber == null ? null : serialNumber.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.EMAIL
    *
    * @return the value of t_auth_staff.EMAIL
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getEmail() {
       return email;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.EMAIL
    *
    * @param email the value for t_auth_staff.EMAIL
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setEmail(String email) {
       this.email = email == null ? null : email.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.AUTHEN_FLAG
    *
    * @return the value of t_auth_staff.AUTHEN_FLAG
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getAuthenFlag() {
       return authenFlag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.AUTHEN_FLAG
    *
    * @param authenFlag the value for t_auth_staff.AUTHEN_FLAG
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setAuthenFlag(String authenFlag) {
       this.authenFlag = authenFlag == null ? null : authenFlag.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.BIRTHDAY
    *
    * @return the value of t_auth_staff.BIRTHDAY
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getBirthday() {
       return birthday;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.BIRTHDAY
    *
    * @param birthday the value for t_auth_staff.BIRTHDAY
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setBirthday(Date birthday) {
       this.birthday = birthday;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.GENDER
    *
    * @return the value of t_auth_staff.GENDER
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getGender() {
       return gender;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.GENDER
    *
    * @param gender the value for t_auth_staff.GENDER
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setGender(String gender) {
       this.gender = gender == null ? null : gender.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.JOB
    *
    * @return the value of t_auth_staff.JOB
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getJob() {
       return job;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.JOB
    *
    * @param job the value for t_auth_staff.JOB
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setJob(String job) {
       this.job = job == null ? null : job.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.QQ
    *
    * @return the value of t_auth_staff.QQ
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getQq() {
       return qq;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.QQ
    *
    * @param qq the value for t_auth_staff.QQ
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setQq(String qq) {
       this.qq = qq == null ? null : qq.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.WE_CHAT
    *
    * @return the value of t_auth_staff.WE_CHAT
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getWeChat() {
       return weChat;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.WE_CHAT
    *
    * @param weChat the value for t_auth_staff.WE_CHAT
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setWeChat(String weChat) {
       this.weChat = weChat == null ? null : weChat.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.DISTURB_FLAG
    *
    * @return the value of t_auth_staff.DISTURB_FLAG
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getDisturbFlag() {
       return disturbFlag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.DISTURB_FLAG
    *
    * @param disturbFlag the value for t_auth_staff.DISTURB_FLAG
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setDisturbFlag(String disturbFlag) {
       this.disturbFlag = disturbFlag == null ? null : disturbFlag.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.START_DATE
    *
    * @return the value of t_auth_staff.START_DATE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getStartDate() {
       return startDate;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.START_DATE
    *
    * @param startDate the value for t_auth_staff.START_DATE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setStartDate(Date startDate) {
       this.startDate = startDate;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.END_DATE
    *
    * @return the value of t_auth_staff.END_DATE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getEndDate() {
       return endDate;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.END_DATE
    *
    * @param endDate the value for t_auth_staff.END_DATE
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setEndDate(Date endDate) {
       this.endDate = endDate;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.CREATE_FROM
    *
    * @return the value of t_auth_staff.CREATE_FROM
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getCreateFrom() {
       return createFrom;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.CREATE_FROM
    *
    * @param createFrom the value for t_auth_staff.CREATE_FROM
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setCreateFrom(String createFrom) {
       this.createFrom = createFrom == null ? null : createFrom.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.LOCK_STATUS
    *
    * @return the value of t_auth_staff.LOCK_STATUS
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getLockStatus() {
       return lockStatus;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.LOCK_STATUS
    *
    * @param lockStatus the value for t_auth_staff.LOCK_STATUS
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setLockStatus(String lockStatus) {
       this.lockStatus = lockStatus == null ? null : lockStatus.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.LOCK_TIME
    *
    * @return the value of t_auth_staff.LOCK_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getLockTime() {
       return lockTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.LOCK_TIME
    *
    * @param lockTime the value for t_auth_staff.LOCK_TIME
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setLockTime(Date lockTime) {
       this.lockTime = lockTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.LAST_LOGIN
    *
    * @return the value of t_auth_staff.LAST_LOGIN
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public Date getLastLogin() {
       return lastLogin;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.LAST_LOGIN
    *
    * @param lastLogin the value for t_auth_staff.LAST_LOGIN
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setLastLogin(Date lastLogin) {
       this.lastLogin = lastLogin;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.head_VFSID
    *
    * @return the value of t_auth_staff.head_VFSID
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getHeadVfsid() {
       return headVfsid;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.head_VFSID
    *
    * @param headVfsid the value for t_auth_staff.head_VFSID
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setHeadVfsid(String headVfsid) {
       this.headVfsid = headVfsid == null ? null : headVfsid.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_auth_staff.staff_flag
    *
    * @return the value of t_auth_staff.staff_flag
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public String getStaffFlag() {
       return staffFlag;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_auth_staff.staff_flag
    *
    * @param staffFlag the value for t_auth_staff.staff_flag
    *
    * @mbg.generated Wed Apr 19 15:02:03 CST 2017
    */
   public void setStaffFlag(String staffFlag) {
       this.staffFlag = staffFlag == null ? null : staffFlag.trim();
   }
}
