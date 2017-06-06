package com.ai.bdex.dataexchange.report.entity;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

import java.util.Date;

/**
 * Created by yaphy on 2017/5/24.
 * Update by xiongqian on 2017/6/6
 */
public class UserStatisticQueryInfo extends BaseInfo {

    //查询条件
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**汇总数据**/
    //新增用户总数
    private int allUserCnt;
    //新增普通用户
    private int userCnt;
    //新增企业用户
    private int companyUserCnt;
    //共计用户总数
    private int allUserTotal;
    //共计普通用户
    private int userTotal;
    //共计企业用户
    private int companyUserTotal;

    /**明细数据**/
    //用户ID
    private String staffId;
    //用户名
    private String staffName;
    //用户类型
    private String staffType;
    //企业名称
    private String companyName;
    //联系电话
    private String phoneNumber;
    //地址
    private String companyAddr;
    //邮箱
    private String companyMail;
    //营业执照编号
    private String taxpayerNo;
    //税务登记号
    private String taxNo;
    //组织机构代码
    private String organCode;

    public int getAllUserCnt() {
        return allUserCnt;
    }

    public void setAllUserCnt(int allUserCnt) {
        this.allUserCnt = allUserCnt;
    }

    public int getUserCnt() {
        return userCnt;
    }

    public void setUserCnt(int userCnt) {
        this.userCnt = userCnt;
    }

    public int getCompanyUserCnt() {
        return companyUserCnt;
    }

    public void setCompanyUserCnt(int companyUserCnt) {
        this.companyUserCnt = companyUserCnt;
    }

    public int getAllUserTotal() {
        return allUserTotal;
    }

    public void setAllUserTotal(int allUserTotal) {
        this.allUserTotal = allUserTotal;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public int getCompanyUserTotal() {
        return companyUserTotal;
    }

    public void setCompanyUserTotal(int companyUserTotal) {
        this.companyUserTotal = companyUserTotal;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }
}
