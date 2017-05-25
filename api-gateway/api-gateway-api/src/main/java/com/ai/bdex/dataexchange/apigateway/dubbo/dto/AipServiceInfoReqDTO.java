package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

public class AipServiceInfoReqDTO extends BaseInfo{
	private static final long serialVersionUID = 1L;

	private String serviceId;

   private String version;
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


	private String serviceName;

	private String serviceDesc;

	private Date createTime;

	private String createStaff;

	private Date updateTime;

	private String updateStaff;

	private String apiUrl;

	private String exampleUrl;

	private String apiRemark;

	private String testTool;

	private String pServiceId;

	private String pVersion;

	private String returnExample;
	
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(String updateStaff) {
		this.updateStaff = updateStaff;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getExampleUrl() {
		return exampleUrl;
	}

	public void setExampleUrl(String exampleUrl) {
		this.exampleUrl = exampleUrl;
	}

	public String getApiRemark() {
		return apiRemark;
	}

	public void setApiRemark(String apiRemark) {
		this.apiRemark = apiRemark;
	}

	public String getTestTool() {
		return testTool;
	}

	public void setTestTool(String testTool) {
		this.testTool = testTool;
	}

	public String getpServiceId() {
		return pServiceId;
	}

	public void setpServiceId(String pServiceId) {
		this.pServiceId = pServiceId;
	}

	public String getReturnExample() {
		return returnExample;
	}

	public void setReturnExample(String returnExample) {
		this.returnExample = returnExample;
	}

	public String getpVersion() {
		return pVersion;
	}

	public void setpVersion(String pVersion) {
		this.pVersion = pVersion;
	}
}
