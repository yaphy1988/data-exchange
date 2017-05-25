package com.ai.bdex.dataexchange.solrutil;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年5月11日下午3:20:18  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public enum ESort {
    
    ASC("asc"),
    DESC("desc");
    
    private String sort;
    
    public String getSort() {
        return sort;
    }
    
    public static ESort getAndValidSort(String sort){
        for(ESort eSort:ESort.values()){
            if(StringUtils.equals(eSort.getSort(), sort)){
                return eSort;
            }
        }
        return null;
    }

    ESort(String sort){
        this.sort=sort;
    }

}

