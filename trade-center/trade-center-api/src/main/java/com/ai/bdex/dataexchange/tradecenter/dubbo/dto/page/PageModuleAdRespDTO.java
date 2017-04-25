package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class PageModuleAdRespDTO  extends BaseResponseDTO{

	private static final long serialVersionUID = 1L;

	private Integer adId;

    private Integer moduleId;

    private String adTitle;

    private String linkPage;

    private String bmpName;

    private String vfsId;

    private Integer adOrder;

    private String status;

    private String remark;

    private String createStaffId;

    private Date createTime;

    private Date updateTime;

    private String updateStaffId;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle == null ? null : adTitle.trim();
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(String linkPage) {
        this.linkPage = linkPage == null ? null : linkPage.trim();
    }

    public String getBmpName() {
        return bmpName;
    }

    public void setBmpName(String bmpName) {
        this.bmpName = bmpName == null ? null : bmpName.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public Integer getAdOrder() {
        return adOrder;
    }

    public void setAdOrder(Integer adOrder) {
        this.adOrder = adOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }
}