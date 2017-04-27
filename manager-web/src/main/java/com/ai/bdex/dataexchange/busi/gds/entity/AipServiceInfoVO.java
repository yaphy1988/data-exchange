package com.ai.bdex.dataexchange.busi.gds.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class AipServiceInfoVO extends BaseInfo{
	private static final long serialVersionUID = 1L;
	/**
	 * 接口类型。 默认00，00通用服务 01个性服务
	 */
	private String type;
	/**
	 * 服务提供商Id
	 */
	private String providerId; 
	/**
	 * 服务状态。0无效，1有效。默认1
	 */
	private String status="1";
	
	/**
	 * 支持格式。例如json
	 */
	private String supportFormat;

	/**
	 * 请求方式。例如 http get/post
	 */
	private String reqType;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSupportFormat() {
		return supportFormat;
	}
	public void setSupportFormat(String supportFormat) {
		this.supportFormat = supportFormat;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	
	
}
