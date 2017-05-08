package com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order;


import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;

 
public interface IOrdInvoiceTaxRSV {
	/**
	 * 发票开具申请
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public Long insertOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;
	/**
	 * 分页获取发票管理信息
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxPage(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;
	/**
	 * 获取发票管理List信息
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxList(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;
	
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
