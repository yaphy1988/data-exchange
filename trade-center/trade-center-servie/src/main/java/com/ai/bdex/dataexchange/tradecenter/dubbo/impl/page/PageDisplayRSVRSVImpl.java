package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModule;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAd;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleAdProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageModuleGoods;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.DataCustomizationRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdPropRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleGoodsRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageNewsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IDataCustomizationSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHeaderNavSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdPropSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleAdSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleGoodsSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageModuleSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageNewsInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortContentSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;

@Service("iPageDisplayRSV")
public class PageDisplayRSVRSVImpl implements IPageDisplayRSV {
    private static final Logger log = LoggerFactory.getLogger(PageDisplayRSVRSVImpl.class);
    /*
     * 获取分类数据
     */
    @Resource
    private ISortInfoSV   iSortInfoSV;
    @Resource
    private ISortContentSV   iSortContentSV;
    @Resource
    private IPageNewsInfoSV   iPageNewsInfoSV;
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
    public PageResponseDTO<PageNewsInfoRespDTO> queryPageNewsInfoList(PageNewsInfoRespDTO pageInfoRespDTO) throws Exception {
    	PageResponseDTO<PageNewsInfoRespDTO> PageNewsInfoRespPageInfo = new PageResponseDTO<>();
        try{
        	 if (pageInfoRespDTO ==null ){
                 throw new Exception("查询热点信息异常，入参为空");
             }
        	 PageNewsInfo exam = new PageNewsInfo();
         	 BeanUtils.copyProperties( pageInfoRespDTO,exam);
         	 PageNewsInfoRespPageInfo = iPageNewsInfoSV.queryPageNewsInfoList(exam);
        }catch(Exception e){
        	log.error("获取热点信息异常:", e);
            throw new Exception(e);
        }
        return PageNewsInfoRespPageInfo;
    }
    //查询热点信息 明细
    @Override
    public  PageNewsInfoRespDTO  queryPageNewsInfoById(Integer PageInfoid) throws Exception {
        PageNewsInfoRespDTO  pageInfoResp  = new  PageNewsInfoRespDTO ();
        try{
        	 if (PageInfoid == 0 ){
                 throw new Exception("查询热点信息 明细，入参为空");
             }
          	 PageNewsInfo   pageInfo = iPageNewsInfoSV.queryPageNewsInfoById(PageInfoid);
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
                    //查询子楼层
                    exam.setModulePid(pageModule.getModuleId());
                    List<PageModule> suPageModules = iPageModuleSV.queryPageModuleList(exam);
                    if(!CollectionUtils.isEmpty(suPageModules)){
                    	List<PageModuleRespDTO> subPageModuleList = new ArrayList<>();
                    	for(PageModule subModule: suPageModules){
                    		PageModuleRespDTO subPageModuleResp = new PageModuleRespDTO();
                    		BeanUtils.copyProperties(subModule, subPageModuleResp); 
                    		subPageModuleList.add(subPageModuleResp);
                    	}
                    	pageModuleResp.setSubPageModuleList(subPageModuleList);
                    }
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
	public PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsList(PageModuleGoodsRespDTO pageModuleGoodsRespDTO)
			throws Exception {
		PageResponseDTO<PageModuleGoodsRespDTO> queryPageModuleGoodsList = new PageResponseDTO<PageModuleGoodsRespDTO>();
        try{
        	 if (pageModuleGoodsRespDTO ==null ){
                 throw new Exception("查询商品楼层信息异常，入参为空");
             }
         	 PageModuleGoods exam = new PageModuleGoods();
         	 BeanUtils.copyProperties( pageModuleGoodsRespDTO,exam); 
         	queryPageModuleGoodsList = iPageModuleGoodsSV.queryPageModuleGoodsList(exam);
           
        }catch(Exception e){
        	log.error("获取商品楼层信息异常:", e);
            throw new Exception(e);
        }
        return queryPageModuleGoodsList;
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
