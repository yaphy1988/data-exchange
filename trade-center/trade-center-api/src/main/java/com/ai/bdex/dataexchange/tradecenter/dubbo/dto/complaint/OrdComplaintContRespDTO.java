package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class OrdComplaintContRespDTO extends BaseResponseDTO{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.COMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Long complaintContId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Long complaintId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.FROM_REMARK
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String fromRemark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.COMPLAINT_TYPE
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Short complaintType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.COMPLAINTS_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Date complaintsTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String updateStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_complaint_cont.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    private String createStaff;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.COMPLAINT_CONT_ID
     *
     * @return the value of t_ord_complaint_cont.COMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Long getComplaintContId() {
        return complaintContId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.COMPLAINT_CONT_ID
     *
     * @param complaintContId the value for t_ord_complaint_cont.COMPLAINT_CONT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintContId(Long complaintContId) {
        this.complaintContId = complaintContId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.COMPLAINT_ID
     *
     * @return the value of t_ord_complaint_cont.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Long getComplaintId() {
        return complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.COMPLAINT_ID
     *
     * @param complaintId the value for t_ord_complaint_cont.COMPLAINT_ID
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.FROM_REMARK
     *
     * @return the value of t_ord_complaint_cont.FROM_REMARK
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getFromRemark() {
        return fromRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.FROM_REMARK
     *
     * @param fromRemark the value for t_ord_complaint_cont.FROM_REMARK
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark == null ? null : fromRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.COMPLAINT_TYPE
     *
     * @return the value of t_ord_complaint_cont.COMPLAINT_TYPE
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Short getComplaintType() {
        return complaintType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.COMPLAINT_TYPE
     *
     * @param complaintType the value for t_ord_complaint_cont.COMPLAINT_TYPE
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintType(Short complaintType) {
        this.complaintType = complaintType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.COMPLAINTS_TIME
     *
     * @return the value of t_ord_complaint_cont.COMPLAINTS_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Date getComplaintsTime() {
        return complaintsTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.COMPLAINTS_TIME
     *
     * @param complaintsTime the value for t_ord_complaint_cont.COMPLAINTS_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setComplaintsTime(Date complaintsTime) {
        this.complaintsTime = complaintsTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.CREATE_TIME
     *
     * @return the value of t_ord_complaint_cont.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.CREATE_TIME
     *
     * @param createTime the value for t_ord_complaint_cont.CREATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.UPDATE_STAFF
     *
     * @return the value of t_ord_complaint_cont.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getUpdateStaff() {
        return updateStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.UPDATE_STAFF
     *
     * @param updateStaff the value for t_ord_complaint_cont.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.UPDATE_TIME
     *
     * @return the value of t_ord_complaint_cont.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.UPDATE_TIME
     *
     * @param updateTime the value for t_ord_complaint_cont.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_complaint_cont.CREATE_STAFF
     *
     * @return the value of t_ord_complaint_cont.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getCreateStaff() {
        return createStaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_complaint_cont.CREATE_STAFF
     *
     * @param createStaff the value for t_ord_complaint_cont.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }
}