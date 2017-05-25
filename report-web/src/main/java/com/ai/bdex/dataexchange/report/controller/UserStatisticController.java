package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.dao.model.AuthStaffSign;
import com.ai.bdex.dataexchange.report.entity.UserStatisticQueryInfo;
import com.ai.bdex.dataexchange.report.service.interfaces.IUserStatisticSV;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping("/reportdata")
public class UserStatisticController {
    private Logger logger = LoggerFactory.getLogger(UserStatisticController.class);

    private IUserStatisticSV userStatisticSV;

    @RequestMapping("/userregister")
    public String queryUserRegister(Model model) throws Exception{

        UserStatisticQueryInfo queryInfo = new UserStatisticQueryInfo();
        Date now = new Date();
        queryInfo.setStartTime(now);

        PageInfo<AuthStaffSign> staffSignPages = userStatisticSV.queryAuthStaffSignPageInfo(queryInfo);

        model.addAttribute("hello","报表");
        model.addAttribute(staffSignPages);
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_register");
        return "report/user_register";
    }
}
