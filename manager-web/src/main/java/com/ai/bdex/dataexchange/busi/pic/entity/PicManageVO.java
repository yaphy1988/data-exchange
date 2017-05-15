package com.ai.bdex.dataexchange.busi.pic.entity;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:manager-web <br>
 * Description: <br>
 * Date:2017年5月10日下午2:32:02  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class PicManageVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1726767753171814661L;
    
    private int pageNo;
    
    private int pageSize;
    /**
     * 图片id
    */
    private Integer picId;
    /**
     * 图片地址（存的mongodbId）
     */
    private String picUuid;

   /**
    * 图片库id
    */
    private Integer libId;

   /**
    * 图片名称
    */
    private String picName;

   /**
    * 图片像素规格。1200*500
    */
    private String picSpe;

   /**
    * 图片大小
    */
    private String picSize;

   /**
    * 展示顺序
    */
    private Integer showOrder;

   /**
    * 状态
    */
    private String status;
   /**
   *图片库名称
   */
   private String libName;

   /**
   * 图片库的封面图片
   */
   private String libPic;

   /**
   * 图片库有多少张图片
   */
    private Integer picMun;
    
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

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getLibPic() {
        return libPic;
    }

    public void setLibPic(String libPic) {
        this.libPic = libPic;
    }

    public Integer getPicMun() {
        return picMun;
    }

    public void setPicMun(Integer picMun) {
        this.picMun = picMun;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPicUuid() {
        return picUuid;
    }

    public void setPicUuid(String picUuid) {
        this.picUuid = picUuid;
    }
}

