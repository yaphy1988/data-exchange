package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.tradecenter.TradeCenterServiceStart;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageHeaderNavRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.PageInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Page.SortInfoRespDTO; 
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.Page.IPageInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHeaderNavSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortContentSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
import com.ai.paas.util.Utils;
import com.ai.paas.utils.StringUtil;

 @SuppressWarnings("unused")
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
                        //获取sortContent信息
                        SortContent sortContentinfo=  iSortContentSV.querysortContenById(sortInfo.getSortId());
                        SortContentRespDTO sortContentRespDTO =new SortContentRespDTO();
                        BeanUtils.copyProperties(sortContentinfo, sortContentRespDTO);
                        sortInfoResp.setSortContentRespDTO(sortContentRespDTO);
                        sortInfoRespLis.add(sortInfoResp);
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
    public List<PageHotSearchRespDTO> queryPageHotSearchNavList(PageHotSearchRespDTO pageHotSearchRespDTO) throws Exception {
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
    //	public  List<PageHeaderNav>  queryPageHeaderNavList(PageHeaderNav example) throws Exception ;
    //查询查询热门搜索关键字
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
    
    public static void main(String[] args) throws Exception  { 
    	PageInfoRSVImpl pageInfoRSVImpl = new PageInfoRSVImpl();
    	SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
    	sortInfoRespDTO.setStatus("1");
    	pageInfoRSVImpl.querySortInfos(sortInfoRespDTO);
    }
}
