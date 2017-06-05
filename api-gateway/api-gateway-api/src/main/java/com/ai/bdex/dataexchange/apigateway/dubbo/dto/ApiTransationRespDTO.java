package com.ai.bdex.dataexchange.apigateway.dubbo.dto;

import java.io.Serializable;

public class ApiTransationRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String respCode;
	private String respDesc;
	private String serialNo;
	private Object result;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
		
}
