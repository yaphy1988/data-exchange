package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class OrdMainInfoReqDTO  extends BaseInfo{ 
    private String orderId;
    private Integer orderAmount;
    private Long orderMoney; 
    private Long realMoney;
    private String staffId;
    private String shopId;
    private Date orderTime;
    private String payFlag;
    private Date payTime;
    private String orderStatus;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String orderType;
    private String source;
    private String buyerMsg;
    private Date createTime;
    private String createStaff;
    private Date updateTime;
    private String updateStaff;
    private String shopProvinceCode;
    private String payWay;
    private String invoiceModType;
    private String invoiceStatus;
    private List<String> invoiceModTypeList;//发票类型List
	private List<String> ordertypeList;//发票类型List
	public List<String> getordertypeList() {
		return ordertypeList;
	}
	private Date startTime;
	private Date endTime;

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

	public List<String> getOrdertypeList() {
		return ordertypeList;
	}

	public void setOrdertypeList(List<String> ordertypeList) {
		this.ordertypeList = ordertypeList;
	}

	public void setordertypeList(List<String> ordertypeList) {
		this.ordertypeList = ordertypeList;
	}

	public List<String> getInvoiceModTypeList() {
		return invoiceModTypeList;
	}
	public void setInvoiceModTypeList(List<String> invoiceModTypeList) {
		this.invoiceModTypeList = invoiceModTypeList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Long getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Long getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(Long realMoney) {
		this.realMoney = realMoney;
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
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBuyerMsg() {
		return buyerMsg;
	}
	public void setBuyerMsg(String buyerMsg) {
		this.buyerMsg = buyerMsg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateStaff() {
		return updateStaff;
	}
	public void setUpdateStaff(String updateStaff) {
		this.updateStaff = updateStaff;
	}
	public String getShopProvinceCode() {
		return shopProvinceCode;
	}
	public void setShopProvinceCode(String shopProvinceCode) {
		this.shopProvinceCode = shopProvinceCode;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getInvoiceModType() {
		return invoiceModType;
	}
	public void setInvoiceModType(String invoiceModType) {
		this.invoiceModType = invoiceModType;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
    
}