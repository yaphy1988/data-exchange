package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.busi.base.entity.BaseAdminAreaInfoVO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IBaseAdminAreaRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ai.bdex.dataexchange.busi.user.entity.InvoiceTaxVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.SystemConfUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.ai.bdex.dataexchange.util.StaffUtil;

@Controller
@RequestMapping(value="/authenapply")
public class AuthenApplyController {
	private static final Logger log = LoggerFactory.getLogger(AuthenApplyController.class);
	
	@DubboConsumer
	private IChnlInvoiceTaxRSV iChnlInvoiceTaxRSV;

	@DubboConsumer
	private IBaseAdminAreaRSV iBaseAdminAreaRSV;
	
	/**
	 * 用户提交审核页面初始化
	 * @param request
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/userinfo")
	public String userinfo(Model model,HttpServletRequest request,HttpSession session,String type) throws Exception{

		String staffId = StaffUtil.getStaffVO(session).getStaffId();

		//查询用户认证信息
		ReqInvoiceTaxDTO invoiceVo = new ReqInvoiceTaxDTO();
		invoiceVo.setStaffId(staffId);
		List<ChnlInvoiceTaxDTO> datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(invoiceVo);
		InvoiceTaxVO vodata = new InvoiceTaxVO();
		if(datas != null && datas.size()>0){
			BeanUtils.copyProperties(datas.get(0), vodata);
			if(!StringUtil.isBlank(vodata.getVfsId1())){
				vodata.setPicSrc(ImageUtil.getImageUrl(vodata.getVfsId1()+"_80x80!"));

			}

			//返回数据
			model.addAttribute("data", vodata);

			//其他为展示申请信息界面
			if("reapply".equals(type) == false) {
				model.addAttribute("authflag","false");
				return "personalCenter/company_approve_details";
			}
		}else{
			//返回数据
			model.addAttribute("data", vodata);
		}

		//如果是重新申请，则跳转到申请界面
		//查询省份列表
		BaseAdminAreaReqDTO baseAdminAreaReqDTO = new BaseAdminAreaReqDTO();
		baseAdminAreaReqDTO.setStatus("1");
		baseAdminAreaReqDTO.setAreaLevel("10");
		List<BaseAdminAreaInfoVO> provinceList = queryBaseArea(baseAdminAreaReqDTO);
		model.addAttribute("provinceList",provinceList);

		if(StringUtil.isBlank(vodata.getProvinceCode()) == false){
			baseAdminAreaReqDTO.setAreaLevel(null);
			baseAdminAreaReqDTO.setStatus("1");
			baseAdminAreaReqDTO.setParentAreaCode(vodata.getProvinceCode());
			List<BaseAdminAreaInfoVO> cityList = queryBaseArea(baseAdminAreaReqDTO);
			model.addAttribute("cityList",cityList);

			if(StringUtil.isBlank(vodata.getCityCode()) == false){
				baseAdminAreaReqDTO.setStatus("1");
				baseAdminAreaReqDTO.setParentAreaCode(vodata.getCityCode());
				List<BaseAdminAreaInfoVO> countryList = queryBaseArea(baseAdminAreaReqDTO);
				model.addAttribute("countryList",countryList);
			}
		}

		return "personalCenter/company_approve";
	}
	
	/**
	 * 提交认证审核
	 * @return
	 */
	@RequestMapping(value="/saveauthen",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveAuthenInfo(Model model,HttpSession session,InvoiceTaxVO vo){
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {

			String staffId = StaffUtil.getStaffVO(session).getStaffId();

			//界面提交的认证信息
			ChnlInvoiceTaxDTO info = new ChnlInvoiceTaxDTO();
			BeanUtils.copyProperties(vo, info);
			info.setStaffId(staffId);
			info.setStatus("10");//待审核

			//查询是否有审核记录，如果有，就更新，没有就新增
			ReqInvoiceTaxDTO input = new ReqInvoiceTaxDTO();
			input.setStaffId(staffId);
			List<ChnlInvoiceTaxDTO> datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
			if(datas!=null&&datas.size()>0){
				ChnlInvoiceTaxDTO oldData = datas.get(0);
				if("20".equals(oldData.getStatus())){
					rMap.put("success", false);
					rMap.put("msg", "您的账号已经审核认证成功，不能再提交认证！");
					return rMap;
				}else{
					//更新
					iChnlInvoiceTaxRSV.updateCheckInfo(info);
				}
			}else{
				//新增
				iChnlInvoiceTaxRSV.saveInvoiceTax(info);
			}
			rMap.put("success", true);
			rMap.put("msg", "提交成功");

		} catch (BusinessException e) {
			log.error(e.getMessage());
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}

		return rMap;
	}

	private List<BaseAdminAreaInfoVO> queryBaseArea(BaseAdminAreaReqDTO baseAdminAreaReqDTO){
		List<BaseAdminAreaInfoVO> list = new ArrayList<BaseAdminAreaInfoVO>();
		try {
			List<BaseAdminAreaRespDTO> baseAdminAreaRespDTOList = iBaseAdminAreaRSV.queryBaseAdminAreaList(baseAdminAreaReqDTO);
			if (!CollectionUtil.isEmpty(baseAdminAreaRespDTOList)){
				for (BaseAdminAreaRespDTO baseAdminAreaRespDTO : baseAdminAreaRespDTOList){
					BaseAdminAreaInfoVO baseAdminAreaInfoVO = new BaseAdminAreaInfoVO();
					ObjectCopyUtil.copyObjValue(baseAdminAreaRespDTO,baseAdminAreaInfoVO,null,false);
					list.add(baseAdminAreaInfoVO);
				}
			}
		}catch (Exception e){
			log.error("查询区域列表信息异常",e);
		}

		return list;
	}

	@RequestMapping(value = "/queryAreaList")
	public String queryAreaList(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		String contentId = request.getParameter("contentId");
		List<BaseAdminAreaInfoVO> baseAdminAreaInfoVOList = new ArrayList<BaseAdminAreaInfoVO>();
		if (!StringUtil.isBlank(code)){
			BaseAdminAreaReqDTO baseAdminAreaReqDTO = new BaseAdminAreaReqDTO();
			baseAdminAreaReqDTO.setParentAreaCode(code);
			baseAdminAreaReqDTO.setStatus("1");
			baseAdminAreaInfoVOList = queryBaseArea(baseAdminAreaReqDTO);
		}
//		else{
//			BaseAdminAreaInfoVO baseAdminAreaInfoVO = new BaseAdminAreaInfoVO();
//			baseAdminAreaInfoVO.setAreaName("请选择");
//			baseAdminAreaInfoVOList.add(baseAdminAreaInfoVO);
//		}
		request.setAttribute(contentId+"List",baseAdminAreaInfoVOList);

		String viewName = "personalCenter/company_approve :: #"+contentId;
		return viewName;
	}
}
