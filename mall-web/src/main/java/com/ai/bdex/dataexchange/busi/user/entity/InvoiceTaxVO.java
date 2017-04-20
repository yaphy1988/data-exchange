package com.ai.bdex.dataexchange.busi.user.entity;

import java.io.Serializable;
import java.util.Date;

public class InvoiceTaxVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.TAX_ID
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private Long taxId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.staff_id
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String staffId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.INVOICE_TITLE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String invoiceTitle;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.TAXPAYER_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String taxpayerNo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.CONTACT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String contactInfo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.REL_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String relName;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.PHONE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String phone;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.BANK_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String bankName;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.ACCT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String acctInfo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.VFS_ID1
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String vfsId1;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.TAX_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String taxNo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.Organ_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String organCode;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.STATUS
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String status;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.PROVINCE_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String provinceCode;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String createStaff;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.CREATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String updateStaff;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private Date updateTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_chnl_invoice_tax.check_DESC
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   private String checkDesc;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.TAX_ID
    *
    * @return the value of t_chnl_invoice_tax.TAX_ID
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public Long getTaxId() {
       return taxId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.TAX_ID
    *
    * @param taxId the value for t_chnl_invoice_tax.TAX_ID
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setTaxId(Long taxId) {
       this.taxId = taxId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.staff_id
    *
    * @return the value of t_chnl_invoice_tax.staff_id
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.staff_id
    *
    * @param staffId the value for t_chnl_invoice_tax.staff_id
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.INVOICE_TITLE
    *
    * @return the value of t_chnl_invoice_tax.INVOICE_TITLE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getInvoiceTitle() {
       return invoiceTitle;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.INVOICE_TITLE
    *
    * @param invoiceTitle the value for t_chnl_invoice_tax.INVOICE_TITLE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setInvoiceTitle(String invoiceTitle) {
       this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.TAXPAYER_NO
    *
    * @return the value of t_chnl_invoice_tax.TAXPAYER_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getTaxpayerNo() {
       return taxpayerNo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.TAXPAYER_NO
    *
    * @param taxpayerNo the value for t_chnl_invoice_tax.TAXPAYER_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setTaxpayerNo(String taxpayerNo) {
       this.taxpayerNo = taxpayerNo == null ? null : taxpayerNo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.CONTACT_INFO
    *
    * @return the value of t_chnl_invoice_tax.CONTACT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getContactInfo() {
       return contactInfo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.CONTACT_INFO
    *
    * @param contactInfo the value for t_chnl_invoice_tax.CONTACT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setContactInfo(String contactInfo) {
       this.contactInfo = contactInfo == null ? null : contactInfo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.REL_NAME
    *
    * @return the value of t_chnl_invoice_tax.REL_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getRelName() {
       return relName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.REL_NAME
    *
    * @param relName the value for t_chnl_invoice_tax.REL_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setRelName(String relName) {
       this.relName = relName == null ? null : relName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.PHONE
    *
    * @return the value of t_chnl_invoice_tax.PHONE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getPhone() {
       return phone;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.PHONE
    *
    * @param phone the value for t_chnl_invoice_tax.PHONE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setPhone(String phone) {
       this.phone = phone == null ? null : phone.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.BANK_NAME
    *
    * @return the value of t_chnl_invoice_tax.BANK_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getBankName() {
       return bankName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.BANK_NAME
    *
    * @param bankName the value for t_chnl_invoice_tax.BANK_NAME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setBankName(String bankName) {
       this.bankName = bankName == null ? null : bankName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.ACCT_INFO
    *
    * @return the value of t_chnl_invoice_tax.ACCT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getAcctInfo() {
       return acctInfo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.ACCT_INFO
    *
    * @param acctInfo the value for t_chnl_invoice_tax.ACCT_INFO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setAcctInfo(String acctInfo) {
       this.acctInfo = acctInfo == null ? null : acctInfo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.VFS_ID1
    *
    * @return the value of t_chnl_invoice_tax.VFS_ID1
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getVfsId1() {
       return vfsId1;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.VFS_ID1
    *
    * @param vfsId1 the value for t_chnl_invoice_tax.VFS_ID1
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setVfsId1(String vfsId1) {
       this.vfsId1 = vfsId1 == null ? null : vfsId1.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.TAX_NO
    *
    * @return the value of t_chnl_invoice_tax.TAX_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getTaxNo() {
       return taxNo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.TAX_NO
    *
    * @param taxNo the value for t_chnl_invoice_tax.TAX_NO
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setTaxNo(String taxNo) {
       this.taxNo = taxNo == null ? null : taxNo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.Organ_CODE
    *
    * @return the value of t_chnl_invoice_tax.Organ_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getOrganCode() {
       return organCode;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.Organ_CODE
    *
    * @param organCode the value for t_chnl_invoice_tax.Organ_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setOrganCode(String organCode) {
       this.organCode = organCode == null ? null : organCode.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.STATUS
    *
    * @return the value of t_chnl_invoice_tax.STATUS
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.STATUS
    *
    * @param status the value for t_chnl_invoice_tax.STATUS
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.PROVINCE_CODE
    *
    * @return the value of t_chnl_invoice_tax.PROVINCE_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getProvinceCode() {
       return provinceCode;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.PROVINCE_CODE
    *
    * @param provinceCode the value for t_chnl_invoice_tax.PROVINCE_CODE
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setProvinceCode(String provinceCode) {
       this.provinceCode = provinceCode == null ? null : provinceCode.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.CREATE_STAFF
    *
    * @return the value of t_chnl_invoice_tax.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getCreateStaff() {
       return createStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.CREATE_STAFF
    *
    * @param createStaff the value for t_chnl_invoice_tax.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setCreateStaff(String createStaff) {
       this.createStaff = createStaff == null ? null : createStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.CREATE_TIME
    *
    * @return the value of t_chnl_invoice_tax.CREATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.CREATE_TIME
    *
    * @param createTime the value for t_chnl_invoice_tax.CREATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.UPDATE_STAFF
    *
    * @return the value of t_chnl_invoice_tax.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getUpdateStaff() {
       return updateStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.UPDATE_STAFF
    *
    * @param updateStaff the value for t_chnl_invoice_tax.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setUpdateStaff(String updateStaff) {
       this.updateStaff = updateStaff == null ? null : updateStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.UPDATE_TIME
    *
    * @return the value of t_chnl_invoice_tax.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.UPDATE_TIME
    *
    * @param updateTime the value for t_chnl_invoice_tax.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_chnl_invoice_tax.check_DESC
    *
    * @return the value of t_chnl_invoice_tax.check_DESC
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public String getCheckDesc() {
       return checkDesc;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_chnl_invoice_tax.check_DESC
    *
    * @param checkDesc the value for t_chnl_invoice_tax.check_DESC
    *
    * @mbg.generated Mon Apr 17 20:36:45 CST 2017
    */
   public void setCheckDesc(String checkDesc) {
       this.checkDesc = checkDesc == null ? null : checkDesc.trim();
   }
}