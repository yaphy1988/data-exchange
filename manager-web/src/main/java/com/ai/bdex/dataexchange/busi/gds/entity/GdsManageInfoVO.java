package com.ai.bdex.dataexchange.busi.gds.entity;

import java.util.Date;

public class GdsManageInfoVO {
    private Integer gdsId;

    private String gdsName;

    private String gdsSubtitle;

    private Integer catFirst;

    private Integer apiId;

    private String gdsPic;

    private String ifRecommend;

    private String funIntroduction;

    private String commpanyName;

    private String status;

    private String shelveUser;

    private Date shelveTime;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    //json串
    private String jsonStr;
    
    private GdsPropVO gdsPropVO;//分类属性表
    
    private GdsLabelVO gdsLabelVO;//商品标签
    
    private GdsInfo2CatVO gdsInfo2CatVO;//商品分类配置关联表
    
    private GdsInfo2PropVO  gdsInfo2PropVO;//商品属性配置
    
    public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public GdsPropVO getGdsPropVO() {
		return gdsPropVO;
	}

	public void setGdsPropVO(GdsPropVO gdsPropVO) {
		this.gdsPropVO = gdsPropVO;
	}

	public GdsLabelVO getGdsLabelVO() {
		return gdsLabelVO;
	}

	public void setGdsLabelVO(GdsLabelVO gdsLabelVO) {
		this.gdsLabelVO = gdsLabelVO;
	}

	public GdsInfo2CatVO getGdsInfo2CatVO() {
		return gdsInfo2CatVO;
	}

	public void setGdsInfo2CatVO(GdsInfo2CatVO gdsInfo2CatVO) {
		this.gdsInfo2CatVO = gdsInfo2CatVO;
	}

	public GdsInfo2PropVO getGdsInfo2PropVO() {
		return gdsInfo2PropVO;
	}

	public void setGdsInfo2PropVO(GdsInfo2PropVO gdsInfo2PropVO) {
		this.gdsInfo2PropVO = gdsInfo2PropVO;
	}

	public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getGdsSubtitle() {
        return gdsSubtitle;
    }

    public void setGdsSubtitle(String gdsSubtitle) {
        this.gdsSubtitle = gdsSubtitle == null ? null : gdsSubtitle.trim();
    }

    public Integer getCatFirst() {
        return catFirst;
    }

    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getGdsPic() {
        return gdsPic;
    }
    public void setGdsPic(String gdsPic) {
        this.gdsPic = gdsPic == null ? null : gdsPic.trim();
    }

    public String getIfRecommend() {
        return ifRecommend;
    }

    public void setIfRecommend(String ifRecommend) {
        this.ifRecommend = ifRecommend == null ? null : ifRecommend.trim();
    }

    public String getFunIntroduction() {
        return funIntroduction;
    }

    public void setFunIntroduction(String funIntroduction) {
        this.funIntroduction = funIntroduction == null ? null : funIntroduction.trim();
    }

    public String getCommpanyName() {
        return commpanyName;
    }

    public void setCommpanyName(String commpanyName) {
        this.commpanyName = commpanyName == null ? null : commpanyName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getShelveUser() {
        return shelveUser;
    }

    public void setShelveUser(String shelveUser) {
        this.shelveUser = shelveUser == null ? null : shelveUser.trim();
    }

    public Date getShelveTime() {
        return shelveTime;
    }

    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }

    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}