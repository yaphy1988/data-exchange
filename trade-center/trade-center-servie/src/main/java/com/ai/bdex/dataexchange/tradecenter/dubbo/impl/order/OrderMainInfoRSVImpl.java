package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrderMainInfoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdMainInfoSV;
@Service("iOrderMainInfoRSV")
public class OrderMainInfoRSVImpl  implements IOrderMainInfoRSV {
	private static final Logger log = LoggerFactory.getLogger(OrderMainInfoRSVImpl.class);

	@Resource
	private IOrdMainInfoSV iOrdMainInfoSV;
	
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
}
