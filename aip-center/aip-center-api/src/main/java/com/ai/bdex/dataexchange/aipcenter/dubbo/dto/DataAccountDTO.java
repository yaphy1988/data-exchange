package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

/**
 * Created by fangyunfeng on 2017/5/5.
 */
public class DataAccountDTO extends BaseInfo{

    /**
     * 数据账户id
     */
    private Long dataAcctId;

    /**
     * 数据账户类型：1-次数，2-金额
     */
    private String dataAcctType;

    /**
     * 归属用户id
     */
    private String userId;

    /**
     * 服务编码
     */
    private String serviceId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余可调用次数
     */
    private Integer leftNum;

    /**
     * 累计已经消耗的调用次数
     */
    private Integer totalConsumeNum;

    /**
     * 总金额
     */
    private Integer totalMoney;

    /**
     * 余额
     */
    private Integer leftMoney;

    /**
     * 累计消费金额
     */
    private Integer totalConsumeMoney;

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
}
