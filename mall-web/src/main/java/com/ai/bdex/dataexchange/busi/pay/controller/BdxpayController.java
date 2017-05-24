package com.ai.bdex.dataexchange.busi.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayRequestReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.PayResultReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsSkuRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.pay.IPayInfoRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.ThymeleafToolsUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

@Controller
@RequestMapping(value="/bdxalipay")
public class BdxpayController {

    private static final String MODULE = BdxpayController.class.getName();
	private static final Logger log = LoggerFactory.getLogger(BdxpayController.class);
	private final static String API_SERVICE_NAME_TMP = "API_SERVICE_NAME_TMP";//API服务名称接口获取不到数据
	@DubboConsumer(timeout = 30000)
	IPayInfoRSV iOrdPayRSV;
	
	@DubboConsumer(timeout = 30000)
	IOrderInfoRSV iOrderInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IAipServiceInfoRSV iAipServiceInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IOrderMainInfoRSV iOrderMainInfoRSV ;
	
	@DubboConsumer(timeout = 30000)
	IGdsSkuRSV iGdsSkuRSV;
	
    @DubboConsumer(timeout = 30000)
   IAipCenterDataAccountRSV iAipCenterDataAccountRSV;
    
	@Autowired
	private AlipayClient alipayClient;
	
    @Value("${application.alipay.appId:}")
    private  String APPID;
    
    @Value("${application.alipay.charset:}")
    private  String CHARSET;
    
    @Value("${application.alipay.alipayPulicKey:}")
    private  String ALIPAY_PUBLIC_KEY;
    
    @Value("${application.alipay.signType:}")
    private  String SIGNTYPE;
    
    @Value("${application.alipay.returnUrl:}")
    private  String RETURN_URL;
    
    @Value("${application.alipay.notifyUrl:}")
    private  String NOTIFY_URL;
    
    /**
     * 支付宝：支付接口（alipay.trade.page.pay）
     * @param model
     * @param request
     * @param httpResponse
     */
    @RequestMapping(value="/alipayRequestPage")
    @ResponseBody
    public void alipayRequestPage(Model model,HttpServletRequest request,HttpServletResponse httpResponse,
    		HttpSession session){
    	String orderId = request.getParameter("orderId");
    	String suborderId = request.getParameter("subOrderid");
    	try {
    		//查询订单/商品信息
    		OrdInfoRespDTO ordInfoRespDTO = this.queryOrdMainInfoByorderId(orderId,suborderId);
    		int gdsId = ordInfoRespDTO.getGdsId();
    		long orderAmout = ordInfoRespDTO.getOrderMoney();
    		String payment = new ThymeleafToolsUtil().formatMoneyClean(new Long(orderAmout).intValue());
    		String gdsName = ordInfoRespDTO.getGdsName();
    		String skuName = ordInfoRespDTO.getSkuName();
    		
    		String staffId = StaffUtil.getStaffId(session);
    		String passbackParams = URLEncoder.encode(suborderId+","+staffId, "utf-8");
    		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	        alipayRequest.setReturnUrl(RETURN_URL);
	        alipayRequest.setNotifyUrl(NOTIFY_URL);//在公共参数中设置回跳和通知地址
	        String biz_content = "{" +
	            "    \"out_trade_no\":\""+orderId+"\"," +
	            "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	            "    \"total_amount\":"+0.01+"," +
	            "    \"subject\":\""+gdsName+"\"," +
	            "    \"body\":\""+skuName+"\"," +
	            "    \"passback_params\":\""+passbackParams+"\"," +
	            "    \"extend_params\":{" +
	            "    \"sys_service_provider_id\":\""+APPID+"\"" +
	            "    }"+
	            "  }";
	        alipayRequest.setBizContent(biz_content);//填充业务参数
	        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            httpResponse.setContentType("text/html;charset=" + CHARSET);
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
            try {
            	PayRequestReqDTO payRequestReqDTO = new PayRequestReqDTO();
         		payRequestReqDTO.setOrderId(orderId);
         		payRequestReqDTO.setPayment(orderAmout);
         		payRequestReqDTO.setRequestTime(DateUtil.getNowAsDate());
         		payRequestReqDTO.setCreateTime(DateUtil.getNowAsDate());
         		payRequestReqDTO.setCreateStaff(staffId);
         		iOrdPayRSV.insertPayRequst(payRequestReqDTO);
             
			} catch (Exception e) {
				log.error("[发起支付请求写入t_pay_requst失败]异常信息:" + e.getMessage());
			}
    	} catch (AlipayApiException | IOException e) {
            log.error("[发起支付请求失败]异常信息:" + e);
        } catch (Exception e) {
        	log.error("[发起支付请求失败]异常信息:" + e);
		}
    }
	/**
	 * 异步通知：获取支付宝POST过来反馈信息
	 * 注意：异步的，第一次收到订单信息（以下都称之为“通知”）是与返回页近乎等同或等同的同步时间，
	 * 	        在判断不成功的情况下，会收到第二次第三次等次数的通知，时间间隔从最先的一两分钟，到后面的几个小时。
	 *      失效时间是48小时
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/alipayNotify")
	@ResponseBody
	public void alipayNotify(Model model, HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = request.getParameterMap();
    	response.setContentType("text/html;charset=" + CHARSET);
    	PrintWriter writer = null;
    	try {
    		writer = response.getWriter();
	    	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    		String name = (String) iter.next();
	    		String[] values = (String[]) requestParams.get(name);
	    		String valueStr = "";
	    		for (int i = 0; i < values.length; i++) {
	    			valueStr = (i == values.length - 1) ? valueStr + values[i]
	    					: valueStr + values[i] + ",";
	    		}
	    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//	    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	    		log.error("通知参数：key="+name+";value="+valueStr);
	    		params.put(name, valueStr);
	    	}
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			log.error("商户订单号：out_trade_no="+out_trade_no);
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			log.error("支付宝交易号：trade_no="+trade_no);
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			log.error("交易状态：trade_status="+trade_status);
			//子订单编号
			String passback_params = new String(request.getParameter("passback_params").getBytes("ISO-8859-1"),"UTF-8");
			passback_params = URLEncoder.encode(passback_params, "utf-8");
			String[] parms = passback_params.split(",");
			String subOrderId = parms[0];
			String staffId = parms[1];
			log.error("子订单编号：out_trade_no="+subOrderId);
			log.error("staffId：staffId="+staffId);
			//异步通知验签结果
			boolean verify_result=false;
			try {
				//验签SIGNTYPE必须与加签中的一致
				verify_result = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGNTYPE);
			} catch (AlipayApiException e) {
				log.error("异步通知验签：code="+e.getErrCode()+",msg="+e.getErrMsg()+",e:"+e);
			}catch (Exception e) {
				log.error("异步通知验签："+e.getMessage()+",e:"+e);
			}
			log.error("异步通知验签：verify_result="+verify_result);
			if(verify_result){
				if (trade_status.equals("TRADE_SUCCESS")){
					//
					/*
					PayResultReqDTO payResultReqDTO = new PayResultReqDTO();
					payResultReqDTO.setOrderId(out_trade_no);//商户订单号
					payResultReqDTO.setPayTransNo(trade_no);//支付宝交易号
					payResultReqDTO.setPayStatus("00");
					payResultReqDTO.setPayment(Long.valueOf("100"));
					payResultReqDTO.setCreateTime(DateUtil.getNowAsDate());
					payResultReqDTO.setRequestTime(requestTime);
					this.payInsertPayResult(payResultReqDTO);
					*/
					boolean baseResponse= this.pay_successDone(out_trade_no,subOrderId,staffId);
		            if (baseResponse) {
		            	log.error("支付反馈：success");
		            	writer.write("success"); 
		            } else { 
		            	log.error("支付反馈：fail");
		            	writer.write("fail");  
		            } 
				}
			}else{//验证失败
				writer.write("fail");
				log.error("[支付宝异步通知验签失败]异常信息fail:"+verify_result);
			}
			writer.flush();
			writer.close();
    	}catch (Exception e) {
    		log.error("[支付宝异步通知异常]异常信息:" + e);
    	}
	}
	
	private void payInsertPayResult(PayResultReqDTO payResultReqDTO){
		try {
			iOrdPayRSV.insertPayResult(payResultReqDTO);
		} catch (Exception e) {
			log.equals("[支付成功写入支付结果失败]，异常："+e.getMessage());
		}
	}
	/**
	 * 支付成功模拟修改后台数据
	 * @param
	 * @param
	 * @return
	 */
	public boolean pay_successDone(String orderId,String subOrderId,String staffId) {
    	Map<String, Object> rMap = new HashMap<String, Object>();
    	try {
	    	
			String staff_id = staffId;
			String orderid = orderId;
			String subordid = subOrderId;
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
		
			OrdMainInfoRespDTO ordMainInfoRespDTO=	iOrderMainInfoRSV.queryOrderDetail(ordMainInfoReqDTO);
			if(ordMainInfoRespDTO.getOrderStatus().equals(Constants.Order.ORDER_STATUS_02))
			{
				return  true;
			}
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
				rechargeDTO.setRechargeUserId(ordMainInfoRespDTO.getStaffId());
				if(Constants.Order.ORDER_TYPE_30.equals(ordMainInfoRespDTO.getOrderType()))
				{
					/*int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
					rechargeDTO.setTotalNum(itotalcount);*/
				}
				else if(Constants.Order.ORDER_TYPE_10.equals(ordMainInfoRespDTO.getOrderType()) || Constants.Order.ORDER_TYPE_20.equals(ordMainInfoRespDTO.getOrderType()) ){
					if(Constants.Order.ORDER_TYPE_10.equals(ordMainInfoRespDTO.getOrderType()) ){
						int iskuid = new Long(ordInfoRespDTO.getSkuId()).intValue();
						rechargeDTO.setSkuId(iskuid);
					}

					int itotalcount = new Long(ordInfoRespDTO.getBuyAllCount()).intValue();
					rechargeDTO.setTotalNum(itotalcount);
					rechargeDTO.setCatId(ordInfoRespDTO.getCatId());
					rechargeDTO.setCatFirst(ordInfoRespDTO.getCatFirst());
					if(ordInfoRespDTO.getAipServiceId() != null)
					{
						rechargeDTO.setServiceId(ordInfoRespDTO.getAipServiceId() );
					}
				}
				rechargeDTO.setRechargeUserId(ordInfoRespDTO.getStaffId());
				rechargeDTO.setOrderId(orderid);
				rechargeDTO.setSubOrder(ordInfoRespDTO.getSubOrder());
				rechargeDTO.setGdsId(igdsid);

				String periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				String  inavidate =  Constants.Order.ORDER_API_NODATE;
				if (  ordInfoRespDTO.getActiveEndTime() == null  ) {
					//大于50年,就是无限期了
					periodType = Constants.Order.ORDER_API_PERIODTYPE_2;
				}
				else	{
					periodType = Constants.Order.ORDER_API_PERIODTYPE_1;
				}
				rechargeDTO.setStartDate(ordInfoRespDTO.getCreateTime());
				rechargeDTO.setEndDate(ordInfoRespDTO.getActiveEndTime());
				rechargeDTO.setPeriodType(periodType);
				String packageType = ordMainInfoRespDTO.getOrderType();
				rechargeDTO.setPackageType(packageType);
				int iordermoney = new Long(ordInfoRespDTO.getOrderMoney()).intValue();
				rechargeDTO.setTotalMoney(iordermoney);
				try {
					iAipCenterDataAccountRSV.dealRecharge(rechargeDTO);
					iOrderMainInfoRSV.updateOrderAndSubOrdStatuss(ordMainInfoReqDTO, ordInfo);
					return true;
				} catch (Exception e) {
					log.error("更新AipCenter失败：" + e.getMessage());
					return false;
				}
			}else{
				return false;
			}
		} catch (Exception er) {
			System.out.print("更新失败：" + er.getMessage());
			return false;
		}
	}
    private OrdInfoRespDTO queryOrdMainInfoByorderId(String orderId,String subOrderId) throws Exception{
    	if(StringUtils.isBlank(orderId)){
    		throw new BusinessException("订单编号不能为空，orderId="+orderId);
    	}
    	if(StringUtils.isBlank(subOrderId)){
    		throw new BusinessException("子订单编号不能为空，orderId="+orderId);
    	}
    	OrdInfoRespDTO ordInfoRespDTO= new OrdInfoRespDTO();
    	OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
    	ordInfoReqDTO.setOrderId(orderId);
    	ordInfoReqDTO.setSubOrder(subOrderId);
    	List<OrdInfoRespDTO> orderInfoList = iOrderInfoRSV.queryOrderInfoList(ordInfoReqDTO);
    	if(!CollectionUtils.isEmpty(orderInfoList)){
    		ordInfoRespDTO = orderInfoList.get(0);
			//获取服务ID，服务名称，商品名称
			//获取名称
			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
			int gdsId = ordInfoRespDTO.getGdsId();
 			gdsInfoReqDTO.setGdsId(gdsId);
			gdsInfoRespDTO =  iGdsInfoRSV.queryGdsInfo(gdsInfoReqDTO); 
			if(gdsInfoRespDTO != null)
			{ 
				    ordInfoReqDTO.setAipServiceId(Integer.toString(gdsInfoRespDTO.getApiId()));
                    try{
                        List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(String.valueOf(gdsInfoRespDTO.getApiId()));
                        if(!CollectionUtil.isEmpty(apiServiceList))
                        {
                            ordInfoReqDTO.setServiceName(apiServiceList.get(0).getServiceName());
                         }
                    }
                    catch(Exception e1){
                        //服务名称取不到
                        ordInfoReqDTO.setServiceName(API_SERVICE_NAME_TMP);
                    }

					ordInfoReqDTO.setCatFirst(gdsInfoRespDTO.getCatFirst());
					ordInfoReqDTO.setCatId(gdsInfoRespDTO.getCatId());
					String gdsname = gdsInfoRespDTO.getGdsName();
					ordInfoRespDTO.setGdsName(gdsname);
			}
    	}
    	return ordInfoRespDTO;
    }
}

