package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.util.StaffUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.user.entity.InvoiceTaxVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping(value="/authencheck")
public class AuthenCheckController {
	private static final Logger log = LoggerFactory.getLogger(AuthenCheckController.class);
	
	@DubboConsumer
	private IChnlInvoiceTaxRSV iChnlInvoiceTaxRSV;

	/**
	 * 认证审核分页初始化页面
	 * @return
	 */
	@RequestMapping(value="/pageInit")
	public String pageInit(Model model){
		return "company_approve";
	}
	
	/**
	 * 用户提交审核页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/userinfo")
	public String userinfo(Model model,HttpSession session) throws Exception{
		ReqInvoiceTaxDTO input = new ReqInvoiceTaxDTO();
		input.setStaffId(StaffUtil.getStaffVO(session).getStaffId());
		List<ChnlInvoiceTaxDTO> datas = null;
		input.setStatus("10");
		datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
		if(datas!=null&&datas.size()>0){
			model.addAttribute("status","10");
			model.addAttribute("msg", "正在认证中，请耐心等待！");
			//有待审核的数据
			return "authen/resultshow";
		}
		input.setStatus("20");
		datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
		if(datas!=null&&datas.size()>0){
			model.addAttribute("status","20");
			model.addAttribute("msg", "恭喜你，已认证成功！");
			return "authen/resultshow";
		}
		return "authen/userinfo";
	}
	
	/**
	 * 提交认证审核
	 * @return
	 */
	@RequestMapping(value="/saveauthen")
	public Map<String,Object> saveAuthenInfo(Model model,InvoiceTaxVO vo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			ChnlInvoiceTaxDTO info = new ChnlInvoiceTaxDTO();
			BeanUtils.copyProperties(vo, info);
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
	
	/**
	 * 获取审核数据，分页
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/getcheckdata",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getcheckdata(Model model,String status,String pageNo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		ReqInvoiceTaxDTO taxDTO = new ReqInvoiceTaxDTO();
		taxDTO.setStatus(status);
		if(pageNo!=null){
			taxDTO.setPageNo(Integer.parseInt(pageNo));			
		}
		taxDTO.setPageSize(10);
		try {
			PageResponseDTO<ChnlInvoiceTaxDTO> pageInfo = iChnlInvoiceTaxRSV.queryTaxPage(taxDTO);
			rMap.put("success", true);
			rMap.put("data", pageInfo.getResult());
			rMap.put("pageInfo", pageInfo);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 进入审核详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getdetail/{taxid}")
	public String getdetail(Model model,@PathVariable Long taxid)throws Exception{
		ChnlInvoiceTaxDTO dto = iChnlInvoiceTaxRSV.getInvoiveTaxDetail(taxid);
		if(dto!=null){
			InvoiceTaxVO vo = new InvoiceTaxVO();
			BeanUtils.copyProperties(dto, vo);
			model.addAttribute("data", vo);
			if(!StringUtil.isBlank(vo.getVfsId1())){
				vo.setPicSrc(ImageUtil.getImageUrl(vo.getVfsId1()+"_80x80!"));
			}			
		}else{
			throw new Exception("查询数据异常！");
		}

		model.addAttribute("authflag","true");

		return "personalCenter/company_approve_details";
	}
	
	
	/**
	 * 审核操作
	 * @param model
	 * @param status
	 * @param taxId
	 * @return
	 */
	@RequestMapping(value="/docheck",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> docheck(Model model,HttpSession session,String status,Long taxId,String checkDesc){
		Map<String,Object> rMap = new HashMap<String,Object>();
		ChnlInvoiceTaxDTO info = new ChnlInvoiceTaxDTO();
		info.setStatus(status);
		info.setTaxId(taxId);
		info.setUpdateStaff(StaffUtil.getStaffVO(session).getStaffId());
		info.setCheckDesc(checkDesc);
		try {
			iChnlInvoiceTaxRSV.doAuditTax(info);
			rMap.put("success", true);
			rMap.put("msg", "审核成功");
		} catch (BusinessException e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
}
