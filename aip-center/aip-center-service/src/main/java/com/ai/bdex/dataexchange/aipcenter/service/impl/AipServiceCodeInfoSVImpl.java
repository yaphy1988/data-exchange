package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceCodeInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceCodeInfoSV;

@Service("aipServiceCodeInfoSV")
public class AipServiceCodeInfoSVImpl implements IAipServiceCodeInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceCodeInfoSVImpl.class);
	@Autowired
	private AipServiceCodeInfoMapper aipServiceCodeInfoMapper;
	@Override
	public List<AipServiceCodeInfo> getBeans(String serviceId,
			String serviceVersion) throws Exception {
		
		try{
			AipServiceCodeInfoExample ex=new AipServiceCodeInfoExample();
			AipServiceCodeInfoExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceCodeInfoMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service code failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
}
