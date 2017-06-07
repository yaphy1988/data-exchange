package com.ai.bdex.dataexchange.report.controller;

import com.ai.bdex.dataexchange.report.entity.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * Created by yaphy on 2017/5/20.
 */
@Controller
@RequestMapping("/reportdata")
public class InvoiceStatisticController {
    private Logger logger = LoggerFactory.getLogger(UserStatisticController.class);

    @Autowired
    private IReportSV reportSV;

    @RequestMapping("/invoicestatistic")
    public String queryUserRegister(Model model,HttpServletRequest request) throws Exception{
        return "report/invoice_tax_register :: #table_content";
    }
    @RequestMapping(value = "/getInvocieDetailData")
    @ResponseBody
    public Object getInvocieDetailData(HttpServletRequest request) throws Exception{
        //查询条件
        OrdInvoiceTaxReqDTO queryInfo = this.getQueryParams(request);
        //报表ID
        String reportId = "invoice_DetailReport_query";
        //查询报表数据
        PageInfo<OrdInvoiceTaxReqDTO> page = reportSV.getRePortData(reportId,queryInfo );
        List<OrdInvoiceTaxReqDTO> ordList = page.getList();
        long totalcount = page.getTotal();
        List<OrdInvoiceTaxReqDTO> rsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ordList)){
            for(OrdInvoiceTaxReqDTO  source : ordList){
                OrdInvoiceTaxReqDTO target = new OrdInvoiceTaxReqDTO();
                BeanUtils.copyProperties(source,target);
                target.setApplyTime(DateUtil.formatTime(source.getCreateTime()));
                target.setApplyMoneyShow("￥"+source.getApplyMoney()/100);
                rsList.add(target);
            }
        }
        page.setList(rsList);
        page.setTotal(totalcount);

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

