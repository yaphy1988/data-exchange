package com.ai.bdex.dataexchange.busi.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yx on 2017/4/26.
 */
public class AipServiceErrorInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.ERROR_ID
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String errorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.SERVICE_ID
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.VERSION
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.TYPE
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.ERROR_CODE
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String errorCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.ERROR_MSG
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String errorMsg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.STATUS
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.CREATE_TIME
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.CREATE_STAFF
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String createStaff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_service_error_info.UPDATE_STAFF
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    private String updateStaff;

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
