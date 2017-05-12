package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public class RechargeReqDTO extends BaseInfo {

    private String rechargeReqId;

    private String rechargeUserId;

    /**
     * 类型：1-次数，2-余额
     */
    private String rechargeType;

    /**
     * 1-有生效日期和失效日期
     */
    private String periodType;

    private String orderId;

    private String subOrder;

    private String serviceId;

    private int catId;

    private int catFirst;

    private int gdsId;

    private int skuId;

    private Integer totalNum;

    /**
     * 单位厘
     */
    private Integer totalMoney;

    private Date startDate;

    private Date endDate;

    private Long dataAccountId;

    //查询是是否要连数据账户信息一起查询
    private boolean queryDataAccount = false;
    private DataAccountDTO dataAccountDTO;

    public String getRechargeReqId() {
        return rechargeReqId;
    }

    public void setRechargeReqId(String rechargeReqId) {
        this.rechargeReqId = rechargeReqId;
    }

    public String getRechargeUserId() {
        return rechargeUserId;
    }

    public void setRechargeUserId(String rechargeUserId) {
        this.rechargeUserId = rechargeUserId;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCatFirst() {
        return catFirst;
    }

    public void setCatFirst(int catFirst) {
        this.catFirst = catFirst;
    }

    public int getGdsId() {
        return gdsId;
    }

    public void setGdsId(int gdsId) {
        this.gdsId = gdsId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
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

    public Long getDataAccountId() {
        return dataAccountId;
    }

    public void setDataAccountId(Long dataAccountId) {
        this.dataAccountId = dataAccountId;
    }

    public boolean isQueryDataAccount() {
        return queryDataAccount;
    }

    public void setQueryDataAccount(boolean queryDataAccount) {
        this.queryDataAccount = queryDataAccount;
    }

    public DataAccountDTO getDataAccountDTO() {
        return dataAccountDTO;
    }

    public void setDataAccountDTO(DataAccountDTO dataAccountDTO) {
        this.dataAccountDTO = dataAccountDTO;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("rechargeDTO:")
                .append("orderId:").append(orderId).append(",")
                .append("subOrder:").append(subOrder).append(",")
                .append("rechargeUserId:").append(rechargeUserId).append(",")
                .append("serviceId:").append(serviceId).append(",")
                .append("rechargeType:").append(rechargeType).append(",")
                .append("periodType:").append(periodType).append(",")
                .append("totalMoney:").append(totalMoney).append(",")
                .append("totalNum:").append(totalNum).append(",")
                .append("startDate:").append(startDate==null?"":startDate.toString()).append(",")
                .append("endDate:").append(endDate==null?"":endDate.toString()).append(",");
        return sb.toString();
    }
}
