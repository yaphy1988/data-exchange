package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;

public interface IChnlInvoiceTaxSV {

	/**
	 * 提交认证审核
	 * @param info
	 * @return
	 * @throws BusinessException
	 */
	public int saveInvoiceTax(ChnlInvoiceTaxDTO info) throws Exception;
	
	/**
	 * 查询审核详情数据
	 * @param taxId
	 * @return
	 * @throws BusinessException
	 */
	public ChnlInvoiceTaxDTO getInvoiveTaxDetail(Long taxId)throws Exception;
	
	/**
	 * 审核操作
	 * @return
	 * @throws Exception
	 */
	public int doAuditTax(ChnlInvoiceTaxDTO info)throws Exception;
	
	/**
	 * 分页获取认证审核数据
	 * @param taxDTO
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<ChnlInvoiceTaxDTO> queryTaxPage(ReqInvoiceTaxDTO taxDTO) throws Exception;
	
	
	/**
	 * 根据用户id查询审核提交记录
	 * @return
	 * @throws BusinessException
	 */
	public List<ChnlInvoiceTaxDTO> queryInvoiceRecord(ReqInvoiceTaxDTO input)throws Exception;
	
	/**
	 * 更新审核记录
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public int updateCheckInfo(ChnlInvoiceTaxDTO info)throws Exception;
}
