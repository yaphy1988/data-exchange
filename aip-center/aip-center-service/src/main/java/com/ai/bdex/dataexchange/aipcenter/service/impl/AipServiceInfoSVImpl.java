package com.ai.bdex.dataexchange.aipcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoKey;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;

@Service("aipServiceInfoSV")
public class AipServiceInfoSVImpl implements IAipServiceInfoSV{
	@Autowired
	private AipServiceInfoMapper aipServiceInfoMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AipServiceInfoSVImpl.class);

	@Override
	public AipServiceInfo getAipServiceInfo(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceInfoKey key=new AipServiceInfoKey();
			key.setServiceId(serviceId);
			key.setVersion(serviceVersion);
			
			return aipServiceInfoMapper.selectByPrimaryKey(key);
		}catch(Exception e){
			log.error("query service failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
	
}
