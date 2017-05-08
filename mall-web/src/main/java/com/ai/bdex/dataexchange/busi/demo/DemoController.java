package com.ai.bdex.dataexchange.busi.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiongqian on 2017/5/5.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/init")
    public String init(Model model){

        return "/demo/demo";
    }
}
