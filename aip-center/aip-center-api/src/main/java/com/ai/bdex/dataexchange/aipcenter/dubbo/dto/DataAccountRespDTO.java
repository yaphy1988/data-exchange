package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.Date;

/**
 * Created by fangyunfeng on 2017/5/5.
 */
public class DataAccountRespDTO extends BaseResponseDTO {

    /**
     * 数据账户id
     */
    private Long dataAcctId;

    /**
     * 数据账户类型：1-次数，2-金额
     */
    private String dataAcctType;

    /**
     * 套餐类型：01-固定套餐，02-自定义套餐，03-跨类套餐
     */
    private String packageType;

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
     * 总金额，单位厘
     */
    private Integer totalMoney;

    /**
     * 余额，单位厘
     */
    private Integer leftMoney;

    /**
     * 累计消费金额，单位厘
     */
    private Integer totalConsumeMoney;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 子订单编码
     */
    private String subOrder;

    /**
     * 商品编码
     */
    private Integer gdsId;

    /**
     * 单品编码
     */
    private Integer skuId;

    /**
     * 商品分类编码
     */
    private Integer catId;

    /**
     * 商品一级分类编码
     */
    private Integer catFirst;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 最早申请的时间
     */
     private Date firstbuytime;

    public Date getfirstBuyTime() {
        return firstbuytime;
    }
    public void setfirstBuyTime(Date firstbuytime) {
        this.firstbuytime = firstbuytime;
    }

    public String getServicename() {
        return serviceName;
    }

    public void setServicename(String serviceName) {
        this.serviceName = serviceName;
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

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
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

    public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatFirst() {
        return catFirst;
    }

    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
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

    public double getTotalMoneyCent(){
        return totalMoney / 10.0;
    }
    public double getLeftMoneyCent(){
        return leftMoney / 10.0;
    }
    public double getTotalConsumeMoneyCent(){
        return totalConsumeMoney / 10.0;
    }

    public void setTotalMoneyCent(double totalMoneyCent){
        totalMoney = (int)totalMoneyCent * 10;
    }

    public void setLeftMoneyCent(double leftMoneyCent){
        leftMoney = (int)leftMoneyCent * 10;
    }
    public void setTotalConsumeMoneyCent(double totalConsumeMoneyCent){
        totalConsumeMoney = (int)totalConsumeMoneyCent * 10;
    }
}
