package com.ai.bdex.dataexchange.aipcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientAccesstokenMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientAccesstoken;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientAccesstokenSV;

@Service("aipClientAccesstokenSV")
public class AipClientAccesstokenSVImpl implements IAipClientAccesstokenSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientAccesstokenSVImpl.class);
	@Autowired
	private AipClientAccesstokenMapper aipClientAccesstokenMapper;

	@Override
	public AipClientAccesstoken getAipClientAccesstokenByKey(String accesstoken) throws Exception {
		try{
			return aipClientAccesstokenMapper.selectByPrimaryKey(accesstoken);
		}catch(Exception e){
			log.error("query accesstoken error:"+accesstoken,e);
			throw e;
		}
	}

	@Override
	public int insertAipClientAccesstoken(AipClientAccesstoken token)
			throws Exception {
		try{
			return aipClientAccesstokenMapper.insert(token);
		}catch(Exception e){
			log.error("insert accesstoken error:"+token.getAccessToken(),e);
			throw e;
		}
	}
	
}
