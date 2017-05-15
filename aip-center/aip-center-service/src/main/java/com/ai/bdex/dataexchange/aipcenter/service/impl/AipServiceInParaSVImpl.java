package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceInParaMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInParaExample;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInParaSV;

@Service("aipServiceInParaSV")
public class AipServiceInParaSVImpl implements IAipServiceInParaSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceInParaSVImpl.class);
	@Autowired
	private AipServiceInParaMapper aipServiceInParaMapper;
	@Override
	public List<AipServiceInPara> getBeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceInParaExample ex=new AipServiceInParaExample();
			AipServiceInParaExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceInParaMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service in param failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}

	@Override
	public String insertInPara(AipServiceInParaDTO aipServiceInParaDTO) throws Exception {
		if (aipServiceInParaDTO==null){
			throw new BusinessException("插入aip服务入参信息的入参为空！");
		}
		String inputId = SeqUtil.getString("SEQ_AIP_SERVICE_IN_PARA");
		AipServiceInPara aipServiceInPara = new AipServiceInPara();
		ObjectCopyUtil.copyObjValue(aipServiceInParaDTO,aipServiceInPara,null,false);
		aipServiceInPara.setInputId(inputId);
		aipServiceInParaMapper.insert(aipServiceInPara);
		return inputId;
	}

	@Override
	public void updateInParaByServiceIdAndVersion(AipServiceInParaDTO aipServiceInParaDTO) throws Exception {
		if (aipServiceInParaDTO == null || StringUtil.isBlank(aipServiceInParaDTO.getServiceId())){
			throw new BusinessException("更新aip服务入参列表异常，入参为空");
		}
		AipServiceInParaExample example = new AipServiceInParaExample();
		AipServiceInParaExample.Criteria criteria = example.createCriteria();
		criteria.andServiceIdEqualTo(aipServiceInParaDTO.getServiceId());
		criteria.andVersionEqualTo(aipServiceInParaDTO.getVersion());
		AipServiceInPara aipServiceInPara = new AipServiceInPara();
		String notCopy = "inputId,serviceId,version";
		ObjectCopyUtil.copyObjValue(aipServiceInParaDTO,aipServiceInPara,notCopy,false);
		aipServiceInParaMapper.updateByExampleSelective(aipServiceInPara,example);
	}


}
