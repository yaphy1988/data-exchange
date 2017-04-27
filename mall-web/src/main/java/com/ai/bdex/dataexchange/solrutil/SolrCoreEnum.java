package com.ai.bdex.dataexchange.solrutil;

/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: Solr集合枚举<br>
 * Date:2017年4月26日上午11:19:40  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public enum SolrCoreEnum {
	/**
	 * 商品集合
	 */
	GDS("gdsCollection");

	/**
	 * 集合名词
	 */
	private String code;

	private SolrCoreEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
