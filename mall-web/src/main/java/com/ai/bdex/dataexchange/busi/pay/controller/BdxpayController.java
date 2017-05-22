package com.ai.bdex.dataexchange.busi.pay.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ai.bdex.dataexchange.busi.page.controller.HomePageController;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.page.IPageDisplayRSV;
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
	private static final Logger log = LoggerFactory.getLogger(HomePageController.class);
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
    public void alipayRequestPage(Model model,HttpServletRequest request,HttpServletResponse httpResponse){
    	String orderId = request.getParameter("orderId");
    	String suborderId = request.getParameter("subOrderid");
    	log.debug("调用支付宝接口");
    	log.equals("调用支付宝接口");
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
    		String passbackParams = URLEncoder.encode(orderId, "utf-8");
    		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	        alipayRequest.setReturnUrl("http://112.74.163.29:8083/manage-web/orderManage/myOrder");
	        alipayRequest.setNotifyUrl("http://112.74.163.29:8082/mall-web/bdxalipay/alipayNotify");//在公共参数中设置回跳和通知地址
	        String biz_content =  
		        "{" +
	            "    \"out_trade_no\":\""+orderId+"\"," +
	            "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	            "    \"total_amount\":"+0.01+"," +
	            "    \"subject\":\""+gdsName+"\"," +
	            "    \"body\":\""+skuName+"\"," +
	            "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
	            "    \"extend_params\":{" +
	            "    \"sys_service_provider_id\":\"2017050507124732\"" +
	            "    }"+
	            "  }";
	        alipayRequest.setBizContent(biz_content);//填充业务参数
        
	        /*alipayRequest.setBizContent("{" +
	                "    \"out_trade_no\":\"20150320010101001994\"," +
	                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	                "    \"total_amount\":0.01," +
	                "    \"subject\":\"Iphone6 16G\"," +
	                "    \"body\":\"Iphone6 16G\"," +
	                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
	                "    \"extend_params\":{" +
	                "    \"sys_service_provider_id\":\"2017050507124732\"" +
	                "    }"+
	                "  }");//填充业务参数
*/	        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
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
    /**
     * 异步通知：获取支付宝POST过来反馈信息
     * @param request
     * @param response
     */
    @RequestMapping(value="/alipayNotify")
    @ResponseBody
    private void alipayNotify(HttpServletRequest request,HttpServletResponse response){
    	log.error("支付宝异步通知日志开始");
    	Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = request.getParameterMap();
    	try {
    		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
        		String name = (String) iter.next();
        		String[] values = (String[]) requestParams.get(name);
        		String valueStr = "";
        		for (int i = 0; i < values.length; i++) {
        			valueStr = (i == values.length - 1) ? valueStr + values[i]
        					: valueStr + values[i] + ",";
        		}
        		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
        		log.error("支付宝异步通知日志：key="+name+";value="+valueStr);
        		params.put(name, valueStr);
        	}
    		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    		//商户订单号

    		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
    		log.error("商户订单号：out_trade_no="+out_trade_no);
    		//支付宝交易号
    		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
    		log.error("支付宝交易号：trade_no="+trade_no);
    		//交易状态
    		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
    		log.error("交易状态：trade_status="+trade_status);
    		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    		//计算得出通知验证结果
    		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
//    		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
    		
    		
    		
    		
    		
		} catch (Exception e) {
			log.error("[支付异步通知异常]异常信息:" + e.getMessage());
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

