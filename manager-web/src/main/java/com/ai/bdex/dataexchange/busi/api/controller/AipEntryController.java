package com.ai.bdex.dataexchange.busi.api.controller;

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
@RequestMapping(value = "/apiEntry")
public class AipEntryController {

    private final static Logger log = LoggerFactory.getLogger(AipEntryController.class);

    @RequestMapping(value = "/baseInfoInit")
    public ModelAndView baseInfoInit(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceId){
        String viewName = "";
        ModelAndView mv = new ModelAndView(viewName);



        return mv;
    }
}
