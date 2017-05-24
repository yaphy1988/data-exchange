package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class PayResultReqDTO extends BaseInfo{
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_RESULT_SN
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private Long payResultSn;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.ORDER_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String orderId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_WAY
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payWay;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.STAFF_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String staffId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.SHOP_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String shopId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAYMENT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private Long payment;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private Date requestTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.CREATE_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.CREATE_STAFF
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String createStaff;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_TRANS_NO
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payTransNo;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_STATUS
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payStatus;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_DESC
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payDesc;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private Date payTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAYEE_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payeeName;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAYEE_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payeeAcct;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.PAY_WAY_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String payWayName;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pay_result.MERCH_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   private String merchAcct;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_RESULT_SN
    *
    * @return the value of t_pay_result.PAY_RESULT_SN
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public Long getPayResultSn() {
       return payResultSn;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_RESULT_SN
    *
    * @param payResultSn the value for t_pay_result.PAY_RESULT_SN
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayResultSn(Long payResultSn) {
       this.payResultSn = payResultSn;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.ORDER_ID
    *
    * @return the value of t_pay_result.ORDER_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getOrderId() {
       return orderId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.ORDER_ID
    *
    * @param orderId the value for t_pay_result.ORDER_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setOrderId(String orderId) {
       this.orderId = orderId == null ? null : orderId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_WAY
    *
    * @return the value of t_pay_result.PAY_WAY
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayWay() {
       return payWay;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_WAY
    *
    * @param payWay the value for t_pay_result.PAY_WAY
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayWay(String payWay) {
       this.payWay = payWay == null ? null : payWay.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.STAFF_ID
    *
    * @return the value of t_pay_result.STAFF_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.STAFF_ID
    *
    * @param staffId the value for t_pay_result.STAFF_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.SHOP_ID
    *
    * @return the value of t_pay_result.SHOP_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getShopId() {
       return shopId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.SHOP_ID
    *
    * @param shopId the value for t_pay_result.SHOP_ID
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setShopId(String shopId) {
       this.shopId = shopId == null ? null : shopId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAYMENT
    *
    * @return the value of t_pay_result.PAYMENT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public Long getPayment() {
       return payment;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAYMENT
    *
    * @param payment the value for t_pay_result.PAYMENT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayment(Long payment) {
       this.payment = payment;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.REQUEST_TIME
    *
    * @return the value of t_pay_result.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public Date getRequestTime() {
       return requestTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.REQUEST_TIME
    *
    * @param requestTime the value for t_pay_result.REQUEST_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setRequestTime(Date requestTime) {
       this.requestTime = requestTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.CREATE_TIME
    *
    * @return the value of t_pay_result.CREATE_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.CREATE_TIME
    *
    * @param createTime the value for t_pay_result.CREATE_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.CREATE_STAFF
    *
    * @return the value of t_pay_result.CREATE_STAFF
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getCreateStaff() {
       return createStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.CREATE_STAFF
    *
    * @param createStaff the value for t_pay_result.CREATE_STAFF
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setCreateStaff(String createStaff) {
       this.createStaff = createStaff == null ? null : createStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_TRANS_NO
    *
    * @return the value of t_pay_result.PAY_TRANS_NO
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayTransNo() {
       return payTransNo;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_TRANS_NO
    *
    * @param payTransNo the value for t_pay_result.PAY_TRANS_NO
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayTransNo(String payTransNo) {
       this.payTransNo = payTransNo == null ? null : payTransNo.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_STATUS
    *
    * @return the value of t_pay_result.PAY_STATUS
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayStatus() {
       return payStatus;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_STATUS
    *
    * @param payStatus the value for t_pay_result.PAY_STATUS
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayStatus(String payStatus) {
       this.payStatus = payStatus == null ? null : payStatus.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_DESC
    *
    * @return the value of t_pay_result.PAY_DESC
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayDesc() {
       return payDesc;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_DESC
    *
    * @param payDesc the value for t_pay_result.PAY_DESC
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayDesc(String payDesc) {
       this.payDesc = payDesc == null ? null : payDesc.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_TIME
    *
    * @return the value of t_pay_result.PAY_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public Date getPayTime() {
       return payTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_TIME
    *
    * @param payTime the value for t_pay_result.PAY_TIME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayTime(Date payTime) {
       this.payTime = payTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAYEE_NAME
    *
    * @return the value of t_pay_result.PAYEE_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayeeName() {
       return payeeName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAYEE_NAME
    *
    * @param payeeName the value for t_pay_result.PAYEE_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayeeName(String payeeName) {
       this.payeeName = payeeName == null ? null : payeeName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAYEE_ACCT
    *
    * @return the value of t_pay_result.PAYEE_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayeeAcct() {
       return payeeAcct;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAYEE_ACCT
    *
    * @param payeeAcct the value for t_pay_result.PAYEE_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayeeAcct(String payeeAcct) {
       this.payeeAcct = payeeAcct == null ? null : payeeAcct.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.PAY_WAY_NAME
    *
    * @return the value of t_pay_result.PAY_WAY_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getPayWayName() {
       return payWayName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.PAY_WAY_NAME
    *
    * @param payWayName the value for t_pay_result.PAY_WAY_NAME
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setPayWayName(String payWayName) {
       this.payWayName = payWayName == null ? null : payWayName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_pay_result.MERCH_ACCT
    *
    * @return the value of t_pay_result.MERCH_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public String getMerchAcct() {
       return merchAcct;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_pay_result.MERCH_ACCT
    *
    * @param merchAcct the value for t_pay_result.MERCH_ACCT
    *
    * @mbg.generated Wed May 24 12:31:27 CST 2017
    */
   public void setMerchAcct(String merchAcct) {
       this.merchAcct = merchAcct == null ? null : merchAcct.trim();
   }
}