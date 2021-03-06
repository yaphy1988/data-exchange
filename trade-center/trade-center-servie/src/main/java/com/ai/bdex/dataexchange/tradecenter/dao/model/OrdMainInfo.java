package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class OrdMainInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_AMOUNT
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Integer orderAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Long orderMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.REAL_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Long realMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date orderTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.PAY_FLAG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String payFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.PAY_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date payTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String provinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String cityCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String countyCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.ORDER_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String orderType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.SOURCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.BUYER_MSG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String buyerMsg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.SHOP_PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String shopProvinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.PAY_WAY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String payWay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.INVOICE_MOD_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String invoiceModType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_main_info.INVOICE_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String invoiceStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_ID
     *
     * @return the value of t_ord_main_info.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_ID
     *
     * @param orderId the value for t_ord_main_info.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_AMOUNT
     *
     * @return the value of t_ord_main_info.ORDER_AMOUNT
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Integer getOrderAmount() {
        return orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_AMOUNT
     *
     * @param orderAmount the value for t_ord_main_info.ORDER_AMOUNT
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_MONEY
     *
     * @return the value of t_ord_main_info.ORDER_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Long getOrderMoney() {
        return orderMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_MONEY
     *
     * @param orderMoney the value for t_ord_main_info.ORDER_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.REAL_MONEY
     *
     * @return the value of t_ord_main_info.REAL_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Long getRealMoney() {
        return realMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.REAL_MONEY
     *
     * @param realMoney the value for t_ord_main_info.REAL_MONEY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.STAFF_ID
     *
     * @return the value of t_ord_main_info.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.STAFF_ID
     *
     * @param staffId the value for t_ord_main_info.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.SHOP_ID
     *
     * @return the value of t_ord_main_info.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.SHOP_ID
     *
     * @param shopId the value for t_ord_main_info.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_TIME
     *
     * @return the value of t_ord_main_info.ORDER_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_TIME
     *
     * @param orderTime the value for t_ord_main_info.ORDER_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.PAY_FLAG
     *
     * @return the value of t_ord_main_info.PAY_FLAG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getPayFlag() {
        return payFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.PAY_FLAG
     *
     * @param payFlag the value for t_ord_main_info.PAY_FLAG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.PAY_TIME
     *
     * @return the value of t_ord_main_info.PAY_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.PAY_TIME
     *
     * @param payTime the value for t_ord_main_info.PAY_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_STATUS
     *
     * @return the value of t_ord_main_info.ORDER_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_STATUS
     *
     * @param orderStatus the value for t_ord_main_info.ORDER_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.PROVINCE_CODE
     *
     * @return the value of t_ord_main_info.PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.PROVINCE_CODE
     *
     * @param provinceCode the value for t_ord_main_info.PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.CITY_CODE
     *
     * @return the value of t_ord_main_info.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.CITY_CODE
     *
     * @param cityCode the value for t_ord_main_info.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.COUNTY_CODE
     *
     * @return the value of t_ord_main_info.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.COUNTY_CODE
     *
     * @param countyCode the value for t_ord_main_info.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.ORDER_TYPE
     *
     * @return the value of t_ord_main_info.ORDER_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.ORDER_TYPE
     *
     * @param orderType the value for t_ord_main_info.ORDER_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.SOURCE
     *
     * @return the value of t_ord_main_info.SOURCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.SOURCE
     *
     * @param source the value for t_ord_main_info.SOURCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.BUYER_MSG
     *
     * @return the value of t_ord_main_info.BUYER_MSG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getBuyerMsg() {
        return buyerMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.BUYER_MSG
     *
     * @param buyerMsg the value for t_ord_main_info.BUYER_MSG
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg == null ? null : buyerMsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.CREATE_TIME
     *
     * @return the value of t_ord_main_info.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.CREATE_TIME
     *
     * @param createTime the value for t_ord_main_info.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.CREATE_STAFF
     *
     * @return the value of t_ord_main_info.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.CREATE_STAFF
     *
     * @param createStaff the value for t_ord_main_info.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.UPDATE_TIME
     *
     * @return the value of t_ord_main_info.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.UPDATE_TIME
     *
     * @param updateTime the value for t_ord_main_info.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.UPDATE_STAFF
     *
     * @return the value of t_ord_main_info.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.UPDATE_STAFF
     *
     * @param updateStaff the value for t_ord_main_info.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.SHOP_PROVINCE_CODE
     *
     * @return the value of t_ord_main_info.SHOP_PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getShopProvinceCode() {
        return shopProvinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.SHOP_PROVINCE_CODE
     *
     * @param shopProvinceCode the value for t_ord_main_info.SHOP_PROVINCE_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setShopProvinceCode(String shopProvinceCode) {
        this.shopProvinceCode = shopProvinceCode == null ? null : shopProvinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.PAY_WAY
     *
     * @return the value of t_ord_main_info.PAY_WAY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.PAY_WAY
     *
     * @param payWay the value for t_ord_main_info.PAY_WAY
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.INVOICE_MOD_TYPE
     *
     * @return the value of t_ord_main_info.INVOICE_MOD_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getInvoiceModType() {
        return invoiceModType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.INVOICE_MOD_TYPE
     *
     * @param invoiceModType the value for t_ord_main_info.INVOICE_MOD_TYPE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setInvoiceModType(String invoiceModType) {
        this.invoiceModType = invoiceModType == null ? null : invoiceModType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_main_info.INVOICE_STATUS
     *
     * @return the value of t_ord_main_info.INVOICE_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_main_info.INVOICE_STATUS
     *
     * @param invoiceStatus the value for t_ord_main_info.INVOICE_STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus == null ? null : invoiceStatus.trim();
    }
}