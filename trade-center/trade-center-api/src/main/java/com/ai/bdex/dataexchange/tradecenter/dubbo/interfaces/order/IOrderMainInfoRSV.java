package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;

 
public interface IOrderMainInfoRSV {
	/**
	 * 分页查询主订单数据
	 * @param ordMainInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<OrdMainInfoRespDTO> queryOrdMainInfoPage(OrdMainInfoReqDTO ordMainInfoReqDTO) throws Exception;

}
