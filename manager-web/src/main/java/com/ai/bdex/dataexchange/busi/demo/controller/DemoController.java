package com.ai.bdex.dataexchange.busi.demo.controller;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @DubboConsumer
    private IDemoRSV userCenterDemoRSV;

    @RequestMapping("/init")
    public String init(Model model){
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setAddr("gx");
        String userName = userCenterDemoRSV.callDemoApi(demoDTO);
        model.addAttribute("username",userName);
        return "/demo/demo";
    }
}
