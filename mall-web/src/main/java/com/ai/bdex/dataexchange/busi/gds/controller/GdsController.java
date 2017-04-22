package com.ai.bdex.dataexchange.busi.gds.controller;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfo2PropVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsLabelVO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsSkuVO;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.*;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.*;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/goods")
public class GdsController {

    private static final Logger log = LoggerFactory.getLogger(GdsController.class);

    private final static Integer AIP_CAT_ID = 1;

    @DubboConsumer
    private IGdsInfoRSV iGdsInfoRSV;
    @DubboConsumer
    private IGdsCatRSV iGdsCatRSV;
    @DubboConsumer
    private IGdsLabelRSV iGdsLabelRSV;
    @DubboConsumer
    private IGdsInfo2PropRSV iGdsInfo2PropRSV;
    @DubboConsumer
    private IGdsSkuRSV iGdsSkuRSV;

    @RequestMapping(value = "/pageInit/{gdsId}-{skuId}")
    public String pageInit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer gdsId,@PathVariable Integer skuId){

        if (gdsId == null || gdsId.intValue()<=0){
            return "error";
        }
        GdsInfoVO gdsInfoVO = new GdsInfoVO();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setGdsId(gdsId);
        GdsInfoRespDTO gdsInfoRespDTO = null;
        try {
            gdsInfoRespDTO = iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
            if (gdsInfoRespDTO!=null){
                ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
//                BeanUtils.copyProperties(gdsInfoRespDTO,gdsInfoVO);
                //设置分类名称
                if (gdsInfoRespDTO.getCatId()!=null && gdsInfoRespDTO.getCatId().intValue()>0){
                    GdsCatRespDTO gdsCatRespDTO = iGdsCatRSV.queryGdsCatByCatId(gdsInfoRespDTO.getCatId());
                    if (gdsCatRespDTO!=null){
                        gdsInfoVO.setCatName(gdsCatRespDTO.getCatName());
                    }
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
                if (!CollectionUtil.isEmpty(gdsInfo2PropRespDTOList)){
                    for (GdsInfo2PropRespDTO gdsInfo2PropRespDTO : gdsInfo2PropRespDTOList){
                        GdsInfo2PropVO gdsInfo2PropVO = new GdsInfo2PropVO();
                        ObjectCopyUtil.copyObjValue(gdsInfo2PropRespDTO,gdsInfo2PropVO,null,false);
//                        BeanUtils.copyProperties(gdsInfo2PropRespDTO,gdsInfo2PropVO);
                        gdsInfo2PropVOList.add(gdsInfo2PropVO);
                    }
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

                    //错误代码参照接口调用

                    //实例代码接口调用
                }
            }
        } catch (Exception e) {
            log.error("查询商品详情异常：",e);
        }


        request.setAttribute("gdsInfo",gdsInfoVO);
        request.setAttribute("ifHiddenHead","hidden");

        return "/goods_details";
    }

    @RequestMapping(value = "/queryRecomGdsList")
    @ResponseBody
    public AjaxJson queryRecomGdsList(){
        AjaxJson ajaxJson = new AjaxJson();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setCatFirst(AIP_CAT_ID);
        gdsInfoReqDTO.setStatus("1");
        List<GdsInfoRespDTO> gdsInfoRespDTOList = new ArrayList<GdsInfoRespDTO>();
        try{
            gdsInfoRespDTOList = iGdsInfoRSV.queryGdsInfoList(gdsInfoReqDTO);
        }catch (Exception e){
            log.error("查询商品信息列表异常",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询商品信息列表异常！");
        }
        ajaxJson.setSuccess(true);
        ajaxJson.setObj(gdsInfoRespDTOList);

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
