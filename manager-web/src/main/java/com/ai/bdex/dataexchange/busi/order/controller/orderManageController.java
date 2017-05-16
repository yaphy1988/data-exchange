package com.ai.bdex.dataexchange.busi.order.controller;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
 import com.ai.bdex.dataexchange.busi.page.entity.PageModuleGoodsVO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
 import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.order.entity.OrdInfoVO;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
 import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
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
	private final static String TMPUSERID = "tmpuser";// 临时用户
	private static final Logger log = LoggerFactory.getLogger(orderManageController.class);

	@DubboConsumer(timeout = 30000)
	private IOrderInfoRSV iOrderInfoRSV;
    @DubboConsumer
    private IOrderMainInfoRSV iOrderMainInfoRSV;
    @DubboConsumer
    private IAipServiceInfoRSV iAipServiceInfoRSV;
    @DubboConsumer
    private IAipCenterDataAccountRSV iAipCenterDataAccountRSV;
	@DubboConsumer
	private IGdsInfoRSV iGdsInfoRSV;

    
    /**
     * 我的数据
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/myData")
    public String myData(Model model,OrdInfoVO ordInfoVO) throws Exception{
        return "mydata";
    }
    /**
	 * 我的数据-全部数据：查询API分类订单
	 * 
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/myAPIDataList")
	public String myAPIDataList(Model model, OrdInfoVO ordInfoVO,HttpSession session) {
		try {
			PageResponseDTO<RechargeDTO> pageInfo = new PageResponseDTO<RechargeDTO>();
			RechargeReqDTO rechargeReqDTO = new RechargeReqDTO();
			rechargeReqDTO.setPageNo(ordInfoVO.getPageNo());
			if(ordInfoVO.getPageSize()!=null||ordInfoVO.getPageSize()!=0){
				rechargeReqDTO.setPageSize(ordInfoVO.getPageSize());
			}else{
				rechargeReqDTO.setPageSize(PAGE_SIZE);
			}
			rechargeReqDTO.setRechargeUserId(StaffUtil.getStaffId(session));
			//查询API分类
			rechargeReqDTO.setCatFirst(Integer.parseInt(AIP_CAT_ID));
			 //查询是是否要连数据账户信息一起查询
			rechargeReqDTO.setQueryDataAccount(true);
			pageInfo = iAipCenterDataAccountRSV.queryRechargePageByOption(rechargeReqDTO);
			if(CollectionUtils.isNotEmpty(pageInfo.getResult())){
				for(RechargeDTO rechargeDTO : pageInfo.getResult()){
					if(StringUtil.isNotBlank(rechargeDTO.getServiceId())){
						List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(rechargeDTO.getServiceId()));
						if (CollectionUtils.isNotEmpty(apiServiceList)) {
							rechargeDTO.setServiceName(apiServiceList.get(0).getServiceName());
						}
					}
				}
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("查询我的数据列表失败！原因是：" + e.getMessage());
		}
		return "mydata :: #tab01";
	}
	/**
	 * 我的数据-定制服务数据
	 * 
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/myCustomServiceDataList")
	public String myCustomServiceDataList(Model model, OrdInfoVO ordInfoVO,HttpSession session) {
		try {
			PageResponseDTO<RechargeDTO> pageInfo = new PageResponseDTO<RechargeDTO>();
			RechargeReqDTO rechargeReqDTO = new RechargeReqDTO();
			rechargeReqDTO.setPageNo(ordInfoVO.getPageNo());
			if(ordInfoVO.getPageSize()!=null||ordInfoVO.getPageSize()!=0){
				rechargeReqDTO.setPageSize(ordInfoVO.getPageSize());
			}else{
				rechargeReqDTO.setPageSize(PAGE_SIZE);
			}
			rechargeReqDTO.setRechargeUserId(StaffUtil.getStaffId(session));
			 //查询是是否要连数据账户信息一起查询
			rechargeReqDTO.setQueryDataAccount(true);
			//查询API分类
			rechargeReqDTO.setCatFirst(Integer.parseInt(CUSTOM_CAT_ID));
			pageInfo = iAipCenterDataAccountRSV.queryRechargePageByOption(rechargeReqDTO);
			if(CollectionUtils.isNotEmpty(pageInfo.getResult())){
				for(RechargeDTO rechargeDTO : pageInfo.getResult()){
					if(StringUtil.isNotBlank(rechargeDTO.getServiceId())){
						List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(rechargeDTO.getServiceId()));
	        			if(CollectionUtils.isNotEmpty(apiServiceList)){
	        				rechargeDTO.setServiceName(apiServiceList.get(0).getServiceName());
	        			}	
					}
				}
			}
			model.addAttribute("pageInfoCustom", pageInfo);
		} catch (Exception e) {
			logger.error("查询我的数据列表失败！原因是：" + e.getMessage());
		}
		return "mydata :: #tab02";
	}
	 /**
     * 我的订单
     * @param model
     * @param
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
   	 * @param
   	 * @return
   	 */
   	@RequestMapping(value = "/myOrderList")
   	public String myOrderList(Model model, OrdMainInfoVO ordMainInfoVO,HttpServletRequest request ) {
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
	 * @param
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
						//去查询可用次数
						try{
							String suborderid = ordInfoRespDTO.getSubOrder();
							DataAccountDTO dataAccountDTO= iAipCenterDataAccountRSV.queryDataAccountBySubOrder(suborderid);
							if(dataAccountDTO != null){
								int iLeftNum = dataAccountDTO.getLeftNum();
								long lLeftNum=(long)iLeftNum;
								ordInfoRespDTO.setBelanceAllCount(lLeftNum);
								/*int iUsedAll = dataAccountDTO.getTotalConsumeNum();
								long lUsedAll=(long)iUsedAll;
								ordInfoRespDTO.setUsedAllCount(lUsedAll);*/
							}
						}
						catch(Exception e2){
							logger.error("查询我的订单列表失败！原因是：" + e2.getMessage());
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
	/**
	 * 线下审核订单变为已支付
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderChangePay")
	@ResponseBody
	public  Map<String, Object> changeOrderPay(HttpServletRequest request,OrdMainInfoVO ordMainInfoVO) {
		//模拟成功
		Map<String, Object> rMap = new HashMap<String, Object>();
		HttpSession hpptsesion = request.getSession();
		String staff_id = StaffUtil.getStaffId(hpptsesion);
		String orderid = request.getParameter("orderid");
		String subordid = request.getParameter("suborderid");
		Date orderTime = DateUtil.getNowAsDate();
		OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
		ordMainInfoReqDTO.setOrderId(orderid);
		ordMainInfoReqDTO.setUpdateStaff(staff_id);
		ordMainInfoReqDTO.setPayWay(Constants.Order.ORDER_PAY_WAY_ZHIFUBAO);
		ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_02);
		ordMainInfoReqDTO.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);
		ordMainInfoReqDTO.setPayTime(orderTime);

		OrdInfoReqDTO ordInfo = new OrdInfoReqDTO();
		ordInfo.setUpdateStaff(staff_id);
		ordInfo.setOrderId(orderid);
		ordInfo.setSubOrder(subordid);
		ordInfo.setStatus(Constants.Order.ORDER_STATUS_02);
		ordInfo.setPayFlag(Constants.Order.ORDER_PAY_FLAG_1);
		ordInfo.setPayTime(orderTime);
		try {
			iOrderMainInfoRSV.updateOrderAndSubOrdStatuss(ordMainInfoReqDTO, ordInfo);
			OrdMainInfoRespDTO ordMainInfoRespDTO=	iOrderMainInfoRSV.queryOrderDetail(ordMainInfoReqDTO);
			OrdInfoRespDTO ordInfoRespDTO = ordMainInfoRespDTO.getOrdInfoRespDTO();

			if (ordInfoRespDTO!= null) {
				//此处为API支付回调
				RechargeReqDTO rechargeDTO = new RechargeReqDTO();
      /*            * rechargeUserId
                    * subOrder
                    * rechargeType (1-次数，2-金额)
                    * periodType (1-有有效期，2-永久有效)
                    * totalNum  (当rechargeType="1")
                    * totalMoney(当rechargeType="2")
                    * serviceId (当rechargeType="1")
                    *
                    * startDate(当periodType="1")
                    * endDate(当periodType="1")*/
				int igdsid = new Long(ordInfoRespDTO.getGdsId()).intValue();
				int iskuid = new Long(ordInfoRespDTO.getSkuId()).intValue();
				String rechargeType = Constants.Order.ORDER_API_RECHARGETYPE_1;
				rechargeDTO.setRechargeUserId(ordInfoRespDTO.getStaffId());
				rechargeDTO.setOrderId(orderid);
				rechargeDTO.setSubOrder(ordInfoRespDTO.getSubOrder());
				rechargeDTO.setGdsId(igdsid);
				rechargeDTO.setSkuId(iskuid);
				rechargeDTO.setCatId(ordInfoRespDTO.getCatId());
				rechargeDTO.setCatFirst(ordInfoRespDTO.getCatFirst());
				rechargeDTO.setRechargeType(Constants.Order.ORDER_API_RECHARGETYPE_1);
				String periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				String  inavidate =  Constants.Order.ORDER_API_NODATE;
				if (ordInfoRespDTO.getActiveEndTime().after(getNodatelength(inavidate))) {
					//大于50年,就是无限期了
					periodType = Constants.Order.ORDER_API_PERIODTYPE_2;
				}
				else	{
					periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				}
				rechargeDTO.setStartDate(ordInfoRespDTO.getCreateTime());
				rechargeDTO.setEndDate(ordInfoRespDTO.getActiveEndTime());
				rechargeDTO.setPeriodType(periodType);
				// * rechargeType (1-次数，2-金额) 普通订单就是次数，后台订单是 金额
			   /*	   * totalNum  (当rechargeType="1")
						* totalMoney(当rechargeType="2")
						* serviceId (当rechargeType="1")*/
				if(Constants.Order.ORDER_TYPE_10.equals(ordMainInfoRespDTO.getOrderType())){
					rechargeType = Constants.Order.ORDER_API_RECHARGETYPE_1;
				}
				else{
					rechargeType = Constants.Order.ORDER_API_RECHARGETYPE_2;
				}
				int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
				rechargeDTO.setTotalNum(itotalcount);
				rechargeDTO.setServiceId(ordInfoRespDTO.getAipServiceId());
				rechargeDTO.setRechargeType(rechargeType);
				int iordermoney = new Long(ordInfoRespDTO.getOrderMoney()).intValue();
				rechargeDTO.setTotalMoney(iordermoney);

				try {
					iAipCenterDataAccountRSV.dealRecharge(rechargeDTO);
					rMap.put("success", true);
				} catch (Exception e) {
					System.out.print("更新AipCenter失败：" + e.getMessage());
					//需要通知运维，去处理数据
					rMap.put("success", false);
				}
			}
			else
			{
				System.out.print("更新AipCenter失败：查不到对于的子订单" );
				//需要通知运维，去处理数据
				rMap.put("success", false);
				rMap.put("err", "更新AipCenter失败");
			}
		} catch (Exception er) {
			System.out.print("更新失败：" + er.getMessage());
			rMap.put("success", false);
			rMap.put("err", "更新订单失败");
		}
		return rMap;
	}
	/**
	 * 线下审核订单变为已失效
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderManageoffline")
	@ResponseBody
	public  Map<String, Object>  orderManageoffline(HttpServletRequest request,OrdMainInfoVO ordMainInfoVO) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		HttpSession hpptsesion = request.getSession();
		String staff_id = StaffUtil.getStaffId(hpptsesion);
		String orderid = request.getParameter("orderid");
		String subordid = request.getParameter("suborderid");

		Date orderTime = DateUtil.getNowAsDate();
		OrdMainInfoReqDTO ordMainInfoReqDTO = new OrdMainInfoReqDTO();
		ordMainInfoReqDTO.setOrderId(orderid);
		ordMainInfoReqDTO.setUpdateStaff(staff_id);
		ordMainInfoReqDTO.setOrderStatus(Constants.Order.ORDER_STATUS_03);
		OrdInfoReqDTO ordInfo = new OrdInfoReqDTO();
		ordInfo.setUpdateStaff(staff_id);
		ordInfo.setOrderId(orderid);
		ordInfo.setSubOrder(subordid);
		ordInfo.setStatus(Constants.Order.ORDER_STATUS_03);
 		try {
			iOrderMainInfoRSV.updateOrderAndSubOrdStatuss(ordMainInfoReqDTO, ordInfo);
			//查询子订单出来去更新数据：
			OrdMainInfoRespDTO ordMainInfoRespDTO=	iOrderMainInfoRSV.queryOrderDetail(ordMainInfoReqDTO);
			OrdInfoRespDTO ordInfoRespDTO = ordMainInfoRespDTO.getOrdInfoRespDTO();
			if(ordInfoRespDTO != null) {
				RechargeReqDTO rechargeDTO = new RechargeReqDTO();
				rechargeDTO.setRechargeUserId(ordInfoRespDTO.getStaffId());
				rechargeDTO.setSubOrder(ordInfoRespDTO.getSubOrder());
				int igdsid = new Long(ordInfoRespDTO.getGdsId()).intValue();
				rechargeDTO.setGdsId(igdsid);
				int iskuid = new Long(ordInfoRespDTO.getSkuId()).intValue();
				rechargeDTO.setSkuId(iskuid);
				rechargeDTO.setCatId(ordInfoRespDTO.getCatId());
				rechargeDTO.setCatFirst(ordInfoRespDTO.getCatFirst());
				rechargeDTO.setRechargeType(Constants.Order.ORDER_API_RECHARGETYPE_1);
				String periodType = Constants.Order.ORDER_API_PERIODTYPE_1;

				rechargeDTO.setPeriodType(periodType);
				int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
				rechargeDTO.setTotalNum(itotalcount);
				int iordermoney = new Long(ordInfoRespDTO.getOrderMoney()).intValue();
				rechargeDTO.setTotalMoney(iordermoney);
				rechargeDTO.setServiceId(ordInfoRespDTO.getAipServiceId());
				rechargeDTO.setStartDate(ordInfoRespDTO.getCreateTime());
				rechargeDTO.setEndDate(ordInfoRespDTO.getActiveEndTime());
				try {
					//需要去调用接口处理，将信息失效掉。
					//iAipCenterDataAccountRSV.dealRecharge(rechargeDTO);
					System.out.print("设置失效时，要将计费数据处理的接口！上线一定要处理的");
					rMap.put("success", true);
				} catch (Exception e) {
					System.out.print("更新AipCenter失败：" + e.getMessage());
					//需要通知运维，去处理数据
					rMap.put("success", false);
					rMap.put("err", "更新AipCenter失败");
				}
			}
			else
			{
				System.out.print("更新AipCenter失败：查不到对于的子订单" );
				//需要通知运维，去处理数据
				rMap.put("success", false);
				rMap.put("err", "更新订单失败:查不到对于的子订单");
			}
		} catch (Exception er) {
			rMap.put("success", false);
			rMap.put("err", "更新订单失败");
		}
		return rMap;
	}

	/**
	 * 管理员手动创建订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/createOrderBymaneger")
	@ResponseBody
	public  Map<String, Object>  createOrderBymaneger(HttpServletRequest request,OrdMainInfoVO ordMainInfoVO) {
		Map<String, Object> rMap = new HashMap<String, Object>();
		HttpSession hpptsesion = request.getSession();
		String createStaff_id = StaffUtil.getStaffId(hpptsesion); //创建人

		String gdsid      = request.getParameter("gdsid");
		String skuid      = request.getParameter("skuid");
		String api_id     = request.getParameter("api_id");
		String staffid    = request.getParameter("staffid");
		String skunum     = request.getParameter("skunum");
		String ordertype  = request.getParameter("ordertype");
		String ordermoney = request.getParameter("ordermoney");
		String inavidate  = request.getParameter("inavidate");
        //ordertype = 30
		/**
		 * 静态商品id
		 * 静态商品名称
		 * 有效期
		 * 总金额
         */
		try {

			OrdInfoReqDTO  ordInfoReqDTO =  new OrdInfoReqDTO();
			ordInfoReqDTO.setStaffId(staffid); //用户
			ordInfoReqDTO.setCreateStaff(createStaff_id);
			ordInfoReqDTO.setSkuName(Constants.Order.ORDER_GDS_30_GDSNAME);
			ordInfoReqDTO.setGdsId(Constants.Order.ORDER_GDS_30_GDSID);
			ordInfoReqDTO.setGdsName(Constants.Order.ORDER_GDS_30_GDSNAME);
			ordInfoReqDTO.setShopId(Constants.Shop.GZDATA_SHOP_ID);
			ordInfoReqDTO.setOrdertype(Constants.Order.ORDER_TYPE_30);
 			ordInfoReqDTO.setActiveEndTime(getNodatelength(inavidate));
			ordInfoReqDTO.setServiceName(Constants.Order.ORDER_GDS_30_GDSNAME);
			long lordermoney = Long.parseLong(ordermoney);//单位元
			ordInfoReqDTO.setOrderMoney(lordermoney*100);
			OrdInfoReqDTO rdInfoReqDTOResp =  iOrderInfoRSV.createOrderByallClass(ordInfoReqDTO);
			rMap.put("success", true);
 		} catch (Exception er) {
			rMap.put("success", false);
			rMap.put("err", "创建订单失败");
		}
		return rMap;
	}

	/**
	 * 获取无限期的比较日期
	 * @param
	 * @return date
	 */
	public   Date getNodatelength(String strdate) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 商品模块查询未选择商品
	 * @param model
	 * @param request
	 * @param pageModuleGoodsVO
	 * @return
	 */
	@RequestMapping(value = "/qryModuleGoods")
	public String qryModuleGoodsUnSelList(Model model, HttpServletRequest request) {
		PageResponseDTO<GdsInfoVO> pageInfo = new PageResponseDTO<GdsInfoVO>();
		try {
			String gdsname = request.getParameter("gdsName");
			int pagesize = Integer.parseInt( request.getParameter("pageSize"));
			int pageno = Integer.parseInt( request.getParameter("pageNo"));
			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			gdsInfoReqDTO.setGdsName(uRLDecoderStr(gdsname));
			gdsInfoReqDTO.setStatus("1");//已上架
			gdsInfoReqDTO.setPageSize(pagesize);
			gdsInfoReqDTO.setPageNo(pageno);
  			PageResponseDTO<GdsInfoRespDTO> gdsInfoRespPage = iGdsInfoRSV.queryGdsInfoPage(gdsInfoReqDTO);
			ObjectCopyUtil.copyObjValue(gdsInfoRespPage,pageInfo,null,false);
			List<GdsInfoVO> gdsInfoVOList = new ArrayList<GdsInfoVO>();
			if(!CollectionUtil.isEmpty(gdsInfoRespPage.getResult())){
				for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespPage.getResult()){
					GdsInfoVO gdsInfoVO = new GdsInfoVO();
					ObjectCopyUtil.copyObjValue(gdsInfoRespDTO,gdsInfoVO,null,false);
/*
					gdsInfoVO.setCatName(traslateCatName(gdsInfoVO.getCatId()));
*/
					gdsInfoVOList.add(gdsInfoVO);
				}
			}
			pageInfo.setResult(gdsInfoVOList);
			model.addAttribute("gdspageInfo", pageInfo);
		}catch (Exception e){
			log.error("查询商品列表异常");
		}
		return "order_manage :: #gdsquery" ;
	}
	private String uRLDecoderStr(String strinfo) {
		String newstrinfo = "";
		try {
			newstrinfo = URLDecoder.decode(strinfo, "utf-8");
		} catch (Exception e) {
		}
		return newstrinfo;
	}

}
