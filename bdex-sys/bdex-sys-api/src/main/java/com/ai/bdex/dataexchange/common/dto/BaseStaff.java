package com.ai.bdex.dataexchange.common.dto;

import com.ai.bdex.dataexchange.util.StaffLocaleUtil;

/**
 * Created by fangyunfeng on 2017/4/17.
 */
public class BaseStaff {
    private static final long serialVersionUID = -991423047502834466L;
    private long id;
    private String staffCode;
    private String staffClass;
    private String staffLevelCode;



    public BaseStaff() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffClass() {
        return staffClass;
    }

    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass;
    }

    public String getStaffLevelCode() {
        return staffLevelCode;
    }

    public void setStaffLevelCode(String staffLevelCode) {
        this.staffLevelCode = staffLevelCode;
    }
}
