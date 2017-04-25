package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.paas.utils.CollectionUtil;

@Service("aipServiceInfoSV")
public class AipServiceInfoSVImpl implements IAipServiceInfoSV{
	@Autowired
	private AipServiceInfoMapper aipServiceInfoMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AipServiceInfoSVImpl.class);

	@Override
	public AipServiceInfo getAipServiceInfo(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceInfoExample key=new AipServiceInfoExample();
			AipServiceInfoExample.Criteria sql=key.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");		
			List<AipServiceInfo> list=aipServiceInfoMapper.selectByExample(key);
			if(!CollectionUtil.isEmpty(list)){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			log.error("query service failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	
	
}
