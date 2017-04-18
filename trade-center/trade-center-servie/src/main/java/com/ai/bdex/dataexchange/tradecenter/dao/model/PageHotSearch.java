package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class PageHotSearch {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.SEARCH_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private Integer searchId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.SEARCH_KEY
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String searchKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.STATUS
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.INFO_ORDER
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private Integer infoOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.REMARK
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String createStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String updateStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_hot_search.SEARCH_URL
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    private String searchUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.SEARCH_ID
     *
     * @return the value of t_page_hot_search.SEARCH_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Integer getSearchId() {
        return searchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.SEARCH_ID
     *
     * @param searchId the value for t_page_hot_search.SEARCH_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.SEARCH_KEY
     *
     * @return the value of t_page_hot_search.SEARCH_KEY
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getSearchKey() {
        return searchKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.SEARCH_KEY
     *
     * @param searchKey the value for t_page_hot_search.SEARCH_KEY
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.STATUS
     *
     * @return the value of t_page_hot_search.STATUS
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.STATUS
     *
     * @param status the value for t_page_hot_search.STATUS
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.INFO_ORDER
     *
     * @return the value of t_page_hot_search.INFO_ORDER
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Integer getInfoOrder() {
        return infoOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.INFO_ORDER
     *
     * @param infoOrder the value for t_page_hot_search.INFO_ORDER
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setInfoOrder(Integer infoOrder) {
        this.infoOrder = infoOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.REMARK
     *
     * @return the value of t_page_hot_search.REMARK
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.REMARK
     *
     * @param remark the value for t_page_hot_search.REMARK
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.CREATE_STAFF_ID
     *
     * @return the value of t_page_hot_search.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.CREATE_STAFF_ID
     *
     * @param createStaffId the value for t_page_hot_search.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.CREATE_TIME
     *
     * @return the value of t_page_hot_search.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.CREATE_TIME
     *
     * @param createTime the value for t_page_hot_search.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.UPDATE_STAFF_ID
     *
     * @return the value of t_page_hot_search.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.UPDATE_STAFF_ID
     *
     * @param updateStaffId the value for t_page_hot_search.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.UPDATE_TIME
     *
     * @return the value of t_page_hot_search.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.UPDATE_TIME
     *
     * @param updateTime the value for t_page_hot_search.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_hot_search.SEARCH_URL
     *
     * @return the value of t_page_hot_search.SEARCH_URL
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getSearchUrl() {
        return searchUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_hot_search.SEARCH_URL
     *
     * @param searchUrl the value for t_page_hot_search.SEARCH_URL
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl == null ? null : searchUrl.trim();
    }
}