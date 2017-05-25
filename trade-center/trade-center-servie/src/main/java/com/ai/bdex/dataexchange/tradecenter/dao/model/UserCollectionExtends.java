package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.Date;

public class UserCollectionExtends {
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.col_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Integer colId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.cat_first
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Integer catFirst;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.gds_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Integer gdsId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.sku_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Integer skuId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.staff_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private String staffId;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.status
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private String status;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.create_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private String createUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.create_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.update_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private String updateUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_COLLECTION.update_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   private Date updateTime;
   
   /**
    * 商品名称
    */
   private String gdsName;
   /**
    * 商品图片uuid
    */
   private String gdsPic;
   /**
    * 商品描述
    */
   private String funIntroduction;
   /**
    * 商品副标题
    */
   private String gdsSubtitle;
   /**
    * 商品状态
    */
   private String gdsStatus;
   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.col_id
    *
    * @return the value of T_USER_COLLECTION.col_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Integer getColId() {
       return colId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.col_id
    *
    * @param colId the value for T_USER_COLLECTION.col_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setColId(Integer colId) {
       this.colId = colId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.cat_first
    *
    * @return the value of T_USER_COLLECTION.cat_first
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Integer getCatFirst() {
       return catFirst;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.cat_first
    *
    * @param catFirst the value for T_USER_COLLECTION.cat_first
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setCatFirst(Integer catFirst) {
       this.catFirst = catFirst;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.gds_id
    *
    * @return the value of T_USER_COLLECTION.gds_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Integer getGdsId() {
       return gdsId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.gds_id
    *
    * @param gdsId the value for T_USER_COLLECTION.gds_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setGdsId(Integer gdsId) {
       this.gdsId = gdsId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.sku_id
    *
    * @return the value of T_USER_COLLECTION.sku_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Integer getSkuId() {
       return skuId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.sku_id
    *
    * @param skuId the value for T_USER_COLLECTION.sku_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setSkuId(Integer skuId) {
       this.skuId = skuId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.staff_id
    *
    * @return the value of T_USER_COLLECTION.staff_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public String getStaffId() {
       return staffId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.staff_id
    *
    * @param staffId the value for T_USER_COLLECTION.staff_id
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setStaffId(String staffId) {
       this.staffId = staffId == null ? null : staffId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.status
    *
    * @return the value of T_USER_COLLECTION.status
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.status
    *
    * @param status the value for T_USER_COLLECTION.status
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.create_user
    *
    * @return the value of T_USER_COLLECTION.create_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public String getCreateUser() {
       return createUser;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.create_user
    *
    * @param createUser the value for T_USER_COLLECTION.create_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setCreateUser(String createUser) {
       this.createUser = createUser == null ? null : createUser.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.create_time
    *
    * @return the value of T_USER_COLLECTION.create_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.create_time
    *
    * @param createTime the value for T_USER_COLLECTION.create_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.update_user
    *
    * @return the value of T_USER_COLLECTION.update_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public String getUpdateUser() {
       return updateUser;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.update_user
    *
    * @param updateUser the value for T_USER_COLLECTION.update_user
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setUpdateUser(String updateUser) {
       this.updateUser = updateUser == null ? null : updateUser.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column T_USER_COLLECTION.update_time
    *
    * @return the value of T_USER_COLLECTION.update_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column T_USER_COLLECTION.update_time
    *
    * @param updateTime the value for T_USER_COLLECTION.update_time
    *
    * @mbg.generated Mon May 08 10:05:46 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }

    public String getGdsName() {
        return gdsName;
    }
    
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getGdsPic() {
        return gdsPic;
    }

    public void setGdsPic(String gdsPic) {
        this.gdsPic = gdsPic;
    }

    public String getFunIntroduction() {
        return funIntroduction;
    }

    public void setFunIntroduction(String funIntroduction) {
        this.funIntroduction = funIntroduction;
    }

    public String getGdsSubtitle() {
        return gdsSubtitle;
    }

    public void setGdsSubtitle(String gdsSubtitle) {
        this.gdsSubtitle = gdsSubtitle;
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
}

