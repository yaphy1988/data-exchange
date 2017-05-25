package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceUsedLogDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipServiceUsedLogRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceUsedLogSV;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipServiceUsedLogRSV")
public class AipServiceUsedLogRSVImpl implements IAipServiceUsedLogRSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceUsedLogRSVImpl.class);
	@Autowired 
	private IAipServiceUsedLogSV aipServiceUsedLogSV;

	@Override
	public String insertAipServiceUsedLog(AipServiceUsedLogDTO dto)
			throws Exception {
		try {
			String c=null;
			if(null!=dto){
				AipServiceUsedLog vo=new AipServiceUsedLog();
				ObjectCopyUtil.copyObjValue(dto, vo, null, false);
				c=aipServiceUsedLogSV.insertAipServiceUsedLog(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert AipServiceUsedLog failted:",e);
			throw e;
		}
	}

	@Override
	public int updateAipServiceUsedLogByPK(AipServiceUsedLogDTO dto)
			throws Exception {
		try {
			int c=0;
			if(null!=dto){
				AipServiceUsedLog vo=new AipServiceUsedLog();
				ObjectCopyUtil.copyObjValue(dto, vo, null, false);
				c=aipServiceUsedLogSV.updateAipServiceUsedLogByPK(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("update AipServiceUsedLog failted:",e);
			throw e;
		}
	}
	
}
