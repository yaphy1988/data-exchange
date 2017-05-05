package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class DataAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.data_acct_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Long dataAcctId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.data_acct_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String dataAcctType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.period_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String periodType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.user_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.service_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.total_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer totalNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.left_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer leftNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.total_consume_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer totalConsumeNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.total_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer totalMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.left_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer leftMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.total_consume_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Integer totalConsumeMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.start_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.end_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.data_acct_status
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String dataAcctStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.create_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.create_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.update_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data_account.update_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    private String updateStaff;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.data_acct_id
     *
     * @return the value of t_data_account.data_acct_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Long getDataAcctId() {
        return dataAcctId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.data_acct_id
     *
     * @param dataAcctId the value for t_data_account.data_acct_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setDataAcctId(Long dataAcctId) {
        this.dataAcctId = dataAcctId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.data_acct_type
     *
     * @return the value of t_data_account.data_acct_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getDataAcctType() {
        return dataAcctType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.data_acct_type
     *
     * @param dataAcctType the value for t_data_account.data_acct_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setDataAcctType(String dataAcctType) {
        this.dataAcctType = dataAcctType == null ? null : dataAcctType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.period_type
     *
     * @return the value of t_data_account.period_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.period_type
     *
     * @param periodType the value for t_data_account.period_type
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setPeriodType(String periodType) {
        this.periodType = periodType == null ? null : periodType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.user_id
     *
     * @return the value of t_data_account.user_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.user_id
     *
     * @param userId the value for t_data_account.user_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.service_id
     *
     * @return the value of t_data_account.service_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.service_id
     *
     * @param serviceId the value for t_data_account.service_id
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.total_num
     *
     * @return the value of t_data_account.total_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.total_num
     *
     * @param totalNum the value for t_data_account.total_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.left_num
     *
     * @return the value of t_data_account.left_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getLeftNum() {
        return leftNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.left_num
     *
     * @param leftNum the value for t_data_account.left_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.total_consume_num
     *
     * @return the value of t_data_account.total_consume_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getTotalConsumeNum() {
        return totalConsumeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.total_consume_num
     *
     * @param totalConsumeNum the value for t_data_account.total_consume_num
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setTotalConsumeNum(Integer totalConsumeNum) {
        this.totalConsumeNum = totalConsumeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.total_money
     *
     * @return the value of t_data_account.total_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getTotalMoney() {
        return totalMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.total_money
     *
     * @param totalMoney the value for t_data_account.total_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.left_money
     *
     * @return the value of t_data_account.left_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getLeftMoney() {
        return leftMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.left_money
     *
     * @param leftMoney the value for t_data_account.left_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setLeftMoney(Integer leftMoney) {
        this.leftMoney = leftMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.total_consume_money
     *
     * @return the value of t_data_account.total_consume_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Integer getTotalConsumeMoney() {
        return totalConsumeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.total_consume_money
     *
     * @param totalConsumeMoney the value for t_data_account.total_consume_money
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setTotalConsumeMoney(Integer totalConsumeMoney) {
        this.totalConsumeMoney = totalConsumeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.start_date
     *
     * @return the value of t_data_account.start_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.start_date
     *
     * @param startDate the value for t_data_account.start_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.end_date
     *
     * @return the value of t_data_account.end_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.end_date
     *
     * @param endDate the value for t_data_account.end_date
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.data_acct_status
     *
     * @return the value of t_data_account.data_acct_status
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getDataAcctStatus() {
        return dataAcctStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.data_acct_status
     *
     * @param dataAcctStatus the value for t_data_account.data_acct_status
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setDataAcctStatus(String dataAcctStatus) {
        this.dataAcctStatus = dataAcctStatus == null ? null : dataAcctStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.create_time
     *
     * @return the value of t_data_account.create_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.create_time
     *
     * @param createTime the value for t_data_account.create_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.create_staff
     *
     * @return the value of t_data_account.create_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.create_staff
     *
     * @param createStaff the value for t_data_account.create_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.update_time
     *
     * @return the value of t_data_account.update_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.update_time
     *
     * @param updateTime the value for t_data_account.update_time
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_data_account.update_staff
     *
     * @return the value of t_data_account.update_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_data_account.update_staff
     *
     * @param updateStaff the value for t_data_account.update_staff
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }
}