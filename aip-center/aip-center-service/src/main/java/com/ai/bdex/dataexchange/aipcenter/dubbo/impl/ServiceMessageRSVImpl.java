package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.ServiceDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IServiceMessageRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderInfoSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.paas.utils.StringUtil;

@Service("serviceMessageRSV")
public class ServiceMessageRSVImpl implements IServiceMessageRSV{
	private static final Logger log = LoggerFactory.getLogger(ServiceMessageRSVImpl.class);
	@Autowired
	private IAipServiceInfoSV aipServiceInfoSV;
	@Autowired
	private IAipProviderInfoSV aipProviderInfoSV;
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
				return dto;
			}
			return null;
		}catch(Exception e){
			log.error("",e);
			throw e;
		}
	}
	
}
