package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.Date;


public class DataCustomizationReqDTO extends BaseInfo {
	private static final long serialVersionUID = 1L;
	private Integer dczaId;
	private String customDescrip;
	private String linkPerson;
	private String linkPhnoe;
	private Date createTime;
	private String status;
	private String remark;
	private String createStaffId;
	private Date createTime2;
	private Date updateTime;
	private String updateStaffId;
	private String customName;
	private String customMail;

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomMail() {
		return customMail;
	}

	public void setCustomMail(String customMail) {
		this.customMail = customMail;
	}

	public Integer getDczaId() {
		return dczaId;
	}

	public void setDczaId(Integer dczaId) {
		this.dczaId = dczaId;
	}

	public String getCustomDescrip() {
		return customDescrip;
	}

	public void setCustomDescrip(String customDescrip) {
		this.customDescrip = customDescrip;
	}

	public String getLinkPerson() {
		return linkPerson;
	}

	public void setLinkPerson(String linkPerson) {
		this.linkPerson = linkPerson;
	}

	public String getLinkPhnoe() {
		return linkPhnoe;
	}

	public void setLinkPhnoe(String linkPhnoe) {
		this.linkPhnoe = linkPhnoe;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	public Date getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

}
