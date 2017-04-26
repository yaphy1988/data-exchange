package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ai.bdex.dataexchange.busi.user.entity.InvoiceTaxVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping(value="/authenapply")
public class AuthenApplyController {
	private static final Logger log = LoggerFactory.getLogger(AuthenApplyController.class);
	
	@DubboConsumer
	private IChnlInvoiceTaxRSV iChnlInvoiceTaxRSV;
	
	/**
	 * 用户提交审核页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/userinfo")
	public String userinfo(Model model,HttpSession session,String type) throws Exception{
		ReqInvoiceTaxDTO input = new ReqInvoiceTaxDTO();
		String staffId = StaffUtil.getStaffVO(session).getStaffId();
		if(StringUtil.isBlank(staffId)){
			return "redirect:/login/pageInit";
		}
		input.setStaffId(staffId);
		List<ChnlInvoiceTaxDTO> datas = null;
		InvoiceTaxVO vodata = new InvoiceTaxVO();
		input.setStatus("10");
		datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
		if(datas!=null&&datas.size()>0){
			BeanUtils.copyProperties(datas.get(0), vodata);
			if(!StringUtil.isBlank(vodata.getVfsId1())){
//				vodata.setPicSrc(ImageUtil.getImageUrl(vo.getVfsId1()+"_80x80!"));
			}
			model.addAttribute("data", vodata);
			model.addAttribute("status","10");
			model.addAttribute("msg", "正在认证中，请耐心等待！");
			//有待审核的数据
			return "personalCenter/company_approve_details";
		}
		input.setStatus("20");
		datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
		if(datas!=null&&datas.size()>0){
			BeanUtils.copyProperties(datas.get(0), vodata);
			if(!StringUtil.isBlank(vodata.getVfsId1())){
//				vodata.setPicSrc(ImageUtil.getImageUrl(vo.getVfsId1()+"_80x80!"));
			}
			model.addAttribute("data", vodata);
			model.addAttribute("status","20");
			model.addAttribute("msg", "恭喜你，已认证成功！");
			return "personalCenter/company_approve_details";
		}
		if(!"reapply".equals(type)){
			input.setStatus("30");
			datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
			if(datas!=null&&datas.size()>0){
				BeanUtils.copyProperties(datas.get(0), vodata);
				if(!StringUtil.isBlank(vodata.getVfsId1())){
//					vodata.setPicSrc(ImageUtil.getImageUrl(vo.getVfsId1()+"_80x80!"));
				}
				model.addAttribute("data", vodata);
				model.addAttribute("status","30");
				model.addAttribute("msg", "认证失败，原因为审核不通过！");
				return "personalCenter/company_approve_details";
			}
		}		
		return "personalCenter/company_approve";
	}
	
	/**
	 * 提交认证审核
	 * @return
	 */
	@RequestMapping(value="/saveauthen")
	public Map<String,Object> saveAuthenInfo(Model model,HttpSession session,InvoiceTaxVO vo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			//查询是否有审核记录，如果有，就更新，没有就新增
			ReqInvoiceTaxDTO input = new ReqInvoiceTaxDTO();
			String staffId = StaffUtil.getStaffVO(session).getStaffId();
			if(StringUtil.isBlank(staffId)){
				rMap.put("success", false);
				rMap.put("msg", "提交审核异常，用户未登录！");
				return rMap;
			}
			ChnlInvoiceTaxDTO info = new ChnlInvoiceTaxDTO();
			BeanUtils.copyProperties(vo, info);		
			info.setStaffId(staffId);
			input.setStaffId(staffId);
			List<ChnlInvoiceTaxDTO> datas = null;
			input.setStatus("10");
			datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
			if(datas!=null&&datas.size()>0){
				rMap.put("success", false);
				rMap.put("msg", "提交审核异常，您的账号已经有待审核的记录！");
				return rMap;
			}
			input.setStatus("20");
			datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
			if(datas!=null&&datas.size()>0){
				rMap.put("success", false);
				rMap.put("msg", "提交审核异常，您的账号已经审核认证成功，无须重新认证！");
				return rMap;
			}
			input.setStatus("30");
			datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
			if(datas!=null&&datas.size()>0){
				info.setStatus("10");//待审核
				iChnlInvoiceTaxRSV.updateCheckInfo(info);
				rMap.put("success", true);
				return rMap;
			}	
			info.setStatus("10");//待审核
			iChnlInvoiceTaxRSV.saveInvoiceTax(info);
			rMap.put("success", true);
			rMap.put("msg", "提交成功");
		} catch (BusinessException e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
}
