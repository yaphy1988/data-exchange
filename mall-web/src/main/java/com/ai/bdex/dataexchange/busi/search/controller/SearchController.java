package com.ai.bdex.dataexchange.busi.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.search.entiry.SearchVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
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
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setPageNo(searchVO.getPageNo());
            gdsInfoReqDTO.setPageSize(10);
            PageResponseDTO<GdsInfoRespDTO> pageInfo = iGdsInfoQueryRSV.queryGdsInfoListPage(gdsInfoReqDTO);
            if(pageInfo != null){
                pageInfo.setPageCount(2);
                pageInfo.setCount(1000);
                model.addAttribute("pageInfo", pageInfo);
            }
        } catch (Exception e) {
            logger.error("查询商品列表失败！原因是："+e.getMessage());
        }
        return "search/list/search_list";
    }
    /**
     * 
     * suggestKeyWord:(搜索关键词联想建议suggest). <br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/suggestkeyword")
    @ResponseBody
    public Model suggestKeyWord(Model model){
        try {
            
        } catch (Exception e) {
            logger.error("关键词联想失败！原因是："+e.getMessage());
        }
        return model;
    }
}

