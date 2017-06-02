package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.report.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.report.entity.OrdMainStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IOrderStatisticSV;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reportdata")
public class OrderStatisticController {
    private Logger logger = LoggerFactory.getLogger(OrderStatisticController.class);
    @Autowired
    private IOrderStatisticSV orderStatisticSV;

    @RequestMapping("/orderstatistics")
    public String queryUserRegister(Model model) throws Exception{
        OrdMainStatisticQueryInfo ordMainStatisticQueryInfo = new OrdMainStatisticQueryInfo();
        ordMainStatisticQueryInfo.setPageSize(10);
        PageInfo<OrdMainInfo> pageInfo = orderStatisticSV.queryOrdMainInfoPage(ordMainStatisticQueryInfo);

        model.addAttribute("hello","报表");
        model.addAttribute("pageInfo",pageInfo);
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/order_register");
        return "report/order_register";
    }
    @RequestMapping("/orderstest")
    public String orderstest(Model model) throws Exception{

        return "report/order_register";
    }
}
