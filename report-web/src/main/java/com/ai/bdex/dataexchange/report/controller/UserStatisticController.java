package com.ai.bdex.dataexchange.report.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping("/reportdata")
public class UserStatisticController {
    private static Logger logger = LoggerFactory.getLogger(UserStatisticController.class);

    @RequestMapping("/userregister")
    public String queryUserRegister(Model model) throws Exception{
        model.addAttribute("hello","报表");
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_register");
        return "report/user_register";
    }
}
