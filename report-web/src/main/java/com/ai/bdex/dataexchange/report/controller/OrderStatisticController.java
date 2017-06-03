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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/reportdata")
public class OrderStatisticController {
    private Logger logger = LoggerFactory.getLogger(OrderStatisticController.class);
    @Autowired
    private IOrderStatisticSV orderStatisticSV;

    @RequestMapping(value = "/orderstatistics")
    public String queryUserRegister(Model model) throws Exception{
        model.addAttribute("hello","报表");
        List<OrdMainInfo> result = new ArrayList<>();
        OrdMainInfo ordMainInfo = new OrdMainInfo();
        ordMainInfo.setOrderId("201834343");
        ordMainInfo.setOrderAmount(23478);
        result.add(ordMainInfo);
        result.add(ordMainInfo);
        model.addAttribute(result);
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/order_register");
        return "report/order_register";
    }
    @RequestMapping(value = "/getjsonData")
    @ResponseBody
    public Object getjsonData(Model model, HttpServletRequest request) throws Exception{
  /*      List<OrdMainInfo> result = new ArrayList<>();
       try {
            OrdMainStatisticQueryInfo ordMainStatisticQueryInfo = new OrdMainStatisticQueryInfo();
            ordMainStatisticQueryInfo.setPageSize(10);
            PageInfo<OrdMainInfo> pageInfo = orderStatisticSV.queryOrdMainInfoPage(ordMainStatisticQueryInfo);
            return pageInfo.getList();
        }catch (Exception e){
            logger.error("订单统计查询异常："+e.getMessage());
        }*/
        String pageNo = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        PageInfo<OrdMainInfo> pageInfo = new PageInfo<>();
        List<OrdMainInfo> result = new ArrayList<>();
        OrdMainInfo ordMainInfo = new OrdMainInfo();
        ordMainInfo.setOrderId("201834343");
        ordMainInfo.setOrderAmount(23478);
        for(int i=0;i<=30;i++){
            result.add(ordMainInfo);
        }
        pageInfo.setTotal(result.size());
        pageInfo.setList(result);
        return pageInfo;
    }

}
