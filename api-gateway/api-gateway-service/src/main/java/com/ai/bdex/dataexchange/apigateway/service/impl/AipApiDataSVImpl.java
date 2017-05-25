package com.ai.bdex.dataexchange.apigateway.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipApiDataMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipApiData;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipApiDataKey;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipApiDataSV;

@Service("aipApiDataSV")
public class AipApiDataSVImpl implements IAipApiDataSV{
	private static final Logger log = LoggerFactory.getLogger(AipApiDataSVImpl.class);
	
	@Autowired
	private AipApiDataMapper aipApiDataMapper;
	
	@Override
	public AipApiData getAipApiDataByKey(String serviceId,String versionId,String requestMd5) {
		AipApiDataKey key=new AipApiDataKey();
		key.setServiceId(serviceId);
		key.setVersionId(versionId);
		key.setRequestMd5(requestMd5);
		return aipApiDataMapper.selectByPrimaryKey(key);
	}

	@Override
	public int insertAipApiData(AipApiData record) {		
		return aipApiDataMapper.insertSelective(record);
	}
	
}
