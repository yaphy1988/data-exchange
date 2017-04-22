package com.ai.bdex.dataexchange.apigateway.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipPServiceUsedLogMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipPServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipPServiceUsedLogSV;

@Service("aipPServiceUsedLogSV")
public class AipPServiceUsedLogSVImpl implements IAipPServiceUsedLogSV{
	private static transient Logger log = LoggerFactory.getLogger(AipPServiceUsedLogSVImpl.class);
	@Autowired
	private AipPServiceUsedLogMapper aipPServiceUsedLogMapper;
	@Override
	public String insertLog(AipPServiceUsedLog log) throws Exception {
		aipPServiceUsedLogMapper.insert(log);
		return log.getUsedId();
	}
	
}
