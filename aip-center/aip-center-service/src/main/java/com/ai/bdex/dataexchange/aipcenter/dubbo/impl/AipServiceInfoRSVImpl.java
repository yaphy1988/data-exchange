package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

@Service("aipServiceInfoRSV")
public class AipServiceInfoRSVImpl implements IAipServiceInfoRSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceInfoRSVImpl.class);
	@Autowired
	private IAipServiceInfoSV aipServiceInfoSV;
	@Override
	public PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req) throws Exception {
		try{	        
			return aipServiceInfoSV.selectServiceWithPage(req);
		}catch(Exception e){
			log.error("query service failted", e);
			throw e;
		}
	}
	
}
