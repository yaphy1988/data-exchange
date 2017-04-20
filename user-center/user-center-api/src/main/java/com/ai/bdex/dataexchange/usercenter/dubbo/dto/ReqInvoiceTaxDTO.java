package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class ReqInvoiceTaxDTO extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;//审核状态
	
	private String staffId;
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
