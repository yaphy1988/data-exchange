package com.ai.bdex.dataexchange.aipcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientLoginLogMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientLoginLog;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientLoginLogSV;
@Service("aipClientLoginLogSV")
public class AipClientLoginLogSVImpl implements IAipClientLoginLogSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientLoginLogSVImpl.class);
	@Autowired
	private AipClientLoginLogMapper aipClientLoginLogMapper;
	@Override
	public int insertAipClientLoginLog(AipClientLoginLog logBean) throws Exception {
		
		try {
			return aipClientLoginLogMapper.insert(logBean);
		} catch (Exception e) {
			log.error("insert AipClientLoginLog failted"+logBean.getLogId()+":"+logBean.getClientId(),e);
			throw e;
		}
	}
	
}
