package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class PayIntfReqLogReqDTO extends BaseInfo{
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private Long id;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.PAY_WAY
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private String payWay;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.TYPE_CODE
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private String typeCode;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.ORDER_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private String orderId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.STAFF_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private String staffId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.RL_REQUEST_NO
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private Long rlRequestNo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private Date requestTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_intf_req_log.RESPONSE_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   private Date responseTime;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.ID
    *
    * @return the value of t_pay_intf_req_log.ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public Long getId() {
       return id;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.ID
    *
    * @param id the value for t_pay_intf_req_log.ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setId(Long id) {
       this.id = id;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.PAY_WAY
    *
    * @return the value of t_pay_intf_req_log.PAY_WAY
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public String getPayWay() {
       return payWay;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.PAY_WAY
    *
    * @param payWay the value for t_pay_intf_req_log.PAY_WAY
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setPayWay(String payWay) {
       this.payWay = payWay == null ? null : payWay.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.TYPE_CODE
    *
    * @return the value of t_pay_intf_req_log.TYPE_CODE
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public String getTypeCode() {
       return typeCode;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.TYPE_CODE
    *
    * @param typeCode the value for t_pay_intf_req_log.TYPE_CODE
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setTypeCode(String typeCode) {
       this.typeCode = typeCode == null ? null : typeCode.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.ORDER_ID
    *
    * @return the value of t_pay_intf_req_log.ORDER_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public String getOrderId() {
       return orderId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.ORDER_ID
    *
    * @param orderId the value for t_pay_intf_req_log.ORDER_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setOrderId(String orderId) {
       this.orderId = orderId == null ? null : orderId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.STAFF_ID
    *
    * @return the value of t_pay_intf_req_log.STAFF_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.STAFF_ID
    *
    * @param staffId the value for t_pay_intf_req_log.STAFF_ID
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.RL_REQUEST_NO
    *
    * @return the value of t_pay_intf_req_log.RL_REQUEST_NO
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public Long getRlRequestNo() {
       return rlRequestNo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.RL_REQUEST_NO
    *
    * @param rlRequestNo the value for t_pay_intf_req_log.RL_REQUEST_NO
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setRlRequestNo(Long rlRequestNo) {
       this.rlRequestNo = rlRequestNo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.REQUEST_TIME
    *
    * @return the value of t_pay_intf_req_log.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public Date getRequestTime() {
       return requestTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.REQUEST_TIME
    *
    * @param requestTime the value for t_pay_intf_req_log.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setRequestTime(Date requestTime) {
       this.requestTime = requestTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_intf_req_log.RESPONSE_TIME
    *
    * @return the value of t_pay_intf_req_log.RESPONSE_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public Date getResponseTime() {
       return responseTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_intf_req_log.RESPONSE_TIME
    *
    * @param responseTime the value for t_pay_intf_req_log.RESPONSE_TIME
    *
    * @mbg.generated Wed May 24 17:36:08 CST 2017
    */
   public void setResponseTime(Date responseTime) {
       this.responseTime = responseTime;
   }
}