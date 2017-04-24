package com.ai.bdex.dataexchange.solrutil;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: 业务异常<br>
 * Date:2017年4月24日上午8:50:49  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 7560621105713460541L;

	public BusinessException() {
		super(); 
	} 
	
	public BusinessException(String msg) {
		super(msg); 
	} 
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause); 
	}
	
	public BusinessException(Throwable cause) {
		super(cause); 
	}
	
}