package com.ai.bdex.dataexchange.busi.demo;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by xiongqian on 2017/5/5.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/init")
    public String init(Model model){
        PageResponseDTO<DemoDTO> pageData = new PageResponseDTO<DemoDTO>();
        pageData.setPageSize(10);
        model.addAttribute("pageData",pageData);

        return "/demo/demo";
    }

    @RequestMapping("/loadDemoTable")
    public String loadDemoTable(Model model){

        PageResponseDTO<DemoDTO> pageData = new PageResponseDTO<DemoDTO>();
        DemoDTO vo1 = new DemoDTO();
        vo1.setUserName("小明1");
        vo1.setId(1111);

        DemoDTO vo2 = new DemoDTO();
        vo2.setUserName("小明2");
        vo2.setId(2222);

        DemoDTO vo3 = new DemoDTO();
        vo3.setUserName("小明3");
        vo3.setId(3333);

        pageData.setResult(new ArrayList<>());
        pageData.setCount(100);
        pageData.setPageSize(10);
        pageData.setPageNo(1);

        pageData.getResult().add(vo1);
        pageData.getResult().add(vo2);
        pageData.getResult().add(vo3);

        model.addAttribute("pageData",pageData);

        return "/demo/demo :: #demoTable";
    }

    @RequestMapping("/loadDemoTab")
    public String loadDemoTab(Model model,HttpServletRequest request){

        String id= request.getParameter("id");

        return "/demo/demo :: "+id;
    }

}
