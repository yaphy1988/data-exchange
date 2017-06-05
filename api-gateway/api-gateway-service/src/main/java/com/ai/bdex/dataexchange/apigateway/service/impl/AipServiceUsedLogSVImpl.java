package com.ai.bdex.dataexchange.apigateway.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.mapper.AipServiceUsedLogMapper;
import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceUsedLog;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceUsedLogSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;
@Service("aipServiceUsedLogSV")
public class AipServiceUsedLogSVImpl implements IAipServiceUsedLogSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceUsedLogSVImpl.class);
	@Autowired
	private AipServiceUsedLogMapper aipServiceUsedLogMapper;
	private static final String seq_name="SEQ_AIP_SERVICE_USED_LOG";
	@Override
	public String insertAipServiceUsedLog(AipServiceUsedLog vo)
			throws Exception {
		try{
			String logId=vo.getUsedId();
			if(StringUtil.isBlank(logId)){
				logId=DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(seq_name);
				vo.setUsedId(logId);
			}
			aipServiceUsedLogMapper.insert(vo);
			return logId;
		}catch(Exception e){
			log.error("insert AipServiceUsedLog error",e);
			throw e;
		}
	}
	@Override
	public int updateAipServiceUsedLogByPK(AipServiceUsedLog vo)
			throws Exception {		
		try{
			int c=aipServiceUsedLogMapper.updateByPrimaryKey(vo);
			return c;
		}catch(Exception e){
			log.error("update AipServiceUsedLog error:"+vo.getUsedId(),e);
			throw e;
		}
	}
	@Override
	public int updateByPrimaryKeySelective(AipServiceUsedLog vo)
			throws Exception {		
		try{
			int c=aipServiceUsedLogMapper.updateByPrimaryKeySelective(vo);
			return c;
		}catch(Exception e){
			log.error("update AipServiceUsedLog error:"+vo.getUsedId(),e);
			throw e;
		}
	}	
	
}
