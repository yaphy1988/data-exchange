package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IUserStatisticSV;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/reportdata")
public class OrderStatisticController {
    private Logger logger = LoggerFactory.getLogger(OrderStatisticController.class);

    private IUserStatisticSV userStatisticSV;

    @RequestMapping("/orderstatistics")
    public String queryUserRegister(Model model) throws Exception{



        model.addAttribute("hello","报表");
//        model.addAttribute(staffSignPages);
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/order_register");
        return "report/order_register";
    }
}
