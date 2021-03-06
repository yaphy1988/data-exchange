package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

public class PageAdPalceRespDTO extends BaseResponseDTO{
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.PLACE_AD
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private Integer placeAd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.MODULE_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private Integer moduleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.PLACE_NAME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String placeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.SHOW_COUNT
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private Integer showCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.PIC_MEASURE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String picMeasure;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.PIC_SIZE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String picSize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.UPDATE_TIME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.STATUS
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.REMARK
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.CREATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String createStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.CREATE_TIME2
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private Date createTime2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_page_ad_palce.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    private String updateStaffId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.PLACE_AD
     *
     * @return the value of t_page_ad_palce.PLACE_AD
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public Integer getPlaceAd() {
        return placeAd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.PLACE_AD
     *
     * @param placeAd the value for t_page_ad_palce.PLACE_AD
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setPlaceAd(Integer placeAd) {
        this.placeAd = placeAd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.MODULE_ID
     *
     * @return the value of t_page_ad_palce.MODULE_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.MODULE_ID
     *
     * @param moduleId the value for t_page_ad_palce.MODULE_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.PLACE_NAME
     *
     * @return the value of t_page_ad_palce.PLACE_NAME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.PLACE_NAME
     *
     * @param placeName the value for t_page_ad_palce.PLACE_NAME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.SHOW_COUNT
     *
     * @return the value of t_page_ad_palce.SHOW_COUNT
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public Integer getShowCount() {
        return showCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.SHOW_COUNT
     *
     * @param showCount the value for t_page_ad_palce.SHOW_COUNT
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.PIC_MEASURE
     *
     * @return the value of t_page_ad_palce.PIC_MEASURE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getPicMeasure() {
        return picMeasure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.PIC_MEASURE
     *
     * @param picMeasure the value for t_page_ad_palce.PIC_MEASURE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setPicMeasure(String picMeasure) {
        this.picMeasure = picMeasure == null ? null : picMeasure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.PIC_SIZE
     *
     * @return the value of t_page_ad_palce.PIC_SIZE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getPicSize() {
        return picSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.PIC_SIZE
     *
     * @param picSize the value for t_page_ad_palce.PIC_SIZE
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setPicSize(String picSize) {
        this.picSize = picSize == null ? null : picSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.UPDATE_TIME
     *
     * @return the value of t_page_ad_palce.UPDATE_TIME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.UPDATE_TIME
     *
     * @param updateTime the value for t_page_ad_palce.UPDATE_TIME
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.STATUS
     *
     * @return the value of t_page_ad_palce.STATUS
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.STATUS
     *
     * @param status the value for t_page_ad_palce.STATUS
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.REMARK
     *
     * @return the value of t_page_ad_palce.REMARK
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.REMARK
     *
     * @param remark the value for t_page_ad_palce.REMARK
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.CREATE_STAFF_ID
     *
     * @return the value of t_page_ad_palce.CREATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getCreateStaffId() {
        return createStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.CREATE_STAFF_ID
     *
     * @param createStaffId the value for t_page_ad_palce.CREATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.CREATE_TIME2
     *
     * @return the value of t_page_ad_palce.CREATE_TIME2
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public Date getCreateTime2() {
        return createTime2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.CREATE_TIME2
     *
     * @param createTime2 the value for t_page_ad_palce.CREATE_TIME2
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setCreateTime2(Date createTime2) {
        this.createTime2 = createTime2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_page_ad_palce.UPDATE_STAFF_ID
     *
     * @return the value of t_page_ad_palce.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public String getUpdateStaffId() {
        return updateStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_page_ad_palce.UPDATE_STAFF_ID
     *
     * @param updateStaffId the value for t_page_ad_palce.UPDATE_STAFF_ID
     *
     * @mbg.generated Tue May 09 15:14:37 CST 2017
     */
    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }
}