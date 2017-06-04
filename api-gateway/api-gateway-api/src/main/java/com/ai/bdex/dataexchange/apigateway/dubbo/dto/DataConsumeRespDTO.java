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

    //数据账户编码
    private Long dataAccountId;

    //套餐类型：01-固定套餐，02-自定义套餐，03-跨类套餐
    private String packageType;

    //订单编号
    private String orderId;

    //子订单编码
    private String subOrder;

    //剩余金额
    private Integer leftNum;

    //剩余次数
    private Integer leftMoney;

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

    public Long getDataAccountId() {
        return dataAccountId;
    }

    public void setDataAccountId(Long dataAccountId) {
        this.dataAccountId = dataAccountId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Integer leftMoney) {
        this.leftMoney = leftMoney;
    }
}
