package com.ai.bdex.dataexchange.tradecenter.dubbo.impl.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxAddrRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.order.OrdInvoiceTaxRespDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.order.IOrdInvoiceTaxRSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInvoiceTaxAddrSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.order.IOrdInvoiceTaxSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
@Service("iOrdInvoiceTaxRSV")
public class OrdInvoiceTaxRSVImpl  implements IOrdInvoiceTaxRSV {
	private static final Logger log = LoggerFactory.getLogger(OrdInvoiceTaxRSVImpl.class);

	@Resource
	private IOrdInvoiceTaxSV iOrdInvoiceTaxSV;
	@Resource
	private IOrdInvoiceTaxAddrSV iOrdInvoiceTaxAddrSV;
	public Long insertOrdInvoice(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception {
		if (ordInvoiceTaxReqDTO ==null){
            throw new Exception("发票开具申请异常，入参为空");
        }
		if(ordInvoiceTaxReqDTO.getTaxId()==null){
            throw new Exception("发票开具申请异常，tax_id入参为空");
		}
		Long orderTaxId;
		try {
			orderTaxId= iOrdInvoiceTaxSV.insertOrdInvoiceTax(ordInvoiceTaxReqDTO);
			OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO = new OrdInvoiceTaxAddrReqDTO();
			//保存发票开具申请收货地址
			if(orderTaxId>0){
				ObjectCopyUtil.copyObjValue(ordInvoiceTaxReqDTO, ordInvoiceTaxAddrReqDTO, null, false);
				ordInvoiceTaxAddrReqDTO.setOrderTaxId(orderTaxId);
				ordInvoiceTaxAddrReqDTO.setOrderId(ordInvoiceTaxReqDTO.getOrderId());
				ordInvoiceTaxAddrReqDTO.setContactInfo(ordInvoiceTaxReqDTO.getTaxAddr());
				ordInvoiceTaxAddrReqDTO.setPhone(ordInvoiceTaxReqDTO.getTaxAddrPhone());
				iOrdInvoiceTaxAddrSV.insertOrdInvoiceAddrTax(ordInvoiceTaxAddrReqDTO);
			}
		} catch (Exception e) {
			log.error("发票开具申请信息异常:", e);
			throw new Exception(e);
		}
		return orderTaxId;
	}
	public Long updateOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception {
		if (ordInvoiceTaxReqDTO ==null){
            throw new Exception("发票开具申请更新异常，入参为空");
        }
		if(ordInvoiceTaxReqDTO.getOrderTaxId()==null){
            throw new Exception("发票开具申请更新异常，order_tax_id入参为空");
		}
		Long orderTaxId;
		try {
			orderTaxId= iOrdInvoiceTaxSV.updateOrdInvoiceTax(ordInvoiceTaxReqDTO);
		} catch (Exception e) {
			log.error("发票开具申请更新信息异常:", e);
			throw new Exception(e);
		}
		return orderTaxId;
	}
	@Override
	public Long insertOrdInvoiceTax(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception {
		if (ordInvoiceTaxReqDTO ==null){
            throw new Exception("发票开具申请异常，入参为空");
        }
		if(ordInvoiceTaxReqDTO.getTaxId()==null){
            throw new Exception("发票开具申请异常，tax_id入参为空");
		}
		Long orderTaxId;
		try {
			orderTaxId= iOrdInvoiceTaxSV.insertOrdInvoiceTax(ordInvoiceTaxReqDTO);
		} catch (Exception e) {
			log.error("发票开具申请信息异常:", e);
			throw new Exception(e);
		}
		return orderTaxId;
	}
	@Override
	public PageResponseDTO<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxPage(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO)
			throws Exception{
		PageResponseDTO<OrdInvoiceTaxRespDTO> pageInfo = new PageResponseDTO<OrdInvoiceTaxRespDTO>();
		try {
			pageInfo= iOrdInvoiceTaxSV.queryOrdInvoiceTaxPage(ordInvoiceTaxReqDTO);
		} catch (Exception e) {
			log.error("分页获取发票管理信息异常:", e);
			throw new Exception(e);
		}
		return pageInfo;
	}
	public List<OrdInvoiceTaxRespDTO> queryOrdInvoiceTaxList(OrdInvoiceTaxReqDTO ordInvoiceTaxReqDTO) throws Exception{
		List<OrdInvoiceTaxRespDTO> taxReqDTOList = new ArrayList<OrdInvoiceTaxRespDTO>();
		try {
			taxReqDTOList= iOrdInvoiceTaxSV.queryOrdInvoiceTaxList(ordInvoiceTaxReqDTO);
		} catch (Exception e) {
			log.error("获取发票管理信息异常:", e);
			throw new Exception(e);
		}
		return taxReqDTOList;
	}
	public List<OrdInvoiceTaxAddrRespDTO> queryOrdInvoiceTaxAddrList(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO)
			throws Exception {
		List<OrdInvoiceTaxAddrRespDTO> taxAddrReqDTOList = new ArrayList<OrdInvoiceTaxAddrRespDTO>();
		try {
			taxAddrReqDTOList= iOrdInvoiceTaxAddrSV.queryOrdInvoiceTaxAddrList(ordInvoiceTaxAddrReqDTO);
		} catch (Exception e) {
			log.error("获取发票管理地址信息异常:", e);
			throw new Exception(e);
		}
		return taxAddrReqDTOList;
	}
	public Long insertOrdInvoiceAddrTax(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) throws Exception{
		if (ordInvoiceTaxAddrReqDTO ==null||ordInvoiceTaxAddrReqDTO.getOrderTaxId()==null){
            throw new Exception("发票开具申请收货地址异常，入参为空");
        }
		Long code;
		try {
			code= iOrdInvoiceTaxAddrSV.insertOrdInvoiceAddrTax(ordInvoiceTaxAddrReqDTO);
		} catch (Exception e) {
			log.error("发票开具申请收货地址信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
	public Long updateOrdInvoiceAddrTax(OrdInvoiceTaxAddrReqDTO ordInvoiceTaxAddrReqDTO) throws Exception{
		if (ordInvoiceTaxAddrReqDTO ==null||ordInvoiceTaxAddrReqDTO.getOrderTaxId()==null){
            throw new Exception("发票开具申请收货地址更新异常，入参为空");
        }
		Long code;
		try {
			code= iOrdInvoiceTaxAddrSV.updateOrdInvoiceAddrTax(ordInvoiceTaxAddrReqDTO);
		} catch (Exception e) {
			log.error("发票开具申请收货地址更新信息异常:", e);
			throw new Exception(e);
		}
		return code;
	}
}
