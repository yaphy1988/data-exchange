package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;

public interface IChnlInvoiceTaxRSV {

	/**
	 * 提交认证审核
	 * @param info
	 * @return
	 * @throws BusinessException
	 */
	public int saveInvoiceTax(ChnlInvoiceTaxDTO info) throws BusinessException;
	
	/**
	 * 查询审核详情数据
	 * @param taxId
	 * @return
	 * @throws BusinessException
	 */
	public ChnlInvoiceTaxDTO getInvoiveTaxDetail(String taxId)throws BusinessException;
}
