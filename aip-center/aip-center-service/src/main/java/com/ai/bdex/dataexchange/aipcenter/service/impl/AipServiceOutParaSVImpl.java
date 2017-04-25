package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceOutParaMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutParaExample;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutPara;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceOutParaSV;

public class AipServiceOutParaSVImpl implements IAipServiceOutParaSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceOutParaSVImpl.class);
	@Autowired
	private AipServiceOutParaMapper aipServiceOutParaMapper;
	@Override
	public List<AipServiceOutPara> getbeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceOutParaExample ex=new AipServiceOutParaExample();
			AipServiceOutParaExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceOutParaMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service out param failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
	
}
