package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class PayShopCfgLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Long shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String payWay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.PAY_WAY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String payWayName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.MERC_CODE
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String mercCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.BRANCH_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String branchId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.CIS_NO
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String cisNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.SHOP_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String shopAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.PAYEE_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String payeeAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.PAYEE_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String payeeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.POS_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String posId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.CER_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String cerName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.KEY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String keyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.CER_PASSWORD
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String cerPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.USE_FLAG
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String useFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.SHOW_ORDER
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Short showOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.UPDATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_shop_cfg_log.LOG_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    private Date logTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.ID
     *
     * @return the value of t_pay_shop_cfg_log.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.ID
     *
     * @param id the value for t_pay_shop_cfg_log.ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.SHOP_ID
     *
     * @return the value of t_pay_shop_cfg_log.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.SHOP_ID
     *
     * @param shopId the value for t_pay_shop_cfg_log.SHOP_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.PAY_WAY
     *
     * @return the value of t_pay_shop_cfg_log.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.PAY_WAY
     *
     * @param payWay the value for t_pay_shop_cfg_log.PAY_WAY
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.PAY_WAY_NAME
     *
     * @return the value of t_pay_shop_cfg_log.PAY_WAY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPayWayName() {
        return payWayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.PAY_WAY_NAME
     *
     * @param payWayName the value for t_pay_shop_cfg_log.PAY_WAY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.MERC_CODE
     *
     * @return the value of t_pay_shop_cfg_log.MERC_CODE
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getMercCode() {
        return mercCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.MERC_CODE
     *
     * @param mercCode the value for t_pay_shop_cfg_log.MERC_CODE
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setMercCode(String mercCode) {
        this.mercCode = mercCode == null ? null : mercCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.BRANCH_ID
     *
     * @return the value of t_pay_shop_cfg_log.BRANCH_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.BRANCH_ID
     *
     * @param branchId the value for t_pay_shop_cfg_log.BRANCH_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.CIS_NO
     *
     * @return the value of t_pay_shop_cfg_log.CIS_NO
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getCisNo() {
        return cisNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.CIS_NO
     *
     * @param cisNo the value for t_pay_shop_cfg_log.CIS_NO
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCisNo(String cisNo) {
        this.cisNo = cisNo == null ? null : cisNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.SHOP_ACCOUNT
     *
     * @return the value of t_pay_shop_cfg_log.SHOP_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getShopAccount() {
        return shopAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.SHOP_ACCOUNT
     *
     * @param shopAccount the value for t_pay_shop_cfg_log.SHOP_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount == null ? null : shopAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.PAYEE_ACCOUNT
     *
     * @return the value of t_pay_shop_cfg_log.PAYEE_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.PAYEE_ACCOUNT
     *
     * @param payeeAccount the value for t_pay_shop_cfg_log.PAYEE_ACCOUNT
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.PAYEE_NAME
     *
     * @return the value of t_pay_shop_cfg_log.PAYEE_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.PAYEE_NAME
     *
     * @param payeeName the value for t_pay_shop_cfg_log.PAYEE_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.POS_ID
     *
     * @return the value of t_pay_shop_cfg_log.POS_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getPosId() {
        return posId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.POS_ID
     *
     * @param posId the value for t_pay_shop_cfg_log.POS_ID
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setPosId(String posId) {
        this.posId = posId == null ? null : posId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.CER_NAME
     *
     * @return the value of t_pay_shop_cfg_log.CER_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getCerName() {
        return cerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.CER_NAME
     *
     * @param cerName the value for t_pay_shop_cfg_log.CER_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCerName(String cerName) {
        this.cerName = cerName == null ? null : cerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.KEY_NAME
     *
     * @return the value of t_pay_shop_cfg_log.KEY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.KEY_NAME
     *
     * @param keyName the value for t_pay_shop_cfg_log.KEY_NAME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.CER_PASSWORD
     *
     * @return the value of t_pay_shop_cfg_log.CER_PASSWORD
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getCerPassword() {
        return cerPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.CER_PASSWORD
     *
     * @param cerPassword the value for t_pay_shop_cfg_log.CER_PASSWORD
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCerPassword(String cerPassword) {
        this.cerPassword = cerPassword == null ? null : cerPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.USE_FLAG
     *
     * @return the value of t_pay_shop_cfg_log.USE_FLAG
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getUseFlag() {
        return useFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.USE_FLAG
     *
     * @param useFlag the value for t_pay_shop_cfg_log.USE_FLAG
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.SHOW_ORDER
     *
     * @return the value of t_pay_shop_cfg_log.SHOW_ORDER
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Short getShowOrder() {
        return showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.SHOW_ORDER
     *
     * @param showOrder the value for t_pay_shop_cfg_log.SHOW_ORDER
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setShowOrder(Short showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.CREATE_STAFF
     *
     * @return the value of t_pay_shop_cfg_log.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.CREATE_STAFF
     *
     * @param createStaff the value for t_pay_shop_cfg_log.CREATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.CREATE_TIME
     *
     * @return the value of t_pay_shop_cfg_log.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.CREATE_TIME
     *
     * @param createTime the value for t_pay_shop_cfg_log.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.UPDATE_STAFF
     *
     * @return the value of t_pay_shop_cfg_log.UPDATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.UPDATE_STAFF
     *
     * @param updateStaff the value for t_pay_shop_cfg_log.UPDATE_STAFF
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.UPDATE_TIME
     *
     * @return the value of t_pay_shop_cfg_log.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.UPDATE_TIME
     *
     * @param updateTime the value for t_pay_shop_cfg_log.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_shop_cfg_log.LOG_TIME
     *
     * @return the value of t_pay_shop_cfg_log.LOG_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_shop_cfg_log.LOG_TIME
     *
     * @param logTime the value for t_pay_shop_cfg_log.LOG_TIME
     *
     * @mbg.generated Tue Apr 18 14:56:53 CST 2017
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}