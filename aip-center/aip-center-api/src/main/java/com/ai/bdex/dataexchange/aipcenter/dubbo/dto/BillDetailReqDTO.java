package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
public class BillDetailReqDTO extends BaseInfo {

    //消费流水
    private String billId;

    //子订单
    private String subOrder;

    private Long dataAcctId;

    //用户id
    private String userId;

    //服务编码
    private String serviceId;

    //查询起始时间
    private Date queryStartDate;

    //查询截止时间
    private Date queryEndDate;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder;
    }

    public Long getDataAcctId() {
        return dataAcctId;
    }

    public void setDataAcctId(Long dataAcctId) {
        this.dataAcctId = dataAcctId;
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

    public Date getQueryStartDate() {
        return queryStartDate;
    }

    public void setQueryStartDate(Date queryStartDate) {
        this.queryStartDate = queryStartDate;
    }

    public Date getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(Date queryEndDate) {
        this.queryEndDate = queryEndDate;
    }
}
