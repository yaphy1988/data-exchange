package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/8.
 */
public class DataAccountDTO extends BaseInfo {

    //数据账户id
    private Long dataAcctId;

    //数据账户类型 1-次数，2-金额
    private String dataAcctType;

    //数据账户有效期类型：1-有生效开始日期生效结束日期，2-永久有效
    private String periodType;

    //
    private String userId;


    private String serviceId;


    private Integer totalNum;


    private Integer leftNum;


    private Integer totalConsumeNum;



    private Integer totalMoney;


    private Integer leftMoney;


    private Integer totalConsumeMoney;


    private Date startDate;


    private Date endDate;


    private String dataAcctStatus;


    public Long getDataAcctId() {
        return dataAcctId;
    }

    public void setDataAcctId(Long dataAcctId) {
        this.dataAcctId = dataAcctId;
    }

    public String getDataAcctType() {
        return dataAcctType;
    }

    public void setDataAcctType(String dataAcctType) {
        this.dataAcctType = dataAcctType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getTotalConsumeNum() {
        return totalConsumeNum;
    }

    public void setTotalConsumeNum(Integer totalConsumeNum) {
        this.totalConsumeNum = totalConsumeNum;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Integer leftMoney) {
        this.leftMoney = leftMoney;
    }

    public Integer getTotalConsumeMoney() {
        return totalConsumeMoney;
    }

    public void setTotalConsumeMoney(Integer totalConsumeMoney) {
        this.totalConsumeMoney = totalConsumeMoney;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDataAcctStatus() {
        return dataAcctStatus;
    }

    public void setDataAcctStatus(String dataAcctStatus) {
        this.dataAcctStatus = dataAcctStatus;
    }
}
