package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

/**
 * Created by yx on 2017/4/18.
 */
public class PageHeaderNavRespDTO extends BaseResponseDTO {
	private static final long serialVersionUID = 1L;
	private Integer navId;
	private String navName;
	private String navLink;
	private Integer navOrder;
	private String status;
	private String remark;
	private String createStaffId;
	private Date createTime;
	private String updateStaffId;
	private Date updateTime;

	public Integer getNavId() {
		return navId;
	}

	public void setNavId(Integer navId) {
		this.navId = navId;
	}

	public String getNavName() {
		return navName;
	}

	public void setNavName(String navName) {
		this.navName = navName;
	}

	public String getNavLink() {
		return navLink;
	}

	public void setNavLink(String navLink) {
		this.navLink = navLink;
	}

	public Integer getNavOrder() {
		return navOrder;
	}

	public void setNavOrder(Integer navOrder) {
		this.navOrder = navOrder;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
