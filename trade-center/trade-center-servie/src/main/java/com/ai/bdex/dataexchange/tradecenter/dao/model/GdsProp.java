package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class GdsProp {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.pro_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private Integer proId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.pro_type
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private String proType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.pro_name
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private String proName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.cat_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private Integer catId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.show_order
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private Integer showOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.status
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.create_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.create_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.update_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gds_prop.update_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.pro_id
     *
     * @return the value of t_gds_prop.pro_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public Integer getProId() {
        return proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.pro_id
     *
     * @param proId the value for t_gds_prop.pro_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setProId(Integer proId) {
        this.proId = proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.pro_type
     *
     * @return the value of t_gds_prop.pro_type
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public String getProType() {
        return proType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.pro_type
     *
     * @param proType the value for t_gds_prop.pro_type
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setProType(String proType) {
        this.proType = proType == null ? null : proType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.pro_name
     *
     * @return the value of t_gds_prop.pro_name
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public String getProName() {
        return proName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.pro_name
     *
     * @param proName the value for t_gds_prop.pro_name
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.cat_id
     *
     * @return the value of t_gds_prop.cat_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.cat_id
     *
     * @param catId the value for t_gds_prop.cat_id
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.show_order
     *
     * @return the value of t_gds_prop.show_order
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public Integer getShowOrder() {
        return showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.show_order
     *
     * @param showOrder the value for t_gds_prop.show_order
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.status
     *
     * @return the value of t_gds_prop.status
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.status
     *
     * @param status the value for t_gds_prop.status
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.create_user
     *
     * @return the value of t_gds_prop.create_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.create_user
     *
     * @param createUser the value for t_gds_prop.create_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.create_time
     *
     * @return the value of t_gds_prop.create_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.create_time
     *
     * @param createTime the value for t_gds_prop.create_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.update_user
     *
     * @return the value of t_gds_prop.update_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.update_user
     *
     * @param updateUser the value for t_gds_prop.update_user
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gds_prop.update_time
     *
     * @return the value of t_gds_prop.update_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gds_prop.update_time
     *
     * @param updateTime the value for t_gds_prop.update_time
     *
     * @mbg.generated Fri Apr 21 20:25:35 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}