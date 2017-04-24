package com.ai.bdex.dataexchange.busi.search.entiry;

import java.io.Serializable;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月21日上午11:14:20  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchVO extends BaseInfo implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3329595398889197798L;
    /**
     * 关键词
     */
    private String keyWord;
    /**
     * 分类id
     */
    private int catId;
    
}

