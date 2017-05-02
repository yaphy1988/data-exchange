package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoReqDTO extends BaseInfo implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1543483611353442297L;

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
    
    //单品信息
    private List<GdsSkuReqDTO> GdsSkuReqDTOList;
    
    //商品标签
    private List<GdsLabelReqDTO> gdsLabelReqDTOList;
    
    //商品属性配置
    private GdsInfo2PropReqDTO GdsInfo2PropReqDTO;

    private Integer catId;
    
    private List<Integer> gdsIds;
    
    public GdsInfo2PropReqDTO getGdsInfo2PropReqDTO() {
		return GdsInfo2PropReqDTO;
	}

	public void setGdsInfo2PropReqDTO(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) {
		GdsInfo2PropReqDTO = gdsInfo2PropReqDTO;
	}

	public List<GdsLabelReqDTO> getGdsLabelReqDTOList() {
		return gdsLabelReqDTOList;
	}

	public void setGdsLabelReqDTOList(List<GdsLabelReqDTO> gdsLabelReqDTOList) {
		this.gdsLabelReqDTOList = gdsLabelReqDTOList;
	}


	public List<GdsSkuReqDTO> getGdsSkuReqDTOList() {
		return GdsSkuReqDTOList;
	}

	public void setGdsSkuReqDTOList(List<GdsSkuReqDTO> gdsSkuReqDTOList) {
		GdsSkuReqDTOList = gdsSkuReqDTOList;
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
        this.gdsName = gdsName;
    }

    public String getGdsSubtitle() {
        return gdsSubtitle;
    }

    public void setGdsSubtitle(String gdsSubtitle) {
        this.gdsSubtitle = gdsSubtitle;
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
        this.gdsPic = gdsPic;
    }

    public String getIfRecommend() {
        return ifRecommend;
    }

    public void setIfRecommend(String ifRecommend) {
        this.ifRecommend = ifRecommend;
    }

    public String getFunIntroduction() {
        return funIntroduction;
    }

    public void setFunIntroduction(String funIntroduction) {
        this.funIntroduction = funIntroduction;
    }

    public String getCommpanyName() {
        return commpanyName;
    }

    public void setCommpanyName(String commpanyName) {
        this.commpanyName = commpanyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShelveUser() {
        return shelveUser;
    }

    public void setShelveUser(String shelveUser) {
        this.shelveUser = shelveUser;
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
        this.createUser = createUser;
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
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public List<Integer> getGdsIds() {
        return gdsIds;
    }

    public void setGdsIds(List<Integer> gdsIds) {
        this.gdsIds = gdsIds;
    }
}
