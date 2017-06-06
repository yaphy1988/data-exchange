package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

public class AipServiceUsedLogDTO implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.USED_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String usedId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.SERVICE_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String serviceId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.VERSION
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String version;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String status;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.CLIENT_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String clientId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.ACCESS_TOKEN
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String accessToken;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private Date updateTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.REQUEST_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String requestMsg;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.RESPONSE_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String responseMsg;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_used_log.RESPONSE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private Date responseTime;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.USED_ID
    *
    * @return the value of t_aip_service_used_log.USED_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getUsedId() {
       return usedId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.USED_ID
    *
    * @param usedId the value for t_aip_service_used_log.USED_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setUsedId(String usedId) {
       this.usedId = usedId == null ? null : usedId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.SERVICE_ID
    *
    * @return the value of t_aip_service_used_log.SERVICE_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getServiceId() {
       return serviceId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.SERVICE_ID
    *
    * @param serviceId the value for t_aip_service_used_log.SERVICE_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setServiceId(String serviceId) {
       this.serviceId = serviceId == null ? null : serviceId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.VERSION
    *
    * @return the value of t_aip_service_used_log.VERSION
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getVersion() {
       return version;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.VERSION
    *
    * @param version the value for t_aip_service_used_log.VERSION
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setVersion(String version) {
       this.version = version == null ? null : version.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.STATUS
    *
    * @return the value of t_aip_service_used_log.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.STATUS
    *
    * @param status the value for t_aip_service_used_log.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.CLIENT_ID
    *
    * @return the value of t_aip_service_used_log.CLIENT_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getClientId() {
       return clientId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.CLIENT_ID
    *
    * @param clientId the value for t_aip_service_used_log.CLIENT_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setClientId(String clientId) {
       this.clientId = clientId == null ? null : clientId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.ACCESS_TOKEN
    *
    * @return the value of t_aip_service_used_log.ACCESS_TOKEN
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getAccessToken() {
       return accessToken;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.ACCESS_TOKEN
    *
    * @param accessToken the value for t_aip_service_used_log.ACCESS_TOKEN
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setAccessToken(String accessToken) {
       this.accessToken = accessToken == null ? null : accessToken.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.CREATE_TIME
    *
    * @return the value of t_aip_service_used_log.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.CREATE_TIME
    *
    * @param createTime the value for t_aip_service_used_log.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.UPDATE_TIME
    *
    * @return the value of t_aip_service_used_log.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.UPDATE_TIME
    *
    * @param updateTime the value for t_aip_service_used_log.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.REQUEST_MSG
    *
    * @return the value of t_aip_service_used_log.REQUEST_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getRequestMsg() {
       return requestMsg;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.REQUEST_MSG
    *
    * @param requestMsg the value for t_aip_service_used_log.REQUEST_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setRequestMsg(String requestMsg) {
       this.requestMsg = requestMsg == null ? null : requestMsg.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.RESPONSE_MSG
    *
    * @return the value of t_aip_service_used_log.RESPONSE_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getResponseMsg() {
       return responseMsg;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.RESPONSE_MSG
    *
    * @param responseMsg the value for t_aip_service_used_log.RESPONSE_MSG
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setResponseMsg(String responseMsg) {
       this.responseMsg = responseMsg == null ? null : responseMsg.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_used_log.RESPONSE_TIME
    *
    * @return the value of t_aip_service_used_log.RESPONSE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public Date getResponseTime() {
       return responseTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_used_log.RESPONSE_TIME
    *
    * @param responseTime the value for t_aip_service_used_log.RESPONSE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setResponseTime(Date responseTime) {
       this.responseTime = responseTime;
   }
}
