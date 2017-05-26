package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class GdsLabel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.lab_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private Integer labId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.gds_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private Integer gdsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.lab_name
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String labName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.lab_color
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String labColor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.show_order
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private Integer showOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.if_edit
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String ifEdit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.status
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.create_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.create_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.update_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_label.update_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.lab_id
     *
     * @return the value of t_gds_label.lab_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Integer getLabId() {
        return labId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.lab_id
     *
     * @param labId the value for t_gds_label.lab_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.gds_id
     *
     * @return the value of t_gds_label.gds_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Integer getGdsId() {
        return gdsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.gds_id
     *
     * @param gdsId the value for t_gds_label.gds_id
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.lab_name
     *
     * @return the value of t_gds_label.lab_name
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getLabName() {
        return labName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.lab_name
     *
     * @param labName the value for t_gds_label.lab_name
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setLabName(String labName) {
        this.labName = labName == null ? null : labName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.lab_color
     *
     * @return the value of t_gds_label.lab_color
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getLabColor() {
        return labColor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.lab_color
     *
     * @param labColor the value for t_gds_label.lab_color
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setLabColor(String labColor) {
        this.labColor = labColor == null ? null : labColor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.show_order
     *
     * @return the value of t_gds_label.show_order
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Integer getShowOrder() {
        return showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.show_order
     *
     * @param showOrder the value for t_gds_label.show_order
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.if_edit
     *
     * @return the value of t_gds_label.if_edit
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getIfEdit() {
        return ifEdit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.if_edit
     *
     * @param ifEdit the value for t_gds_label.if_edit
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setIfEdit(String ifEdit) {
        this.ifEdit = ifEdit == null ? null : ifEdit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.status
     *
     * @return the value of t_gds_label.status
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.status
     *
     * @param status the value for t_gds_label.status
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.create_user
     *
     * @return the value of t_gds_label.create_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.create_user
     *
     * @param createUser the value for t_gds_label.create_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.create_time
     *
     * @return the value of t_gds_label.create_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.create_time
     *
     * @param createTime the value for t_gds_label.create_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.update_user
     *
     * @return the value of t_gds_label.update_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.update_user
     *
     * @param updateUser the value for t_gds_label.update_user
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_label.update_time
     *
     * @return the value of t_gds_label.update_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_label.update_time
     *
     * @param updateTime the value for t_gds_label.update_time
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}