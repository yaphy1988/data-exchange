package com.ai.bdex.dataexchange.aipcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientRefreshtokenMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientRefreshtoken;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientRefreshtokenSV;

@Service("aipClientRefreshtokenSV")
public class AipClientRefreshtokenSVImpl implements IAipClientRefreshtokenSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientRefreshtokenSVImpl.class);
	@Autowired
	private AipClientRefreshtokenMapper aipClientRefreshtokenMapper;
	
	@Override
	public AipClientRefreshtoken getAipClientRefreshtokenByKey(
			String refreshToken) throws Exception {		
		try {
			return aipClientRefreshtokenMapper.selectByPrimaryKey(refreshToken);
		} catch (Exception e) {
			log.error("query refreshToken failted:"+refreshToken,e);
			throw e;
		}
		
	}

	@Override
	public int insertAipClientRefreshtoken(AipClientRefreshtoken token)
			throws Exception {
		
		try {
			return aipClientRefreshtokenMapper.insert(token);
		} catch (Exception e) {
			log.error("insert refreshtoken error:"+token.getRefreshToken(),e);
			throw e;
		}
	}

	@Override
	public int updateAipClientRefreshtokenExpireTime(AipClientRefreshtoken token)
			throws Exception {
		
		try {
			
			return aipClientRefreshtokenMapper.updateByPrimaryKey(token);
		} catch (Exception e) {
			log.error("update refreshtoken error:"+token.getRefreshToken(),e);
			throw e;
		}
	}
	
}
