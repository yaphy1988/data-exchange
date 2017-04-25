package com.ai.bdex.dataexchange.aipcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipProviderInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderInfoSV;

@Service("aipProviderInfoSV")
public class AipProviderInfoSVImpl implements IAipProviderInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipProviderInfoSVImpl.class);
	@Autowired
	private AipProviderInfoMapper aipProviderInfoMapper;
	@Override
	public AipProviderInfo getAipProviderInfo(String providerId)
			throws Exception {
		try{
			
			return aipProviderInfoMapper.selectByPrimaryKey(providerId);
		}catch(Exception e){
			log.error("query provider failted."+providerId, e);
			throw e;
		}		
	}
	
	
}
