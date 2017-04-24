package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.*;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModule;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageInfoRSV;

@Service("iPageInfoRSV")
public class PageInfoRSVImpl implements IPageInfoRSV {
    private static final Logger log = LoggerFactory.getLogger(PageInfoRSVImpl.class);
    /*
     * 获取分类数据
     * @see com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Page.IPageInfoRSV#querySortInfos(com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO)
     */
    @Resource
    private ISortInfoSV   iSortInfoSV;
    @Resource
    private ISortContentSV   iSortContentSV;
    @Resource
    private IPageInfoSV   iPageInfoSV;
    @Resource
    private IPageHotSearchSV   iPageHotSearchSV;
    @Resource
    private IPageHeaderNavSV   iPageHeaderNavSV;
    @Resource
    private IPageModuleSV   iPageModuleSV;
    @Resource
    private IPageModuleAdSV iPageModuleAdSV;
    @Resource
    private IPageModuleGoodsSV   iPageModuleGoodsSV;
    @Resource
    private IPageModuleAdPropSV   iPageModuleAdpropSV;
    @Resource
    private IDataCustomizationSV iDataCustomizationSV;

    @Override
    public List<SortInfoRespDTO> querySortInfos(SortInfoRespDTO sortInfoRespDTO) throws Exception {
        List<SortInfoRespDTO> sortInfoRespLis = new ArrayList<SortInfoRespDTO>();
        try{
        	 if (sortInfoRespDTO ==null ){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
         	 SortInfo exam = new SortInfo();
         	 BeanUtils.copyProperties( sortInfoRespDTO,exam);
          	 List<SortInfo> listSortInfo = iSortInfoSV.querySortInfoList(exam);
          	    if(!CollectionUtils.isEmpty(listSortInfo)){
                    for (SortInfo sortInfo : listSortInfo){
                    	SortInfoRespDTO sortInfoResp = new SortInfoRespDTO();
                        BeanUtils.copyProperties(sortInfo, sortInfoResp);
                        sortInfoRespLis.add(sortInfoResp);
                        
                    	SortContent sortContent = new SortContent();
                    	sortContent.setSortId(sortInfo.getSortId());
                    	sortContent.setStatus("1");//有效的
                        //获取sortContent信息
                        List<SortContent> sortContenList=  iSortContentSV.querysortContenList(sortContent);
                        if(!CollectionUtils.isEmpty(sortContenList)){
                        	SortContentRespDTO sortContentRespDTO =new SortContentRespDTO();
                        	BeanUtils.copyProperties(sortContenList.get(0), sortContentRespDTO);
                        	sortInfoResp.setSortContentRespDTO(sortContentRespDTO);
                        }
                    }
                 }   
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return sortInfoRespLis;
    }
    //查询热点信息 列表
    @Override
    public List<PageInfoRespDTO> queryPageInfoList(PageInfoRespDTO pageInfoRespDTO) throws Exception {
        List<PageInfoRespDTO> PageInfoRespList = new ArrayList<PageInfoRespDTO>();
        try{
        	 if (pageInfoRespDTO ==null ){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
        	 PageInfo exam = new PageInfo();
         	 BeanUtils.copyProperties( pageInfoRespDTO,exam);
          	 List<PageInfo> listPageInfo = iPageInfoSV.queryPageInfoList(exam);
          	    if(!CollectionUtils.isEmpty(listPageInfo)){
                    for (PageInfo pageInfo : listPageInfo){
                    	PageInfoRespDTO pageInfoResp = new PageInfoRespDTO();
                        BeanUtils.copyProperties(pageInfo, pageInfoResp); 
                        PageInfoRespList.add(pageInfoResp);
                    }
                 }   
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return PageInfoRespList;
    }
    //查询热点信息 明细
    @Override
    public  PageInfoRespDTO  queryPageInfoBYid(Integer PageInfoid) throws Exception {
        PageInfoRespDTO  pageInfoResp  = new  PageInfoRespDTO ();
        try{
        	 if (PageInfoid == 0 ){
                 throw new Exception("查询热点信息 明细，入参为空");
             }
        	 PageInfo exam = new PageInfo(); 
          	 PageInfo   pageInfo = iPageInfoSV.queryPageInfoById(PageInfoid);
          	    if(pageInfo != null){ 
                        BeanUtils.copyProperties(pageInfo, pageInfoResp); 
                  }   
        }catch(Exception e){
        	log.error("查询热点信息 明细异常:", e);
            throw new Exception(e);
        }
        return pageInfoResp;
    } 
    //查询查询热门搜索关键字
    @Override 
    public List<PageHotSearchRespDTO> queryPageHotSearchList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception {
        List<PageHotSearchRespDTO> PageInfoRespList = new ArrayList<PageHotSearchRespDTO>();
        try{
        	 if (pageHotSearchRespDTO ==null ){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
        	 PageHotSearch exam = new PageHotSearch();
         	 BeanUtils.copyProperties( pageHotSearchRespDTO,exam);
          	 List<PageHotSearch> listPageInfo = iPageHotSearchSV.queryPageHotSearchNavList(exam);
          	    if(!CollectionUtils.isEmpty(listPageInfo)){
                    for (PageHotSearch pageInfoHot : listPageInfo){
                    	PageHotSearchRespDTO pageInfoResp = new PageHotSearchRespDTO();
                        BeanUtils.copyProperties(pageInfoHot, pageInfoResp); 
                        PageInfoRespList.add(pageInfoResp);
                    }
                 }   
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return PageInfoRespList;
    }
    //查询商品分类信息
    @Override 
    public List<PageHeaderNavRespDTO> queryPageHeaderNavList(PageHeaderNavRespDTO PageHeaderNavRespDTO) throws Exception {
        List<PageHeaderNavRespDTO> PageInfoRespList = new ArrayList<PageHeaderNavRespDTO>();
        try{
        	 if (PageHeaderNavRespDTO ==null ){
                 throw new Exception("查询商品分类信息异常，入参为空");
             }
        	 PageHeaderNav exam = new PageHeaderNav();
         	 BeanUtils.copyProperties( PageHeaderNavRespDTO,exam);
          	 List<PageHeaderNav> listPageInfo = iPageHeaderNavSV.queryPageHeaderNavList(exam);
          	    if(!CollectionUtils.isEmpty(listPageInfo)){
                    for (PageHeaderNav pageHeaderNav : listPageInfo){
                    	PageHeaderNavRespDTO pageInfoResp = new PageHeaderNavRespDTO();
                        BeanUtils.copyProperties(pageHeaderNav, pageInfoResp); 
                        PageInfoRespList.add(pageInfoResp);
                    }
                 }   
        }catch(Exception e){
        	log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
        }
        return PageInfoRespList;
    }
    
	@Override
	public List<PageModuleRespDTO> queryPageModuleList(PageModuleRespDTO pageModuleRespDTO) throws Exception {

        List<PageModuleRespDTO> pageModuleRespDTOList = new ArrayList<PageModuleRespDTO>();
        try{
        	 if (pageModuleRespDTO ==null ){
                 throw new Exception("查询首页楼层信息异常，入参为空");
             }
         	 PageModule exam = new PageModule();
         	 BeanUtils.copyProperties( pageModuleRespDTO,exam);
          	 List<PageModule> queryPageModuleList = iPageModuleSV.queryPageModuleList(exam);
          	 if(!CollectionUtils.isEmpty(queryPageModuleList)){
          		for (PageModule pageModule : queryPageModuleList){
          			PageModuleRespDTO pageModuleResp = new PageModuleRespDTO();
                    BeanUtils.copyProperties(pageModule, pageModuleResp); 
                    pageModuleRespDTOList.add(pageModuleResp);
                }
          	 }
        }catch(Exception e){
        	log.error("获取首页楼层信息异常:", e);
            throw new Exception(e);
        }
        return pageModuleRespDTOList;
    
	}
	@Override
	public List<PageModuleAdRespDTO> queryPageModuleAdList(PageModuleAdRespDTO pageModuleAdRespDTO) throws Exception {
		List<PageModuleAdRespDTO> ageModuleAdRespDTOList = new ArrayList<PageModuleAdRespDTO>();
        try{
        	 if (ageModuleAdRespDTOList ==null ){
                 throw new Exception("查询广告楼层信息异常，入参为空");
             }
         	 PageModuleAd exam = new PageModuleAd();
         	 BeanUtils.copyProperties( pageModuleAdRespDTO,exam);
          	 List<PageModuleAd> queryPageModuleAdList = iPageModuleAdSV.queryPageModuleAdList(exam);
          	 if(!CollectionUtils.isEmpty(queryPageModuleAdList)){
          		for (PageModuleAd pageModuleAd : queryPageModuleAdList){
          			PageModuleAdRespDTO pageModuleAdResp = new PageModuleAdRespDTO();
                    BeanUtils.copyProperties(pageModuleAd, pageModuleAdResp); 
                    ageModuleAdRespDTOList.add(pageModuleAdResp);
                }
          	 }
        }catch(Exception e){
        	log.error("获取广告楼层信息异常:", e);
            throw new Exception(e);
        }
        return ageModuleAdRespDTOList;
	}
	@Override
	public List<PageModuleGoodsRespDTO> queryPageModuleGoodsList(PageModuleGoodsRespDTO pageModuleGoodsRespDTO)
			throws Exception {
		List<PageModuleGoodsRespDTO> ageModuleGoodsRespDTOList = new ArrayList<PageModuleGoodsRespDTO>();
        try{
        	 if (pageModuleGoodsRespDTO ==null ){
                 throw new Exception("查询商品楼层信息异常，入参为空");
             }
         	 PageModuleGoods exam = new PageModuleGoods();
         	 BeanUtils.copyProperties( pageModuleGoodsRespDTO,exam);
          	 List<PageModuleGoods> queryPageModuleGoodsList = iPageModuleGoodsSV.queryPageModuleGoodsList(exam);
          	 if(!CollectionUtils.isEmpty(queryPageModuleGoodsList)){
          		for (PageModuleGoods pageModuleGoods : queryPageModuleGoodsList){
          			PageModuleGoodsRespDTO pageModuleGoodsResp = new PageModuleGoodsRespDTO();
                    BeanUtils.copyProperties(pageModuleGoods, pageModuleGoodsResp); 
                    ageModuleGoodsRespDTOList.add(pageModuleGoodsResp);
                }
          	 }
        }catch(Exception e){
        	log.error("获取商品楼层信息异常:", e);
            throw new Exception(e);
        }
        return ageModuleGoodsRespDTOList;
    }
	@Override
	public List<PageModuleAdPropRespDTO> queryPageModuleAdPropList(PageModuleAdPropRespDTO pageModuleAdpropRespDTO)
			throws Exception {
		List<PageModuleAdPropRespDTO> ageModuleAdPropRespDTOList = new ArrayList<PageModuleAdPropRespDTO>();
        try{
        	 if (pageModuleAdpropRespDTO ==null ){
                 throw new Exception("查询广告属性信息异常，入参为空");
             }
         	 PageModuleAdProp exam = new PageModuleAdProp();
         	 BeanUtils.copyProperties( pageModuleAdpropRespDTO,exam);
          	 List<PageModuleAdProp> queryPageModuleAdPropList = iPageModuleAdpropSV.queryPageModuleAdPropList(exam);
          	 if(!CollectionUtils.isEmpty(queryPageModuleAdPropList)){
          		for (PageModuleAdProp pageModuleAdProp : queryPageModuleAdPropList){
          			PageModuleAdPropRespDTO pageModuleAdpropResp = new PageModuleAdPropRespDTO();
                    BeanUtils.copyProperties(pageModuleAdProp, pageModuleAdpropResp); 
                    ageModuleAdPropRespDTOList.add(pageModuleAdpropResp);
                }
          	 }
        }catch(Exception e){
        	log.error("获取广告属性信息异常:", e);
            throw new Exception(e);
        }
        return ageModuleAdPropRespDTOList;
    }
    public int saveDataCustomizationRsv(DataCustomizationRespDTO dataCustomizationRespDTO) throws Exception
    {
         return   iDataCustomizationSV.saveDataCustomization(dataCustomizationRespDTO);
    }
	@Override
	public List<SortContentRespDTO> querysortContenList(SortContentRespDTO sortContentRespDTO) throws Exception {
		List<SortContentRespDTO> respDTOs = new ArrayList<SortContentRespDTO>();
		try {
			SortContent sortContent = new SortContent();
			BeanUtils.copyProperties(sortContentRespDTO, sortContent);
			List<SortContent> contenList = iSortContentSV.querysortContenList(sortContent);
			if(!CollectionUtils.isEmpty(contenList)){
				for(SortContent contentVO : contenList){
					SortContentRespDTO  respDTO = new SortContentRespDTO();
					BeanUtils.copyProperties(contentVO, respDTO);
					respDTOs.add(respDTO);
				}
			}
		} catch (Exception e) {
			log.error("获取商品分类信息异常:", e);
            throw new Exception(e);
		}
		return respDTOs;
	}
}
