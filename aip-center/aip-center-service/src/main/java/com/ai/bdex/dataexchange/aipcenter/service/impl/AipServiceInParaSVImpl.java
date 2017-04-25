package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceInParaMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInParaExample;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInParaSV;

@Service("aipServiceInParaSV")
public class AipServiceInParaSVImpl implements IAipServiceInParaSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceInParaSVImpl.class);
	@Autowired
	private AipServiceInParaMapper aipServiceInParaMapper;
	@Override
	public List<AipServiceInPara> getBeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceInParaExample ex=new AipServiceInParaExample();
			AipServiceInParaExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceInParaMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service in param failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
}
