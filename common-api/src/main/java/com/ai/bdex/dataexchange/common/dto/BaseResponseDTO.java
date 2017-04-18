package com.ai.bdex.dataexchange.common.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author yafei
 * @since 2016/11/15
 */
public class BaseResponseDTO implements Serializable {

    private static final long serialVersionUID = -6065026252741405316L;

    public BaseResponseDTO() {
    }
    
    public String toString() {
		try {
			return JSONObject.toJSONString(this);
		} catch (Exception e) {
		}
		return super.toString();
	}
}
