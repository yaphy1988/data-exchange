package com.ai.bdex.dataexchange.busi.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.util.StaffUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.order.entity.OrdInfoVO;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
import com.ai.bdex.dataexchange.busi.page.entity.PageModuleAdVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageModuleAdReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 订单管理
 * @author win8
 *
 */
@Controller
@RequestMapping("/orderManage")
public class orderManageController {
    /**
     * 记录日志
     */
    private final Log logger = LogFactory.getLog(getClass());
	private final static String PAY_FLAG_SUCCESS = "1";// 已支付
    private final static String AIP_CAT_ID = "1";//aip的catId
    private final static String CUSTOM_CAT_ID = "2";//定制需求catId
    private final static String SOLUTION_CAT_ID = "3";//解决方案catId
    private final static Integer PAGE_SIZE = 10;//页数
    private final static String ORDER_TYPE_COMMON = "10";//普通订单

	@DubboConsumer(timeout = 30000)
	private IOrderInfoRSV iOrderInfoRSV;
    @DubboConsumer
    private IOrderMainInfoRSV iOrderMainInfoRSV;
    @DubboConsumer
    private IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer
    private IAipCenterDataAccountRSV iAipCenterDataAccountRSV;
    
    /**
     * 我的数据
     * @param model
     * @param gdsInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/myData")
    public String myData(Model model,OrdInfoVO ordInfoVO) throws Exception{
        return "mydata";
    }
    /**
	 * 我的数据
	 * 
	 * @param model
	 * @param searchVO
	 * @return
	 */
	@RequestMapping(value = "/myOrderDataList")
	public String myOrderData(HttpServletRequest request,Model model, OrdInfoVO ordInfoVO) {
		try {
			PageResponseDTO<OrdInfoRespDTO> pageInfo = new PageResponseDTO<OrdInfoRespDTO>();
			OrdInfoReqDTO ordReqDTO = new OrdInfoReqDTO();
			ordReqDTO.setPageNo(ordInfoVO.getPageNo());
			if(ordInfoVO.getPageSize()!=null||ordInfoVO.getPageSize()!=0){
				ordReqDTO.setPageSize(ordInfoVO.getPageSize());
			}else{
				ordReqDTO.setPageSize(PAGE_SIZE);
			}
			//支付成功
			ordReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);
			pageInfo = iOrderInfoRSV.queryOrdInfoPage(ordReqDTO);
			if(CollectionUtils.isNotEmpty(pageInfo.getResult())){
				for(OrdInfoRespDTO ordInfoRespDTO : pageInfo.getResult()){
					//根据子订单编码，查询数据账户信息
					DataAccountDTO dataAccountDTO =	iAipCenterDataAccountRSV.queryDataAccountBySubOrder(ordInfoRespDTO.getSubOrder());
					if(dataAccountDTO!=null&&dataAccountDTO.getLeftNum()!=null)
					ordInfoRespDTO.setLeftCount(dataAccountDTO.getLeftNum());
				}
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("查询我的数据列表失败！原因是：" + e.getMessage());
		}
		return "mydata :: #tab01";
	}
	 /**
     * 我的订单
     * @param model
     * @param gdsInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/myOrder")
    public String myOrder(Model model,OrdMainInfoVO ordMainInfoVO) throws Exception{
        return "myOrder";
    }
    /**
   	 * 查询我的订单List
   	 * 
   	 * @param model
   	 * @param searchVO
   	 * @return
   	 */
   	@RequestMapping(value = "/myOrderList")
   	public String myOrderList(Model model, OrdMainInfoVO ordMainInfoVO,HttpServletRequest request ) {
   		try {
   			PageResponseDTO<OrdMainInfoRespDTO> pageInfo = new PageResponseDTO<OrdMainInfoRespDTO>();
   			OrdMainInfoReqDTO ordMainReqDTO = new OrdMainInfoReqDTO();
   			ordMainReqDTO.setPageNo(ordMainInfoVO.getPageNo());
   			ordMainReqDTO.setPageSize(PAGE_SIZE);
   			ordMainReqDTO.setOrderType(ORDER_TYPE_COMMON);
			HttpSession hpptsesion = request.getSession();
			String staff_id = StaffUtil.getStaffId(hpptsesion);
			ordMainReqDTO.setStaffId(staff_id);
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
   					}
   					ordMainRespDTO.setOrdInfoRespDTO(ordInfoRespDTO);
   				}
   			}
   			model.addAttribute("pageInfo", pageInfo);
   		} catch (Exception e) {
   			logger.error("查询我的订单列表失败！原因是：" + e.getMessage());
   		}
   		return "myOrder :: #myOrderList";
   	}
   	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cancelOrder")
	@ResponseBody
	public Map<String,Object> cancelOrder(HttpServletRequest request,OrdMainInfoVO ordMainInfoVO){
		Map<String,Object>  rMap = new HashMap<>();
		String status = ordMainInfoVO.getOrderStatus();
		try {
			OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
			if(!StringUtils.isBlank(status)){
				ordMainInfoReqDTO.setOrderStatus(status);
			}
			if(StringUtils.isNotEmpty(ordMainInfoVO.getOrderId())){
				ordMainInfoReqDTO.setOrderId(ordMainInfoVO.getOrderId());
			}
			int code=iOrderMainInfoRSV.cancelOrder(ordMainInfoReqDTO);
			rMap.put("success", true);
		} catch (Exception e) {
			rMap.put("success", false);
			logger.error("取消订单出错：" + e.getMessage());
		}
		return rMap;
	}
	/**
	 * 管理员的订单管理
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderManage")
	public String orderManage(){
		return "order_manage";
	}
	/**
	 * 管理员的订单管理--查询数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderManagequery")
 	public String orderManagequery(Model model, OrdMainInfoVO ordMainInfoVO,HttpServletRequest request ) {
		try {
				PageResponseDTO<OrdMainInfoRespDTO> pageInfo = new PageResponseDTO<OrdMainInfoRespDTO>();
				OrdMainInfoReqDTO ordMainReqDTO = new OrdMainInfoReqDTO();
				ordMainReqDTO.setPageNo(ordMainInfoVO.getPageNo());
				ordMainReqDTO.setPageSize(PAGE_SIZE);
					ordMainReqDTO.setShopId(Constants.Shop.GZDATA_SHOP_ID);
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
						}
						ordMainRespDTO.setOrdInfoRespDTO(ordInfoRespDTO);
					}
				}
				model.addAttribute("pageInfo", pageInfo);
			} catch (Exception e) {
				logger.error("查询我的订单列表失败！原因是：" + e.getMessage());
			}
			return "order_manage :: #managerdata";
		}
}
