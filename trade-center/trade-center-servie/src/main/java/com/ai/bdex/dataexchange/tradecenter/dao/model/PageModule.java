package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class PageModule {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.MODULE_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Integer moduleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.MODULE_NAME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String moduleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.MODULE_TYPE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String moduleType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.AD_PLACE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String adPlace;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.MODULE_PID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Integer modulePid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.MODULE_COUNT
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Integer moduleCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Integer orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.REMARK
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.STATUS
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String createStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private String updateStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_module.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.MODULE_ID
     *
     * @return the value of t_page_module.MODULE_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.MODULE_ID
     *
     * @param moduleId the value for t_page_module.MODULE_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.MODULE_NAME
     *
     * @return the value of t_page_module.MODULE_NAME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.MODULE_NAME
     *
     * @param moduleName the value for t_page_module.MODULE_NAME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.MODULE_TYPE
     *
     * @return the value of t_page_module.MODULE_TYPE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.MODULE_TYPE
     *
     * @param moduleType the value for t_page_module.MODULE_TYPE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.AD_PLACE
     *
     * @return the value of t_page_module.AD_PLACE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getAdPlace() {
        return adPlace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.AD_PLACE
     *
     * @param adPlace the value for t_page_module.AD_PLACE
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setAdPlace(String adPlace) {
        this.adPlace = adPlace == null ? null : adPlace.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.MODULE_PID
     *
     * @return the value of t_page_module.MODULE_PID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Integer getModulePid() {
        return modulePid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.MODULE_PID
     *
     * @param modulePid the value for t_page_module.MODULE_PID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setModulePid(Integer modulePid) {
        this.modulePid = modulePid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.MODULE_COUNT
     *
     * @return the value of t_page_module.MODULE_COUNT
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Integer getModuleCount() {
        return moduleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.MODULE_COUNT
     *
     * @param moduleCount the value for t_page_module.MODULE_COUNT
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setModuleCount(Integer moduleCount) {
        this.moduleCount = moduleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.ORDER_NO
     *
     * @return the value of t_page_module.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.ORDER_NO
     *
     * @param orderNo the value for t_page_module.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.REMARK
     *
     * @return the value of t_page_module.REMARK
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.REMARK
     *
     * @param remark the value for t_page_module.REMARK
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.STATUS
     *
     * @return the value of t_page_module.STATUS
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.STATUS
     *
     * @param status the value for t_page_module.STATUS
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.CREATE_STAFF_ID
     *
     * @return the value of t_page_module.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.CREATE_STAFF_ID
     *
     * @param createStaffId the value for t_page_module.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.CREATE_TIME
     *
     * @return the value of t_page_module.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.CREATE_TIME
     *
     * @param createTime the value for t_page_module.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.UPDATE_STAFF_ID
     *
     * @return the value of t_page_module.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.UPDATE_STAFF_ID
     *
     * @param updateStaffId the value for t_page_module.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_module.UPDATE_TIME
     *
     * @return the value of t_page_module.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_module.UPDATE_TIME
     *
     * @param updateTime the value for t_page_module.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:28:28 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}