package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class PageHeaderNav {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.NAV_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Integer navId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.NAV_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String navName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.NAV_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String navLink;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.NAV_ORDER
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Integer navOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.REMARK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String createStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String updateStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_header_nav.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.NAV_ID
     *
     * @return the value of t_page_header_nav.NAV_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Integer getNavId() {
        return navId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.NAV_ID
     *
     * @param navId the value for t_page_header_nav.NAV_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.NAV_NAME
     *
     * @return the value of t_page_header_nav.NAV_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getNavName() {
        return navName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.NAV_NAME
     *
     * @param navName the value for t_page_header_nav.NAV_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setNavName(String navName) {
        this.navName = navName == null ? null : navName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.NAV_LINK
     *
     * @return the value of t_page_header_nav.NAV_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getNavLink() {
        return navLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.NAV_LINK
     *
     * @param navLink the value for t_page_header_nav.NAV_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setNavLink(String navLink) {
        this.navLink = navLink == null ? null : navLink.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.NAV_ORDER
     *
     * @return the value of t_page_header_nav.NAV_ORDER
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Integer getNavOrder() {
        return navOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.NAV_ORDER
     *
     * @param navOrder the value for t_page_header_nav.NAV_ORDER
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setNavOrder(Integer navOrder) {
        this.navOrder = navOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.STATUS
     *
     * @return the value of t_page_header_nav.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.STATUS
     *
     * @param status the value for t_page_header_nav.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.REMARK
     *
     * @return the value of t_page_header_nav.REMARK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.REMARK
     *
     * @param remark the value for t_page_header_nav.REMARK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.CREATE_STAFF_ID
     *
     * @return the value of t_page_header_nav.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.CREATE_STAFF_ID
     *
     * @param createStaffId the value for t_page_header_nav.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.CREATE_TIME
     *
     * @return the value of t_page_header_nav.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.CREATE_TIME
     *
     * @param createTime the value for t_page_header_nav.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.UPDATE_STAFF_ID
     *
     * @return the value of t_page_header_nav.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.UPDATE_STAFF_ID
     *
     * @param updateStaffId the value for t_page_header_nav.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_header_nav.UPDATE_TIME
     *
     * @return the value of t_page_header_nav.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_header_nav.UPDATE_TIME
     *
     * @param updateTime the value for t_page_header_nav.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}