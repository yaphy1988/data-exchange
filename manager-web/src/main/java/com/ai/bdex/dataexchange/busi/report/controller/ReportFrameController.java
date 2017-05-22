package com.ai.bdex.dataexchange.busi.report.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping(value="/report")
public class ReportFrameController {
    private static final Logger logger = LoggerFactory.getLogger(ReportFrameController.class);

    @RequestMapping(value="/{reportId}")
    public String generateReportTemplate(Model model, @PathVariable String reportId) throws Exception{
        model.addAttribute("reportId",reportId);
        logger.info("请求报表，reportId=" + reportId);
        return "report/report_frame";
    }
}
