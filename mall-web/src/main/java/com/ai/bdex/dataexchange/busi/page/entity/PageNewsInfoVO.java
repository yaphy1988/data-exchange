package com.ai.bdex.dataexchange.busi.page.entity;

import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

/**
 * Created by yx on 2017/4/18.
 */
public class PageNewsInfoVO extends BaseInfo {
	private static final long serialVersionUID = 1L;

	private Integer infoId;

	private Integer moduleId;
	private String remark;

	private String infoTitle;

	private String infoType;

	private String status;

	private String infoUrl;

	private Integer infoOrder;

	private Date pubTime;

	private Date lostTime;

	private String vfsId;

	private String attachmentName;

	private String createStaffId;

	private Date createTime;

	private String updateStaffId;

	private Date updateTime;

	private PageModuleVO pageModuleVO;
	
	private List<PageNewsInfoVO> newsInfoList;
	
	public List<PageNewsInfoVO> getNewsInfoList() {
		return newsInfoList;
	}

	public void setNewsInfoList(List<PageNewsInfoVO> newsInfoList) {
		this.newsInfoList = newsInfoList;
	}

	public PageModuleVO getPageModuleVO() {
		return pageModuleVO;
	}

	public void setPageModuleVO(PageModuleVO pageModuleVO) {
		this.pageModuleVO = pageModuleVO;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	public Integer getInfoOrder() {
		return infoOrder;
	}

	public void setInfoOrder(Integer infoOrder) {
		this.infoOrder = infoOrder;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Date getLostTime() {
		return lostTime;
	}

	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}

	public String getVfsId() {
		return vfsId;
	}

	public void setVfsId(String vfsId) {
		this.vfsId = vfsId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
