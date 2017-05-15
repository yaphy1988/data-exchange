package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class RechargeRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_req_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String rechargeReqId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_user_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String rechargeUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String rechargeType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.period_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String periodType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.order_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.sub_order
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String subOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.service_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.total_num
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer totalNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.total_money
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer totalMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.start_date
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.end_date
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.data_account_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Long dataAccountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.gds_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer gdsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.sku_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer skuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.cat_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer catId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.cat_first
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Integer catFirst;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.recharge_status
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String rechargeStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.create_time
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.create_staff
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.update_time
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.update_staff
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recharge_record.package_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    private String packageType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_req_id
     *
     * @return the value of t_recharge_record.recharge_req_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setRechargeUserId(String rechargeUserId) {
        this.rechargeUserId = rechargeUserId == null ? null : rechargeUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_type
     *
     * @return the value of t_recharge_record.recharge_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public String getRechargeType() {
        return rechargeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.recharge_type
     *
     * @param rechargeType the value for t_recharge_record.recharge_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType == null ? null : rechargeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.period_type
     *
     * @return the value of t_recharge_record.period_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.period_type
     *
     * @param periodType the value for t_recharge_record.period_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setPeriodType(String periodType) {
        this.periodType = periodType == null ? null : periodType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.order_id
     *
     * @return the value of t_recharge_record.order_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setDataAccountId(Long dataAccountId) {
        this.dataAccountId = dataAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.gds_id
     *
     * @return the value of t_recharge_record.gds_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public Integer getGdsId() {
        return gdsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.gds_id
     *
     * @param gdsId the value for t_recharge_record.gds_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.sku_id
     *
     * @return the value of t_recharge_record.sku_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.sku_id
     *
     * @param skuId the value for t_recharge_record.sku_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.cat_id
     *
     * @return the value of t_recharge_record.cat_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.cat_id
     *
     * @param catId the value for t_recharge_record.cat_id
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.cat_first
     *
     * @return the value of t_recharge_record.cat_first
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public Integer getCatFirst() {
        return catFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.cat_first
     *
     * @param catFirst the value for t_recharge_record.cat_first
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.recharge_status
     *
     * @return the value of t_recharge_record.recharge_status
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
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
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recharge_record.package_type
     *
     * @return the value of t_recharge_record.package_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recharge_record.package_type
     *
     * @param packageType the value for t_recharge_record.package_type
     *
     * @mbg.generated Mon May 15 22:20:25 CST 2017
     */
    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
    }
}