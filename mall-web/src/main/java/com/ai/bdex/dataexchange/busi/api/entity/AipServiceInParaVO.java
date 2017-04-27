package com.ai.bdex.dataexchange.busi.api.entity;

import java.util.Date;

/**
 * Created by yx on 2017/4/26.
 */
public class AipServiceInParaVO {
    private static final long serialVersionUID = 1L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.INPUT_ID
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String inputId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.SERVICE_ID
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.VERSION
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.STATUS
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.TYPE
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.PARA_CODE
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String paraCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.PARA_TYPE
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String paraType;

    private String paraTypeName;//对paraType的转义

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.CREATE_TIME
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_in_para.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String updateStaff;

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getParaTypeName() {
        return paraTypeName;
    }

    public void setParaTypeName(String paraTypeName) {
        this.paraTypeName = paraTypeName;
    }
}