package com.ai.bdex.dataexchange.solrutil;

import org.apache.commons.lang.StringUtils;

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

