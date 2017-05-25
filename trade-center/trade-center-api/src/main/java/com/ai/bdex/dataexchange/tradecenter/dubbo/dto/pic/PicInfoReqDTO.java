package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年5月10日上午10:15:05  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class PicInfoReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6691893838467979066L;
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.pic_id
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private Integer picId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.lib_id
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private Integer libId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.pic_name
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String picName;
   
   private String picUuid;
   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.pic_spe
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String picSpe;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.pic_size
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String picSize;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.show_order
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private Integer showOrder;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.status
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String status;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.create_user
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String createUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.create_time
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.update_user
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private String updateUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_pic_info.update_time
    *
    * @mbg.generated Tue May 09 20:27:19 CST 2017
    */
   private Date updateTime;

    public Integer getPicId() {
        return picId;
    }
    
    public void setPicId(Integer picId) {
        this.picId = picId;
    }
    
    public Integer getLibId() {
        return libId;
    }
    
    public void setLibId(Integer libId) {
        this.libId = libId;
    }
    
    public String getPicName() {
        return picName;
    }
    
    public void setPicName(String picName) {
        this.picName = picName;
    }
    
    public String getPicSpe() {
        return picSpe;
    }
    
    public void setPicSpe(String picSpe) {
        this.picSpe = picSpe;
    }
    
    public String getPicSize() {
        return picSize;
    }
    
    public void setPicSize(String picSize) {
        this.picSize = picSize;
    }
    
    public Integer getShowOrder() {
        return showOrder;
    }
    
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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

    public String getPicUuid() {
        return picUuid;
    }

    public void setPicUuid(String picUuid) {
        this.picUuid = picUuid;
    }

       
}

