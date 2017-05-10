package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientScopeMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientScope;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientScopeExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientScopeSV;
import com.ai.paas.utils.CollectionUtil;

@Service("aipClientScopeSV")
public class AipClientScopeSVImpl implements IAipClientScopeSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientScopeSVImpl.class);
	@Autowired
	private AipClientScopeMapper aipClientScopeMapper;
	@Override
	public AipClientScope getAipClientScopeByKey(String clientId, String scopeId)
			throws Exception {
		
		try {
			AipClientScopeExample ex=new AipClientScopeExample();
			AipClientScopeExample.Criteria sql=ex.createCriteria();
			sql.andClientIdEqualTo(clientId);
			sql.andScopeIdEqualTo(scopeId);
			sql.andStatusEqualTo("1");
			List<AipClientScope> list=aipClientScopeMapper.selectByExample(ex);
			if(!CollectionUtil.isEmpty(list)){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			log.error("query scope failted,"+clientId+":"+scopeId, e);
			throw e;
		}
	}
	
}
