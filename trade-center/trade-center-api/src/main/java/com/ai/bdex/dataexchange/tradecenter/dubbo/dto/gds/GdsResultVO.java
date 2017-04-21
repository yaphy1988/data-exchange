package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.io.Serializable;

public class GdsResultVO implements Serializable {
	private static final long serialVersionUID = 570589561812635540L;
	private Boolean result;
	private String message;
	private int gdsId;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getGdsId() {
		return gdsId;
	}

	public void setGdsId(int gdsId) {
		this.gdsId = gdsId;
	}

}
