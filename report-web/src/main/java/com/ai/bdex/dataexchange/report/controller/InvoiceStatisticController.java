package com.ai.bdex.dataexchange.report.controller;

import java.util.Date;

import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.bdex.dataexchange.report.service.interfaces.IRowForMat;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

 import com.ai.bdex.dataexchange.report.entity.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping("/reportdata")
public class InvoiceStatisticController {
    private Logger logger = LoggerFactory.getLogger(UserStatisticController.class);

    @Autowired
    private IReportSV reportSV;

    @RequestMapping("/userregister")
    public String queryUserRegister(Model model,HttpServletRequest request) throws Exception{
        return "report/invoice_tax_register :: #table_content";
    }
    @RequestMapping(value = "/getInvocieDetailData")
    @ResponseBody
    public Object getInvocieDetailData(HttpServletRequest request) throws Exception{
        //查询条件
        OrdInvoiceTaxReqDTO queryInfo = this.getQueryParams(request);
        //报表ID
        String reportId = "invoice_DetailReport";
        //查询报表数据
        PageInfo<OrdInvoiceTaxReqDTO> page = reportSV.getRePortData(reportId,queryInfo );
        return page;
    }
    private OrdInvoiceTaxReqDTO getQueryParams(HttpServletRequest request){
        OrdInvoiceTaxReqDTO vo = new OrdInvoiceTaxReqDTO();
        vo.setPageNo(1);
        vo.setPageSize(20);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNo = request.getParameter("pageindex");
        String pageSize = request.getParameter("pageSize");
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
        }catch (Exception e){
        }
        return vo;
    }
}

