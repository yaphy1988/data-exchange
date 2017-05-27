package com.ai.bdex.dataexchange.busi.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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


	@RequestMapping(value="/pageInit")
	public ModelAndView pageInit(){
		ModelAndView modelAndView = new ModelAndView("goods_complaint");
		return modelAndView;
	}
	/**
	 * 查询我的订单投诉列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryComplaint")
	public String complaintPageInfo(HttpServletRequest request,Model model){
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
		try {
			if(!StringUtil.isBlank(pageNo)){
				ordComplaintReqDTO.setPageNo(Integer.valueOf(pageNo));
			}
			if(!StringUtil.isBlank(pageSize)){
				ordComplaintReqDTO.setPageSize(Integer.valueOf(pageSize));
			}else{
				ordComplaintReqDTO.setPageSize(10);
			}
			PageResponseDTO<OrdComplaintRespDTO> pageInfo = iOrdComplaintRSV.queryOrdComplaintPageInfo(ordComplaintReqDTO);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("[查询我的订单投诉异常]，异常信息："+e.getMessage());
		}
		return "goods_complaint :: #tab02";
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
			ordMainReqDTO.setStaffId(staff_id);
			//不是30的数据要查询出来
			List<String> listOrderType = new ArrayList<String>();
			listOrderType.add(Constants.Order.ORDER_TYPE_10);
			listOrderType.add(Constants.Order.ORDER_TYPE_20);
			ordMainReqDTO.setordertypeList(listOrderType)  ;
			pageInfo = iOrderMainInfoRSV.queryOrdMainInfoPage(ordMainReqDTO);
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
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("查询我的订单列表失败！原因是：" + e.getMessage());
		}
		return "goods_complaint :: #tab01";
	}
	@RequestMapping(value = "/iwantComplaint")
	public String iwantComplaint(HttpServletRequest request,Model model){
		String orderId = request.getParameter("orderId");
		String gdsName="";
		try{
			OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
			ordInfoReqDTO.setOrderId(orderId);
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
			model.addAttribute("orderId",orderId);
		}catch (Exception e){
			logger.error("根据订单id查询订单信息失败：" + e.getMessage());
		}
		return "goods_complaint :: #modal_content";
	}
	@RequestMapping(value = "/modalDataSubmit")
	public Map<String, Object> modalDataSubmit(Model model,HttpServletRequest request){
		String orderId = request.getParameter("orderId");
		String complaintItem = request.getParameter("complaintItem");
		String complaintContent = request.getParameter("complaintContent");
		String linPerson = request.getParameter("linPerson");
		String linkPhone = request.getParameter("linkPhone");
		String email = request.getParameter("email");
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			OrdComplaintReqDTO ordComplaintReqDTO = new OrdComplaintReqDTO();
			ordComplaintReqDTO.setOrderId(orderId);
			ordComplaintReqDTO.setComplaintItem(complaintItem);
//			iOrdComplaintRSV.insertOrdComplaint();

		}catch (Exception e){
			logger.error("投诉信息新增/编辑失败：" + e.getMessage());
		}
		return rMap;
	}
}
