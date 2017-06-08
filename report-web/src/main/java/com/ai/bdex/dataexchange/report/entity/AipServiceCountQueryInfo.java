package com.ai.bdex.dataexchange.report.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by chenjy on 2017/6/6.
 */
public class AipServiceCountQueryInfo extends BaseInfo {




    //查询条件
    private String serviceNameKey;
    private String queryStatus;
    private Date startTime;
    private Date endTime;

    public String getServiceNameKey() {
        return serviceNameKey;
    }
    public void setServiceNameKey(String serviceNameKey) {
        this.serviceNameKey = serviceNameKey;
    }
    public String getQueryStatus() {
        return queryStatus;
    }
    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    /**分页数据**/
    //服务编码
    private String serviceId;
    //服务名称
    private String serviceName;
    //服务状态
    private String status;
    //连接量（当前累计已被多少用户订购过）
    private int ordAmount;
    //请求次数（查询时间段内，被请求总量）
    private int requestSum;
    //成功次数(被请求次数中，成功返回报文的次数)
    private int requestSucess;
    //缓存调用次数(有返回报文的次数中，从缓存数据库直接调用的次数)
    private int localData;


    public  String getServiceId() {
        return serviceId;
    }
    public void   setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void   setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public int    getOrdAmount() {
        return ordAmount;
    }
    public void   setOrdAmount(int ordAmount) {
        this.ordAmount = ordAmount;
    }
    public int    getRequestSum() {
        return requestSum;
    }
    public void   setRequestSum(int requestSum) {
        this.requestSum = requestSum;
    }
    public int    getRequestSucess() {
        return requestSucess;
    }
    public void   setRequestSucess(int requestSucess) {
        this.requestSucess = requestSucess;
    }
    public int    getLocalData() {
        return localData;
    }
    public void   setLocalData(int localData) {
        this.localData = localData;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
