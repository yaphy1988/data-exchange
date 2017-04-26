package com.ai.bdex.dataexchange.busi.search.controller;

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
import com.ai.bdex.dataexchange.solrutil.SolrSearchUtil;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoQueryRSV;
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
    public String init(Model model){
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
    @RequestMapping(value="gridgdsinfo")
    public String gridGdsInfo(Model model,SearchVO searchVO){
        try {
//            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
//            gdsInfoReqDTO.setPageNo(searchVO.getPageNo());
//            gdsInfoReqDTO.setPageSize(20);
//            PageResponseDTO<GdsInfoRespDTO> pageInfo = iGdsInfoQueryRSV.queryGdsInfoListPage(gdsInfoReqDTO);
//            if(pageInfo != null){
//                model.addAttribute("pageInfo", pageInfo);
//            }
            String[] field = new String[]{"*"};
            String[] key = new String[]{"*"};
            String[] sortfield =  new String[]{"hotDegree"};
            Boolean[] flag = new Boolean[]{false};
            SolrDocumentList result = SolrSearchUtil.Search(field, key, searchVO.getPageNo(), 20, sortfield, flag, true, solrClient);
            PageResponseDTO<GdsInfoRespDTO> pageInfo = new PageResponseDTO<GdsInfoRespDTO>();
            model.addAttribute("resultList", result);
            pageInfo.setCount(result.getNumFound());
            pageInfo.setPageSize(20);
            pageInfo.setPageNo(searchVO.getPageNo());
            String pageCount = (result.getNumFound() % 20 == 0) ? (result.getNumFound() / 20)+"" : (result.getNumFound()/ 20 + 1)+"";
            pageInfo.setPageCount(Integer.parseInt(pageCount));
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("查询商品列表失败！原因是："+e.getMessage());
        }
        return "search/list/search_list";
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
    @RequestMapping(value="/suggest")
    @ResponseBody
    public AjaxJson suggest(Model model,SearchVO searchVO){
        AjaxJson json = new AjaxJson();
        try {
            json.setObj(null);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error("关键词联想失败！原因是："+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
}

