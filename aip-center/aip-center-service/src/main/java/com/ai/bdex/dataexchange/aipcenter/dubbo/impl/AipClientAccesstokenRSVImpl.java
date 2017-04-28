package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientAccesstoken;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientAccesstokenDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientAccesstokenRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientAccesstokenSV;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipClientAccesstokenRSV")
public class AipClientAccesstokenRSVImpl implements IAipClientAccesstokenRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientAccesstokenRSVImpl.class);
	@Autowired
	private IAipClientAccesstokenSV aipClientAccesstokenSV;
	@Override
	public AipClientAccesstokenDTO getAipClientAccesstokenByKey(
			String accesstoken) throws Exception {
		
		try {
			AipClientAccesstokenDTO dto=null;
			AipClientAccesstoken vo=aipClientAccesstokenSV.getAipClientAccesstokenByKey(accesstoken);
			if(null!=vo){
				dto=new AipClientAccesstokenDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);
			}
			return dto;
		} catch (Exception e) {
			log.error("query accesstoken failted:"+accesstoken, e);
			throw e;
		}
	}

	@Override
	public int insertAipClientAccesstoken(AipClientAccesstokenDTO token)
			throws Exception {
		
		try {
			int c=0;
			AipClientAccesstoken vo=null;
			if(null!=token){
				vo=new AipClientAccesstoken();
				ObjectCopyUtil.copyObjValue(token, vo, null, false);
				c=aipClientAccesstokenSV.insertAipClientAccesstoken(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert token failted:"+token.getAccessToken(),e);
			throw e;
		}
	}
	
}
