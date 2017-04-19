package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class SortContent {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.SORT_CONTENT_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Integer sortContentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.CONTENT_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String contentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.CONTENT_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String contentLink;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.VFS_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String vfsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.more_url
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String moreUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String createStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private String updateStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sort_content.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.SORT_CONTENT_ID
     *
     * @return the value of t_sort_content.SORT_CONTENT_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Integer getSortContentId() {
        return sortContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.SORT_CONTENT_ID
     *
     * @param sortContentId the value for t_sort_content.SORT_CONTENT_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setSortContentId(Integer sortContentId) {
        this.sortContentId = sortContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.CONTENT_NAME
     *
     * @return the value of t_sort_content.CONTENT_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getContentName() {
        return contentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.CONTENT_NAME
     *
     * @param contentName the value for t_sort_content.CONTENT_NAME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setContentName(String contentName) {
        this.contentName = contentName == null ? null : contentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.CONTENT_LINK
     *
     * @return the value of t_sort_content.CONTENT_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getContentLink() {
        return contentLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.CONTENT_LINK
     *
     * @param contentLink the value for t_sort_content.CONTENT_LINK
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setContentLink(String contentLink) {
        this.contentLink = contentLink == null ? null : contentLink.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.VFS_ID
     *
     * @return the value of t_sort_content.VFS_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getVfsId() {
        return vfsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.VFS_ID
     *
     * @param vfsId the value for t_sort_content.VFS_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.ORDER_NO
     *
     * @return the value of t_sort_content.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.ORDER_NO
     *
     * @param orderNo the value for t_sort_content.ORDER_NO
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.more_url
     *
     * @return the value of t_sort_content.more_url
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getMoreUrl() {
        return moreUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.more_url
     *
     * @param moreUrl the value for t_sort_content.more_url
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl == null ? null : moreUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.STATUS
     *
     * @return the value of t_sort_content.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.STATUS
     *
     * @param status the value for t_sort_content.STATUS
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.CREATE_STAFF_ID
     *
     * @return the value of t_sort_content.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.CREATE_STAFF_ID
     *
     * @param createStaffId the value for t_sort_content.CREATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.CREATE_TIME
     *
     * @return the value of t_sort_content.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.CREATE_TIME
     *
     * @param createTime the value for t_sort_content.CREATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.UPDATE_STAFF_ID
     *
     * @return the value of t_sort_content.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.UPDATE_STAFF_ID
     *
     * @param updateStaffId the value for t_sort_content.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sort_content.UPDATE_TIME
     *
     * @return the value of t_sort_content.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sort_content.UPDATE_TIME
     *
     * @param updateTime the value for t_sort_content.UPDATE_TIME
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}