package com.ai.bdex.dataexchange.busi.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private static final Logger Log = LoggerFactory.getLogger(SearchController.class);
    /**
     * 
     * init:(搜索页初始化入口). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(){
        return URL;
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
    public String gridGdsInfo(Model model){
        try {
            
        } catch (Exception e) {
            Log.error("关键词联想失败！原因是："+e.getMessage());
        }
        return URL;
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
            Log.error("关键词联想失败！原因是："+e.getMessage());
        }
        return model;
    }
}

