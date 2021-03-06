package com.ai.bdex.dataexchange.busi.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.busi.order.entity.OrdInfoVO;
import com.ai.bdex.dataexchange.busi.order.entity.OrdInvoiceTaxVO;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrdInvoiceTaxRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 发票管理
 * @author win8
 *
 */
@Controller
@RequestMapping("/invoiceManage")
public class invoiceManageController {
    /**
     * 记录日志
     */
    private final Log logger = LogFactory.getLog(getClass());
    private final static Integer PAGE_SIZE = 10;//页数
    
	@DubboConsumer
	private IOrderInfoRSV iOrderInfoRSV;

	@DubboConsumer(timeout = 30000)
	private IOrdInvoiceTaxRSV iOrdInvoiceTaxRSV;
	@DubboConsumer
	private IChnlInvoiceTaxRSV iChnlInvoiceTaxRSV;
	@DubboConsumer
	private IOrderMainInfoRSV iOrderMainInfoRSV;
    
    /**
     * 发票管理
     * @param model
     * @param gdsInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/mybill")
    public String myData(Model model,OrdInfoVO ordInfoVO) throws Exception{
        return "mybill";
    }
    /**
     * 发票开具申请
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/applyInvoice")
	public String applyInvoice(Model model,HttpSession session,OrdInvoiceTaxVO ordInvoiceTaxVO) throws Exception{
		ReqInvoiceTaxDTO input = new ReqInvoiceTaxDTO();
		input.setStaffId(StaffUtil.getStaffVO(session).getStaffId());
		List<ChnlInvoiceTaxDTO> datas = new ArrayList<>();
		ChnlInvoiceTaxDTO invoiceTaxDTO =new  ChnlInvoiceTaxDTO();
		input.setStatus("20");
		datas = iChnlInvoiceTaxRSV.queryInvoiceRecord(input);
		if(CollectionUtils.isNotEmpty(datas)){
			invoiceTaxDTO=datas.get(0);
		}
		model.addAttribute("orderId", ordInvoiceTaxVO.getOrderId());
		model.addAttribute("invoiceTaxDTO", invoiceTaxDTO);
		return "mybill_apply";
	}
   	/**
	 * 保存发票开具申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveInvoiceTax")
	@ResponseBody
	public Map<String,Object> saveInvoiceTax(HttpServletRequest request,HttpSession session,OrdInvoiceTaxVO ordInvoiceTaxVO){
		Map<String,Object>  rMap = new HashMap<>();
		try {
			//查询我的子订单
			OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
			ordMainInfoReqDTO.setOrderId(ordInvoiceTaxVO.getOrderId());
			List<OrdMainInfoRespDTO> ordInfoList = iOrderMainInfoRSV.queryOrdMainInfoList(ordMainInfoReqDTO);
			OrdInvoiceTaxReqDTO taxReqDTO = new OrdInvoiceTaxReqDTO();
			ObjectCopyUtil.copyObjValue(ordInvoiceTaxVO, taxReqDTO, null, false);
			taxReqDTO.setCreateStaff(StaffUtil.getStaffId(session));
			taxReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
			if(CollectionUtils.isNotEmpty(ordInfoList)){
				taxReqDTO.setShopId(ordInfoList.get(0).getShopId());
				taxReqDTO.setStaffId(ordInfoList.get(0).getStaffId());
			}
			taxReqDTO.setStatus(Constants.Order.ORDER_INVOICE_STATUS_1);
			Long ordTaxId=iOrdInvoiceTaxRSV.insertOrdInvoice(taxReqDTO);
			if(ordTaxId>0){
				OrdMainInfoReqDTO ordMainInfo= new OrdMainInfoReqDTO();
				ordMainInfo.setOrderId(ordInvoiceTaxVO.getOrderId());
				ordMainInfo.setInvoiceStatus(Constants.Order.ORDER_INVOICE_STATUS_1);
				iOrderMainInfoRSV.updateOrderMainInfo(ordMainInfo);
			}
			rMap.put("success", true);

		} catch (Exception e) {
			rMap.put("success", false);
			logger.error("保存发票开具申请信息出错：" + e.getMessage());
		}
		return rMap;
	}
	 /**
     * 发票管理-管理员审核
     * @param model
     * @param gdsInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/mybillCheck")
    public String mybillCheck(Model model,OrdInfoVO ordInfoVO) throws Exception{
        return "mybillCheck";
    }
	/**
   	 * 查询发票管理审核列表
   	 * 
   	 * @param model
   	 * @param searchVO
   	 * @return
   	 */
   	@RequestMapping(value = "/myOrderInvoiceList")
   	public String myOrderInvoiceList(Model model,HttpSession session, OrdMainInfoVO ordMainInfoVO) {
   		try {
   			PageResponseDTO<OrdMainInfoRespDTO> pageInfo = new PageResponseDTO<OrdMainInfoRespDTO>();
   			OrdMainInfoReqDTO ordMainReqDTO = new OrdMainInfoReqDTO();
   			ordMainReqDTO.setStaffId(StaffUtil.getStaffId(session));
   			ordMainReqDTO.setPageNo(ordMainInfoVO.getPageNo());
   			ordMainReqDTO.setPageSize(PAGE_SIZE);
   			ordMainInfoVO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);//已支付
   			//查询发票类型为1普通发票、2增值税发票
   			List<String> invoiceModTypeList = new ArrayList<String>();
   			invoiceModTypeList.add(Constants.Order.ORDER_invoiceModType_1);
   			invoiceModTypeList.add(Constants.Order.ORDER_invoiceModType_2);
   			ordMainReqDTO.setInvoiceModTypeList(invoiceModTypeList);
   			pageInfo = iOrderMainInfoRSV.queryOrdMainInfoPage(ordMainReqDTO);
			if (CollectionUtils.isNotEmpty(pageInfo.getResult())) {
				for (OrdMainInfoRespDTO ordMainInfo : pageInfo.getResult()) {
					// 获取已经开具发票的信息
					OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO = new OrdInvoiceTaxReqDTO();
					ordInvoiceTaxReqDTO.setOrderId(ordMainInfo.getOrderId());
					List<OrdInvoiceTaxRespDTO> invoiceTaxList = iOrdInvoiceTaxRSV
							.queryOrdInvoiceTaxList(ordInvoiceTaxReqDTO);
					OrdInvoiceTaxRespDTO texRespDTO = new OrdInvoiceTaxRespDTO();
					OrdInvoiceTaxAddrRespDTO taxAddrRespDTO = new OrdInvoiceTaxAddrRespDTO();
					if (CollectionUtils.isNotEmpty(invoiceTaxList)) {
						texRespDTO = invoiceTaxList.get(0);
						OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO = new OrdInvoiceTaxAddrReqDTO();
						ordInvoiceTaxAddrReqDTO.setOrderTaxId(texRespDTO.getOrderTaxId());
						List<OrdInvoiceTaxAddrRespDTO> invoiceTaxAddrList = iOrdInvoiceTaxRSV.queryOrdInvoiceTaxAddrList(ordInvoiceTaxAddrReqDTO);
						if(CollectionUtils.isNotEmpty(invoiceTaxAddrList)){
							taxAddrRespDTO=invoiceTaxAddrList.get(0);
						}
					}
					ordMainInfo.setOrdInvoiceTaxReqDTO(texRespDTO);
					ordMainInfo.setOrdInvoiceTaxAddrRespDTO(taxAddrRespDTO);
				}
			}
   			model.addAttribute("pageInfo", pageInfo);
   		} catch (Exception e) {
   			logger.error("查询发票管理列表失败！原因是：" + e.getMessage());
   		}
   		return "mybill :: #myOrderBillList";
   	}
   	/**
   	 * 查询发票管理
   	 * 
   	 * @param model
   	 * @param searchVO
   	 * @return
   	 */
   	@RequestMapping(value = "/myInvoiceCheckList")
   	public String myInvoiceCheckList(Model model,HttpSession session, OrdMainInfoVO ordMainInfoVO) {
   		try {
   			PageResponseDTO<OrdInvoiceTaxRespDTO> pageInfo = new PageResponseDTO<OrdInvoiceTaxRespDTO>();
   			OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO = new OrdInvoiceTaxReqDTO();
   			ordInvoiceTaxReqDTO.setPageNo(ordMainInfoVO.getPageNo());
   			ordInvoiceTaxReqDTO.setPageSize(PAGE_SIZE);
   			pageInfo = iOrdInvoiceTaxRSV.queryOrdInvoiceTaxPage(ordInvoiceTaxReqDTO);
			if (CollectionUtils.isNotEmpty(pageInfo.getResult())) {
				for (OrdInvoiceTaxRespDTO taxRespDTO : pageInfo.getResult()) {
					// 获取订单信息
					OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
					ordMainInfoReqDTO.setOrderId(taxRespDTO.getOrderId());
					List<OrdMainInfoRespDTO> ordMainList = iOrderMainInfoRSV.queryOrdMainInfoList(ordMainInfoReqDTO);
					OrdMainInfoRespDTO ordMainInfoRespDTO = new OrdMainInfoRespDTO();
					OrdInvoiceTaxAddrRespDTO taxAddrRespDTO = new OrdInvoiceTaxAddrRespDTO();
					if (CollectionUtils.isNotEmpty(ordMainList)) {
						ordMainInfoRespDTO = ordMainList.get(0);
					}
					//获取发票开具申请收货地址
					OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO = new OrdInvoiceTaxAddrReqDTO();
					ordInvoiceTaxAddrReqDTO.setOrderTaxId(taxRespDTO.getOrderTaxId());
					List<OrdInvoiceTaxAddrRespDTO> invoiceTaxAddrList = iOrdInvoiceTaxRSV.queryOrdInvoiceTaxAddrList(ordInvoiceTaxAddrReqDTO);
					if(CollectionUtils.isNotEmpty(invoiceTaxAddrList)){
						taxAddrRespDTO=invoiceTaxAddrList.get(0);
					}
					taxRespDTO.setOrdMainInfoRespDTO(ordMainInfoRespDTO);
					taxRespDTO.setOrdInvoiceTaxAddrRespDTO(taxAddrRespDTO);
				}
			}
   			model.addAttribute("pageInfo", pageInfo);
   		} catch (Exception e) {
   			logger.error("查询发票管理列表失败！原因是：" + e.getMessage());
   		}
   		return "mybillCheck :: #myInvoiceCheckList";
   	}
	/**
	 * 发票审核开票、拒开票
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/makeInvoiceTax")
	@ResponseBody
	public Map<String,Object> makeInvoiceTax(HttpServletRequest request,HttpSession session,OrdInvoiceTaxVO ordInvoiceTaxVO){
		Map<String,Object>  rMap = new HashMap<>();
		try {
			OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO = new OrdInvoiceTaxReqDTO();
			ordInvoiceTaxReqDTO.setOrderTaxId(ordInvoiceTaxVO.getOrderTaxId());
			ordInvoiceTaxReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
			if(StringUtil.isNotBlank(ordInvoiceTaxVO.getStatus())){
				ordInvoiceTaxReqDTO.setStatus(ordInvoiceTaxVO.getStatus());
			}
			Long ordTaxCode=iOrdInvoiceTaxRSV.updateOrdInvoiceTax(ordInvoiceTaxReqDTO);
			
			OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO = new OrdInvoiceTaxAddrReqDTO();
			ordInvoiceTaxAddrReqDTO.setOrderTaxId(ordInvoiceTaxVO.getOrderTaxId());
			ordInvoiceTaxAddrReqDTO.setUpdateStaff(StaffUtil.getStaffId(session));
			if(StringUtil.isNotBlank(ordInvoiceTaxVO.getStatus())){
				ordInvoiceTaxAddrReqDTO.setStatus(ordInvoiceTaxVO.getStatus());
			}
			if(ordTaxCode>0){
				iOrdInvoiceTaxRSV.updateOrdInvoiceAddrTax(ordInvoiceTaxAddrReqDTO);
			}
			OrdMainInfoReqDTO ordMainInfo= new OrdMainInfoReqDTO();
			ordMainInfo.setOrderId(ordInvoiceTaxVO.getOrderId());
			if(StringUtil.isNotBlank(ordInvoiceTaxVO.getStatus())){
				ordMainInfo.setInvoiceStatus(ordInvoiceTaxVO.getStatus());
			}
			iOrderMainInfoRSV.updateOrderMainInfo(ordMainInfo);
			rMap.put("success", true);

		} catch (Exception e) {
			rMap.put("success", false);
			logger.error("保存发票开具申请信息出错：" + e.getMessage());
		}
		return rMap;
	}
}
