package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.DateUtil;
@Service("iOrderMainInfoRSV")
public class OrderMainInfoRSVImpl  implements IOrderMainInfoRSV {
	private static final Logger log = LoggerFactory.getLogger(OrderMainInfoRSVImpl.class);

	@Resource
	private IOrdMainInfoSV iOrdMainInfoSV;
	@Resource
	private IOrdInfoSV iOrdInfoSV;
	
	public PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception{
		PageResponseDTO<OrdMainInfoRespDTO> ordMainInfoPage = new PageResponseDTO<>();
		try {
			ordMainInfoPage = iOrdMainInfoSV.queryOrdMainInfoPage(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("分页获取订单主信息异常:", e);
			throw new Exception(e);
		}
		return ordMainInfoPage;
	}
	public int cancelOrder(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
            throw new Exception("取消订单异常，oderId入参为空");
        }
		if(StringUtil.isBlank(ordMainInfoReqDTO.getOrderStatus())){
			 throw new Exception("取消订单异常，status入参为空");
		}
		int code=0;
		try {
			//取消主订单
			code= iOrdMainInfoSV.cancelOrderMainInfo(ordMainInfoReqDTO);
			if(code>0){
				OrdInfoReqDTO ordInfoReqDTO = new OrdInfoReqDTO();
				ordInfoReqDTO.setOrderId(ordMainInfoReqDTO.getOrderId());
				ordInfoReqDTO.setStatus(ordMainInfoReqDTO.getOrderStatus());
				//取消主订单成功，更新子订单状态
				code=iOrdInfoSV.cancelOrderInfo(ordInfoReqDTO);
			}
		} catch (Exception e) {
			log.error("取消订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
	public int updateOrderMainInfo(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
            throw new Exception("更新订单异常，oderId入参为空");
        }
		int code=0;
		try {
			code= iOrdMainInfoSV.updateOrderMainInfo(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("更新订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
	public List<OrdMainInfoRespDTO> queryOrdMainInfoList(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception {
		List<OrdMainInfoRespDTO> respDTOList = new ArrayList<OrdMainInfoRespDTO>();
		try {
			respDTOList= iOrdMainInfoSV.queryOrdMainInfoList(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("获取订单主表信息异常:", e);
			throw new Exception(e);
		}
		return respDTOList;
	}
	/***
	 * 更新主订单和子订单的状态信息
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAndSubOrdStatuss(OrdMainInfoReqDTO ordMainInfoReqDTO,OrdInfoReqDTO ordInfo) throws Exception {
		if (ordMainInfoReqDTO ==null ||StringUtil.isBlank(ordMainInfoReqDTO.getOrderId())){
			throw new Exception("更新订单异常，oderId入参为空");
		}
		int code=0;

		Date updateTime =  DateUtil.getNowAsDate();
		ordMainInfoReqDTO.setUpdateTime(updateTime);
		ordInfo.setUpdateTime(updateTime);
		try {
			code= iOrdMainInfoSV.updateOrderMainInfo(ordMainInfoReqDTO);
		} catch (Exception e) {
			log.error("更新主订单信息异常:", e);
			throw new Exception(e);
		}
		try {
			code= iOrdInfoSV.updateOrderStatus(ordInfo);
		} catch (Exception e) {
			log.error("更新子订单信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
}
