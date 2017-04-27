package com.ai.bdex.dataexchange.solrutil;
/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月27日上午8:45:01  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SortField extends Field<ESort>{

    private static final long serialVersionUID = 1L;
    
    public SortField(String name,ESort sort){
        super(name, sort);
    }

}

