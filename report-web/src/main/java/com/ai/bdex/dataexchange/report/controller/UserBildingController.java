package com.ai.bdex.dataexchange.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.bdex.dataexchange.report.service.interfaces.IReportSV;
import com.ai.bdex.dataexchange.report.service.interfaces.IRowForMat;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.bdex.dataexchange.util.ThymeleafToolsUtil;
import com.ai.paas.utils.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.report.dao.model.UserBildInfo;
import com.ai.bdex.dataexchange.report.entity.UserBildingQueryInfo;
import com.github.pagehelper.PageInfo;


/**
 * Created by zouwj on 2017/6/6.
 */
@Controller
@RequestMapping("/reportdata")
public class UserBildingController {
    private Logger logger = LoggerFactory.getLogger(UserBildingController.class);

    @Autowired
    private IReportSV reportSV;

    @RequestMapping(value = "/userbilling")
    public String queryUserRegister(Model model) throws Exception{
        model.addAttribute("hello","报表");
        logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_billing");
        return "report/user_billing :: #table_content";
    }
    
    /**
     * 用户对账单统计
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/userbildingquery")
    @ResponseBody
    public Object queryUserBilding(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //查询条件
    	UserBildingQueryInfo queryInfo = new UserBildingQueryInfo();
    	String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
    	String pageNo = request.getParameter("pageindex");
        String pageSize = request.getParameter("pageSize");
        String userId = request.getParameter("userId");
        //查询报表数据
        PageInfo<UserBildInfo> pageInfo = new PageInfo<UserBildInfo>();
		try {
			if (!StringUtil.isBlank(pageNo)){
				queryInfo.setPageNo(Integer.valueOf(pageNo));
			}
			if (!StringUtil.isBlank(pageSize)){
				queryInfo.setPageSize(Integer.valueOf(pageSize));
			}
			if (!StringUtil.isBlank(startDate)){
				queryInfo.setStartTime(DateUtil.parse(startDate));
	        }
	        if (!StringUtil.isBlank(endDate)){
	        	queryInfo.setEndTime(DateUtil.parse(endDate));
	        }
	        if (!StringUtil.isBlank(userId)){
	        	queryInfo.setUserId(userId);
	        }	        
			//报表ID
			String reportId = "UserBinging_InfoReport";
			pageInfo = reportSV.getRePortData(reportId,queryInfo);
		} catch (Exception e) {
			logger.error("用户对账单查询异常："+e.getMessage());
		}
        
        return pageInfo;
    }
    
    /**
     * 用户对账单明细
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/userbildingdetailquery")
    public String queryUserBilddatil(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //查询条件
    	UserBildingQueryInfo queryInfo = new UserBildingQueryInfo();
    	String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
    	String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        String userId = request.getParameter("userId");
        String serviceId = request.getParameter("serviceId");
        //查询报表数据
        PageInfo<UserBildInfo> pageInfo = new PageInfo<UserBildInfo>();
		try {
			if (!StringUtil.isBlank(pageNo)){
				queryInfo.setPageNo(Integer.valueOf(pageNo));
			}
			if (!StringUtil.isBlank(pageSize)){
				queryInfo.setPageSize(Integer.valueOf(pageSize));
			}
			if (!StringUtil.isBlank(startDate)){
				queryInfo.setStartTime(DateUtil.parse(startDate));
	        }
	        if (!StringUtil.isBlank(endDate)){
	        	queryInfo.setEndTime(DateUtil.parse(endDate));
	        }
	        if (!StringUtil.isBlank(userId)){
	        	queryInfo.setUserId(userId);
	        }
	        if (!StringUtil.isBlank(serviceId)){
	        	queryInfo.setServiceId(serviceId);
	        }	
			//报表ID
			String reportId = "UserBinging_DetailReport";
			pageInfo = reportSV.getRePortData(reportId,queryInfo,new IRowForMat<UserBildInfo>() {
	            @Override
	            public void forMat(UserBildInfo vo) {
	            	ThymeleafToolsUtil tool = new ThymeleafToolsUtil();
	                int unitPrice = vo.getUnitPrice();
	                String priceText = tool.formatLiToMoneyClean(unitPrice);
	                vo.setPriceText(priceText);
	            }
	        });
		} catch (Exception e) {
			logger.error("用户对账单查询异常："+e.getMessage());
		}
		request.setAttribute("pageInfo", pageInfo);
		logger.info("报表数据请求已经到达report-web，返回页面模板：report/user_billing");
        return "report/user_billing :: #staffList";
    }
}
