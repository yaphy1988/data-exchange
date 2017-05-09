package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInvoiceTaxMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTax;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTaxExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInvoiceTaxSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
 
@Service("iOrdInvoiceTaxSV")
public class OrdInvoiceTaxSVImpl  implements IOrdInvoiceTaxSV {
	  private static final Logger log = Logger.getLogger(OrdInvoiceTaxSVImpl.class);
	  @Autowired
	  private OrdInvoiceTaxMapper ordInvoiceTaxMapper; 

	@Override
	public Long insertOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception {
		Long orderTaxId = SeqUtil.getLong("SEQ_ORD_INVOICETAC");
		OrdInvoiceTax invoiceTax = new OrdInvoiceTax();
		invoiceTax.setOrderTaxId(orderTaxId);
		invoiceTax.setCreateTime(DateUtil.getNowAsDate());
		invoiceTax.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordInvoiceTaxReqDTO, invoiceTax, null, false);
		int code = ordInvoiceTaxMapper.insert(invoiceTax);
		return orderTaxId;
	}
	@Override
	public PageResponseDTO<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxPage(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO)
			throws Exception {
		Integer pageNo = ordInvoiceTaxReqDTO.getPageNo();
		Integer pageSize = ordInvoiceTaxReqDTO.getPageSize();
		OrdInvoiceTaxExample example = new OrdInvoiceTaxExample();
		OrdInvoiceTaxExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordInvoiceTaxReqDTO);
		example.setOrderByClause("CREATE_TIME desc");
		PageHelper.startPage(pageNo, pageSize);
		List<OrdInvoiceTax> ordInvoiceTaxList = ordInvoiceTaxMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(ordInvoiceTaxList);
		PageResponseDTO<OrdInvoiceTaxRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdInvoiceTaxRespDTO.class);
		return pageResponseDTO;
	}
	@Override
	public List<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxList(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO)
			throws Exception {
		OrdInvoiceTaxExample example = new OrdInvoiceTaxExample();
		OrdInvoiceTaxExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordInvoiceTaxReqDTO);
		List<OrdInvoiceTax> ordInvoiceTaxList = ordInvoiceTaxMapper.selectByExample(example);
		List<OrdInvoiceTaxRespDTO> taxReqDTOList = new ArrayList<OrdInvoiceTaxRespDTO>();
		if(!CollectionUtil.isEmpty(ordInvoiceTaxList)){
			for(OrdInvoiceTax invoiceTax : ordInvoiceTaxList){
				OrdInvoiceTaxRespDTO taxReqDTO = new OrdInvoiceTaxRespDTO();
				ObjectCopyUtil.copyObjValue(invoiceTax, taxReqDTO, null, false);
				taxReqDTOList.add(taxReqDTO);
			}
		}
		return taxReqDTOList;
	}

	private void initCriteria(OrdInvoiceTaxExample.Criteria criteria, OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) {
		if (ordInvoiceTaxReqDTO.getOrderTaxId()!=null) {
			criteria.andOrderTaxIdEqualTo(ordInvoiceTaxReqDTO.getOrderTaxId());
		}
		if (ordInvoiceTaxReqDTO.getTaxId()!=null) {
			criteria.andTaxIdEqualTo(ordInvoiceTaxReqDTO.getTaxId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordInvoiceTaxReqDTO.getOrderId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getShopId())) {
			criteria.andShopIdEqualTo(ordInvoiceTaxReqDTO.getShopId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getStaffId())) {
			criteria.andStaffIdEqualTo(ordInvoiceTaxReqDTO.getStaffId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getInvoiceTitle())) {
			criteria.andInvoiceTitleLike("%"+ordInvoiceTaxReqDTO.getInvoiceTitle()+"%");
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getProvinceCode())) {
			criteria.andProvinceCodeEqualTo(ordInvoiceTaxReqDTO.getProvinceCode());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxReqDTO.getStatus())) {
			criteria.andStatusEqualTo(ordInvoiceTaxReqDTO.getStatus());
		}
	}

}
