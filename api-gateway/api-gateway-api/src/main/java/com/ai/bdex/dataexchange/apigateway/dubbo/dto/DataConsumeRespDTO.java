package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/10.
 */
public class DataConsumeRespDTO {

    //消费流水
    private String billId;

    //消费结果:OK - 扣费成功，NA - 没有找到可以扣费的记录或余额不足
    private String result;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
