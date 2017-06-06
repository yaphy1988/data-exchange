package com.ai.bdex.dataexchange.report.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

public class OrdInvoiceTaxReqDTO  extends BaseInfo{ 
   private Long orderTaxId; 
   private Long taxId; 
   private String orderId; 
   private String staffId; 
   private String shopId; 
   private String invoiceTitle; 
   private String taxpayerNo; 
   private String contactInfo; 
   private String phone; 
   private String bankName; 
   private String acctInfo; 
   private String vfsId1; 
   private String vfsId2; 
   private String vfsId3; 
   private String vfsId4; 
   private String status; 
   private String provinceCode; 
   private String createStaff; 
   private Date createTime; 
   private String updateStaff; 
   private Date updateTime; 
   private String invoiceDesc;
	private String invoiceType;//发票类型

	//查询条件
	private Date startTime;

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

	private Date endTime;

	public long getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(long applyMoney) {
		this.applyMoney = applyMoney;
	}

	private long applyMoney;//申请金额


	private String taxAddr;//邮寄地址

    private String taxAddrPhone;//邮寄电话

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getTaxAddr() {
		return taxAddr;
	}

	public void setTaxAddr(String taxAddr) {
		this.taxAddr = taxAddr;
	}

	public String getTaxAddrPhone() {
		return taxAddrPhone;
	}

	public void setTaxAddrPhone(String taxAddrPhone) {
		this.taxAddrPhone = taxAddrPhone;
	}

	public Long getOrderTaxId() {
		return orderTaxId;
	}

	public void setOrderTaxId(Long orderTaxId) {
		this.orderTaxId = orderTaxId;
	}
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getTaxpayerNo() {
		return taxpayerNo;
	}
	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAcctInfo() {
		return acctInfo;
	}
	public void setAcctInfo(String acctInfo) {
		this.acctInfo = acctInfo;
	}
	public String getVfsId1() {
		return vfsId1;
	}
	public void setVfsId1(String vfsId1) {
		this.vfsId1 = vfsId1;
	}
	public String getVfsId2() {
		return vfsId2;
	}
	public void setVfsId2(String vfsId2) {
		this.vfsId2 = vfsId2;
	}
	public String getVfsId3() {
		return vfsId3;
	}
	public void setVfsId3(String vfsId3) {
		this.vfsId3 = vfsId3;
	}
	public String getVfsId4() {
		return vfsId4;
	}
	public void setVfsId4(String vfsId4) {
		this.vfsId4 = vfsId4;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
	public String getInvoiceDesc() {
		return invoiceDesc;
	}
	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}
   
}
