package com.ai.bdex.dataexchange.busi.gds.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsCatVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

/**
 * 
 * Title: ECP <br>
 * Project Name:manager-web <br>
 * Description: <br>
 * Date:2017年5月4日下午2:57:48  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/gdscat")
public class GdsCatController{
    private static final Logger logger = LoggerFactory.getLogger(GdsCatController.class.getName());
    
    private static final String MODULE = GdsCatController.class.getName();
    @DubboConsumer
    private IGdsCatRSV iGdsCatRSV;
    
    @RequestMapping()
    public String init(Model model){
        //初始化必须返回一个对象。否则页面解析报错
        PageResponseDTO<GdsCatRespDTO> pageResponseDTO = new PageResponseDTO<GdsCatRespDTO>();
        model.addAttribute("pageInfo", pageResponseDTO);
        GdsCatRespDTO gdsCatRespDTO = new GdsCatRespDTO();
        model.addAttribute("catInfo", gdsCatRespDTO);
        return "goods_classification";
    }
    
    /**
     * 
     * queryGdsCat:(获取商品分类). <br/> 
     * 
     * @author gxq 
     * @param request
     * @param response
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querygdscat")
    @ResponseBody
    public AjaxJson queryGdsCat(){
        AjaxJson ajaxJson = new AjaxJson();
        GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
        gdsCatReqDTO.setStatus("1");
        try {
            List<GdsCatRespDTO> gdsCatRespDTOs = iGdsCatRSV.queryGdsCatList(gdsCatReqDTO);
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(gdsCatRespDTOs);
        } catch (Exception e) {
            logger.error("查询商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询商品分类信息异常");
        }
        return ajaxJson;
    }
    
    /**
     * 
     * queryCatNodeInfo:(获取单个分类节点信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querycatnodeinfo")
    public String queryCatNodeInfo(Model model,GdsCatVO gdsCatVO){
        GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
        gdsCatReqDTO.setStatus("1");
        gdsCatReqDTO.setCatId(gdsCatVO.getCatId());
        try {
            GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsCatVO.getCatId());
            model.addAttribute("catInfo", gdsCatRespDTO);
        } catch (Exception e) {
            logger.error("查询商品分类信息异常：",e);
        }
        return "goods_classification :: #node_info_template";
    }
    
    /**
     * 
     * queryCatNodeInfo:(获取单个分类节点信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querycatchild")
    public String queryCatChild(Model model,GdsCatVO gdsCatVO){
        AjaxJson ajaxJson = new AjaxJson();
        GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
        gdsCatReqDTO.setStatus("1");
        gdsCatReqDTO.setCatPid(gdsCatVO.getCatId());
        gdsCatReqDTO.setPageNo(gdsCatVO.getPageNo());
        gdsCatReqDTO.setPageSize(10);
        try {
            PageResponseDTO<GdsCatRespDTO> gdsCatRespDTO = iGdsCatRSV.queryCatPageInfo(gdsCatReqDTO);
            model.addAttribute("pageInfo", gdsCatRespDTO);
        } catch (Exception e) {
            logger.error("查询商品分类信息异常：",e);
        }
        return "goods_classification :: #child_List_template";
    }
    
    /**
     * 
     * saveGdsCat:(新增分类). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/savegdscat")
    @ResponseBody
    public AjaxJson saveGdsCat(Model model,GdsCatVO gdsCatVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
            ObjectCopyUtil.copyObjValue(gdsCatVO, gdsCatReqDTO, null, false);
            iGdsCatRSV.saveGdsCatInfo(gdsCatReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("保存商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 
     * updategdscat:(编辑分类). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updategdscat")
    @ResponseBody
    public AjaxJson updateGdsCat(Model model,GdsCatVO gdsCatVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
            ObjectCopyUtil.copyObjValue(gdsCatVO, gdsCatReqDTO, null, false);
            iGdsCatRSV.updateGdsCatInfo(gdsCatReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("编辑商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 
     * deleteGdsCat:(删除分类). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deletegdscat")
    @ResponseBody
    public AjaxJson deleteGdsCat(Model model,GdsCatVO gdsCatVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            iGdsCatRSV.deleteGdsCatInfo(gdsCatVO.getCatId());
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("删除商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 
     * queryEditCatInfo:(获取编辑的信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryeditcatinfo")
    @ResponseBody
    public AjaxJson queryEditCatInfo(Model model,GdsCatVO gdsCatVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            GdsCatRespDTO catInfo = iGdsCatRSV.queryGdsCatByCatId(gdsCatVO.getCatId());
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(catInfo);
        } catch (BusinessException e) {
            logger.error("删除商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
        } catch (Exception e) {
            logger.error("删除商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
}

