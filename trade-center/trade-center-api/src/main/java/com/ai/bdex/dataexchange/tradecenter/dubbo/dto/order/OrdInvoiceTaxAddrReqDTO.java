package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class OrdInvoiceTaxAddrReqDTO   extends BaseInfo{ 
   private Long orderTaxId; 
   private String orderId; 
   private String staffId; 
   private String shopId; 
   private String invoiceTitle; 
   private String phone; 
   private String province; 
   private String cityCode; 
   private String countyCode; 
   private String townCode; 
   private String contactInfo; 
   private String status; 
   private String createStaff; 
   private Date createTime; 
   private String updateStaff; 
   private Date updateTime; 
   private String invoiceDesc;
	public Long getOrderTaxId() {
		return orderTaxId;
	}
	public void setOrderTaxId(Long orderTaxId) {
		this.orderTaxId = orderTaxId;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
