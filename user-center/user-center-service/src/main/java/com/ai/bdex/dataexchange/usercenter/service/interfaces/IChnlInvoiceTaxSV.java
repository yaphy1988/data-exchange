package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;

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
}
