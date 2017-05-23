package com.ai.bdex.dataexchange.busi.pay.controller;

import java.io.IOException;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.order.controller.OrderController;
import com.ai.bdex.dataexchange.busi.page.controller.HomePageController;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
import com.ai.bdex.dataexchange.util.StaffUtil;
import com.ai.bdex.dataexchange.util.ThymeleafToolsUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

@Controller
@RequestMapping(value="/bdxalipay")
public class BdxpayController {

    private static final String MODULE = BdxpayController.class.getName();
	private static final Logger log = LoggerFactory.getLogger(BdxpayController.class);
	private final static String API_SERVICE_NAME_TMP = "API_SERVICE_NAME_TMP";//API服务名称接口获取不到数据
	@DubboConsumer(timeout = 30000)
	IPageDisplayRSV iPageDisplayRSV;
	
	@DubboConsumer(timeout = 30000)
	IOrderInfoRSV iOrderInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IAipServiceInfoRSV iAipServiceInfoRSV;
	
	@DubboConsumer(timeout = 30000)
	IGdsInfoRSV iGdsInfoRSV;
	
	@Autowired
	private AlipayClient alipayClient;
	
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
    		Long gdsId = ordInfoRespDTO.getGdsId();
    		int orderAmout = ordInfoRespDTO.getOrderAmount();
    		String moneyAmout = new ThymeleafToolsUtil().formatMoneyClean(orderAmout);
    		String gdsName = ordInfoRespDTO.getGdsName();
    		String skuName = ordInfoRespDTO.getSkuName();
//    		String passbackParams = "orderId="+URLEncoder.encode(orderId, "utf-8")+"&suborderId="
//    				+URLEncoder.encode(String.valueOf(suborderId),"utf-8")+"&gdsName"+URLEncoder.encode(gdsName, "utf-8");
    		String passbackParams = "suborderId="+URLEncoder.encode(suborderId, "utf-8")+
    				"staffId="+StaffUtil.getStaffId(session);
    		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	        alipayRequest.setReturnUrl("http://112.74.163.29:8083/manage-web/orderManage/myOrder");
	        alipayRequest.setNotifyUrl("http://112.74.163.29:8082/mall-web/order/alipayNotify");//在公共参数中设置回跳和通知地址
	        String biz_content =  
		        "{" +
	            "    \"out_trade_no\":\""+orderId+"\"," +
	            "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	            "    \"total_amount\":"+0.01+"," +
	            "    \"subject\":\""+gdsName+"\"," +
	            "    \"body\":\""+skuName+"\"," +
	            "    \"passback_params\":\""+passbackParams+"\"," +
	            "    \"extend_params\":{" +
	            "    \"sys_service_provider_id\":\"2017050507124732\"" +
	            "    }"+
	            "  }";
	        alipayRequest.setBizContent(biz_content);//填充业务参数
	        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            httpResponse.setContentType("text/html;charset=" + "GBK");
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            log.error("[发起支付请求失败]异常信息:" + e.getMessage());
        } catch (Exception e) {
        	log.error("[发起支付请求失败]异常信息:" + e.getMessage());
			e.printStackTrace();
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
			Long gdsId = ordInfoRespDTO.getGdsId();
 			gdsInfoReqDTO.setGdsId(gdsId.intValue());
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

