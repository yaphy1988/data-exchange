package com.ai.bdex.dataexchange.busi.api.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientInfoRSV;
import com.ai.bdex.dataexchange.busi.api.entity.AipClientInfoVo;
import com.ai.bdex.dataexchange.common.AjaxJson;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@Controller
@RequestMapping("/apiKeyManage")
public class ApiKeyManageController {
	 private final static Logger log = LoggerFactory.getLogger(AipEntryController.class);
	 @DubboConsumer(timeout=3000)
	 private IAipClientInfoRSV aipClientInfoRSV;
	 
	 /**
	  * 初始化页面
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value="init")
	 public ModelAndView initPage(HttpServletRequest request, HttpServletResponse response){
		 String viewName="apikey_manage";
		 ModelAndView mv = new ModelAndView(viewName);
		 AipClientInfoReqDTO dto=new AipClientInfoReqDTO();
		 dto.setPageNo(1);
		 dto.setPageSize(10);
		 String status="1";//ALL:所有状态，0失效，1有效，2冻结
		 try{		
			 List<String> stateList=new ArrayList<String>();
			 stateList.add(status);
			 dto.setStatusList(stateList);					
			 PageResponseDTO<AipClientInfoDTO> pageInfo=aipClientInfoRSV.getAipClientInfoPage(dto);
			 if(null==pageInfo){
				 pageInfo=new PageResponseDTO<AipClientInfoDTO>();
			 }
			 mv.addObject("pageInfo", pageInfo);
		 }catch(Exception e){
			 log.error("", e);
		 }
		 return mv;
		  
	 }
	 /**
	  * 分页查询
	  * @param model
	  * @param vo
	  * @return
	  */
	 @RequestMapping(value="/getClientInfoPage")
	 public String getClientInfoPage(Model model, AipClientInfoVo vo){
		 String viewPartName="apikey_manage :: #tab01";
		 try{
			 AipClientInfoReqDTO dto=new AipClientInfoReqDTO();
			 ObjectCopyUtil.copyObjValue(vo, dto, null, false);
			 String status=vo.getStatus();//ALL:所有状态，0失效，1有效，2冻结
			 List<String> stateList=new ArrayList<String>();
			 if(!StringUtil.isBlank(status)){
				 if("ALL".equals(status)){
					 stateList.add("0");
					 stateList.add("1");
					 stateList.add("2");
				 }else{
					 stateList.add(status);
				 }
			 }
			 dto.setStatusList(stateList);
			 PageResponseDTO<AipClientInfoDTO> pageInfo=aipClientInfoRSV.getAipClientInfoPage(dto);
			 if(null==pageInfo){
				 pageInfo=new PageResponseDTO<AipClientInfoDTO>();
			 }
			 model.addAttribute("pageInfo", pageInfo);
		 }catch(Exception e){
			 
		 }
		 return viewPartName;
	 }
	 
	 @RequestMapping(value="/updateStateByClentId")
	 public @ResponseBody AjaxJson updateStateByClentId(AipClientInfoVo vo){
		 int cnt=0;
		 AjaxJson ajx=new AjaxJson();		 
		 try{
			String clientId=vo.getClientId();
			String status=vo.getStatus();
			if(!StringUtil.isBlank(clientId)){
				cnt=aipClientInfoRSV.updateStatus(clientId, status);
				if(cnt==1){
					 ajx.setSuccess(true);
					 ajx.setMsg("客户端编码:"+clientId+"更新状态成功");
				}else{
					 ajx.setSuccess(true);
					 ajx.setMsg("客户端编码:"+clientId+"更新状态失败");
				}
			}else{
				 ajx.setSuccess(false);
				 ajx.setMsg("客户端编码不能为空");
			}			
		 }catch(Exception e){
			 log.error("",e);
			 ajx.setSuccess(false);
			 ajx.setMsg(vo.getClientId()+"更新状态出现异常，请联系管理员");
		 }
		 return ajx;
	 }
	 
	 @RequestMapping(value="/batchUpdateStateByClentId")
	 public @ResponseBody AjaxJson batchUpdateStateByClentId(HttpServletRequest request, HttpServletResponse response){
		 int cnt=0;
		 AjaxJson ajx=new AjaxJson();		 
		 try{
			String clientIdes=request.getParameter("ClientIdList");
			List<String> clientIdList=null;
			String status=request.getParameter("status");
			if(!CollectionUtil.isEmpty(clientIdList)){
				cnt=aipClientInfoRSV.batchUpdateStatus(clientIdList, status);
				if(cnt>0){
					 ajx.setSuccess(true);
					 ajx.setMsg("批量更新状态成功");
				}else{
					 ajx.setSuccess(true);
					 ajx.setMsg("批量更新状态失败");
				}
			}else{
				 ajx.setSuccess(false);
				 ajx.setMsg("客户端编码不能为空");
			}			
		 }catch(Exception e){
			 log.error("",e);
			 ajx.setSuccess(false);
			 ajx.setMsg("批量更新状态出现异常，请联系管理员");
		 }
		 return ajx;
	 }
	 
	 @RequestMapping(value="/createAipClienter")
	 public @ResponseBody AjaxJson createAipClienter(AipClientInfoVo vo){
		 int cnt=0;
		 AjaxJson ajx=new AjaxJson();		 
		 try{
			String clientId=vo.getClientId();		
			if(!StringUtil.isBlank(clientId)){
				//查询clientId是否已存在
				AipClientInfoDTO old=aipClientInfoRSV.getAipClientInfoByKey(clientId);
				if(null==old){
					AipClientInfoDTO dto=new AipClientInfoDTO();
					ObjectCopyUtil.copyObjValue(vo, dto, null, false);
					dto.setCreateStaff("");
					Timestamp nowTime=new Timestamp(System.currentTimeMillis());
					dto.setCreateTime(nowTime);
					dto.setUpdateStaff("");
					dto.setUpdateTime(nowTime);
					if(StringUtil.isBlank(vo.getStatus())){
						dto.setStatus("2");//冻结
					}
					//生成密码
					String password=null;
					dto.setPassword(password);
					//生成密钥
					String clientSecret=null;
					dto.setClientSecret(clientSecret);
					
					cnt=aipClientInfoRSV.insertAipClientInfo(dto);
					if(cnt==1){
						 ajx.setSuccess(true);
						 ajx.setMsg("客户端编码:"+clientId+"新增成功");
					}else{
						 ajx.setSuccess(true);
						 ajx.setMsg("客户端编码:"+clientId+"新增失败");
					}
				}else{
					 ajx.setSuccess(false);
					 ajx.setMsg(clientId+"客户端编码已存在，请修改");
				}
			}else{
				 ajx.setSuccess(false);
				 ajx.setMsg("客户端编码不能为空");
			}
		 }catch(Exception e){
			 log.error("",e);
			 ajx.setSuccess(false);
			 ajx.setMsg(vo.getClientId()+"新增出现异常，请联系管理员");
		 }
		 return ajx;
	 }
	 
	 @RequestMapping(value="/editAipClientInfo")
	 public @ResponseBody AjaxJson editAipClientInfo(AipClientInfoVo vo){
		 int cnt=0;
		 AjaxJson ajx=new AjaxJson();		 
		 try{
			String clientId=vo.getClientId();		
			if(!StringUtil.isBlank(clientId)){		
				AipClientInfoDTO dto=new AipClientInfoDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);					
				Timestamp nowTime=new Timestamp(System.currentTimeMillis());
				dto.setClientSecret(null);
				dto.setPassword(null);
				dto.setCreateTime(null);
				dto.setCreateStaff(null);
				//如何取?
				dto.setUpdateStaff("");
				dto.setUpdateTime(nowTime);					
				cnt=aipClientInfoRSV.updateAipClientInfo(dto);
				if(cnt==1){
					 ajx.setSuccess(true);
					 ajx.setMsg("客户端:"+clientId+"保存编辑成功");
				}else{
					 ajx.setSuccess(true);
					 ajx.setMsg("客户端:"+clientId+"保存编辑失败");
				}
			}else{
				 ajx.setSuccess(false);
				 ajx.setMsg(clientId+"客户端编码不能为空");
			}
			
		 }catch(Exception e){
			 log.error("",e);
			 ajx.setSuccess(false);
			 ajx.setMsg(vo.getClientId()+"保存编辑出现异常，请联系管理员");
		 }
		 return ajx;
	 }
}