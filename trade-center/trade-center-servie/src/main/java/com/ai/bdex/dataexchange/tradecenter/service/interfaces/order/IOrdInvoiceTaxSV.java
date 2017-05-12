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

public interface IOrdInvoiceTaxSV {
	/**
	 * 发票开具申请
	 * 
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public Long insertOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;
	/**
	 * 更新发票开具申请数据
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public Long updateOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;

	/**
	 * 分页获取发票管理信息
	 * 
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxPage(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO)
			throws Exception;

	/**
	 * 查询发票管理信息
	 * 
	 * @param ordInvoiceTaxReqDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxList(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception;
}
