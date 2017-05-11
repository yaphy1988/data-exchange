package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;

public class PageModuleGoodsRespDTO   extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

	private Integer pmgId;

    private Integer moduleId;

    private Integer gdsId;

    private String recommendName;

    private Integer orderNo;

    private Date createTime;

    private String createStaffId;

    private Date updateTime;

    private String updateStaffId;

    private String status;
    private String vfsid;
    private String catName;
    
    private GdsInfoRespDTO gdsInfoRespDTO;
    private GdsCatRespDTO gdsCatRespDTO;
    
    public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public GdsCatRespDTO getGdsCatRespDTO() {
		return gdsCatRespDTO;
	}

	public void setGdsCatRespDTO(GdsCatRespDTO gdsCatRespDTO) {
		this.gdsCatRespDTO = gdsCatRespDTO;
	}

	public GdsInfoRespDTO getGdsInfoRespDTO() {
		return gdsInfoRespDTO;
	}

	public void setGdsInfoRespDTO(GdsInfoRespDTO gdsInfoRespDTO) {
		this.gdsInfoRespDTO = gdsInfoRespDTO;
	}

	public String getVfsid() {
		return vfsid;
	}

	public void setVfsid(String vfsid) {
		this.vfsid = vfsid;
	}

	public Integer getPmgId() {
        return pmgId;
    }

    public void setPmgId(Integer pmgId) {
        this.pmgId = pmgId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName == null ? null : recommendName.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
}