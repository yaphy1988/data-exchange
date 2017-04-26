package com.ai.bdex.dataexchange.busi.gds.entity;

import com.ai.bdex.dataexchange.busi.api.entity.ServiceVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/4/20.
 */
public class GdsInfoVO implements Serializable {
    private Integer gdsId;

    private String gdsName;

    private String gdsSubtitle;

    private Integer catFirst;

    private String catFirstName;

    private Integer apiId;

    private String apiStatus;//api接口状态

    private String apiProviderName;//api提供者名称

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

    private Integer catId;

    private String catName;

    private List<GdsLabelVO> gdsLabelVOList;//商品标签信息列表

    private List<GdsSkuVO> gdsSkuVOList;//单品列表

    private List<GdsInfo2PropVO> gdsInfo2PropVOList;//商品属性列表

    private String dataDescription;//数据描述，客户定制分类商品详情使用

    private String adaptRange;//适用范围，解决方案分类商品详情使用

    private String feature;//特点，解决方案分类商品详情使用

    private List<GdsDetailTabContent> tabContentList;//商品详情下方详情tab页内容页面ID

    private ServiceVO serviceVO;

    public ServiceVO getServiceVO() {
        return serviceVO;
    }

    public void setServiceVO(ServiceVO serviceVO) {
        this.serviceVO = serviceVO;
    }

    public List<GdsDetailTabContent> getTabContentList() {
        return tabContentList;
    }

    public void setTabContentList(List<GdsDetailTabContent> tabContentList) {
        this.tabContentList = tabContentList;
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

    public String getCatFirstName() {
        return catFirstName;
    }

    public void setCatFirstName(String catFirstName) {
        this.catFirstName = catFirstName;
    }

    public List<GdsLabelVO> getGdsLabelVOList() {
        return gdsLabelVOList;
    }

    public void setGdsLabelVOList(List<GdsLabelVO> gdsLabelVOList) {
        this.gdsLabelVOList = gdsLabelVOList;
    }

    public List<GdsSkuVO> getGdsSkuVOList() {
        return gdsSkuVOList;
    }

    public void setGdsSkuVOList(List<GdsSkuVO> gdsSkuVOList) {
        this.gdsSkuVOList = gdsSkuVOList;
    }

    public List<GdsInfo2PropVO> getGdsInfo2PropVOList() {
        return gdsInfo2PropVOList;
    }

    public void setGdsInfo2PropVOList(List<GdsInfo2PropVO> gdsInfo2PropVOList) {
        this.gdsInfo2PropVOList = gdsInfo2PropVOList;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public String getAdaptRange() {
        return adaptRange;
    }

    public void setAdaptRange(String adaptRange) {
        this.adaptRange = adaptRange;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiProviderName() {
        return apiProviderName;
    }

    public void setApiProviderName(String apiProviderName) {
        this.apiProviderName = apiProviderName;
    }
}
