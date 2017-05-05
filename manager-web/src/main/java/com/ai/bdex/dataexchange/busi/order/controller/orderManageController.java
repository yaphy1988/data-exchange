package com.ai.bdex.dataexchange.busi.order.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.busi.order.entity.OrdInfoVO;
import com.ai.bdex.dataexchange.busi.order.entity.OrdMainInfoVO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;

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
	public String myOrderData(Model model, OrdInfoVO ordInfoVO) {
		try {
			PageResponseDTO<OrdInfoRespDTO> pageInfo = new PageResponseDTO<OrdInfoRespDTO>();
			OrdInfoReqDTO ordReqDTO = new OrdInfoReqDTO();
			ordReqDTO.setPageNo(ordInfoVO.getPageNo());
//			if(ordInfoVO.getPageSize()!=null||ordInfoVO.getPageSize()!=0){
//				ordReqDTO.setPageSize(ordInfoVO.getPageSize());
//			}else{
				ordReqDTO.setPageSize(2);
//			}
			ordReqDTO.setPayFlag(PAY_FLAG_SUCCESS);
			pageInfo = iOrderInfoRSV.queryOrdInfoPage(ordReqDTO);
			if(!CollectionUtils.isEmpty(pageInfo.getResult())){
				for(OrdInfoRespDTO ordRespDTO :pageInfo.getResult()){
					if(AIP_CAT_ID.equals(ordRespDTO.getProductType())){
						 //根据APIID查找API接口信息
	                    if(StringUtil.isNotBlank(ordRespDTO.getSkuInfo())){
	                    	List<AipServiceInfoDTO> apiServiceList = iAipServiceInfoRSV.selectServiceByServiceId(ordRespDTO.getSkuInfo());
	            			if(CollectionUtils.isNotEmpty(apiServiceList)){
	            				ordRespDTO.setApiName(apiServiceList.get(0).getServiceName());
	            			}
	                    }
					}
					
				}
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error("查询我的数据列表失败！原因是：" + e.getMessage());
		}
		return "order/div/myOrderData";
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
   	public String myOrderList(Model model, OrdMainInfoVO ordMainInfoVO) {
   		try {
   			PageResponseDTO<OrdMainInfoRespDTO> pageInfo = new PageResponseDTO<OrdMainInfoRespDTO>();
   			OrdMainInfoReqDTO ordMainReqDTO = new OrdMainInfoReqDTO();
   			ordMainReqDTO.setPageNo(ordMainInfoVO.getPageNo());
   			ordMainReqDTO.setPageSize(PAGE_SIZE);
   			ordMainReqDTO.setOrderType(ORDER_TYPE_COMMON);
   			pageInfo = iOrderMainInfoRSV.queryOrdMainInfoPage(ordMainReqDTO);
   			if(!CollectionUtils.isEmpty(pageInfo.getResult())){
   				for(OrdMainInfoRespDTO ordMainRespDTO :pageInfo.getResult()){
   					OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
   					ordInfoReqDTO.setOrderId(ordMainRespDTO.getOrderId());
   					List<OrdInfoRespDTO> ordInfoList = iOrderInfoRSV.queryOrderInfoList(ordInfoReqDTO);
   					if(CollectionUtils.isNotEmpty(ordInfoList)){
   						//一个订单只有一个子订单
   						ordMainRespDTO.setOrdInfoRespDTO(ordInfoList.get(0));
   					}
   					
   				}
   			}
   			model.addAttribute("pageInfo", pageInfo);
   		} catch (Exception e) {
   			logger.error("查询我的订单列表失败！原因是：" + e.getMessage());
   		}
   		return "order/div/myOrderList";
   	}
}
