package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IChnlInvoiceTaxRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IChnlInvoiceTaxSV;

@Service("iChnlInvoiceTaxRSV")
public class ChnlInvoiceTaxRSVImpl implements IChnlInvoiceTaxRSV {
	private static final Logger log = Logger.getLogger(ChnlInvoiceTaxRSVImpl.class);
	
	@Autowired
	private IChnlInvoiceTaxSV iChnlInvoiceTaxSV;

	@Override
	public int saveInvoiceTax(ChnlInvoiceTaxDTO info) throws BusinessException {
		try {
			return iChnlInvoiceTaxSV.saveInvoiceTax(info);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("提交审核异常：" + e.getMessage());
				throw new BusinessException("提交审核异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public ChnlInvoiceTaxDTO getInvoiveTaxDetail(String taxId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
