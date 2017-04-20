package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.ChnlInvoiceTaxMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaff;
import com.ai.bdex.dataexchange.usercenter.dao.model.ChnlInvoiceTax;
import com.ai.bdex.dataexchange.usercenter.dao.model.ChnlInvoiceTaxExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ChnlInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqInvoiceTaxDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IChnlInvoiceTaxSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("iChnlInvoiceTaxSV")
public class ChnlInvoiceTaxSVImpl implements IChnlInvoiceTaxSV {
	
	private static final Logger log = Logger.getLogger(ChnlInvoiceTaxSVImpl.class);
	
	@Autowired
	private ChnlInvoiceTaxMapper chnlInvoiceTaxMapper;
	
	@Autowired
	private AuthStaffMapper authStaffMapper;

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
		//查询审核表中是否有数据
		ChnlInvoiceTax bean = chnlInvoiceTaxMapper.selectByPrimaryKey(info.getTaxId());
		if(bean==null){
			throw new BusinessException("审核异常：无此数据!");
		}
		ChnlInvoiceTax record = new ChnlInvoiceTax();
		BeanUtils.copyProperties(record, info);
		record.setUpdateTime(DateUtil.getNowAsDate());
		int count = chnlInvoiceTaxMapper.updateByPrimaryKeySelective(record);
		if("10".equals(info.getStatus())){
			//更新authStaff表的认证状态
			AuthStaff recordStaff = new AuthStaff();
			recordStaff.setStaffId(bean.getStaffId());
			recordStaff.setAuthenFlag("1");
			recordStaff.setUpdateStaff(info.getUpdateStaff());
			recordStaff.setUpdateTime(DateUtil.getNowAsDate());
			authStaffMapper.updateByPrimaryKeySelective(recordStaff);
		}
		return count;
	}

	@Override
	public PageResponseDTO<ChnlInvoiceTaxDTO> queryTaxPage(ReqInvoiceTaxDTO taxDTO) throws Exception {
		//分页信息赋值
		int page = taxDTO.getPageNo();
		int rows = taxDTO.getPageSize();
		//查询条件赋值
		ChnlInvoiceTaxExample example = new ChnlInvoiceTaxExample();
		ChnlInvoiceTaxExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause(taxDTO.getGridQuerySortName() +" "+taxDTO.getGridQuerySortOrder());
		if(!StringUtils.isBlank(taxDTO.getStatus())){
			criteria.andStatusEqualTo(taxDTO.getStatus());
		}
		//开启分页查询，使用mybatis-PageHelper分页插件，第一个参数 PageNo，第二个参数PageSize，该设置只对紧随其后的第一次查询有效
		PageHelper.startPage(page, rows);
		//执行查询第一个mybatis查询方法，会被进行分页
		List<ChnlInvoiceTax> lists = chnlInvoiceTaxMapper.selectByExample(example);
		//使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(lists);
		log.info("queryTaxPage查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
		//按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
		PageResponseDTO<ChnlInvoiceTaxDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,ChnlInvoiceTaxDTO.class);
		return respDTO;
	}

	@Override
	public List<ChnlInvoiceTaxDTO> queryInvoiceRecord(ReqInvoiceTaxDTO input) throws Exception {
		ChnlInvoiceTaxExample example = new ChnlInvoiceTaxExample();
		ChnlInvoiceTaxExample.Criteria sql = example.createCriteria();
		sql.andStaffIdEqualTo(input.getStaffId());
		if(!StringUtil.isBlank(input.getStatus())){
			sql.andStatusEqualTo(input.getStatus());
		}
		List<ChnlInvoiceTax> info = chnlInvoiceTaxMapper.selectByExample(example);
		if(!CollectionUtil.isEmpty(info)){
			List<ChnlInvoiceTaxDTO> datas = new ArrayList<ChnlInvoiceTaxDTO>();
			for(ChnlInvoiceTax vo:info){
				ChnlInvoiceTaxDTO dto = new ChnlInvoiceTaxDTO();
				BeanUtils.copyProperties(dto, vo);
				datas.add(dto);
			}
			return datas;
		}
		return null;
	}

}
