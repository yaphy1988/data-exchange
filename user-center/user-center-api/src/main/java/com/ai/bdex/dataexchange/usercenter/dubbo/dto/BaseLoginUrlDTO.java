package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

public class BaseLoginUrlDTO extends BaseInfo {

    private String sysCode;
    private String url;
    private String unloginAccess;

    public String getUnloginAccess() {
        return unloginAccess;
    }

    public void setUnloginAccess(String unloginAccess) {
        this.unloginAccess = unloginAccess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}