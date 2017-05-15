package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInParaExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceOutParaMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutPara;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutParaExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceOutParaSV;
import org.springframework.stereotype.Service;

@Service("aipServiceOutParaSV")
public class AipServiceOutParaSVImpl implements IAipServiceOutParaSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceOutParaSVImpl.class);
	@Autowired
	private AipServiceOutParaMapper aipServiceOutParaMapper;
	@Override
	public List<AipServiceOutPara> getbeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceOutParaExample ex=new AipServiceOutParaExample();
			AipServiceOutParaExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceOutParaMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service out param failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}

	@Override
	public String insertOutPara(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception {
		if (aipServiceOutParaDTO==null){
			throw new BusinessException("插入aip服务出参信息异常，入参为空！");
		}
		String outputId = SeqUtil.getString("SEQ_AIP_SERVICE_OUT_PARA");
		AipServiceOutPara aipServiceOutPara = new AipServiceOutPara();
		ObjectCopyUtil.copyObjValue(aipServiceOutParaDTO,aipServiceOutPara,null,false);
		aipServiceOutPara.setOutputId(outputId);
		aipServiceOutParaMapper.insert(aipServiceOutPara);
		return outputId;
	}

	@Override
	public void updateOutParaByServiceIdAndVersion(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception {
		if (aipServiceOutParaDTO == null || StringUtil.isBlank(aipServiceOutParaDTO.getServiceId())){
			throw new BusinessException("更新aip服务出参列表异常，入参为空");
		}
		AipServiceOutParaExample example = new AipServiceOutParaExample();
		AipServiceOutParaExample.Criteria criteria = example.createCriteria();
		criteria.andServiceIdEqualTo(aipServiceOutParaDTO.getServiceId());
		criteria.andVersionEqualTo(aipServiceOutParaDTO.getVersion());
		AipServiceOutPara aipServiceOutPara = new AipServiceOutPara();
		String notCopy = "outputId,serviceId,version";
		ObjectCopyUtil.copyObjValue(aipServiceOutParaDTO,aipServiceOutPara,notCopy,false);
		aipServiceOutParaMapper.updateByExampleSelective(aipServiceOutPara,example);
	}


}
