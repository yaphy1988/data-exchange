package com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

/**
 * Created by yx on 2017/4/18.
 */
public class GdsCatRespDTO extends BaseResponseDTO implements Serializable {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -9131157960075792281L;

    private Integer catId;

    private Integer catPid;

    private String catName;

    private String catDesc;

    private Integer showOrder;

    private String ifEdit;

    private String status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    /**
     * 子分类
     */
    private List<GdsCatRespDTO> subCatList;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatPid() {
        return catPid;
    }

    public void setCatPid(Integer catPid) {
        this.catPid = catPid;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getIfEdit() {
        return ifEdit;
    }

    public void setIfEdit(String ifEdit) {
        this.ifEdit = ifEdit;
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

    public List<GdsCatRespDTO> getSubCatList() {
        return subCatList;
    }

    public void setSubCatList(List<GdsCatRespDTO> subCatList) {
        this.subCatList = subCatList;
    }
}
