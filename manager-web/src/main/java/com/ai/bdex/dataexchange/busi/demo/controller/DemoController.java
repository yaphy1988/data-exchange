package com.ai.bdex.dataexchange.busi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/init")
    public String init(Model model){
        model.addAttribute("username","齐天大圣");
        return "/demo/demo";
    }
}
