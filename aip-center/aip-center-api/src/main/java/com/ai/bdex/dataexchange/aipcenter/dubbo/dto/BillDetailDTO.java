package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
public class BillDetailDTO extends BaseResponseDTO {

    private String billId;

    private String invokeSeq;

    private String realServiceId;

    private String userId;

    private Long dataAcctId;

    private String dataAcctType;

    private Integer consumeNum;

    //消费金额，单位厘
    private Integer consumeMoney;

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

    /**
     * 将消费金额转化陈分返回，原来是厘
     * @return
     */
    public double getConsumeMoneyCent(){
        return consumeMoney / 10.0;
    }
}
