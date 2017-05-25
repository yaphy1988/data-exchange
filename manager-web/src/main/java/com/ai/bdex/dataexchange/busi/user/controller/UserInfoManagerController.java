package com.ai.bdex.dataexchange.busi.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.busi.base.entity.BaseAdminAreaInfoVO;
import com.ai.bdex.dataexchange.busi.user.entity.InvoiceTaxVO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IBaseAdminAreaRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.user.entity.AuthStaffVO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthStaffRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.SystemConfUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping(value="/infomanager")
public class UserInfoManagerController {

	private static final Logger log = LoggerFactory.getLogger(UserInfoManagerController.class);

	@DubboConsumer
	private IAuthStaffRSV iAuthStaffRSV;

	@DubboConsumer
	private IChnlInvoiceTaxRSV iChnlInvoiceTaxRSV;

	@DubboConsumer
	private IBaseAdminAreaRSV iBaseAdminAreaRSV;
	
	@RequestMapping(value="/pageInit")
	public String pageInit(Model model,HttpSession session,HttpServletRequest request) throws Exception{
		String staffId = StaffUtil.getStaffVO(session).getStaffId();
		//获取用户信息
		AuthStaffDTO input = new AuthStaffDTO();
		input.setStaffId(staffId);
		AuthStaffDTO authInfo = iAuthStaffRSV.findAuthStaffInfo(input);

		InvoiceTaxVO vodata = new InvoiceTaxVO();
		AuthStaffVO data = new AuthStaffVO();
		if(authInfo!=null){
			BeanUtils.copyProperties(authInfo, data);
			String vfsId = data.getHeadVfsid();
			if(!StringUtil.isBlank(vfsId)){
				data.setHeadSrc(ImageUtil.getImageUrl(vfsId+"_80x80!"));
			}

			ReqInvoiceTaxDTO invoiceVo = new ReqInvoiceTaxDTO();
			invoiceVo.setStaffId(staffId);
			List<ChnlInvoiceTaxDTO> datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(invoiceVo);

			if(datas != null && datas.size()>0) {
				BeanUtils.copyProperties(datas.get(0), vodata);
				if (!StringUtil.isBlank(vodata.getVfsId1())) {
					vodata.setPicSrc(ImageUtil.getImageUrl(vodata.getVfsId1() + "_80x80!"));
				}

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
			}
		}else{
			data.setStaffId(staffId);
		}

		//返回数据
		model.addAttribute("vodata", vodata);
		model.addAttribute("userinfo", data);
		
		return "personalCenter/userinfo";
	}
	
	/**
	 * 修改用户信息
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> modify(Model model,AuthStaffVO vo,HttpSession session,InvoiceTaxVO voData){
		Map<String,Object> rMap = new HashMap<String,Object>();
		AuthStaffDTO input = new AuthStaffDTO();
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		try {
			BeanUtils.copyProperties(vo, input);
			input.setStaffId(staffId);
			iAuthStaffRSV.updateAuthStaffInfo(input);

			//查询是否有审核记录，如果有就更新
			ReqInvoiceTaxDTO voInput = new ReqInvoiceTaxDTO();
			voInput.setStaffId(staffId);
			List<ChnlInvoiceTaxDTO> datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(voInput);
			if(datas!=null&&datas.size()>0){

				ChnlInvoiceTaxDTO voInfo = new ChnlInvoiceTaxDTO();
				BeanUtils.copyProperties(voData, voInfo);
				voInfo.setStaffId(staffId);
				voInfo.setStatus(null);

				iChnlInvoiceTaxRSV.updateCheckInfo(voInfo);
			}

			rMap.put("success", true);
			rMap.put("msg", "修改成功！");
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 获取手机号
	 */
	@RequestMapping(value="/getphone",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getphone(Model model,HttpSession session){
		Map<String,Object> rMap = new HashMap<String,Object>();
		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		AuthStaffDTO input = new AuthStaffDTO();
		input.setStaffId(staffId);
		try {
			AuthStaffDTO result = this.iAuthStaffRSV.findAuthStaffInfo(input);
			if(result!=null){
				rMap.put("success", true);
				rMap.put("phoneNo", result.getSerialNumber());
			}
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 修改用户手机号
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value="/updatephone",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updatephone(Model model,String phoneNo,HttpSession session,String smsCode){
		Map<String,Object> rMap = new HashMap<String,Object>();

		String staffId = StaffUtil.getStaffId(session);
		if(StringUtil.isBlank(staffId)){
			rMap.put("success", false);
			rMap.put("msg", "请先登录！");
			return rMap;
		}
		if(!isMobile(phoneNo)){
			rMap.put("success", false);
			rMap.put("msg", "手机号格式不正确！");
			return rMap;
		}

		String oldphoneNo = "";
		AuthStaffDTO input = new AuthStaffDTO();
		try {
			input.setStaffId(staffId);
			AuthStaffDTO staffInfo = this.iAuthStaffRSV.findAuthStaffInfo(input);
			if (staffInfo != null) {
				oldphoneNo = staffInfo.getSerialNumber();
			}
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", "获取旧手机号失败，请稍后再试");
			return rMap;
		}
		//判断是否旧手机验证通过，且新手机验证通过
		Object oldPhoneNoVerify = session.getAttribute("SMS_CHANGPHONE_VERIFY_1"+oldphoneNo);
		if(!"true".equals(oldPhoneNoVerify)){
			rMap.put("success", false);
			rMap.put("msg", "旧手机验证失败，请重新修改");
			return rMap;
		}
		//新手机验证
		Object seccode = CacheUtil.getItem("SMS_CHANGPHONE_EXPIRY_2"+phoneNo);
		if(seccode == null || seccode.toString().equals(smsCode) == false){
			rMap.put("success", false);
			rMap.put("msg", "手机验证错误");
			return rMap;
		}

		try {
			input.setSerialNumber(phoneNo);
			input.setStaffId(staffId);
			iAuthStaffRSV.updateMobilePhone(input);
			rMap.put("success", true);
			rMap.put("msg", "修改成功！");

			CacheUtil.delItem("SMS_CHANGPHONE_EXPIRY_2"+phoneNo);
			session.removeAttribute("SMS_CHANGPHONE_VERIFY_1"+oldphoneNo);
		} catch (BusinessException e) {
			rMap.put("success", false);
			rMap.put("msg", e.getMessage());
		}
		return rMap;
	}
	
	/**
	 * 校验手机号码
	 * @param mobileNo
	 * @return
	 */
	private boolean isMobile(String mobileNo) {
		if (!StringUtil.isBlank(mobileNo)) {
			 String reg = "^1[34578]\\d{9}$";//13.14.15.17.18这些号段
		     return Pattern.compile(reg).matcher(mobileNo).find();
		}

		return false;
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
}
