package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


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

        UserStatisticQueryInfo queryInfo = new UserStatisticQueryInfo();
        Date now = new Date();
        queryInfo.setStartTime(now);

        Page<AuthStaffSign> staffSignPages = reportSV.getRePortData("AuthStaffSign_staffSignReport",queryInfo);

        model.addAttribute("hello","报表");
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_register");
        return "report/user_register";
    }
}
