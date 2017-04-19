package com.ai.bdex.dataexchange.usercenter.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.ChnlInvoiceTaxMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.ChnlInvoiceTax;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IChnlInvoiceTaxSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;

public class ChnlInvoiceTaxSVImpl implements IChnlInvoiceTaxSV {
	
	@Autowired
	private ChnlInvoiceTaxMapper chnlInvoiceTaxMapper;

	@Override
	public int saveInvoiceTax(ChnlInvoiceTaxDTO info) throws Exception {
		ChnlInvoiceTax record = new ChnlInvoiceTax();	
		BeanUtils.copyProperties(record, info);
		record.setTaxId(SeqUtil.getLong("SEQ_CHNL_INVOICE_TAX"));
		record.setCreateStaff(info.getStaffId());
		record.setCreateTime(DateUtil.getNowAsDate());
		record.setStatus("10");//状态： 10:待审核  20:有效（审核通过）  30：失效（审核不通过）
		return chnlInvoiceTaxMapper.insertSelective(record);
	}

	@Override
	public ChnlInvoiceTaxDTO getInvoiveTaxDetail(Long taxId) throws Exception {
		ChnlInvoiceTax record = chnlInvoiceTaxMapper.selectByPrimaryKey(taxId);
		if(record!=null){
			ChnlInvoiceTaxDTO info = new ChnlInvoiceTaxDTO();
			BeanUtils.copyProperties(info, record);
			return info;
		}
		return null;
	}

	@Override
	public int doAuditTax(ChnlInvoiceTaxDTO info) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
