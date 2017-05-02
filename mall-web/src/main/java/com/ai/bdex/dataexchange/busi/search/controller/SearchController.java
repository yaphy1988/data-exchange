package com.ai.bdex.dataexchange.busi.search.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.search.entiry.SearchVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.solrutil.ESort;
import com.ai.bdex.dataexchange.solrutil.FacetRespVO;
import com.ai.bdex.dataexchange.solrutil.ResultRespVO;
import com.ai.bdex.dataexchange.solrutil.SearchField;
import com.ai.bdex.dataexchange.solrutil.SearchParam;
import com.ai.bdex.dataexchange.solrutil.SolrCoreEnum;
import com.ai.bdex.dataexchange.solrutil.SolrSearchUtil;
import com.ai.bdex.dataexchange.solrutil.SortField;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageHotSearchRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.solr.IDeltaIndexServiceRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: 搜索页面<br>
 * Date:2017年4月21日上午11:05:02  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/search")
public class SearchController{
    private static final String URL = "";
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
    
    @DubboConsumer
    private IGdsCatRSV iGdsCatRSV;
    @DubboConsumer
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    @Autowired
    private SolrClient solrClient;
    @DubboConsumer
    private IPageHotSearchRSV iPageHotSearchRSV;
    /**
     * 
     * init:(搜索页初始化入口). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,SearchVO searchVO){
        commonInit(searchVO,model);
        return "search/search";
    }
    
    /**
     * 
     * init:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param searchVO
     * @param gdstype
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/{gdstype}")
    public String init(Model model,SearchVO searchVO,@PathVariable(value="gdstype")String gdstype){
        int catFirst = 0;
        switch(gdstype){
            case "api":
                catFirst = 1;
                break;
            case "custom":
                catFirst = 2;
                break;
            case "solution":
                catFirst = 3;
                break;
            default:
                catFirst = 1;
            break;
        }
        searchVO.setCatFirst(catFirst);
        commonInit(searchVO,model);
        return "search/search";
    }
    
    public void commonInit(SearchVO searchVO,Model model){
        try {
            if(searchVO.getCatFirst() == 0){
                //默认搜索API
                searchVO.setCatFirst(1);
            }
            if(searchVO.getCatFirst()==1 || searchVO.getCatFirst() == 3){
                GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
                if(searchVO.getCatFirst() == 3){
                    gdsCatReqDTO.setCatId(searchVO.getCatFirst());
                    gdsCatReqDTO.setCatPid(0);
                }else{
                    gdsCatReqDTO.setCatPid(searchVO.getCatFirst());
                }
                List<GdsCatRespDTO> catList = iGdsCatRSV.queryGdsCatList(gdsCatReqDTO);
                if(catList != null && catList.size() >= 1){
                    GdsCatReqDTO reqDTO = new GdsCatReqDTO();
                    for(GdsCatRespDTO dto : catList){
                        reqDTO.setCatPid(dto.getCatId());
                        List<GdsCatRespDTO> subCatList = iGdsCatRSV.queryGdsCatList(reqDTO);
                        dto.setSubCatList(subCatList);
                    }
                }
                
                model.addAttribute("catList", catList);
            }
            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
                searchVO.setKeyWord(URLDecoder.decode(searchVO.getKeyWord()));
            }
            model.addAttribute("searchVO", searchVO);
        } catch (Exception e) {
            logger.error("获取分类错误！", e);
        }
    }
    /**
     * 
     * gridGdsInfo:(搜索列表商品列表信息查询。分页). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="gridgdsinfo")
    public String gridGdsInfo(Model model,SearchVO searchVO){
        generGdsList(model,searchVO);
        String returnUrl = "";
        if(searchVO.getCatFirst()==3){
            returnUrl = "search/list/search_list_solution";
        }else{
            returnUrl = "search/list/search_list";
        }
        return returnUrl;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void generGdsList(Model model,SearchVO searchVO){
        try {
            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
                searchVO.setKeyWord(URLDecoder.decode(searchVO.getKeyWord()));
            }
            SearchParam searchParam = new SearchParam();
            searchParam.setCollectionName(SolrCoreEnum.GDS.getCode());
            searchParam.setSolrClient(solrClient);
            searchParam.setKeyWord(searchVO.getKeyWord());
            //查询字段 and
            List<SearchField> searchFieldList = new ArrayList<SearchField>();
            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
                SearchField searchField = new SearchField();
                searchField.setName("name");
                searchField.setValue(searchVO.getKeyWord());
                searchFieldList.add(searchField);
            }
            if(searchVO.getCatFirst() >=1){
                SearchField searchField = new SearchField();
                searchField.setName("catFirst");
                searchField.setValue(searchVO.getCatFirst());
                searchFieldList.add(searchField);
            }
            //查询字段 and
            if(StringUtil.isNotBlank(searchVO.getSelectedCondition())){
                String[] strs = searchVO.getSelectedCondition().split(",");
                SearchField searchField = null;
                for(String str : strs){
                    searchField = new SearchField();
                    searchField.setName("catId");
                    searchField.setValue(str);
                    searchFieldList.add(searchField);
                }
            }
            searchParam.setSearchField(searchFieldList);
            //排序字段
            List<SortField> sortFieldList = new ArrayList<SortField>();
            if(StringUtil.isNotBlank(searchVO.getSortField()) && StringUtil.isNotBlank(searchVO.getSortValue())){
                ESort sortValue = ESort.DESC;
                if(ESort.ASC.getSort().equalsIgnoreCase(searchVO.getSortValue())){
                    sortValue = ESort.ASC;
                }else{
                    sortValue = ESort.DESC;
                }
                SortField sortField = new SortField(searchVO.getSortField(),sortValue);
                sortFieldList.add(sortField);
            }
            searchParam.setSortField(sortFieldList);
            searchParam.setPageNo(searchVO.getPageNo());
            searchParam.setPageSize(20);
            searchParam.setIfHightlight(true);
            PageResponseDTO<ResultRespVO> pageInfo = SolrSearchUtil.Search(searchParam);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("searchVO", searchVO);
        } catch (Exception e) {
            logger.error("查询商品列表失败！原因是："+e.getMessage());
        }
    }
    /**
     * 
     * suggest:(搜索关键词联想建议suggest). <br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/suggest")
    @ResponseBody
    public AjaxJson suggest(Model model,SearchVO searchVO){
        AjaxJson json = new AjaxJson();
        try {
            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
                searchVO.setKeyWord(URLDecoder.decode(searchVO.getKeyWord()));
            }
            SearchParam searchParam = new SearchParam();
            searchParam.setCollectionName(SolrCoreEnum.GDS.getCode());
            searchParam.setSolrClient(solrClient);
            //查询字段
            searchParam.setPageNo(searchVO.getPageNo());
            searchParam.setPageSize(10);
            searchParam.setKeyWord(searchVO.getKeyWord());
            List<FacetRespVO> result = SolrSearchUtil.facetSuggest(searchParam);
//            List<ResultRespVO> result = SolrSearchUtil.suggest(searchParam);
           
            json.setObj(result);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error("关键词联想失败！原因是："+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    
    /**
     * 
     * generHotKey:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author gxq 
     * @param searchVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/generhotkey")
    @ResponseBody
    public AjaxJson generHotKey(SearchVO searchVO){
        AjaxJson json = new AjaxJson();
        try {
            PageHotSearchReqDTO pageHotSearchReqDTO = new PageHotSearchReqDTO();
            pageHotSearchReqDTO.setPageNo(1);
            pageHotSearchReqDTO.setPageSize(8);
            PageResponseDTO<PageHotSearchRespDTO> list = iPageHotSearchRSV.queryPageHotSearchPageInfo(pageHotSearchReqDTO);
            json.setObj(list);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error("获取商品标签失败！原因是："+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    
    
    @DubboConsumer
    private IDeltaIndexServiceRSV iDeltaIndexServiceRSV;
    @RequestMapping(value="/index")
    public String deltaImport(){
        try {
            iDeltaIndexServiceRSV.deltaFullImport(SolrCoreEnum.GDS.getCode(), true);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "search/search";
    }
}

