package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientScope;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientScopeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientScopeRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientScopeSV;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipClientScopeRSV")
public class AipClientScopeRSVImpl implements IAipClientScopeRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientScopeRSVImpl.class);
	@Autowired
	private IAipClientScopeSV aipClientScopeSV;
	@Override
	public AipClientScopeDTO getAipClientScopeByKey(String clientId,
			String scopeId) throws Exception {
		try {
			AipClientScopeDTO dto=null;
			AipClientScope vo=aipClientScopeSV.getAipClientScopeByKey(clientId, scopeId);
			if(null!=vo){
				dto=new AipClientScopeDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);
				dto.setClientId(clientId);
				dto.setScopeId(scopeId);
			}
			return dto;
		} catch (Exception e) {
			log.error("query scope messgae failted,"+clientId+":"+scopeId, e);
			throw e;
		}
	}
	
	
}
