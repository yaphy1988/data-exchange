package com.ai.bdex.dataexchange.busi.pic.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.pic.entity.PicManageVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic.IPicInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pic.IPicLibRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@RequestMapping(value="/picmanage")
@Controller
public class PicManageController {
    private static final Logger logger = LoggerFactory.getLogger(PicManageController.class.getName());
    @DubboConsumer
    private IPicInfoRSV iPicInfoRSV;
    
    @DubboConsumer
    private IPicLibRSV iPicLibRSV;
    
    /**================================================图片库start========================================*/
    @RequestMapping(value="/piclib")
    public String init(Model model){
        //初始化必须返回一个对象,对象不能是null噢。否则页面解析报错
        PageResponseDTO<GdsCatRespDTO> pageResponseDTO = new PageResponseDTO<GdsCatRespDTO>();
        model.addAttribute("pageInfo", pageResponseDTO);
        return "multiMedia_manage";
    }
    /**
     * 
     * gridPicInfo:(获取某个图片库列表). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param picManageVO
     * @param session
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridpiclib")
    public String gridPicLib(Model model,PicManageVO picManageVO,HttpSession session){
        PicLibReqDTO picLibReqDTO = new PicLibReqDTO();
        picLibReqDTO.setStatus("1");
        picLibReqDTO.setPageNo(picManageVO.getPageNo());
        picLibReqDTO.setPageSize(10);
        picLibReqDTO.setLibName(picManageVO.getLibName());
        try {
            PageResponseDTO<PicLibRespDTO> pageInfo = iPicLibRSV.queryPicLibPage(picLibReqDTO);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("获取某个图片库列表：",e);
        }
        return "multiMedia_manage :: #picLib_List_template";
    }
    
    
    
    /**
     * 
     * savePicLib:(保存图片库信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param picManageVO
     * @param session
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/savepiclib")
    @ResponseBody
    public AjaxJson savePicLib(Model model,PicManageVO picManageVO,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicLibReqDTO picLibReqDTO = new PicLibReqDTO();
            picLibReqDTO.setCreateUser(StaffUtil.getStaffId(session));
            picLibReqDTO.setLibName(picManageVO.getLibName());
            picLibReqDTO.setLibPic(picManageVO.getLibPic());
            picLibReqDTO.setStatus("1");
            iPicLibRSV.insertPicLib(picLibReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("保保存图片库信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
   
    /**
     * 
     * updatePicLib:(更新图片库信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updatepiclib")
    @ResponseBody
    public AjaxJson updatePicLib(Model model,PicManageVO picManageVO,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicLibReqDTO picLibReqDTO = new PicLibReqDTO();
            ObjectCopyUtil.copyObjValue(picManageVO, picLibReqDTO, null, false);
            picLibReqDTO.setUpdateUser(StaffUtil.getStaffId(session));
            iPicLibRSV.updatePicLib(picLibReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("编辑图片库信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 
     * deletePicLib:(删除图片库信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deletepiclib")
    @ResponseBody
    public AjaxJson deletePicLib(Model model,PicManageVO picManageVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicLibReqDTO picLibReqDTO = new PicLibReqDTO();
            ObjectCopyUtil.copyObjValue(picManageVO, picLibReqDTO, null, false);
            iPicLibRSV.deletePicLib(picLibReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("删除图片库信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    /**================================================图片库end========================================*/
    
    /**================================================图片start========================================*/
    @RequestMapping(value="/picinfo")
    public String picinfo(Model model,PicManageVO picManageVO){
        //初始化必须返回一个对象,对象不能是null噢。否则页面解析报错
        PageResponseDTO<GdsCatRespDTO> pageResponseDTO = new PageResponseDTO<GdsCatRespDTO>();
        model.addAttribute("pageInfo", pageResponseDTO);
        model.addAttribute("picManageVO", picManageVO);
        return "singlePicture_manage";
    }
    /**
     * 
     * gridPicInfo:(获取某个图片库里的图片列表). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridpicinfo")
    public String gridPicInfo(Model model,PicManageVO picManageVO,HttpSession session){
        PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
        picInfoReqDTO.setStatus("1");
        picInfoReqDTO.setLibId(picManageVO.getLibId());
        picInfoReqDTO.setPageNo(picManageVO.getPageNo());
        picInfoReqDTO.setPageSize(10);
        picInfoReqDTO.setPicName(picManageVO.getPicName());
        try {
            PageResponseDTO<PicInfoRespDTO> pageInfo = iPicInfoRSV.queryPicInfoPage(picInfoReqDTO);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("获取某个图片库里的图片列表信息异常：",e);
        }
        return "singlePicture_manage :: #picinfo_List_template";
    }
   
    /**
     * 
     * savePicInfo:(保存图片信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param picManageVO
     * @param session
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/savepicinfo")
    @ResponseBody
    public AjaxJson savePicInfo(Model model,PicManageVO picManageVO,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
            picInfoReqDTO.setCreateUser(StaffUtil.getStaffId(session));
            ObjectCopyUtil.copyObjValue(picManageVO, picInfoReqDTO, null, false);
            iPicInfoRSV.insertPicInfo(picInfoReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("保存图片信异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
   
    /**
     * 
     * updatePicInfo:(更新图片信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/updatepicinfo")
    @ResponseBody
    public AjaxJson updatePicInfo(Model model,PicManageVO picManageVO,HttpSession session){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
            ObjectCopyUtil.copyObjValue(picManageVO, picInfoReqDTO, null, false);
            picInfoReqDTO.setUpdateUser(StaffUtil.getStaffId(session));
            iPicInfoRSV.updatePicInfo(picInfoReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("更新图片信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 
     * deletePicInfo:(删除图片信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param gdsCatVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deletepicinfo")
    @ResponseBody
    public AjaxJson deletePicInfo(Model model,PicManageVO picManageVO){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
            ObjectCopyUtil.copyObjValue(picManageVO, picInfoReqDTO, null, false);
            iPicInfoRSV.deletePicInfo(picInfoReqDTO);
            ajaxJson.setSuccess(true);
        } catch (BusinessException e) {
            logger.error("删除图片信息异常：",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**================================================图片end========================================*/
}

