package com.ai.bdex.dataexchange.busi.gds;

import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private IGdsInfoRSV iGdsInfoRSV;

    @RequestMapping(value = "/pageInit/{gdsId}-{skuId}")
    public String pageInit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer gdsId,@PathVariable Integer skuId){

        if (gdsId == null || gdsId.intValue()<=0){
            return "error";
        }
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setGdsId(gdsId);
        try {
            GdsInfoRespDTO gdsInfoRespDTO = iGdsInfoRSV.queryGdsInfoDetails(gdsInfoReqDTO);
        } catch (Exception e) {
            log.error("查询商品详情异常：",e);
        }


        return "/gds/gdsInfo";
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
            log.error("查询商品列表信息异常：",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询商品列表信息异常!");
        }
        ajaxJson.setSuccess(true);
        ajaxJson.setObj(gdsInfoRespDTOList);

        return ajaxJson;
    }

}
