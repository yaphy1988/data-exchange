package com.ai.bdex.dataexchange.report.controller;

import java.util.Date;

import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.github.pagehelper.Page;


/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping("/reportdata")
public class UserStatisticController {
    private Logger logger = LoggerFactory.getLogger(UserStatisticController.class);

    @Autowired
    private IReportSV reportSV;

    @RequestMapping("/userregister")
    public String queryUserRegister(Model model) throws Exception{
        //查询条件
        UserStatisticQueryInfo queryInfo = new UserStatisticQueryInfo();
        queryInfo.setPageSize(20);
        queryInfo.setPageNo(1);
        Date now = new Date();
        queryInfo.setStartTime(now);
        //报表ID
        String reportId = "AuthStaffSign_staffSignReport";
        //查询报表数据
        Page<AuthStaffSign> staffSignPages = reportSV.getRePortData(reportId,queryInfo);

        model.addAttribute("hello","报表");
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_register");
        return "report/user_register :: #table_content";
    }
}
