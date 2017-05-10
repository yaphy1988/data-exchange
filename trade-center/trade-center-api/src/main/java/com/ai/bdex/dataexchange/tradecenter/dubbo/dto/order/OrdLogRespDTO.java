package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class OrdLogRespDTO  extends BaseResponseDTO{
    private Long sysLogId;
    private String orderId;
    private String subOrder;
    private String node;
    private String nodeDesc;
    private BigDecimal nexd1;
    private String reserved1; 
    private String reserved2; 
    private String reserved3;
    private String remark;
    private String provinceCode;
    private String createStaff;
    private Date createTime;
	public Long getSysLogId() {
		return sysLogId;
	}
	public void setSysLogId(Long sysLogId) {
		this.sysLogId = sysLogId;
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
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public BigDecimal getNexd1() {
		return nexd1;
	}
	public void setNexd1(BigDecimal nexd1) {
		this.nexd1 = nexd1;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    

}
