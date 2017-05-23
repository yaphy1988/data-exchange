package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoRespDTO extends BaseResponseDTO implements Serializable {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6519890226668240246L;

    private Integer gdsId;

    private String gdsName;

    private String gdsSubtitle;

    private Integer catFirst;

    private String catFirstName;

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

    private List<GdsLabelRespDTO> gdsLabelRespDTOs;//商品标签信息列表

    private List<GdsSkuRespDTO> gdsSkuRespDTOList;//单品列表

    private List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList;//商品属性列表

    private Integer catId;
    
    private String providerId;//供应商id

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

    public String getCatFirstName() {
        return catFirstName;
    }

    public void setCatFirstName(String catFirstName) {
        this.catFirstName = catFirstName;
    }

    public List<GdsLabelRespDTO> getGdsLabelRespDTOs() {
        return gdsLabelRespDTOs;
    }

    public void setGdsLabelRespDTOs(List<GdsLabelRespDTO> gdsLabelRespDTOs) {
        this.gdsLabelRespDTOs = gdsLabelRespDTOs;
    }

    public List<GdsSkuRespDTO> getGdsSkuRespDTOList() {
        return gdsSkuRespDTOList;
    }

    public void setGdsSkuRespDTOList(List<GdsSkuRespDTO> gdsSkuRespDTOList) {
        this.gdsSkuRespDTOList = gdsSkuRespDTOList;
    }

    public List<GdsInfo2PropRespDTO> getGdsInfo2PropRespDTOList() {
        return gdsInfo2PropRespDTOList;
    }

    public void setGdsInfo2PropRespDTOList(List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList) {
        this.gdsInfo2PropRespDTOList = gdsInfo2PropRespDTOList;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
