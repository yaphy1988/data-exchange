package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public class DemoDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 1L;

    private int id;
    private String userName;
    private String addr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
