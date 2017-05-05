package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdLog;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
@Service("iOrderInfoRSV")
public class OrderInfoRSVImpl  implements IOrderInfoRSV {
	private static final Logger log = LoggerFactory.getLogger(OrderInfoRSVImpl.class);

	@Resource
	private IOrdMainInfoSV iOrdMainInfoSV;
	@Resource
	private IOrdInfoSV iOrdInfoSV;
 
	
	@Override
    public int createOrderInfo(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfoReqDTO) throws Exception
    {
		//从数据库获取商品价格  * 界面数量 是否等于界面的总价格
		iOrdMainInfoSV.creatOrderByweb(ordMainInfoReqDTO);
		iOrdInfoSV.creatsubOrderByweb(ordInfoReqDTO);
		//写日志
		OrdLog ordLog = new OrdLog();
		ordLog.setOrderId(ordMainInfoReqDTO.getOrderId());
		ordLog.setNode(node);
		ordLog.setNodeDesc(node);
		iOrdMainInfoSV.saveOrderlog();
		
		//需要调用主表和子表的实物
        // return   iDataCustomizationSV.saveDataCustomization(dataCustomizationRespDTO);
		return 1;
    }
	@Override
	public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 我的所有子订单
	@Override
	public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 商品投诉
	@Override
	public List<OrdInfoRespDTO> queryOrderByStaffGds(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 我的数据：为我的订单中已经支付成功的数据: 根据API key做唯一值
	@Override
	public List<OrdInfoRespDTO> queryAllDataByStaff(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	@Override
	public PageResponseDTO<OrdInfoRespDTO> queryOrdInfoPage(OrdInfoReqDTO ordInfoReqDTO) throws Exception {
		PageResponseDTO<OrdInfoRespDTO> ordInfoPage = new PageResponseDTO<>();
		try {
			ordInfoPage = iOrdInfoSV.queryOrdInfoPage(ordInfoReqDTO);
		} catch (Exception e) {
			log.error("分页获取订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoPage;
	}
}
