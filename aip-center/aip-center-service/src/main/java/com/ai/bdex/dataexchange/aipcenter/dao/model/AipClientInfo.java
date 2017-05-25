package com.ai.bdex.dataexchange.aipcenter.dao.model;

import java.util.Date;

public class AipClientInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.CLIENT_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.CLIENT_SECRET
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String clientSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.USER_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.USERNAME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.STATUS
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.CREATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.CREATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.UPDATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.UPDATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.EFFECTIVE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private Date effectiveTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_info.PASSWORD
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.CLIENT_ID
     *
     * @return the value of t_aip_client_info.CLIENT_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.CLIENT_ID
     *
     * @param clientId the value for t_aip_client_info.CLIENT_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.CLIENT_SECRET
     *
     * @return the value of t_aip_client_info.CLIENT_SECRET
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.CLIENT_SECRET
     *
     * @param clientSecret the value for t_aip_client_info.CLIENT_SECRET
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.USER_ID
     *
     * @return the value of t_aip_client_info.USER_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.USER_ID
     *
     * @param userId the value for t_aip_client_info.USER_ID
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.USERNAME
     *
     * @return the value of t_aip_client_info.USERNAME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.USERNAME
     *
     * @param username the value for t_aip_client_info.USERNAME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.STATUS
     *
     * @return the value of t_aip_client_info.STATUS
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.STATUS
     *
     * @param status the value for t_aip_client_info.STATUS
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.CREATE_TIME
     *
     * @return the value of t_aip_client_info.CREATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.CREATE_TIME
     *
     * @param createTime the value for t_aip_client_info.CREATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.CREATE_STAFF
     *
     * @return the value of t_aip_client_info.CREATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.CREATE_STAFF
     *
     * @param createStaff the value for t_aip_client_info.CREATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.UPDATE_TIME
     *
     * @return the value of t_aip_client_info.UPDATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.UPDATE_TIME
     *
     * @param updateTime the value for t_aip_client_info.UPDATE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.UPDATE_STAFF
     *
     * @return the value of t_aip_client_info.UPDATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.UPDATE_STAFF
     *
     * @param updateStaff the value for t_aip_client_info.UPDATE_STAFF
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.EFFECTIVE_TIME
     *
     * @return the value of t_aip_client_info.EFFECTIVE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.EFFECTIVE_TIME
     *
     * @param effectiveTime the value for t_aip_client_info.EFFECTIVE_TIME
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_info.PASSWORD
     *
     * @return the value of t_aip_client_info.PASSWORD
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_info.PASSWORD
     *
     * @param password the value for t_aip_client_info.PASSWORD
     *
     * @mbg.generated Thu May 25 20:36:03 CST 2017
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}