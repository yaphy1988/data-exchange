package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class AipClientAccesstoken {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_accesstoken.ACCESS_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private String accessToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_accesstoken.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_accesstoken.EXPIRES_IN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private Date expiresIn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_accesstoken.REFRESH_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private String refreshToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_accesstoken.CREATE_TIME
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_accesstoken.ACCESS_TOKEN
     *
     * @return the value of t_aip_client_accesstoken.ACCESS_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_accesstoken.ACCESS_TOKEN
     *
     * @param accessToken the value for t_aip_client_accesstoken.ACCESS_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_accesstoken.CLIENT_ID
     *
     * @return the value of t_aip_client_accesstoken.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_accesstoken.CLIENT_ID
     *
     * @param clientId the value for t_aip_client_accesstoken.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_accesstoken.EXPIRES_IN
     *
     * @return the value of t_aip_client_accesstoken.EXPIRES_IN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public Date getExpiresIn() {
        return expiresIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_accesstoken.EXPIRES_IN
     *
     * @param expiresIn the value for t_aip_client_accesstoken.EXPIRES_IN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_accesstoken.REFRESH_TOKEN
     *
     * @return the value of t_aip_client_accesstoken.REFRESH_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_accesstoken.REFRESH_TOKEN
     *
     * @param refreshToken the value for t_aip_client_accesstoken.REFRESH_TOKEN
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_accesstoken.CREATE_TIME
     *
     * @return the value of t_aip_client_accesstoken.CREATE_TIME
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_accesstoken.CREATE_TIME
     *
     * @param createTime the value for t_aip_client_accesstoken.CREATE_TIME
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}