package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class PageModuleAdPropRespDTO  extends BaseResponseDTO{
   
	private static final long serialVersionUID = 1L;

	private Integer attrId;

    private String moduleId;
    private Integer showCount;

    private String picMeasure;

    private String picSize;

    private Date updateTime;

    private String status;

    private String remark;

    private String createStaffId;

    private Date createTime2;

    private String updateStaffId;

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public String getPicMeasure() {
        return picMeasure;
    }

    public void setPicMeasure(String picMeasure) {
        this.picMeasure = picMeasure == null ? null : picMeasure.trim();
    }

    public String getPicSize() {
        return picSize;
    }

    public void setPicSize(String picSize) {
        this.picSize = picSize == null ? null : picSize.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Date getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(Date createTime2) {
        this.createTime2 = createTime2;
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }
}