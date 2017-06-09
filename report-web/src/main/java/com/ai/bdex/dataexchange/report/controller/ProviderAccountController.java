package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.entity.ProviderAccountStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.bdex.dataexchange.report.service.interfaces.IRowForMat;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yx on 2017/6/6.
 */
@Controller
@RequestMapping("/reportdata")
public class ProviderAccountController {

    private final static Logger log = LoggerFactory.getLogger(ProviderAccountController.class);

    @Autowired
    private IReportSV reportSV;

    @RequestMapping(value = "/supplierbilling")
    public String queryUserRegister(Model model) throws Exception{
        model.addAttribute("hello","报表");



        log.info("报表数据请求已经到达report-web，返回页面模板：report/user_billing");
        return "report/provider_account :: #table_content";
    }

    @RequestMapping(value = "/queryBillSummary")
    @ResponseBody
    public Object queryBillSummary(HttpServletRequest request, HttpServletResponse response){
        PageInfo<ProviderAccountStatisticQueryInfo> pageInfo = new PageInfo<ProviderAccountStatisticQueryInfo>();

        ProviderAccountStatisticQueryInfo queryInfo = new ProviderAccountStatisticQueryInfo();
        queryInfo = getQueryParams(request);

        String reportId = "providerAccount_billSummary";

        pageInfo = reportSV.getRePortData(reportId, queryInfo);

        return pageInfo;
    }

    @RequestMapping(value = "/queryBillDetail")
    @ResponseBody
    public Object queryBillDetail(HttpServletRequest request, HttpServletResponse response){
        PageInfo<ProviderAccountStatisticQueryInfo> pageInfo = new PageInfo<ProviderAccountStatisticQueryInfo>();

        ProviderAccountStatisticQueryInfo queryInfo = new ProviderAccountStatisticQueryInfo();
        queryInfo = getQueryParams(request);

        String reportId = "providerAccount_billDetail";

        pageInfo = reportSV.getRePortData(reportId, queryInfo);

        return pageInfo;
    }

    private ProviderAccountStatisticQueryInfo getQueryParams(HttpServletRequest request){
        ProviderAccountStatisticQueryInfo vo = new ProviderAccountStatisticQueryInfo();
        vo.setPageNo(1);
        vo.setPageSize(20);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNo = request.getParameter("pageindex");
        String pageSize = request.getParameter("pageSize");
        String providerId = request.getParameter("providerId");
        try{
            if (!StringUtil.isBlank(pageNo)){
                vo.setPageNo(Integer.valueOf(pageNo));
            }
            if (!StringUtil.isBlank(pageSize)){
                vo.setPageSize(Integer.valueOf(pageSize));
            }
            if (!StringUtil.isBlank(startDate)){
                vo.setStartTime(DateUtil.parse(startDate));
            }
            if (!StringUtil.isBlank(endDate)){
                vo.setStartTime(DateUtil.parse(endDate));
            }
            if (!StringUtil.isBlank(providerId)){
                vo.setProviderId(providerId);
            }
        }catch (Exception e){

        }

        return vo;
    }
}
