package com.ai.bdex.dataexchange.busi.aip.controller;

import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipProviderServiceMgrRSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yx on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/aipProvider")
public class AipProviderController {

    private final static Logger log = LoggerFactory.getLogger(AipProviderController.class);

    private IAipProviderServiceMgrRSV iAipProviderServiceMgrRSV;

    @RequestMapping(value = "/pageInit")
    public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response){
        String viewName = "goods_list_gys";



        ModelAndView mv = new ModelAndView(viewName);
        return mv;
    }
}
