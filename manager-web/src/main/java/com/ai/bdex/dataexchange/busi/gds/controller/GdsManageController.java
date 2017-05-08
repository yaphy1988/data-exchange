package com.ai.bdex.dataexchange.busi.gds.controller;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsCatVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsCatRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yx on 2017/4/27.
 */
@Controller
@RequestMapping(value = "/gdsManage")
public class GdsManageController {

    private final static Logger log = LoggerFactory.getLogger(GdsManageController.class);

    @DubboConsumer(timeout = 30000)
    private IGdsCatRSV iGdsCatRSV;
    @DubboConsumer(timeout = 30000)
    private IGdsInfoRSV iGdsInfoRSV;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){

        String viewName = "goods_manager";
        ModelAndView mv = new ModelAndView(viewName);
        return mv;
    }

    /**
     * 查询所有商品分类列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryAllCats")
    @ResponseBody
    public AjaxJson queryAllCats(HttpServletRequest request,HttpServletResponse response){
        AjaxJson ajaxJson = new AjaxJson();

        GdsCatReqDTO gdsCatReqDTO = new GdsCatReqDTO();
        gdsCatReqDTO.setStatus("1");
        try {
            List<GdsCatRespDTO> gdsCatRespDTOs = iGdsCatRSV.queryGdsCatList(gdsCatReqDTO);
//            List<GdsCatRespDTO> gdsCatRespDTOs = new ArrayList<GdsCatRespDTO>();
//            GdsCatRespDTO gdsCatRespDTO1 = iGdsCatRSV.queryGdsCatByCatId(1);
//            gdsCatRespDTOs.add(gdsCatRespDTO1);
            List<GdsCatVO> gdsCatVOList = new ArrayList<GdsCatVO>();
            if (!CollectionUtil.isEmpty(gdsCatRespDTOs)){
                for (GdsCatRespDTO gdsCatRespDTO : gdsCatRespDTOs){
                    GdsCatVO gdsCatVO = new GdsCatVO();
                    ObjectCopyUtil.copyObjValue(gdsCatRespDTO,gdsCatVO,null,false);
                    gdsCatVOList.add(gdsCatVO);
                }
            }
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(gdsCatVOList);
        } catch (Exception e) {
            log.error("查询商品分类信息异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询商品分类信息异常");
        }

        return ajaxJson;
    }

    /**
     * 查询商品列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryGdsList")
    public ModelAndView queryGdsList(HttpServletRequest request, HttpServletResponse response, GdsInfoReqDTO gdsInfoReqDTO){

        if (gdsInfoReqDTO == null){
            gdsInfoReqDTO = new GdsInfoReqDTO();
        }
        gdsInfoReqDTO.setGridQuerySortOrder("desc");
        gdsInfoReqDTO.setGridQuerySortName("shelve_time");
        gdsInfoReqDTO.setPageSize(10);
        PageResponseDTO<GdsInfoVO> pageInfo = new PageResponseDTO<GdsInfoVO>();
        try {

            PageResponseDTO<GdsInfoRespDTO> gdsInfoRespPage = iGdsInfoRSV.queryGdsInfoPage(gdsInfoReqDTO);
            ObjectCopyUtil.copyObjValue(gdsInfoRespPage,pageInfo,null,false);
            List<GdsInfoVO> gdsInfoVOList = new ArrayList<GdsInfoVO>();
            if(!CollectionUtil.isEmpty(gdsInfoRespPage.getResult())){
                for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespPage.getResult()){
                    GdsInfoVO gdsInfoVO = new GdsInfoVO();
                    ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
                    gdsInfoVO.setStatusName(traslateStatusToName(gdsInfoVO.getStatus()));
                    gdsInfoVO.setCatName(traslateCatName(gdsInfoVO.getCatId()));
                    gdsInfoVOList.add(gdsInfoVO);
                }
            }
            pageInfo.setResult(gdsInfoVOList);
        }catch (Exception e){
            log.error("查询商品列表异常");
        }

        String viewName = "gds/gdsManage/gdsManageList";
        ModelAndView mv = new ModelAndView(viewName);
//        mv.addObject("gdsInfoList",pageInfo.getResult());
        mv.addObject("pageInfo",pageInfo);
        if (StringUtil.isBlank(gdsInfoReqDTO.getStatus())){
            mv.addObject("status","");
        }else{
            mv.addObject("status",gdsInfoReqDTO.getStatus());
        }
        return mv;
    }

    /**
     * 上下架、删除、启用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/doGdsStatus")
    @ResponseBody
    public AjaxJson doGdsStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxJson ajaxJson = new AjaxJson();

        String gdsId = request.getParameter("gdsId");
        String targetStatus = request.getParameter("targetStatus");
        String oldStatus = request.getParameter("oldStatus");
        HttpSession session = request.getSession();
        try {

            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setGdsId(Integer.parseInt(gdsId));
            GdsInfoRespDTO gdsInfoRespDTO = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
            if (gdsInfoRespDTO!=null){
                GdsInfoReqDTO updateReqDTO = new GdsInfoReqDTO();
                ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,updateReqDTO,null,false);
                updateReqDTO.setStatus(targetStatus);
                Date nowDate = new Date();
                updateReqDTO.setUpdateTime(nowDate);
                updateReqDTO.setUpdateUser(StaffUtil.getStaffId(session));
                updateReqDTO.setShelveTime(nowDate);
                updateReqDTO.setShelveUser(StaffUtil.getStaffId(session));
                iGdsInfoRSV.updateGdsInfoManager(updateReqDTO);

            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("操作失败，查询不到该商品信息！");
                return ajaxJson;
            }

            ajaxJson.setSuccess(true);
        }catch (Exception e){
           if ("1".equals(targetStatus)){
               log.error("商品上架失败：",e);
               ajaxJson.setMsg("商品上架失败！");
           }else if ("2".equals(targetStatus)){
               if ("9".equals(oldStatus)){
                   log.error("商品重新启用失败：",e);
                   ajaxJson.setMsg("商品重新启用失败！");
               }else{
                   log.error("商品下架失败：",e);
                   ajaxJson.setMsg("商品下架失败！");
               }
           }else if ("9".equals(targetStatus)){
               log.error("商品删除失败：",e);
               ajaxJson.setMsg("商品删除失败！");
           }
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    @RequestMapping(value = "/dealGdsRec")
    @ResponseBody
    public AjaxJson dealGdsRec(HttpServletRequest request,HttpServletResponse response){
        AjaxJson ajaxJson = new AjaxJson();

        String gdsId = request.getParameter("gdsId");
        String ifRecGds = request.getParameter("ifRecGds");
        String errorMsg = "";
        HttpSession session = request.getSession();
        if ("1".equals(ifRecGds)){
            errorMsg = "商品推荐失败";
        }else{
            errorMsg = "取消商品推荐失败";
        }
        try {

            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setGdsId(Integer.parseInt(gdsId));
            GdsInfoRespDTO gdsInfoRespDTO = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
            if (gdsInfoRespDTO!=null){
                GdsInfoReqDTO updateReqDTO = new GdsInfoReqDTO();
                ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,updateReqDTO,null,false);
                updateReqDTO.setIfRecommend(ifRecGds);
                updateReqDTO.setUpdateTime(new Date());
                updateReqDTO.setUpdateUser(StaffUtil.getStaffId(session));
                iGdsInfoRSV.updateGdsInfo(updateReqDTO);
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(errorMsg+"，查询不到该商品信息！");
                return ajaxJson;
            }
            ajaxJson.setSuccess(true);
        }catch (Exception e){
            log.error(errorMsg+"：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(errorMsg+"！");
        }

        return ajaxJson;
    }

    private String traslateStatusToName(String status){
        String statusName = "";
        if("0".equals(status)){
            statusName = "待上架";
        }else if("1".equals(status)){
            statusName = "已上架";
        }else if ("2".equals(status)){
            statusName = "已下架";
        }else if ("9".equals(status)){
            statusName = "已删除";
        }
        return statusName;
    }

    private String traslateCatName(Integer catId){
        String catName = "";
        if (catId!=null && catId.intValue()>0){
            GdsCatRespDTO gdsCatRespDTO = new GdsCatRespDTO();
            try {
                gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(catId);
            } catch (Exception e) {
                log.error("根据catId查询分类信息异常：",e);
            }
            if (gdsCatRespDTO!=null){
                catName = gdsCatRespDTO.getCatName();
            }
        }
        return  catName;
    }
}
