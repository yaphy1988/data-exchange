package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsInfoRespVO implements Serializable{
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

    private List<GdsLabelRespVO> gdsLabelRespVOs;

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

    public List<GdsLabelRespVO> getGdsLabelRespVOs() {
        return gdsLabelRespVOs;
    }

    public void setGdsLabelRespVOs(List<GdsLabelRespVO> gdsLabelRespVOs) {
        this.gdsLabelRespVOs = gdsLabelRespVOs;
    }
}
