package com.ai.bdex.dataexchange.apigateway.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipServiceSpringMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceSpring;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceSpringKey;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceSpringSV;

@Service("aipServiceSpringSV")
public class AipServiceSpringSVImpl implements IAipServiceSpringSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceSpringSVImpl.class);
	@Autowired
	private AipServiceSpringMapper aipServiceSpringMapper;
	@Override
	public AipServiceSpring getAipServiceSpringByKey(String serviceId,String version){
		AipServiceSpringKey key=new AipServiceSpringKey();
		key.setServiceId(serviceId);
		key.setVersion(version);
		return aipServiceSpringMapper.selectByPrimaryKey(key);
	}
	
	
}
