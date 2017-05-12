package com.ai.bdex.dataexchange.solrutil;

import java.io.Serializable;

/**]
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年5月11日下午3:20:07  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version @param <T> 
 * @since JDK 1.6
 */
@SuppressWarnings("rawtypes")
public class Field<T> implements Serializable, Comparable<Field>{

    private static final long serialVersionUID = 1L;

    private String name;
    
    private String nameCn;

    private T value;
    
    private boolean except = false;

    public Field(){}

    public Field(String name,T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isExcept() {
        return except;
    }

    public void setExcept(boolean except) {
        this.except = except;
    }

    @Override
    public int compareTo(Field o) {
        return this.name.compareTo(o.name);
    }

}
