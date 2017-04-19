package com.ai.bdex.dataexchange.busi.gds.entity;

/**
 * 商品前后台交互 json对象
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2017-4-19 <br>
 * Copyright (c) 2017 AILK <br>
 * 
 */
public class GdsJsonBean {
    
    /**
     * 成功标志
     */
    private String success;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object object;
    
    
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
  

}
