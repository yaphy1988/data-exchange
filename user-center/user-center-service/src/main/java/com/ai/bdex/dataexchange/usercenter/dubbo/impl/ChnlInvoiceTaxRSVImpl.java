package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
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
	public ChnlInvoiceTaxDTO getInvoiveTaxDetail(Long taxId) throws BusinessException {
		try {
			return iChnlInvoiceTaxSV.getInvoiveTaxDetail(taxId);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("获取审核数据异常：" + e.getMessage());
				throw new BusinessException("获取审核数据异常:" + e.getMessage());
			}		
		}
	}

	@Override
	public int doAuditTax(ChnlInvoiceTaxDTO info) throws BusinessException {
		try {
			return iChnlInvoiceTaxSV.doAuditTax(info);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("审核异常：" + e.getMessage());
				throw new BusinessException("审核异常:" + e.getMessage());
			}		
		}	
	}

	@Override
	public List<ChnlInvoiceTaxDTO> queryInvoiceRecord(ReqInvoiceTaxDTO input) throws BusinessException {
		try {
			return iChnlInvoiceTaxSV.queryInvoiceRecord(input);
		} catch (Exception e) {
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			else {
				log.error("查询认证记录异常：" + e.getMessage());
				throw new BusinessException("查询认证记录异常:" + e.getMessage());
			}		
		}
	}

}
