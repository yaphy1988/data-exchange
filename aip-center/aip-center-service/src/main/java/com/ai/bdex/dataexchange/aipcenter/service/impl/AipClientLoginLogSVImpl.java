package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientLoginLogMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientLoginLog;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientLoginLogSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("aipClientLoginLogSV")
public class AipClientLoginLogSVImpl implements IAipClientLoginLogSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientLoginLogSVImpl.class);
	private static final String seq_name="SEQ_AIP_CLIENT_LOGIN_LOG";
	@Autowired
	private AipClientLoginLogMapper aipClientLoginLogMapper;
	@Override
	public int insertAipClientLoginLog(AipClientLoginLog logBean) throws Exception {
		
		try {
			if(StringUtils.isBlank(logBean.getLogId())){			
				logBean.setLogId(DateUtil.getDateString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmss")+SeqUtil.getNextValueLong(seq_name));
			}
			return aipClientLoginLogMapper.insert(logBean);
		} catch (Exception e) {
			log.error("insert AipClientLoginLog failted"+logBean.getLogId()+":"+logBean.getClientId(),e);
			throw e;
		}
	}
	
}
