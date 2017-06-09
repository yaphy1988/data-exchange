package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.entity.AipServiceCountQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenjy on 2017/6/6.
 */
@Controller
@RequestMapping("/reportdata")
public class ApiServiceCountController {

    @Autowired
    private IReportSV reportSV;

    private final String API_COUNT_KEY = "ApiServiceCountReport";

    @RequestMapping("/apiservice")
    public String queryUserRegister(Model model,HttpServletRequest request) throws Exception{
        return "report/api_service_count :: #table_content";
    }

    @RequestMapping("/getServiceCount")
    @ResponseBody
    public Object getApiServiceCountData(Model model, HttpServletRequest request){
        AipServiceCountQueryInfo queryInfo = new AipServiceCountQueryInfo();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNo = request.getParameter("pageindex");
        String pageSize = request.getParameter("pageSize");
        String serviceNameKey = request.getParameter("serviceNameKey");
        String queryStatus = request.getParameter("queryStatus");
        if (StringUtils.isNotBlank(pageNo)){
            queryInfo.setPageNo(Integer.valueOf(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)){
            queryInfo.setPageSize(Integer.valueOf(pageSize));
        }
        if (StringUtils.isNotBlank(startDate)){
            queryInfo.setStartTime(DateUtil.parse(startDate));
        }
        if (StringUtils.isNotBlank(endDate)){
            queryInfo.setEndTime(DateUtil.parse(endDate));
        }
        if (StringUtils.isNotBlank(serviceNameKey)){
            queryInfo.setServiceName(serviceNameKey);
        }
        if (StringUtils.isNotBlank(queryStatus)){
            queryInfo.setQueryStatus(queryStatus);
        }
        PageInfo<AipServiceCountQueryInfo> page = reportSV.getRePortData(API_COUNT_KEY,queryInfo);
        return page;
    }


}
