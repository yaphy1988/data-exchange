package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class PayRequestReqDTO extends BaseInfo{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String payWay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.ORDER_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.STAFF_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.PAYMENT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long payment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.REQUEST_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Date requestTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_request.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long createStaff;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.ID
     *
     * @return the value of t_pay_request.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.ID
     *
     * @param id the value for t_pay_request.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.PAY_WAY
     *
     * @return the value of t_pay_request.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.PAY_WAY
     *
     * @param payWay the value for t_pay_request.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.ORDER_ID
     *
     * @return the value of t_pay_request.ORDER_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.ORDER_ID
     *
     * @param orderId the value for t_pay_request.ORDER_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.STAFF_ID
     *
     * @return the value of t_pay_request.STAFF_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.STAFF_ID
     *
     * @param staffId the value for t_pay_request.STAFF_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.SHOP_ID
     *
     * @return the value of t_pay_request.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.SHOP_ID
     *
     * @param shopId the value for t_pay_request.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.PAYMENT
     *
     * @return the value of t_pay_request.PAYMENT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getPayment() {
        return payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.PAYMENT
     *
     * @param payment the value for t_pay_request.PAYMENT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayment(Long payment) {
        this.payment = payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.REQUEST_TIME
     *
     * @return the value of t_pay_request.REQUEST_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.REQUEST_TIME
     *
     * @param requestTime the value for t_pay_request.REQUEST_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.CREATE_TIME
     *
     * @return the value of t_pay_request.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.CREATE_TIME
     *
     * @param createTime the value for t_pay_request.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_request.CREATE_STAFF
     *
     * @return the value of t_pay_request.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_request.CREATE_STAFF
     *
     * @param createStaff the value for t_pay_request.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }
}