package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutPara;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceCodeInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceErrorInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.ServiceDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceCodeInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceErrorInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInParaSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceOutParaSV;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;

@Service("serviceMessageRSV")
public class ServiceMessageRSVImpl implements IServiceMessageRSV{
	private static final Logger log = LoggerFactory.getLogger(ServiceMessageRSVImpl.class);
	@Autowired
	private IAipServiceInfoSV aipServiceInfoSV;
	@Autowired
	private IAipProviderInfoSV aipProviderInfoSV;
	@Autowired
	private IAipServiceCodeInfoSV aipServiceCodeInfoSV;
	@Autowired
	private IAipServiceErrorInfoSV aipServiceErrorInfoSV;
	@Autowired
	private IAipServiceInParaSV aipServiceInParaSV;
	@Autowired
	private IAipServiceOutParaSV aipServiceOutParaSV;
	@Override
	public ServiceDTO getServiceMessage(String serviceId, String serviceVersion)
			throws Exception {
		try{
			ServiceDTO dto=null;
			AipServiceInfo service=aipServiceInfoSV.getAipServiceInfo(serviceId, serviceVersion);	
			if(service!=null){
				dto=new ServiceDTO();
				BeanUtils.copyProperties(service, dto);
				if(!StringUtil.isBlank(service.getProviderId())){
					AipProviderInfo provider=aipProviderInfoSV.getAipProviderInfo(service.getProviderId());
					if(null!=provider){
						dto.setProviderName(provider.getProviderName());
						dto.setProviderStatus(provider.getStatus());
					}
				}
				//查询入参
				List<AipServiceInPara> inList=aipServiceInParaSV.getBeans(serviceId, serviceVersion);
				if(!CollectionUtil.isEmpty(inList)){
					List<AipServiceInParaDTO> inDTOList=new ArrayList<AipServiceInParaDTO>();				
					AipServiceInParaDTO inDto=null;
					for(AipServiceInPara in :inList){
						inDto=new AipServiceInParaDTO();
						BeanUtils.copyProperties(in, inDto);
						inDTOList.add(inDto);
					}
					dto.setServiceInParas(inDTOList);
				}
				//查询出参
				List<AipServiceOutPara> outList=aipServiceOutParaSV.getbeans(serviceId, serviceVersion);
				if(!CollectionUtil.isEmpty(outList)){
					List<AipServiceOutParaDTO> outDTOList=new ArrayList<AipServiceOutParaDTO>();				
					AipServiceOutParaDTO outDto=null;
					for(AipServiceOutPara out :outList){
						outDto=new AipServiceOutParaDTO();
						BeanUtils.copyProperties(out, outDto);
						outDTOList.add(outDto);
					}
					dto.setServiceOutParas(outDTOList);
				}
				//查询错误码
				List<AipServiceErrorInfo> errorList=aipServiceErrorInfoSV.getbeans(serviceId, serviceVersion);
				if(!CollectionUtil.isEmpty(errorList)){
					List<AipServiceErrorInfoDTO> errDTOList=new ArrayList<AipServiceErrorInfoDTO>();				
					AipServiceErrorInfoDTO errDto=null;
					for(AipServiceErrorInfo err :errorList){
						errDto=new AipServiceErrorInfoDTO();
						BeanUtils.copyProperties(err, errDto);
						errDTOList.add(errDto);
					}
					dto.setServiceErrores(errDTOList);
				}
				//查询样例代码信息
				List<AipServiceCodeInfo> exampleList=aipServiceCodeInfoSV.getBeans(serviceId, serviceVersion);
				if(!CollectionUtil.isEmpty(exampleList)){
					List<AipServiceCodeInfoDTO> exDTOList=new ArrayList<AipServiceCodeInfoDTO>();				
					AipServiceCodeInfoDTO exDto=null;
					for(AipServiceCodeInfo ex :exampleList){
						exDto=new AipServiceCodeInfoDTO();
						BeanUtils.copyProperties(ex, exDto);
						exDTOList.add(exDto);
					}
					dto.setServiceCodeInfoes(exDTOList);
				}				
				return dto;
			}
			return null;
		}catch(Exception e){
			log.error("",e);
			throw e;
		}
	}

	@Override
	public List<AipServiceInParaDTO> queryAipServiceInParaList(String serviceId, String version) throws Exception {
		if(StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
			throw new BusinessException("查询aip入参信息列表的参数为空！");
		}
		List<AipServiceInParaDTO> list = new ArrayList<AipServiceInParaDTO>();

		try {
			List<AipServiceInPara> paras = aipServiceInParaSV.getBeans(serviceId,version);
			if (!CollectionUtil.isEmpty(paras)){
				for (AipServiceInPara aipServiceInPara : paras){
					AipServiceInParaDTO aipServiceInParaDTO = new AipServiceInParaDTO();
					ObjectCopyUtil.copyObjValue(aipServiceInPara,aipServiceInParaDTO,null,false);
					list.add(aipServiceInParaDTO);
				}
			}
		}catch (Exception e){
			log.error("查询aip入参列表异常：",e);
		}

		return list;
	}

	@Override
	public List<AipServiceOutParaDTO> queryAipServiceOutParaList(String serviceId, String version) throws Exception {
		if(StringUtil.isBlank(serviceId) || StringUtil.isBlank(version)){
			throw new BusinessException("查询aip出参信息列表的参数为空！");
		}
		List<AipServiceOutParaDTO> list = new ArrayList<AipServiceOutParaDTO>();

		try {
			List<AipServiceOutPara> paras = aipServiceOutParaSV.getbeans(serviceId,version);
			if (!CollectionUtil.isEmpty(paras)){
				for (AipServiceOutPara aipServiceOutPara : paras){
					AipServiceOutParaDTO aipServiceOutParaDTO = new AipServiceOutParaDTO();
					ObjectCopyUtil.copyObjValue(aipServiceOutPara,aipServiceOutParaDTO,null,false);
					list.add(aipServiceOutParaDTO);
				}
			}
		}catch (Exception e){
			log.error("查询aip出参列表异常：",e);
		}

		return list;
	}

}
