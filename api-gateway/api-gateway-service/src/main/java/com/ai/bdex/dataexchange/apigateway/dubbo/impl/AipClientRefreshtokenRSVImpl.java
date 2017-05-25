package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientRefreshtoken;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientRefreshtokenDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipClientRefreshtokenRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipClientRefreshtokenSV;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipClientRefreshtokenRSV")
public class AipClientRefreshtokenRSVImpl implements IAipClientRefreshtokenRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientRefreshtokenRSVImpl.class);
	@Autowired
	private IAipClientRefreshtokenSV aipClientRefreshtokenSV;
	@Override
	public AipClientRefreshtokenDTO getAipClientRefreshtokenByKey(
			String refreshToken) throws Exception {
		
		try {
			AipClientRefreshtokenDTO dto=null;
			AipClientRefreshtoken vo=aipClientRefreshtokenSV.getAipClientRefreshtokenByKey(refreshToken);
			if(null!=vo){
				dto=new AipClientRefreshtokenDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);
			}
			return dto;
		} catch (Exception e) {
			log.error("query by refreshToken failted",e);
			throw e;
		}
	}
	@Override
	public int insertAipClientRefreshtoken(AipClientRefreshtokenDTO token)
			throws Exception {
		try {
			int c=0;
			if(null!=token){
				AipClientRefreshtoken vo=new AipClientRefreshtoken();
				ObjectCopyUtil.copyObjValue(token, vo, null, false);
				c=aipClientRefreshtokenSV.insertAipClientRefreshtoken(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert  AipClientRefreshtoken failted,"+token.getClientId()+":"+token.getRefreshToken(),e);
			throw e;
		}
	}
	@Override
	public int updateAipClientRefreshtokenExpireTime(
			AipClientRefreshtokenDTO token) throws Exception {
		try {
			int c=0;
			if(null!=token){
				AipClientRefreshtoken vo=new AipClientRefreshtoken();
				ObjectCopyUtil.copyObjValue(token, vo, null, false);
				c=aipClientRefreshtokenSV.updateAipClientRefreshtokenExpireTime(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("update  AipClientRefreshtoken failted,"+token.getClientId()+":"+token.getRefreshToken(),e);
			throw e;
		}
	}
	
	
}
