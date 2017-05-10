package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.util.Date;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-api <br>
 * Description: <br>
 * Date:2017年5月9日下午5:28:12  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class UserFootPrintRespDTO extends BaseResponseDTO{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6089950693650942652L;

    /**
    *
    * 浏览记录id
    */
    private Integer fpId;

   /**
    * 一级分类
    */
    private Integer catFirst;
    /**
     * 一级分类名称
     */
    private String catFirstName;

   /**
    *
    * 商品id
    */
    private Integer gdsId;

   /**
    * 浏览次数
    */
    private Integer seeNum;

   /**
    * 用户id
    */
    private String staffId;

   /**
    * 状态
    */
    private String status;

   /**
    * 
    */
    private String updateUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_FOOTPRINT.create_user
    *
    * @mbg.generated Tue May 09 15:38:26 CST 2017
    */
    private String createUser;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_FOOTPRINT.create_time
    *
    * @mbg.generated Tue May 09 15:38:26 CST 2017
    */
    private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_FOOTPRINT.last_time
    *
    * @mbg.generated Tue May 09 15:38:26 CST 2017
    */
    private Date lastTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column T_USER_FOOTPRINT.update_time
    *
    * @mbg.generated Tue May 09 15:38:26 CST 2017
    */
    private Date updateTime;
    
    private String gdsName;
    
    private String gdsPic;
    
    public Integer getFpId() {
        return fpId;
    }
    
    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }
    
    public Integer getCatFirst() {
        return catFirst;
    }
    
    public void setCatFirst(Integer catFirst) {
        this.catFirst = catFirst;
    }
    
    public Integer getGdsId() {
        return gdsId;
    }
    
    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }
    
    public Integer getSeeNum() {
        return seeNum;
    }
    
    public void setSeeNum(Integer seeNum) {
        this.seeNum = seeNum;
    }
    
    public String getStaffId() {
        return staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUpdateUser() {
        return updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
    
    public Date getLastTime() {
        return lastTime;
    }
    
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
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

    public String getCatFirstName() {
        return catFirstName;
    }

    public void setCatFirstName(String catFirstName) {
        this.catFirstName = catFirstName;
    }
}

