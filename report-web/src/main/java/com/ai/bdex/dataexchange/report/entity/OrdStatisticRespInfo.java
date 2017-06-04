package com.ai.bdex.dataexchange.report.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by liangwy on 2017/5/24.
 */
public class OrdStatisticRespInfo extends BaseInfo {
    private String id;//orderId
    private String orderId;
    private String staffId;
    private String orderTime;
    private String gdsName;
    private String skuName;
    private Integer orderAmount;
    private String orderMoney;
    private String orderStatus;
    private String invoiceStatus;//开票标识
    private String payTradeno;//交易流水号

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayTradeno() {
        return payTradeno;
    }

    public void setPayTradeno(String payTradeno) {
        this.payTradeno = payTradeno;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}
