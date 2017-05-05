package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class RechargeRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_req_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String rechargeReqId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_user_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String rechargeUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.order_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.sub_order
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String subOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.service_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.total_num
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Integer totalNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.total_money
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Integer totalMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.start_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.end_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.data_account_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Long dataAccountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_status
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String rechargeStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.create_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.create_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.update_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.update_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    private String updateStaff;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_req_id
     *
     * @return the value of t_recharge_record.recharge_req_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getRechargeReqId() {
        return rechargeReqId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.recharge_req_id
     *
     * @param rechargeReqId the value for t_recharge_record.recharge_req_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setRechargeReqId(String rechargeReqId) {
        this.rechargeReqId = rechargeReqId == null ? null : rechargeReqId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_user_id
     *
     * @return the value of t_recharge_record.recharge_user_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getRechargeUserId() {
        return rechargeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.recharge_user_id
     *
     * @param rechargeUserId the value for t_recharge_record.recharge_user_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setRechargeUserId(String rechargeUserId) {
        this.rechargeUserId = rechargeUserId == null ? null : rechargeUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.order_id
     *
     * @return the value of t_recharge_record.order_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.order_id
     *
     * @param orderId the value for t_recharge_record.order_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.sub_order
     *
     * @return the value of t_recharge_record.sub_order
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getSubOrder() {
        return subOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.sub_order
     *
     * @param subOrder the value for t_recharge_record.sub_order
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder == null ? null : subOrder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.service_id
     *
     * @return the value of t_recharge_record.service_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.service_id
     *
     * @param serviceId the value for t_recharge_record.service_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.total_num
     *
     * @return the value of t_recharge_record.total_num
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.total_num
     *
     * @param totalNum the value for t_recharge_record.total_num
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.total_money
     *
     * @return the value of t_recharge_record.total_money
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Integer getTotalMoney() {
        return totalMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.total_money
     *
     * @param totalMoney the value for t_recharge_record.total_money
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.start_date
     *
     * @return the value of t_recharge_record.start_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.start_date
     *
     * @param startDate the value for t_recharge_record.start_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.end_date
     *
     * @return the value of t_recharge_record.end_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.end_date
     *
     * @param endDate the value for t_recharge_record.end_date
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.data_account_id
     *
     * @return the value of t_recharge_record.data_account_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Long getDataAccountId() {
        return dataAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.data_account_id
     *
     * @param dataAccountId the value for t_recharge_record.data_account_id
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setDataAccountId(Long dataAccountId) {
        this.dataAccountId = dataAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_status
     *
     * @return the value of t_recharge_record.recharge_status
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getRechargeStatus() {
        return rechargeStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.recharge_status
     *
     * @param rechargeStatus the value for t_recharge_record.recharge_status
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus == null ? null : rechargeStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.create_time
     *
     * @return the value of t_recharge_record.create_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.create_time
     *
     * @param createTime the value for t_recharge_record.create_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.create_staff
     *
     * @return the value of t_recharge_record.create_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.create_staff
     *
     * @param createStaff the value for t_recharge_record.create_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.update_time
     *
     * @return the value of t_recharge_record.update_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.update_time
     *
     * @param updateTime the value for t_recharge_record.update_time
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.update_staff
     *
     * @return the value of t_recharge_record.update_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.update_staff
     *
     * @param updateStaff the value for t_recharge_record.update_staff
     *
     * @mbg.generated Thu May 04 21:20:58 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }
}