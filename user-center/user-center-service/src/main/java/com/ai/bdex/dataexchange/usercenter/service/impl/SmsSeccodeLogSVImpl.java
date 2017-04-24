package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.SmsSeccodeLogMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.SmsSeccodeLog;
import com.ai.bdex.dataexchange.usercenter.dao.model.SmsSeccodeLogExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.SmsSeccodeReqDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.ISmsSeccodeLogSV;
import com.ai.paas.utils.CollectionUtil;

@Service("iSmsSeccodeLogSV")
public class SmsSeccodeLogSVImpl implements ISmsSeccodeLogSV {
	
	@Autowired
	private SmsSeccodeLogMapper smsSeccodeLogMapper;

	@Override
	public SmsSeccodeReqDTO getSmsSendInfo(SmsSeccodeReqDTO input) throws Exception {
		SmsSeccodeLogExample example = new SmsSeccodeLogExample();
		SmsSeccodeLogExample.Criteria sql = example.createCriteria();
		sql.andTockenEqualTo(input.getTocken());
		sql.andPhoneNoEqualTo(input.getPhoneNo());
		List<SmsSeccodeLog> datas = smsSeccodeLogMapper.selectByExample(example);
		if(!CollectionUtil.isEmpty(datas)){
			SmsSeccodeLog log = datas.get(0);
			SmsSeccodeReqDTO info = new SmsSeccodeReqDTO();
			BeanUtils.copyProperties(log, info);			
			return info;
		}
		return null;		
	}

}
