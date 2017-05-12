package com.ai.bdex.dataexchange.tradecenter.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInvoiceTaxAddrMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdInvoiceTaxMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTax;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTaxAddr;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTaxAddrExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdInvoiceTaxExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdMainInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdMainInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInvoiceTaxAddrSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInvoiceTaxSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
 
@Service("iOrdInvoiceTaxAddrSV")
public class OrdInvoiceTaxAddrSVImpl  implements IOrdInvoiceTaxAddrSV {
	  private static final Logger log = Logger.getLogger(OrdInvoiceTaxAddrSVImpl.class);
	  @Autowired
	  private OrdInvoiceTaxAddrMapper ordInvoiceTaxAddrMapper; 

	@Override
	public Long insertOrdInvoiceAddrTax(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) throws Exception{
		OrdInvoiceTaxAddr invoiceTaxAddr = new OrdInvoiceTaxAddr();
		invoiceTaxAddr.setOrderTaxId(ordInvoiceTaxAddrReqDTO.getOrderTaxId());
		invoiceTaxAddr.setCreateTime(DateUtil.getNowAsDate());
		invoiceTaxAddr.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordInvoiceTaxAddrReqDTO, invoiceTaxAddr, null, false);
		int code = ordInvoiceTaxAddrMapper.insert(invoiceTaxAddr);
		return Long.valueOf(code);
	}
	@Override
	public Long updateOrdInvoiceAddrTax(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) throws Exception{
		OrdInvoiceTaxAddr invoiceTaxAddr = new OrdInvoiceTaxAddr();
		OrdInvoiceTaxAddrExample example = new OrdInvoiceTaxAddrExample();
		OrdInvoiceTaxAddrExample.Criteria criteria = example.createCriteria();
		if (ordInvoiceTaxAddrReqDTO.getOrderTaxId()!=null) {
			criteria.andOrderTaxIdEqualTo(ordInvoiceTaxAddrReqDTO.getOrderTaxId());
		}
		invoiceTaxAddr.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(ordInvoiceTaxAddrReqDTO, invoiceTaxAddr, null, false);
		int code = ordInvoiceTaxAddrMapper.updateByExampleSelective(invoiceTaxAddr, example);
		return Long.valueOf(code);
	}
	
	@Override
	public List<OrdInvoiceTaxAddrRespDTO> queryOrdInvoiceTaxAddrList(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO)
			throws Exception {
		OrdInvoiceTaxAddrExample example = new OrdInvoiceTaxAddrExample();
		OrdInvoiceTaxAddrExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, ordInvoiceTaxAddrReqDTO);
		List<OrdInvoiceTaxAddr> ordInvoiceTaxAddrList = ordInvoiceTaxAddrMapper.selectByExample(example);
		List<OrdInvoiceTaxAddrRespDTO> taxAddrReqDTOList = new ArrayList<OrdInvoiceTaxAddrRespDTO>();
		if(!CollectionUtil.isEmpty(ordInvoiceTaxAddrList)){
			for(OrdInvoiceTaxAddr invoiceTaxAddr : ordInvoiceTaxAddrList){
				OrdInvoiceTaxAddrRespDTO taxAddrReqDTO = new OrdInvoiceTaxAddrRespDTO();
				ObjectCopyUtil.copyObjValue(invoiceTaxAddr, taxAddrReqDTO, null, false);
				taxAddrReqDTOList.add(taxAddrReqDTO);
			}
		}
		return taxAddrReqDTOList;
	}

	private void initCriteria(OrdInvoiceTaxAddrExample.Criteria criteria, OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) {
		if (ordInvoiceTaxAddrReqDTO.getOrderTaxId()!=null) {
			criteria.andOrderTaxIdEqualTo(ordInvoiceTaxAddrReqDTO.getOrderTaxId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxAddrReqDTO.getOrderId())) {
			criteria.andOrderIdEqualTo(ordInvoiceTaxAddrReqDTO.getOrderId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxAddrReqDTO.getShopId())) {
			criteria.andShopIdEqualTo(ordInvoiceTaxAddrReqDTO.getShopId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxAddrReqDTO.getStaffId())) {
			criteria.andStaffIdEqualTo(ordInvoiceTaxAddrReqDTO.getStaffId());
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxAddrReqDTO.getInvoiceTitle())) {
			criteria.andInvoiceTitleLike("%"+ordInvoiceTaxAddrReqDTO.getInvoiceTitle()+"%");
		}
		if (StringUtil.isNotBlank(ordInvoiceTaxAddrReqDTO.getStatus())) {
			criteria.andStatusEqualTo(ordInvoiceTaxAddrReqDTO.getStatus());
		}
	}

}
