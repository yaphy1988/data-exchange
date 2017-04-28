package com.ai.bdex.dataexchange.solrutil;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;
import com.ai.paas.util.ImageUtil;

public class ResultRespVO  extends BaseResponseDTO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5752899360992097309L;
    @Field
    private String id;
    @Field
    private String skuId;
    @Field
    private String gdsName;
    @Field
    private String gdsNameSrc;
    @Field
    private String gdsSubtitle;
    @Field
    private String gdsSubtitleSrc;
    @Field
    private String catFirst;
    @Field
    private String apiId;
    @Field
    private String gdsPic;
    @Field
    private String ifRecommend;
    @Field
    private String funIntroduction;
    @Field
    private String funIntroductionSrc;
    @Field
    private String status;
    @Field
    private Date shelveTime;
    @Field
    private Date createTime;
    @Field
    private double hotDegree;
    @Field
    private String catId;
    @Field
    private String commpanyName;
    @Field
    private int packPrice;
    @Field
    private int packTimes;
    @Field
    private int gdsSale;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public String getGdsNameSrc() {
        return gdsNameSrc;
    }
    public void setGdsNameSrc(String gdsNameSrc) {
        this.gdsNameSrc = gdsNameSrc;
    }
    public String getGdsSubtitle() {
        return gdsSubtitle;
    }
    public void setGdsSubtitle(String gdsSubtitle) {
        this.gdsSubtitle = gdsSubtitle;
    }
    public String getGdsSubtitleSrc() {
        return gdsSubtitleSrc;
    }
    public void setGdsSubtitleSrc(String gdsSubtitleSrc) {
        this.gdsSubtitleSrc = gdsSubtitleSrc;
    }
    public String getCatFirst() {
        return catFirst;
    }
    public void setCatFirst(String catFirst) {
        this.catFirst = catFirst;
    }
    public String getApiId() {
        return apiId;
    }
    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
    public String getGdsPic() {
        return gdsPic;
    }
    public void setGdsPic(String gdsPic) {
        try {
            this.gdsPic = ImageUtil.getImageUrl(gdsPic + "_80x80");
        } catch (Exception e) {
            this.gdsPic = gdsPic;
        }
        
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
    public String getFunIntroductionSrc() {
        return funIntroductionSrc;
    }
    public void setFunIntroductionSrc(String funIntroductionSrc) {
        this.funIntroductionSrc = funIntroductionSrc;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getShelveTime() {
        return shelveTime;
    }
    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public double getHotDegree() {
        return hotDegree;
    }
    public void setHotDegree(double hotDegree) {
        this.hotDegree = hotDegree;
    }
    public String getCatId() {
        return catId;
    }
    public void setCatId(String catId) {
        this.catId = catId;
    }
    public String getCommpanyName() {
        return commpanyName;
    }
    public void setCommpanyName(String commpanyName) {
        this.commpanyName = commpanyName;
    }
    public int getPackPrice() {
        return packPrice;
    }
    public void setPackPrice(int packPrice) {
        this.packPrice = packPrice;
    }
    public int getPackTimes() {
        return packTimes;
    }
    public void setPackTimes(int packTimes) {
        this.packTimes = packTimes;
    }
    public int getGdsSale() {
        return gdsSale;
    }
    public void setGdsSale(int gdsSale) {
        this.gdsSale = gdsSale;
    }

    
}

