package com.ai.bdex.dataexchange.report.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yaphy on 2017/5/20.
 */
@RequestMapping("/reportdata")
public class UserStatisticController {

    @RequestMapping("/userregister")
    public String queryUserRegister(Model model) throws Exception{
        model.addAttribute("hello","报表");
        return "report/user_register";
    }
}
