package com.ai.bdex.dataexchange.busi.gds.entity;

import java.util.Date;
import java.util.List;

public class GdsManageInfoVO {
	private GdsInfoVO gdsInfoVO;
    private GdsInfo2CatVO gdsInfo2CatVO;//商品分类配置关联表
    //json串
    private String jsonStr;
    
    private List<GdsLabelVO> gdsLabelVOList;//商品标签
    
    private List<GdsInfo2PropVO>  gdsInfo2PropVOList;//商品属性配置
    
    private List<GdsSkuVO> gdsSkuVOList;
    public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	

	public GdsInfo2CatVO getGdsInfo2CatVO() {
		return gdsInfo2CatVO;
	}

	public void setGdsInfo2CatVO(GdsInfo2CatVO gdsInfo2CatVO) {
		this.gdsInfo2CatVO = gdsInfo2CatVO;
	}

	public GdsInfoVO getGdsInfoVO() {
		return gdsInfoVO;
	}

	public void setGdsInfoVO(GdsInfoVO gdsInfoVO) {
		this.gdsInfoVO = gdsInfoVO;
	}

	public List<GdsLabelVO> getGdsLabelVOList() {
		return gdsLabelVOList;
	}

	public void setGdsLabelVOList(List<GdsLabelVO> gdsLabelVOList) {
		this.gdsLabelVOList = gdsLabelVOList;
	}

	public List<GdsInfo2PropVO> getGdsInfo2PropVOList() {
		return gdsInfo2PropVOList;
	}

	public void setGdsInfo2PropVOList(List<GdsInfo2PropVO> gdsInfo2PropVOList) {
		this.gdsInfo2PropVOList = gdsInfo2PropVOList;
	}

	public List<GdsSkuVO> getGdsSkuVOList() {
		return gdsSkuVOList;
	}

	public void setGdsSkuVOList(List<GdsSkuVO> gdsSkuVOList) {
		this.gdsSkuVOList = gdsSkuVOList;
	}

	
    
}