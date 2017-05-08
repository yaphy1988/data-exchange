package com.ai.bdex.dataexchange.busi.api.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.gds.entity.AipServiceInfoVO;
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
        //判断是否是录入界面
        if (StringUtil.isBlank(serviceId) && StringUtil.isBlank(version)){
            return mv;
        }

        try {
            AipServiceInfoDTO aipServiceInfoDTO= iAipServiceInfoRSV.selectServiceByPk(serviceId,version);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mv;
    }
}
