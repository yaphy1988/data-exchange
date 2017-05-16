package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class OrdInfoReqDTO extends BaseInfo{
	    private String subOrder; 
	    private String orderId;
 	    private Integer orderAmount; 
	    private Long agentPrice;
        private Long orderPrice;
 	    private Long discountPrice;
 	    private Long orderMoney;
 	    private Date orderTime;
 	    private String staffId;
 	    private String shopId;
 	    private Integer catId;
 	     public Integer getCatId() {
			return catId;
		}
		public void setCatId(Integer catId) {
			this.catId = catId;
		}
		public Integer getCatFirst() {
			return catFirst;
		}
		public void setCatFirst(Integer catFirst) {
			this.catFirst = catFirst;
		}
		private Integer catFirst;
 	    private String productType;
 	    private Long gdsId;
 	    private String gdsName;
 	    private Long skuId;
 	    private String skuInfo;
 	    private String skuName;
 	    private Integer eachCount;
 	    private String provinceCode;
 	    private Date createTime;
 	    private String createStaff;
 	    private String updateStaff;
 	    private Date updateTime;
 	    private String repType;
 	    private Long stockId;
	    private String payFlag;
 	    private Date activeEndTime;
	    private Date payTime;
 	    private Long buyAllCount;
 	    private Long usedAllCount;
   	    private Long belanceAllCount;
 	    private String status;
 	    private String serviceName;
	    private String aipServiceId;
	    private String ordertype;

	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.serviceName = ordertype;
	}

	    public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public String getAipServiceId() {
			return aipServiceId;
		}
		public void setAipServiceId(String aipServiceId) {
			this.aipServiceId = aipServiceId;
		}
		public String getSubOrder() {
			return subOrder;
		}
		public void setSubOrder(String subOrder) {
			this.subOrder = subOrder;
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
		public Long getAgentPrice() {
			return agentPrice;
		}
		public void setAgentPrice(Long agentPrice) {
			this.agentPrice = agentPrice;
		}
		public Long getOrderPrice() {
			return orderPrice;
		}
		public void setOrderPrice(Long orderPrice) {
			this.orderPrice = orderPrice;
		}
		public Long getDiscountPrice() {
			return discountPrice;
		}
		public void setDiscountPrice(Long discountPrice) {
			this.discountPrice = discountPrice;
		}
		public Long getOrderMoney() {
			return orderMoney;
		}
		public void setOrderMoney(Long orderMoney) {
			this.orderMoney = orderMoney;
		}
		public Date getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(Date orderTime) {
			this.orderTime = orderTime;
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
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public Long getGdsId() {
			return gdsId;
		}
		public void setGdsId(Long gdsId) {
			this.gdsId = gdsId;
		}
		public String getGdsName() {
			return gdsName;
		}
		public void setGdsName(String gdsName) {
			this.gdsName = gdsName;
		}
		public Long getSkuId() {
			return skuId;
		}
		public void setSkuId(Long skuId) {
			this.skuId = skuId;
		}
		public String getSkuInfo() {
			return skuInfo;
		}
		public void setSkuInfo(String skuInfo) {
			this.skuInfo = skuInfo;
		}
		public String getSkuName() {
			return skuName;
		}
		public void setSkuName(String skuName) {
			this.skuName = skuName;
		}
		public Integer getEachCount() {
			return eachCount;
		}
		public void setEachCount(Integer eachCount) {
			this.eachCount = eachCount;
		}
		public String getProvinceCode() {
			return provinceCode;
		}
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
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
		public String getRepType() {
			return repType;
		}
		public void setRepType(String repType) {
			this.repType = repType;
		}
		public Long getStockId() {
			return stockId;
		}
		public void setStockId(Long stockId) {
			this.stockId = stockId;
		}
		public String getPayFlag() {
			return payFlag;
		}
		public void setPayFlag(String payFlag) {
			this.payFlag = payFlag;
		}
		public Date getActiveEndTime() {
			return activeEndTime;
		}
		public void setActiveEndTime(Date activeEndTime) {
			this.activeEndTime = activeEndTime;
		}
		public Date getPayTime() {
			return payTime;
		}
		public void setPayTime(Date payTime) {
			this.payTime = payTime;
		}
		public Long getBuyAllCount() {
			return buyAllCount;
		}
		public void setBuyAllCount(Long buyAllCount) {
			this.buyAllCount = buyAllCount;
		}
		public Long getUsedAllCount() {
			return usedAllCount;
		}
		public void setUsedAllCount(Long usedAllCount) {
			this.usedAllCount = usedAllCount;
		}
		public Long getBelanceAllCount() {
			return belanceAllCount;
		}
		public void setBelanceAllCount(Long belanceAllCount) {
			this.belanceAllCount = belanceAllCount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
 	    
}
