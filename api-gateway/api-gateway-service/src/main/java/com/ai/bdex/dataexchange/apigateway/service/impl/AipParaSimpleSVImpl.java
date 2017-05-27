package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipParaSimpleMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimple;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaSimpleExample;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaSimpleSV;
import com.ai.paas.utils.CollectionUtil;

@Service("gatewayAipParaSimpleSV")
public class AipParaSimpleSVImpl implements IAipParaSimpleSV{
	private static final Logger log = LoggerFactory.getLogger(AipParaSimpleSVImpl.class);	
	@Autowired
	private AipParaSimpleMapper aipParaSimpleMapper;
	@Override
	public AipParaSimple getValidAipParaSimple(String paraLinkKey, String spCode) {
		AipParaSimpleExample ex=new AipParaSimpleExample();
		AipParaSimpleExample.Criteria sql=ex.createCriteria();
		sql.andParaLinkKeyEqualTo(paraLinkKey);
		sql.andSpCodeEqualTo(spCode);
		sql.andStateEqualTo(APIConstants.AipCache.AIP_PARA_VALID_STATE);		
		List<AipParaSimple> list=aipParaSimpleMapper.selectByExample(ex);
		return CollectionUtil.isEmpty(list)?null:list.get(0);
	}
	@Override
	public List<AipParaSimple> getValidAipParaSimpleListByLinkKey(String paraLinkKey) {
		AipParaSimpleExample ex=new AipParaSimpleExample();
		AipParaSimpleExample.Criteria sql=ex.createCriteria();
		sql.andStateEqualTo(APIConstants.AipCache.AIP_PARA_VALID_STATE);	
		sql.andParaLinkKeyEqualTo(paraLinkKey);
		return aipParaSimpleMapper.selectByExample(ex);
	}
	@Override
	public int updateAipParaSimpleByKey(AipParaSimple cfg) {
		
		return aipParaSimpleMapper.updateByPrimaryKeySelective(cfg);
	}
	
	
}
