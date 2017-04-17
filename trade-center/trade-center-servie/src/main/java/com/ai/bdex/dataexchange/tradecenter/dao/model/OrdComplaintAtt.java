package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class OrdComplaintAtt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.COMPLAINT_ATT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Long complaintAttId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Long complaintId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.CMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Long cmplaintContId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.VFS_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String vfsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.VFS_NAME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String vfsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_att.STATUS
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.COMPLAINT_ATT_ID
     *
     * @return the value of t_ord_complaint_att.COMPLAINT_ATT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Long getComplaintAttId() {
        return complaintAttId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.COMPLAINT_ATT_ID
     *
     * @param complaintAttId the value for t_ord_complaint_att.COMPLAINT_ATT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintAttId(Long complaintAttId) {
        this.complaintAttId = complaintAttId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.COMPLAINT_ID
     *
     * @return the value of t_ord_complaint_att.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Long getComplaintId() {
        return complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.COMPLAINT_ID
     *
     * @param complaintId the value for t_ord_complaint_att.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.CMPLAINT_CONT_ID
     *
     * @return the value of t_ord_complaint_att.CMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Long getCmplaintContId() {
        return cmplaintContId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.CMPLAINT_CONT_ID
     *
     * @param cmplaintContId the value for t_ord_complaint_att.CMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setCmplaintContId(Long cmplaintContId) {
        this.cmplaintContId = cmplaintContId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.VFS_ID
     *
     * @return the value of t_ord_complaint_att.VFS_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getVfsId() {
        return vfsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.VFS_ID
     *
     * @param vfsId the value for t_ord_complaint_att.VFS_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.VFS_NAME
     *
     * @return the value of t_ord_complaint_att.VFS_NAME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getVfsName() {
        return vfsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.VFS_NAME
     *
     * @param vfsName the value for t_ord_complaint_att.VFS_NAME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setVfsName(String vfsName) {
        this.vfsName = vfsName == null ? null : vfsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.STAFF_ID
     *
     * @return the value of t_ord_complaint_att.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.STAFF_ID
     *
     * @param staffId the value for t_ord_complaint_att.STAFF_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.CREATE_TIME
     *
     * @return the value of t_ord_complaint_att.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.CREATE_TIME
     *
     * @param createTime the value for t_ord_complaint_att.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_att.STATUS
     *
     * @return the value of t_ord_complaint_att.STATUS
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_att.STATUS
     *
     * @param status the value for t_ord_complaint_att.STATUS
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}