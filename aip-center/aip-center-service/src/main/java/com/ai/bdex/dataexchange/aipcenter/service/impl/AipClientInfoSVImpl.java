package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientInfoSV;
import com.ai.paas.utils.CollectionUtil;

@Service("aipClientInfoSV")
public class AipClientInfoSVImpl implements IAipClientInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientInfoSVImpl.class);
	@Autowired
	private AipClientInfoMapper aipClientInfoMapper;
	
	@Override
	public AipClientInfo getAipClientInfoByKey(String clientId)
			throws Exception {
		try{
			AipClientInfoExample ex=new AipClientInfoExample();
			AipClientInfoExample.Criteria sql=ex.createCriteria();
			sql.andClientIdEqualTo(clientId);
			sql.andStatusEqualTo("1");
			List<AipClientInfo> list=aipClientInfoMapper.selectByExample(ex);
			if(!CollectionUtil.isEmpty(list)){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			log.error("query clientId message error:"+clientId, e);
			throw e;
		}
	}

	@Override
	public int insertAipClientInfo(AipClientInfo info) throws Exception {
		try{
			return aipClientInfoMapper.insert(info);
		}catch(Exception e){
			log.error("insert client message error:"+info.getClientId(), e);
			throw e;
		}
	}
	
}
