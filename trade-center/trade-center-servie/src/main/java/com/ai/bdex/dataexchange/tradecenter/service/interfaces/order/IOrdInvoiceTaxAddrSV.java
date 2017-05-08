package com.ai.bdex.dataexchange.tradecenter.service.interfaces.order;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;

public interface IOrdInvoiceTaxAddrSV {

	/**
	 * 发票开具申请收货地址
	 * 
	 * @param ordInvoiceTaxAddrReqDTO
	 * @return
	 * @throws Exception
	 */
	public Long insertOrdInvoiceAddrTax(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) throws Exception;

	/**
	 * 查询发票收货地址信息
	 * 
	 * @param ordInvoiceTaxAddrReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdInvoiceTaxAddrRespDTO> queryOrdInvoiceTaxAddrList(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO)
			throws Exception;

}
