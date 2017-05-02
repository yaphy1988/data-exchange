package com.ai.bdex.dataexchange.common;

import java.util.Map;

/**
 * Created by yx on 2017/4/19.
 */
public class AjaxJson {

    private boolean success = true;// 是否成功
    private String msg = "操作成功";// 提示信息
    private Object obj = null;// 其他信息
    private Map<String, Object> attributes;// 其他参数
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
