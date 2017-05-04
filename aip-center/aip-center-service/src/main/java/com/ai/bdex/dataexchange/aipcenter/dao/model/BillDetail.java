package com.ai.bdex.dataexchange.aipcenter.dao.model;

import java.util.Date;

public class BillDetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.bill_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private String billId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.invoke_seq
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private String invokeSeq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.real_service_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private String realServiceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.user_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.data_acct_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private Long dataAcctId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.data_acct_type
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private String dataAcctType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.consume_num
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private Integer consumeNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.consume_money
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private Integer consumeMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bill_detail.consume_time
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    private Date consumeTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.bill_id
     *
     * @return the value of t_bill_detail.bill_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public String getBillId() {
        return billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.bill_id
     *
     * @param billId the value for t_bill_detail.bill_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.invoke_seq
     *
     * @return the value of t_bill_detail.invoke_seq
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public String getInvokeSeq() {
        return invokeSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.invoke_seq
     *
     * @param invokeSeq the value for t_bill_detail.invoke_seq
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setInvokeSeq(String invokeSeq) {
        this.invokeSeq = invokeSeq == null ? null : invokeSeq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.real_service_id
     *
     * @return the value of t_bill_detail.real_service_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public String getRealServiceId() {
        return realServiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.real_service_id
     *
     * @param realServiceId the value for t_bill_detail.real_service_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setRealServiceId(String realServiceId) {
        this.realServiceId = realServiceId == null ? null : realServiceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.user_id
     *
     * @return the value of t_bill_detail.user_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.user_id
     *
     * @param userId the value for t_bill_detail.user_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.data_acct_id
     *
     * @return the value of t_bill_detail.data_acct_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public Long getDataAcctId() {
        return dataAcctId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.data_acct_id
     *
     * @param dataAcctId the value for t_bill_detail.data_acct_id
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setDataAcctId(Long dataAcctId) {
        this.dataAcctId = dataAcctId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.data_acct_type
     *
     * @return the value of t_bill_detail.data_acct_type
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public String getDataAcctType() {
        return dataAcctType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.data_acct_type
     *
     * @param dataAcctType the value for t_bill_detail.data_acct_type
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setDataAcctType(String dataAcctType) {
        this.dataAcctType = dataAcctType == null ? null : dataAcctType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.consume_num
     *
     * @return the value of t_bill_detail.consume_num
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public Integer getConsumeNum() {
        return consumeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.consume_num
     *
     * @param consumeNum the value for t_bill_detail.consume_num
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.consume_money
     *
     * @return the value of t_bill_detail.consume_money
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.consume_money
     *
     * @param consumeMoney the value for t_bill_detail.consume_money
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bill_detail.consume_time
     *
     * @return the value of t_bill_detail.consume_time
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public Date getConsumeTime() {
        return consumeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bill_detail.consume_time
     *
     * @param consumeTime the value for t_bill_detail.consume_time
     *
     * @mbg.generated Thu May 04 20:59:51 CST 2017
     */
    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }
}