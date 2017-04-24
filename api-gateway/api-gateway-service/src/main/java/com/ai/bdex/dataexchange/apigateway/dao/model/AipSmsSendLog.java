package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class AipSmsSendLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String logId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.owner
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.batch_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String batchId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.rev_log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String revLogId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.request_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private Date requestTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.request
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String request;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.response_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private Date responseTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.response
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private String response;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_sms_send_log.create_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.log_id
     *
     * @return the value of t_aip_sms_send_log.log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.log_id
     *
     * @param logId the value for t_aip_sms_send_log.log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.owner
     *
     * @return the value of t_aip_sms_send_log.owner
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.owner
     *
     * @param owner the value for t_aip_sms_send_log.owner
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.batch_id
     *
     * @return the value of t_aip_sms_send_log.batch_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.batch_id
     *
     * @param batchId the value for t_aip_sms_send_log.batch_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.rev_log_id
     *
     * @return the value of t_aip_sms_send_log.rev_log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getRevLogId() {
        return revLogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.rev_log_id
     *
     * @param revLogId the value for t_aip_sms_send_log.rev_log_id
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setRevLogId(String revLogId) {
        this.revLogId = revLogId == null ? null : revLogId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.request_time
     *
     * @return the value of t_aip_sms_send_log.request_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.request_time
     *
     * @param requestTime the value for t_aip_sms_send_log.request_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.request
     *
     * @return the value of t_aip_sms_send_log.request
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getRequest() {
        return request;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.request
     *
     * @param request the value for t_aip_sms_send_log.request
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.response_time
     *
     * @return the value of t_aip_sms_send_log.response_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public Date getResponseTime() {
        return responseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.response_time
     *
     * @param responseTime the value for t_aip_sms_send_log.response_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.response
     *
     * @return the value of t_aip_sms_send_log.response
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public String getResponse() {
        return response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.response
     *
     * @param response the value for t_aip_sms_send_log.response
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_sms_send_log.create_time
     *
     * @return the value of t_aip_sms_send_log.create_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_sms_send_log.create_time
     *
     * @param createTime the value for t_aip_sms_send_log.create_time
     *
     * @mbg.generated Mon Apr 24 09:48:21 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}