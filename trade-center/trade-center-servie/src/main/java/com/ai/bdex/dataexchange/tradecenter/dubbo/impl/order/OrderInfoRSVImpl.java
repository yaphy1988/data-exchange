package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
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
 
	/**
	 * 创建订单
	 */
	@Override
    public int createOrderInfo(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfoReqDTO) throws Exception
    {
		//从数据库获取商品价格  * 界面数量 是否等于界面的总价格
		iOrdMainInfoSV.creatOrderByweb(ordMainInfoReqDTO);
		iOrdInfoSV.creatsubOrderByweb(ordInfoReqDTO);
		//写日志
		OrdLog ordLog = new OrdLog();
		ordLog.setCreateStaff(ordMainInfoReqDTO.getCreateStaff());
		ordLog.setOrderId(ordMainInfoReqDTO.getOrderId());
		ordLog.setNode(Constants.Order.LOG_CODE_01);
		ordLog.setNodeDesc(Constants.Order.LOG_CODE_DESC_01);
		return iOrdMainInfoSV.saveOrderlog(ordLog); 
    }
	@Override
	public OrdInfoRespDTO querySubOrderDetail(OrdInfoRespDTO ordInfo) throws Exception {
		return null;
	}

	// 我的所有子订单
	@Override
	public List<OrdInfoRespDTO> queryOrderByStaff(OrdInfoReqDTO ordInfo) throws Exception {
		List<OrdInfoRespDTO> ordInfoList = new ArrayList<>();
		try {
			ordInfoList = iOrdInfoSV.queryOrderByStaff(ordInfo);
		} catch (Exception e) {
			log.error("分页获取子订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoList;
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
			log.error("分页获取子订单信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoPage;
	}
	public List<OrdInfoRespDTO> queryOrderInfoList(OrdInfoReqDTO ordInfoReqDTO) throws Exception{
		List<OrdInfoRespDTO> ordInfoList = new ArrayList<OrdInfoRespDTO>();
		try {
			ordInfoList = iOrdInfoSV.queryOrderInfoList(ordInfoReqDTO);
		} catch (Exception e) {
			log.error("获取子订单List信息异常:", e);
			throw new Exception(e);
		}
		return ordInfoList;
	}
}
