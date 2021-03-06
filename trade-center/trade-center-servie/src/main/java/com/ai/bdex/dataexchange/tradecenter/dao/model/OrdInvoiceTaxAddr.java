package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class OrdInvoiceTaxAddr {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.ORDER_TAX_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Long orderTaxId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.INVOICE_TITLE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String invoiceTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.PHONE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.PROVINCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String cityCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String countyCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.TOWN_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String townCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.CONTACT_INFO
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String contactInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_invoice_tax_addr.INVOICE_DESC
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    private String invoiceDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.ORDER_TAX_ID
     *
     * @return the value of t_ord_invoice_tax_addr.ORDER_TAX_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Long getOrderTaxId() {
        return orderTaxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.ORDER_TAX_ID
     *
     * @param orderTaxId the value for t_ord_invoice_tax_addr.ORDER_TAX_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderTaxId(Long orderTaxId) {
        this.orderTaxId = orderTaxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.ORDER_ID
     *
     * @return the value of t_ord_invoice_tax_addr.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.ORDER_ID
     *
     * @param orderId the value for t_ord_invoice_tax_addr.ORDER_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.STAFF_ID
     *
     * @return the value of t_ord_invoice_tax_addr.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.STAFF_ID
     *
     * @param staffId the value for t_ord_invoice_tax_addr.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.SHOP_ID
     *
     * @return the value of t_ord_invoice_tax_addr.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.SHOP_ID
     *
     * @param shopId the value for t_ord_invoice_tax_addr.SHOP_ID
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.INVOICE_TITLE
     *
     * @return the value of t_ord_invoice_tax_addr.INVOICE_TITLE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.INVOICE_TITLE
     *
     * @param invoiceTitle the value for t_ord_invoice_tax_addr.INVOICE_TITLE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.PHONE
     *
     * @return the value of t_ord_invoice_tax_addr.PHONE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.PHONE
     *
     * @param phone the value for t_ord_invoice_tax_addr.PHONE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.PROVINCE
     *
     * @return the value of t_ord_invoice_tax_addr.PROVINCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.PROVINCE
     *
     * @param province the value for t_ord_invoice_tax_addr.PROVINCE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.CITY_CODE
     *
     * @return the value of t_ord_invoice_tax_addr.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.CITY_CODE
     *
     * @param cityCode the value for t_ord_invoice_tax_addr.CITY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.COUNTY_CODE
     *
     * @return the value of t_ord_invoice_tax_addr.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.COUNTY_CODE
     *
     * @param countyCode the value for t_ord_invoice_tax_addr.COUNTY_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.TOWN_CODE
     *
     * @return the value of t_ord_invoice_tax_addr.TOWN_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getTownCode() {
        return townCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.TOWN_CODE
     *
     * @param townCode the value for t_ord_invoice_tax_addr.TOWN_CODE
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setTownCode(String townCode) {
        this.townCode = townCode == null ? null : townCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.CONTACT_INFO
     *
     * @return the value of t_ord_invoice_tax_addr.CONTACT_INFO
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.CONTACT_INFO
     *
     * @param contactInfo the value for t_ord_invoice_tax_addr.CONTACT_INFO
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.STATUS
     *
     * @return the value of t_ord_invoice_tax_addr.STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.STATUS
     *
     * @param status the value for t_ord_invoice_tax_addr.STATUS
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.CREATE_STAFF
     *
     * @return the value of t_ord_invoice_tax_addr.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.CREATE_STAFF
     *
     * @param createStaff the value for t_ord_invoice_tax_addr.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.CREATE_TIME
     *
     * @return the value of t_ord_invoice_tax_addr.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.CREATE_TIME
     *
     * @param createTime the value for t_ord_invoice_tax_addr.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.UPDATE_STAFF
     *
     * @return the value of t_ord_invoice_tax_addr.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.UPDATE_STAFF
     *
     * @param updateStaff the value for t_ord_invoice_tax_addr.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.UPDATE_TIME
     *
     * @return the value of t_ord_invoice_tax_addr.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.UPDATE_TIME
     *
     * @param updateTime the value for t_ord_invoice_tax_addr.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_invoice_tax_addr.INVOICE_DESC
     *
     * @return the value of t_ord_invoice_tax_addr.INVOICE_DESC
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public String getInvoiceDesc() {
        return invoiceDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_invoice_tax_addr.INVOICE_DESC
     *
     * @param invoiceDesc the value for t_ord_invoice_tax_addr.INVOICE_DESC
     *
     * @mbg.generated Mon Apr 17 21:03:39 CST 2017
     */
    public void setInvoiceDesc(String invoiceDesc) {
        this.invoiceDesc = invoiceDesc == null ? null : invoiceDesc.trim();
    }
}