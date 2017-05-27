package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipParaCfgMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfg;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipParaCfgExample;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipParaCfgSV;
import com.ai.paas.utils.CollectionUtil;

@Service("gatewayAipParaCfgSV")
public class AipParaCfgSVImpl implements IAipParaCfgSV{
	@Autowired
	private  AipParaCfgMapper aipParaCfgMapper;	
	private static final Logger log = LoggerFactory.getLogger(AipParaCfgSVImpl.class);

	@Override
	public List<AipParaCfg> getValidAipParaCfg() {
		AipParaCfgExample ex=new AipParaCfgExample();
		AipParaCfgExample.Criteria sql=ex.createCriteria();
		sql.andStateEqualTo(APIConstants.AipCache.AIP_PARA_VALID_STATE);
		return aipParaCfgMapper.selectByExample(ex);
	}

	@Override
	public AipParaCfg getValidAipParaCfgByKey(String paraCode) {
		AipParaCfgExample ex=new AipParaCfgExample();
		AipParaCfgExample.Criteria sql=ex.createCriteria();
		sql.andStateEqualTo(APIConstants.AipCache.AIP_PARA_VALID_STATE);
		sql.andParaCodeEqualTo(paraCode);
		List<AipParaCfg> list=aipParaCfgMapper.selectByExample(ex);
		return CollectionUtil.isEmpty(list)?null:list.get(0);
	}

	@Override
	public int updateAipParaCfgByKey(AipParaCfg cfg) {		
		return aipParaCfgMapper.updateByPrimaryKeySelective(cfg);
	}	
}
