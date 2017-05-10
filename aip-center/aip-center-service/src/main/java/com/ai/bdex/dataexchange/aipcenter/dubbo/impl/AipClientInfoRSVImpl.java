package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientInfoSV;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipClientInfoRSV")
public class AipClientInfoRSVImpl implements IAipClientInfoRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientInfoRSVImpl.class);
	@Autowired
	private IAipClientInfoSV aipClientInfoSV;
	@Override
	public AipClientInfoDTO getAipClientInfoByKey(String clientId)
			throws Exception {
		
		try {
			AipClientInfoDTO dto=null;
			AipClientInfo vo=aipClientInfoSV.getAipClientInfoByKey(clientId);
			if(null!=vo){
				dto=new AipClientInfoDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);
			}
			return dto;
		} catch (Exception e) {
			log.error("query by clientId failted:"+clientId, e);
			throw e;
		}
	}

	@Override
	public int insertAipClientInfo(AipClientInfoDTO info) throws Exception {
		
		try {
			int c=0;
			if(null!=info){
				AipClientInfo vo=new AipClientInfo();
				ObjectCopyUtil.copyObjValue(info, vo, null, false);
				c=aipClientInfoSV.insertAipClientInfo(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert client messgae failted:"+info.getClientId(), e);
			throw e;
		}
	}
	
}
