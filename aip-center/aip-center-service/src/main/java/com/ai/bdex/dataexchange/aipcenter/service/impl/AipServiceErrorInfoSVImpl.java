package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceErrorInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceErrorInfoSV;

public class AipServiceErrorInfoSVImpl implements IAipServiceErrorInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceErrorInfoSVImpl.class);
	@Autowired
	AipServiceErrorInfoMapper aipServiceErrorInfoMapper;
	
	@Override
	public List<AipServiceErrorInfo> getbeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceErrorInfoExample ex=new AipServiceErrorInfoExample();
			AipServiceErrorInfoExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceErrorInfoMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service error code failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
}
