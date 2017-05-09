package com.ai.bdex.dataexchange.busi.api.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.api.entity.AipServiceDetailsInfoVO;
import com.ai.bdex.dataexchange.busi.gds.entity.AipServiceInfoVO;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yx on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/aipEntry")
public class AipEntryController {

    private final static Logger log = LoggerFactory.getLogger(AipEntryController.class);

    @DubboConsumer(timeout = 30000)
    private IAipServiceInfoRSV iAipServiceInfoRSV;

    /**
     * 初始化基本信息界面
     * @param request
     * @param response
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/baseInfoInit")
    public ModelAndView baseInfoInit(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceId,@PathVariable String version){
        String viewName = "aip_documnet_deploy";
        ModelAndView mv = new ModelAndView(viewName);
        AipServiceDetailsInfoVO aipServiceDetailsInfoVO = new AipServiceDetailsInfoVO();
        //判断是否是录入界面
        if (StringUtil.isBlank(serviceId) && StringUtil.isBlank(version)){
            return mv;
        }

        //aip基本信息
        try {
            AipServiceInfoDTO aipServiceInfoDTO= iAipServiceInfoRSV.selectServiceByPk(serviceId,version);
            if (aipServiceInfoDTO!=null){
                ObjectCopyUtil.copyObjValue(aipServiceInfoDTO,aipServiceDetailsInfoVO,null,false);
            }
        } catch (Exception e) {
            log.error("查询aip信息异常：",e);
        }

        //入参信息
        try{

        }catch (Exception e){
            log.error("查询aip入参信息异常：",e);
        }


        return mv;
    }
}
