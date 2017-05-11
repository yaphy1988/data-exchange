package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/10.
 */
public class DataConsumeDTO {

    //消费流水
    private String billId;

    //调用流水
    private String invokeSeq;

    //调用接口
    private String realServiceId;

    //用户id
    private String userId;

    //消费的数据账户
    private Long dataAcctId;

    //消费的数据账户类型
    private String dataAcctType;

    //消费次数
    private Integer consumeNum;

    //消费金额
    private Integer consumeMoney;

    //消费时间
    private Date consumeTime;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getInvokeSeq() {
        return invokeSeq;
    }

    public void setInvokeSeq(String invokeSeq) {
        this.invokeSeq = invokeSeq;
    }

    public String getRealServiceId() {
        return realServiceId;
    }

    public void setRealServiceId(String realServiceId) {
        this.realServiceId = realServiceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }
}
