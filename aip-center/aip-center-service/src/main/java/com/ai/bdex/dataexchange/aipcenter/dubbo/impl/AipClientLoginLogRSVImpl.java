package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientLoginLog;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientLoginLogDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientLoginLogRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientLoginLogSV;
import com.ai.paas.utils.ObjectCopyUtil;
import com.ai.paas.utils.StringUtil;

@Service("aipClientLoginLogRSV")
public class AipClientLoginLogRSVImpl implements IAipClientLoginLogRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientLoginLogRSVImpl.class);
	@Autowired
	private IAipClientLoginLogSV aipClientLoginLogSV;
	@Override
	public int insertAipClientLoginLog(AipClientLoginLogDTO logDTO)
			throws Exception {
		
		try {
			int c=0;
			if(null!=logDTO){
				AipClientLoginLog vo=new AipClientLoginLog();
				ObjectCopyUtil.copyObjValue(logDTO, vo, null, false);
				c=aipClientLoginLogSV.insertAipClientLoginLog(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert AipClientLoginLog failted:"+logDTO.getLogId()+":"+logDTO.getClientId(),e);
			throw e;
		}
	}
	
}
