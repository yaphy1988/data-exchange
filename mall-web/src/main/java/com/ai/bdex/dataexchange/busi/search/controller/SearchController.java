package com.ai.bdex.dataexchange.busi.search.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.search.entiry.SearchVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.solrutil.SearchField;
import com.ai.bdex.dataexchange.solrutil.SearchParam;
import com.ai.bdex.dataexchange.solrutil.SolrCoreEnum;
import com.ai.bdex.dataexchange.solrutil.SolrSearchUtil;
import com.ai.bdex.dataexchange.solrutil.SortField;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
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
        try {
            GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
            gdsCatReqDTO.setCatPid(1);
            List<GdsCatRespDTO> catList = iGdsCatRSV.queryGdsCatList(gdsCatReqDTO);
            if(catList != null && catList.size() >= 1){
                for(GdsCatRespDTO dto : catList){
                    gdsCatReqDTO.setCatPid(dto.getCatId());
                    List<GdsCatRespDTO> subCatList = iGdsCatRSV.queryGdsCatList(gdsCatReqDTO);
                    dto.setSubCatList(subCatList);
                }
            }
            model.addAttribute("catList", catList);
            generGdsList(model,searchVO);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "search/search";
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
        return "search/list/search_list";
    }
    
    public void generGdsList(Model model,SearchVO searchVO){
        try {
            SearchParam searchParam = new SearchParam();
            searchParam.setCollectionName(SolrCoreEnum.GDS.getCode());
            searchParam.setSolrClient(solrClient);
            //查询字段
            List<SearchField> searchFieldList = new ArrayList<SearchField>();
            if(StringUtil.isNotBlank(searchVO.getKeyWord())){
                searchVO.setKeyWord(URLDecoder.decode(searchVO.getKeyWord()));
                SearchField searchField = new SearchField();
                searchField.setName("name");
                searchField.setValue(searchVO.getKeyWord());
                searchFieldList.add(searchField);
            }
            searchParam.setSearchField(searchFieldList);
            //排序字段
            List<SortField> sortFieldList = new ArrayList<SortField>();
            searchParam.setSortField(sortFieldList);
            searchParam.setPageNo(searchVO.getPageNo());
            searchParam.setPageSize(20);
            searchParam.setIfHightlight(true);
            SolrDocumentList result = SolrSearchUtil.Search(searchParam);
            PageResponseDTO<GdsInfoRespDTO> pageInfo = new PageResponseDTO<GdsInfoRespDTO>();
            model.addAttribute("resultList", result);
            if(result != null){
                pageInfo.setCount(result.getNumFound());
                pageInfo.setPageNo(searchVO.getPageNo());
                String pageCount = (result.getNumFound() % 20 == 0) ? (result.getNumFound() / 20)+"" : (result.getNumFound()/ 20 + 1)+"";
                pageInfo.setPageCount(Integer.parseInt(pageCount));
            }
            pageInfo.setPageSize(20);
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
            SearchParam searchParam = new SearchParam();
            searchParam.setCollectionName(SolrCoreEnum.GDS.getCode());
            searchParam.setSolrClient(solrClient);
            //查询字段
            searchParam.setPageNo(searchVO.getPageNo());
            searchParam.setPageSize(10);
            searchParam.setKeyWord(searchVO.getKeyWord());
            SolrDocumentList result = SolrSearchUtil.suggest(searchParam);
            json.setObj(result);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error("关键词联想失败！原因是："+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
}

