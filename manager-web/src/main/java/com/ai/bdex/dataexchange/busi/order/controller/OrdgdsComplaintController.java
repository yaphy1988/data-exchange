package com.ai.bdex.dataexchange.busi.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.complaint.IOrdComplaintRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 订单商品投诉
 * Description: <br>
 * Date: 2017年5月25日 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author liangwy
 */
@Controller
@RequestMapping(value = "/ordgdsComplaint")
public class OrdgdsComplaintController {
	@DubboConsumer(timeout=3000)
	private IOrdComplaintRSV iOrdComplaintRSV;
	@DubboConsumer(timeout = 300000)
	private IOrderInfoRSV iOrderInfoRSV;
	@DubboConsumer(timeout = 300000)
	private IOrderMainInfoRSV iOrderMainInfoRSV;
	@DubboConsumer
	private IAipCenterDataAccountRSV iAipCenterDataAccountRSV;
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	private final Log logger = LogFactory.getLog(getClass());
	private final static Integer PAGE_SIZE = 10;//页数
	private final static String TMPUSERID = "tmpuser";// 临时用户

	/**
	 * 买家我的投诉入口
	 * @return
	 */
	@RequestMapping(value="/pageInit")
	public ModelAndView pageInit(){
		ModelAndView modelAndView = new ModelAndView("goods_complaint");
		return modelAndView;
	}
	/**
	 * 管理员投诉列表入口
	 * @return
	 */
	@RequestMapping(value="/manager")
	public ModelAndView manager(){
		ModelAndView modelAndView = new ModelAndView("complaint_manager");
		return modelAndView;
	}
	/**
	 * 查询我的订单投诉列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryComplaint")
	public String complaintPageInfo(HttpServletRequest request,Model model,HttpSession session){
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String compStatus = request.getParameter("compStatus");
		String compItem = request.getParameter("compItem");
		OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
		String staffId = StaffUtil.getStaffId(session);
		String viewModel = "goods_complaint :: #tab02";
		if (StaffUtil.isAdmin(StaffUtil.getStaffVO(session).getStaffType())){
			viewModel = "complaint_manager :: #tab02";
			staffId ="";
		}
		try {
			if(!StringUtil.isBlank(pageNo)){
				ordComplaintReqDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(!StringUtil.isBlank(pageSize)){
				ordComplaintReqDTO.setPageSize(Integer.valueOf(pageSize));
			}else{
				ordComplaintReqDTO.setPageSize(10);
			}
			if (!StringUtil.isBlank(startTime)){
				ordComplaintReqDTO.setBeginTime(DateUtil.parse(startTime));
			}
			if (!StringUtil.isBlank(endTime)){
				ordComplaintReqDTO.setEndTime(DateUtil.parse(endTime));
			}
			if (!StringUtil.isBlank(compStatus)){
				ordComplaintReqDTO.setComplaintStatus(compStatus);
			}
			if (!StringUtil.isBlank(compItem)){
				ordComplaintReqDTO.setComplaintItem(compItem);
			}
			ordComplaintReqDTO.setStaffId(staffId);
			PageResponseDTO<OrdComplaintRespDTO> pageInfo = this.queryOrdComplaintPageInfo(ordComplaintReqDTO);
			model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("compStatus", compStatus);
            model.addAttribute("compItem", compItem);
		} catch (Exception e) {
			logger.error("[查询我的订单投诉异常]，异常信息："+e.getMessage());
		}
		return viewModel;
	}

	/**
	 * 查询我的订单List
	 *
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/myOrderList")
	public String myOrderList(Model model, OrdMainInfoVO ordMainInfoVO, HttpServletRequest request ) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ordStatus = request.getParameter("ordStatus");
		try {
			PageResponseDTO<OrdMainInfoRespDTO> pageInfo = new PageResponseDTO<OrdMainInfoRespDTO>();
			OrdMainInfoReqDTO ordMainReqDTO = new OrdMainInfoReqDTO();
			ordMainReqDTO.setPageNo(ordMainInfoVO.getPageNo());
			ordMainReqDTO.setPageSize(PAGE_SIZE);
			HttpSession hpptsesion = request.getSession();
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			if(StringUtil.isBlank(staff_id)){
				staff_id = TMPUSERID;
			}
			if (!StringUtil.isBlank(startTime)){
                ordMainReqDTO.setStartTime(DateUtil.parse(startTime));
            }
            if (!StringUtil.isBlank(endTime)){
                ordMainReqDTO.setEndTime(DateUtil.parse(endTime));
            }
            if (!StringUtil.isBlank(ordStatus)){
                ordMainReqDTO.setOrderStatus(ordStatus);
            }
			ordMainReqDTO.setStaffId(staff_id);
			//不是30的数据要查询出来
			List<String> listOrderType = new ArrayList<String>();
			listOrderType.add(Constants.Order.ORDER_TYPE_10);
			listOrderType.add(Constants.Order.ORDER_TYPE_20);
			ordMainReqDTO.setordertypeList(listOrderType)  ;
			pageInfo = this.queryOrdMainInfoPage(ordMainReqDTO);
			model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("ordStatus", ordStatus);
		} catch (Exception e) {
			logger.error("查询我的订单列表失败！原因是：" + e.getMessage());
		}
		return "goods_complaint :: #tab01";
	}
	private PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainReqDTO) throws  Exception{
		PageResponseDTO<OrdMainInfoRespDTO> pageInfo = iOrderMainInfoRSV.queryOrdMainInfoPage(ordMainReqDTO);
		if(!CollectionUtils.isEmpty(pageInfo.getResult())){
			for(OrdMainInfoRespDTO ordMainRespDTO :pageInfo.getResult()){
				OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
				ordInfoReqDTO.setOrderId(ordMainRespDTO.getOrderId());
				List<OrdInfoRespDTO> ordInfoList = iOrderInfoRSV.queryOrderInfoList(ordInfoReqDTO);
				OrdInfoRespDTO ordInfoRespDTO = new OrdInfoRespDTO();
				if(CollectionUtils.isNotEmpty(ordInfoList)){
					//一个订单只有一个子订单
					ordInfoRespDTO=ordInfoList.get(0);
					//从计费接口去可用次数
					DataAccountDTO dataAccountDTO= iAipCenterDataAccountRSV.queryDataAccountBySubOrder(ordInfoRespDTO.getSubOrder());
					if(dataAccountDTO!=null&&dataAccountDTO.getLeftNum()!=null){
						ordInfoRespDTO.setLeftCount(dataAccountDTO.getLeftNum());
					}
				}
				ordMainRespDTO.setOrdInfoRespDTO(ordInfoRespDTO);
			}
		}
		return  pageInfo;
	}
	@RequestMapping(value = "/iwantComplaint")
	public String iwantComplaint(HttpServletRequest request,Model model,HttpSession session){
		String compId = request.getParameter("compId");
		String orderId = request.getParameter("orderId");
		String staffId = StaffUtil.getStaffId(session);
		String viewModel = "goods_complaint :: #modal_content";
		if (StaffUtil.isAdmin(StaffUtil.getStaffVO(session).getStaffType())){
			viewModel = "complaint_manager :: #modal_content";
			staffId="";
		}
		OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
		ordInfoReqDTO.setOrderId(orderId);
		this.queryOrdInfoById(ordInfoReqDTO,model);
		OrdComplaintRespDTO complaintRespDTO= new OrdComplaintRespDTO();
		if(!StringUtil.isBlank(compId)){
				OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
				ordComplaintReqDTO.setStaffId(staffId);
				ordComplaintReqDTO.setComplaintId(Long.parseLong(compId));
				complaintRespDTO =this.queryComplaintInfoByExm(ordComplaintReqDTO,model);
		}
		model.addAttribute("ordCompDTO",complaintRespDTO);
		model.addAttribute("compId",compId);
		return viewModel;
	}
	private OrdComplaintRespDTO queryComplaintInfoByExm(OrdComplaintReqDTO ordComplaintReqDTO,Model model){
		OrdComplaintRespDTO complaintRespDTO= new OrdComplaintRespDTO();
		try{

			PageResponseDTO<OrdComplaintRespDTO> pageInfo = this.queryOrdComplaintPageInfo(ordComplaintReqDTO);
			List<OrdComplaintRespDTO> result = pageInfo.getResult();
			if (!CollectionUtils.isEmpty(result)){
				complaintRespDTO = result.get(0);
			}else{
				complaintRespDTO.setOrdComplaintContRespDTO(new OrdComplaintContRespDTO());
			}
		}catch (Exception e){
			logger.error("根据id查询我的投诉信息失败：" + e.getMessage());
		}
		return complaintRespDTO;
	}
	private void queryOrdInfoById(OrdInfoReqDTO ordInfoReqDTO,Model model){
		String gdsName="";
		try{
			List<OrdInfoRespDTO> ordInfoList = iOrderInfoRSV.queryOrderInfoList(ordInfoReqDTO);
			OrdInfoRespDTO ordInfo = ordInfoList.get(0);
			if (ordInfo != null){
				//通过商品id去搜索商品信息，获取到商品的图片ID
				GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
				GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
				gdsInfoReqDTO.setGdsId(ordInfo.getGdsId());
				gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO);
				gdsName = gdsInfoRespDTO.getGdsName();
			}
			model.addAttribute("gdsName",gdsName);
			model.addAttribute("orderId",ordInfoReqDTO.getOrderId());
		}catch (Exception e){
			logger.error("根据订单id查询订单信息失败：" + e.getMessage());
		}
	}
	private PageResponseDTO<OrdComplaintRespDTO> queryOrdComplaintPageInfo(OrdComplaintReqDTO ordComplaintReqDTO) throws  Exception{
		PageResponseDTO<OrdComplaintRespDTO> pageInfo = iOrdComplaintRSV.queryOrdComplaintPageInfo(ordComplaintReqDTO);
		if (!CollectionUtils.isEmpty(pageInfo.getResult())){
			OrdComplaintContReqDTO ordComplaintContReqDTO = new OrdComplaintContReqDTO();
			for (OrdComplaintRespDTO ordComplaint:pageInfo.getResult()){
				ordComplaintContReqDTO.setComplaintId(ordComplaint.getComplaintId());
				PageResponseDTO<OrdComplaintContRespDTO> contPageInfo = iOrdComplaintRSV.queryOrdComplaintContPageInfo(ordComplaintContReqDTO);
				List<OrdComplaintContRespDTO>  contRespDTOList= contPageInfo.getResult();
				if (!CollectionUtils.isEmpty(contRespDTOList)){
					ordComplaint.setOrdComplaintContRespDTO(contRespDTOList.get(0));
				}else{
					ordComplaint.setOrdComplaintContRespDTO(new OrdComplaintContRespDTO());
				}
			}
		}
		return pageInfo;
	}
	@RequestMapping(value = "/modalDataSubmit")
	@ResponseBody
	public Map<String, Object> modalDataSubmit(HttpServletRequest request,HttpSession session){
		String orderId = request.getParameter("orderId");
		String complaintItem = request.getParameter("complaintItem");
		String complaintContent = request.getParameter("complaintContent");
		String linPerson = request.getParameter("linPerson");
		String linkPhone = request.getParameter("linkPhone");
//		String email = request.getParameter("email");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
			ordComplaintReqDTO.setOrderId(orderId);
			ordComplaintReqDTO.setComplaintItem(complaintItem);
			ordComplaintReqDTO.setLinkPerson(linPerson);
			ordComplaintReqDTO.setMobile(linkPhone);
			ordComplaintReqDTO.setDelFlag("0");//删除标志 0未删除 1已经删除
			//投诉状态,1待处理,2已回复,3已撤销
			ordComplaintReqDTO.setComplaintStatus("1");
			ordComplaintReqDTO.setStaffId(StaffUtil.getStaffId(session));
			ordComplaintReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
			long compId = iOrdComplaintRSV.insertOrdComplaint(ordComplaintReqDTO);
			if(compId>0){
				OrdComplaintContReqDTO OrdComplaintContReqDTO = new OrdComplaintContReqDTO();
				OrdComplaintContReqDTO.setComplaintId(compId);
				OrdComplaintContReqDTO.setFromRemark(complaintContent);//投诉内容
				OrdComplaintContReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
				OrdComplaintContReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
				iOrdComplaintRSV.insertOrdComplaintCont(OrdComplaintContReqDTO);
			}
			rMap.put("success",true);
		}catch (Exception e){
			rMap.put("success",false);
			rMap.put("errMsg",e.getMessage());
			logger.error("投诉信息新增/编辑失败：" + e.getMessage());
		}
		return rMap;
	}

	@RequestMapping(value="/updateComplaint")
	@ResponseBody
	public Map<String,Object> updateComplaint(HttpServletRequest request,HttpSession session){
		String comStatus = request.getParameter("comStatus");
		String compId = request.getParameter("compId");
		String compContId = request.getParameter("compContId");
		String compRepeat = request.getParameter("compRepeat");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
			if(!StringUtil.isBlank(compId)){
				ordComplaintReqDTO.setComplaintId(Long.parseLong(compId));
			}
			if(!StringUtil.isBlank(comStatus)){
				ordComplaintReqDTO.setComplaintStatus(comStatus);
			}
			ordComplaintReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
			long compflag = iOrdComplaintRSV.updateOrdComplaint(ordComplaintReqDTO);
			if (!StringUtil.isBlank(compContId) && compflag > 0){
				OrdComplaintContReqDTO ordComplaintContReqDTO = new OrdComplaintContReqDTO();
				ordComplaintContReqDTO.setComplaintContId(Long.parseLong(compContId));
				ordComplaintContReqDTO.setRepeatCont(compRepeat);
				ordComplaintContReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
				long contflag =updateComplaintRepeat(ordComplaintContReqDTO);
			}
			rMap.put("success",true);
		}catch (Exception e){
			rMap.put("success",false);
			rMap.put("errMsg",e.getMessage());
			logger.error("更新失败：" + e.getMessage());
		}
		return rMap;
	}
	public long updateComplaintRepeat(OrdComplaintContReqDTO ordComplaintContReqDTO){
		long contflag =0;
		try {
			 contflag = iOrdComplaintRSV.updateOrdComplaintCont(ordComplaintContReqDTO);
		}catch (Exception e){
			logger.error("更新失败：" + e.getMessage());
		}
		return contflag;
	}
	@RequestMapping(value = "/initComplaintRepeat")
	public String initComplaintRepeat(HttpServletRequest request,Model model,HttpSession session){
		String compId = request.getParameter("complaintId");
		String orderId = request.getParameter("orderId");
		String compContId = request.getParameter("complaintContId");
		String compItem = request.getParameter("complaintItem");
		model.addAttribute("compId",compId);
		model.addAttribute("compContId",compContId);
		model.addAttribute("compItem",compItem);
		OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
		ordInfoReqDTO.setOrderId(orderId);
		this.queryOrdInfoById(ordInfoReqDTO,model);
		OrdComplaintRespDTO complaintRespDTO= new OrdComplaintRespDTO();
		if(!StringUtil.isBlank(compId)){
			OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
			ordComplaintReqDTO.setComplaintId(Long.parseLong(compId));
			complaintRespDTO =this.queryComplaintInfoByExm(ordComplaintReqDTO,model);
		}
		model.addAttribute("ordCompDTO",complaintRespDTO);
		return "complaint_manager :: #repeat_content";
	}
}
