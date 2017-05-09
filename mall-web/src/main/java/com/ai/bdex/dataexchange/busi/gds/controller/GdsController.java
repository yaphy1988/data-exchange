package com.ai.bdex.dataexchange.busi.gds.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.busi.api.entity.*;
import com.ai.bdex.dataexchange.busi.gds.entity.*;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/goods")
public class GdsController {

    private static final Logger log = LoggerFactory.getLogger(GdsController.class);

    private final static Integer AIP_CAT_ID = 1;//aip的catId
    private final static Integer CUSTOM_CAT_ID = 2;//定制需求catId
    private final static Integer SOLUTION_CAT_ID = 3;//解决方案catId

    @DubboConsumer(timeout = 30000)
    private IGdsInfoRSV iGdsInfoRSV;
    @DubboConsumer(timeout = 30000)
    private IGdsCatRSV iGdsCatRSV;
    @DubboConsumer(timeout = 30000)
    private IGdsLabelRSV iGdsLabelRSV;
    @DubboConsumer(timeout = 30000)
    private IGdsInfo2PropRSV iGdsInfo2PropRSV;
    @DubboConsumer(timeout = 30000)
    private IGdsSkuRSV iGdsSkuRSV;
    @DubboConsumer(timeout = 30000)
    private IServiceMessageRSV iServiceMessageRSV;
    @DubboConsumer 
    private IOrderInfoRSV iOrderInfoRSV;

    @RequestMapping(value = "/details/{gdsId}-{skuId}")
    public String pageInit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer gdsId,@PathVariable Integer skuId){

        if (gdsId == null || gdsId.intValue()<=0){
            return "error";
        }
        String viewName = "";
        request.setAttribute("ifHiddenCatNav","hidden");
        GdsInfoVO gdsInfoVO = new GdsInfoVO();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setGdsId(gdsId);
        GdsInfoRespDTO gdsInfoRespDTO = null;
        try {
            gdsInfoRespDTO = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
            if (gdsInfoRespDTO!=null){
                ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
                if (!StringUtil.isBlank(gdsInfoVO.getGdsPic())){
                    gdsInfoVO.setGdsPic(ImageUtil.getImageUrl(gdsInfoRespDTO.getGdsPic() + "_260x260"));
                }
//                BeanUtils.copyProperties(gdsInfoRespDTO,gdsInfoVO);
                //设置分类名称
                if (gdsInfoRespDTO.getCatId()!=null && gdsInfoRespDTO.getCatId().intValue()>0){
                    GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsInfoRespDTO.getCatId());
                    if (gdsCatRespDTO!=null){
                        gdsInfoVO.setCatName(gdsCatRespDTO.getCatName());
                    }
                    //获取从最高级到最低价的分类列表
                    List<GdsCatRespDTO> ladderCatList = iGdsCatRSV.queryLadderCatListByCatId(gdsInfoRespDTO.getCatId());
                    Collections.reverse(ladderCatList);
                    request.setAttribute("ladderCatList",ladderCatList);

                }
                if (gdsInfoRespDTO.getCatFirst()!=null && gdsInfoRespDTO.getCatId().intValue()>0){
                    GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsInfoRespDTO.getCatFirst());
                    if (gdsCatRespDTO!=null){
                        gdsInfoVO.setCatFirstName(gdsCatRespDTO.getCatName());
                    }
                }

                //设置商品标签列表
                List<GdsLabelVO> gdsLabelVOList = new ArrayList<GdsLabelVO>();
                GdsLabelReqDTO gdsLabelReqDTO = new GdsLabelReqDTO();
                gdsLabelReqDTO.setStatus("1");
                gdsLabelReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                List<GdsLabelRespDTO> gdsLabelRespDTOList = iGdsLabelRSV.queryGdsLabelList(gdsLabelReqDTO);
                if (!CollectionUtil.isEmpty(gdsLabelRespDTOList)){
                    for (GdsLabelRespDTO gdsLabelRespDTO : gdsLabelRespDTOList){
                        GdsLabelVO gdsLabelVO = new GdsLabelVO();
                        ObjectCopyUtil.copyObjValue(gdsLabelRespDTO,gdsLabelVO,null,false);
//                        BeanUtils.copyProperties(gdsLabelRespDTO,gdsLabelVO);
                        gdsLabelVOList.add(gdsLabelVO);
                    }
                    gdsInfoVO.setGdsLabelVOList(gdsLabelVOList);
                }

                //设置商品属性列表
                List<GdsInfo2PropVO> gdsInfo2PropVOList = new ArrayList<GdsInfo2PropVO>();
                GdsInfo2PropReqDTO gdsInfo2PropReqDTO = new GdsInfo2PropReqDTO();
                gdsInfo2PropReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                gdsInfo2PropReqDTO.setStatus("1");
                List<GdsInfo2PropRespDTO> gdsInfo2PropRespDTOList = iGdsInfo2PropRSV.queryGdsInfo2PropList(gdsInfo2PropReqDTO);
                List<GdsDetailTabContent> tabContentList = new ArrayList<GdsDetailTabContent>();
                if (!CollectionUtil.isEmpty(gdsInfo2PropRespDTOList)){
                    for (GdsInfo2PropRespDTO gdsInfo2PropRespDTO : gdsInfo2PropRespDTOList){
                        GdsInfo2PropVO gdsInfo2PropVO = new GdsInfo2PropVO();
                        ObjectCopyUtil.copyObjValue(gdsInfo2PropRespDTO,gdsInfo2PropVO,null,false);
//                        BeanUtils.copyProperties(gdsInfo2PropRespDTO,gdsInfo2PropVO);
                        gdsInfo2PropVOList.add(gdsInfo2PropVO);
                        if ("2".equals(gdsInfo2PropVO.getProType())){
                            GdsDetailTabContent gdsDetailTabContent = new GdsDetailTabContent();
                            gdsDetailTabContent.setTabName(gdsInfo2PropVO.getProName());
                            gdsDetailTabContent.setTabContentType("2");
                            if (!StringUtil.isBlank(gdsInfo2PropVO.getProType())){
                                gdsDetailTabContent.setTabContentId(ImageUtil.getStaticDocUrl(gdsInfo2PropVO.getProValue(),"html"));
                            }
//                            gdsDetailTabContent.setTabContentId(gdsInfo2PropVO.getProValue());
                            tabContentList.add(gdsDetailTabContent);
                        }
                    }
                    gdsInfoVO.setTabContentList(tabContentList);
                    gdsInfoVO.setGdsInfo2PropVOList(gdsInfo2PropVOList);
                }

                if (AIP_CAT_ID.equals(gdsInfoRespDTO.getCatFirst())){
                    //api的分类要设置单品列表信息
                    List<GdsSkuVO> gdsSkuVOList = new ArrayList<GdsSkuVO>();
                    GdsSkuReqDTO gdsSkuReqDTO = new GdsSkuReqDTO();
                    gdsSkuReqDTO.setGdsId(gdsInfoRespDTO.getGdsId());
                    gdsSkuReqDTO.setStatus("1");
                    List<GdsSkuRespDTO> gdsSkuRespDTOList = iGdsSkuRSV.queryGdsSkuList(gdsSkuReqDTO);
                    if (!CollectionUtil.isEmpty(gdsSkuRespDTOList)){
                        boolean ifFirstSelflag = true;//是否设置第一个为选中的单品
                        for (GdsSkuRespDTO gdsSkuRespDTO : gdsSkuRespDTOList){
                            GdsSkuVO gdsSkuVO = new GdsSkuVO();
                            ObjectCopyUtil.copyObjValue(gdsSkuRespDTO,gdsSkuVO,null,false);
                            //将分转换成元，并保留两位小数
                            if (gdsSkuVO.getPackPrice()!=null && gdsSkuVO.getPackPrice().intValue()>0){
                                gdsSkuVO.setPackPriceStr(decimalTwo(gdsSkuVO.getPackPrice()/(float)100));
                            }else{
                                gdsSkuVO.setPackPriceStr("0.00");
                            }
//                            BeanUtils.copyProperties(gdsSkuRespDTO,gdsSkuVO);
                            if (skuId!=null && skuId.intValue()>0){
                                if (skuId.intValue() == gdsSkuVO.getSkuId().intValue()){
                                    request.setAttribute("curGdsSku",gdsSkuVO);
                                    gdsSkuVO.setIfHaveSel("active");//直接返回class，省去再根据是否等于1去判断使用class=active
                                    ifFirstSelflag = false;
                                }
                            }
                            gdsSkuVOList.add(gdsSkuVO);
                        }
                        if (ifFirstSelflag){
                            gdsSkuVOList.get(0).setIfHaveSel("1");
                            request.setAttribute("curGdsSku",gdsSkuVOList.get(0));
                        }
                        gdsInfoVO.setGdsSkuVOList(gdsSkuVOList);
                    }
                    //API信息接口调用
                    String serviceVersion = "";
                    String serviceId = gdsInfoVO.getApiId()+"";
                    ServiceDTO serviceDTO = iServiceMessageRSV.getServiceMessage(serviceId,serviceVersion);
                    ServiceVO serviceVO = new ServiceVO();
                    if (serviceDTO!=null){
                        ObjectCopyUtil.copyObjValue(serviceDTO,serviceVO,null,false);
                        //aip接口入参信息
                        if(!CollectionUtil.isEmpty(serviceDTO.getServiceInParas())){
                            List<AipServiceInParaVO> aipServiceInParaVOs = new ArrayList<AipServiceInParaVO>();
                            for (AipServiceInParaDTO aipServiceInParaDTO : serviceDTO.getServiceInParas()){
                                AipServiceInParaVO aipServiceInParaVO = new AipServiceInParaVO();
                                ObjectCopyUtil.copyObjValue(aipServiceInParaDTO,aipServiceInParaVO,null,false);
                                if ("00".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("Object");
                                }else if ("01".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("String");
                                }else if ("02".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("Interger");
                                }else if ("03".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("Double");
                                }else if ("04".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("Float");
                                }else if ("05".equals(aipServiceInParaVO.getParaType())){
                                    aipServiceInParaVO.setParaTypeName("Date");
                                }
                                aipServiceInParaVOs.add(aipServiceInParaVO);
                            }
                            serviceVO.setServiceInParas(aipServiceInParaVOs);
                        }
                        //aip接口出参信息
                        if (!CollectionUtil.isEmpty(serviceDTO.getServiceOutParas())){
                            List<AipServiceOutParaVO> aipServiceOutParaVOs = new ArrayList<AipServiceOutParaVO>();
                            for (AipServiceOutParaDTO aipServiceOutParaDTO : serviceDTO.getServiceOutParas()){
                                AipServiceOutParaVO aipServiceOutParaVO = new AipServiceOutParaVO();
                                ObjectCopyUtil.copyObjValue(aipServiceOutParaDTO,aipServiceOutParaVO,null,false);
                                if ("00".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("Object");
                                }else if ("01".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("String");
                                }else if ("02".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("Interger");
                                }else if ("03".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("Double");
                                }else if ("04".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("Float");
                                }else if ("05".equals(aipServiceOutParaVO.getParaType())){
                                    aipServiceOutParaVO.setParaTypeName("Date");
                                }
                                aipServiceOutParaVOs.add(aipServiceOutParaVO);
                            }
                            serviceVO.setServiceOutParas(aipServiceOutParaVOs);
                        }
                        //错误码定义
                        if (!CollectionUtil.isEmpty(serviceDTO.getServiceErrores())){
                            List<AipServiceErrorInfoVO> aipServiceErrorInfoVOs = new ArrayList<AipServiceErrorInfoVO>();
                            List<AipServiceErrorInfoVO> aipSystemErrorInfoVOs = new ArrayList<AipServiceErrorInfoVO>();
                            for (AipServiceErrorInfoDTO aipServiceErrorInfoDTO : serviceDTO.getServiceErrores()){
                                AipServiceErrorInfoVO aipServiceErrorInfoVO = new AipServiceErrorInfoVO();
                                ObjectCopyUtil.copyObjValue(aipServiceErrorInfoDTO,aipServiceErrorInfoVO,null,false);
                                if ("00".equals(aipServiceErrorInfoVO.getType())){
                                    aipServiceErrorInfoVOs.add(aipServiceErrorInfoVO);
                                }else if ("01".equals(aipServiceErrorInfoVO.getType())){
                                    aipSystemErrorInfoVOs.add(aipServiceErrorInfoVO);
                                }
                            }
                            serviceVO.setSystemServiceErrores(aipSystemErrorInfoVOs);
                            serviceVO.setServiceServiceErrores(aipServiceErrorInfoVOs);
                        }
                        //接口demo信息
                        if (!CollectionUtil.isEmpty(serviceDTO.getServiceCodeInfoes())){
                            List<AipServiceCodeInfoVO> aipServiceCodeInfoVOs = new ArrayList<AipServiceCodeInfoVO>();
                            for (AipServiceCodeInfoDTO aipServiceCodeInfoDTO : serviceDTO.getServiceCodeInfoes()){
                                AipServiceCodeInfoVO aipServiceCodeInfoVO = new AipServiceCodeInfoVO();
                                ObjectCopyUtil.copyObjValue(aipServiceCodeInfoDTO,aipServiceCodeInfoVO,null,false);
                                aipServiceCodeInfoVOs.add(aipServiceCodeInfoVO);
                            }
                            serviceVO.setServiceCodeInfoes(aipServiceCodeInfoVOs);
                        }
                        gdsInfoVO.setServiceVO(serviceVO);
                    }


                    viewName = "goods_details";

                }else if (CUSTOM_CAT_ID.equals(gdsInfoRespDTO.getCatFirst())){
                    if (!CollectionUtil.isEmpty(gdsInfoVO.getGdsInfo2PropVOList())){
                        for (GdsInfo2PropVO gdsInfo2PropVO : gdsInfoVO.getGdsInfo2PropVOList()){
                            if (gdsInfo2PropVO.getProId() == 2){//数据描述
                                gdsInfoVO.setDataDescription(gdsInfo2PropVO.getProValue());
                            }
                        }
                    }

                    viewName = "goods_custom";
                }else if (SOLUTION_CAT_ID.equals(gdsInfoRespDTO.getCatFirst())){
                    if (!CollectionUtil.isEmpty(gdsInfoVO.getGdsInfo2PropVOList())){
                        for (GdsInfo2PropVO gdsInfo2PropVO : gdsInfoVO.getGdsInfo2PropVOList()){
                            if (gdsInfo2PropVO.getProId() == 5){//适用范围
                                gdsInfoVO.setAdaptRange(gdsInfo2PropVO.getProValue());
                            }
                            if (gdsInfo2PropVO.getProId() == 6){//特点
                                gdsInfoVO.setFeature(gdsInfo2PropVO.getProValue());
                            }
                        }
                    }
                    viewName = "goods_solution";
                }

            }
        } catch (Exception e) {
            log.error("查询商品详情异常：",e);
        }


        request.setAttribute("gdsInfo",gdsInfoVO);
        request.setAttribute("ifHiddenHead","hidden");

        return viewName;
    }

    @RequestMapping(value = "/queryRecomGdsList")
    public ModelAndView queryRecomGdsList(){
        String viewName = "goods/details/recomGdsList";
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setCatFirst(AIP_CAT_ID);
        gdsInfoReqDTO.setStatus("1");
        gdsInfoReqDTO.setPageSize(8);
        gdsInfoReqDTO.setIfRecommend("1");
        gdsInfoReqDTO.setGridQuerySortName("shelve_time");
        gdsInfoReqDTO.setGridQuerySortOrder("desc");
        PageResponseDTO<GdsInfoRespDTO> pageResponseDTO = new PageResponseDTO<GdsInfoRespDTO>();
        List<GdsInfoVO> gdsInfoVOList = new ArrayList<GdsInfoVO>();
        try{
            pageResponseDTO = iGdsInfoRSV.queryGdsInfoPage(gdsInfoReqDTO);
        }catch (Exception e){
            log.error("查询商品信息列表异常",e);
        }
        if (pageResponseDTO!=null && !CollectionUtil.isEmpty(pageResponseDTO.getResult())){
            for (GdsInfoRespDTO gdsInfoRespDTO : pageResponseDTO.getResult()){
                GdsInfoVO gdsInfoVO = new GdsInfoVO();
                ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
                if (!StringUtil.isBlank(gdsInfoVO.getGdsPic())){
                    gdsInfoVO.setGdsPic(ImageUtil.getImageUrl(gdsInfoVO.getGdsPic()+"_80x80"));
                }
                gdsInfoVOList.add(gdsInfoVO);
            }
        }

        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("recGdsList",gdsInfoVOList);
        return mv;
    }

    @RequestMapping(value = "/applyDataValidate")
    @ResponseBody
    public AjaxJson applyDataValidate(HttpServletRequest request, HttpServletResponse response,@PathVariable Long gdsId,@PathVariable Long skuId){
        AjaxJson ajaxJson = new AjaxJson();
        HttpSession session = request.getSession();

        try {
        	OrdInfoReqDTO ordInfoRespDTO = new OrdInfoReqDTO();
            ordInfoRespDTO.setGdsId(gdsId);
            ordInfoRespDTO.setSkuId(skuId);
            ordInfoRespDTO.setStaffId(StaffUtil.getStaffId(session));
            ordInfoRespDTO.setPayFlag("1");
            List<OrdInfoRespDTO> list = iOrderInfoRSV.queryOrderByStaff(ordInfoRespDTO);
            if (!CollectionUtil.isEmpty(list)){
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("1");
            }else{
                ajaxJson.setSuccess(true);
            }
        }catch (Exception e){
            log.error("查询是否购买过商品异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setErrorCode("0");
        }

        return ajaxJson;
    }

    /**
     * 将float保留两位小数
     * @param num
     * @return
     */
    private String decimalTwo(float num){
        String returnNum = "0.00";
        if (num == 0){
            return returnNum;
        }
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        returnNum = df.format(num);//返回的是String类型

        return  returnNum;
    }

}
