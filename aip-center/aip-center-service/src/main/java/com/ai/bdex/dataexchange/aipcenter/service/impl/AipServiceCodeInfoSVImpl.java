package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceCodeInfoReqDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceCodeInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceCodeInfoSV;

@Service("aipServiceCodeInfoSV")
public class AipServiceCodeInfoSVImpl implements IAipServiceCodeInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceCodeInfoSVImpl.class);
	@Autowired
	private AipServiceCodeInfoMapper aipServiceCodeInfoMapper;
	@Override
	public List<AipServiceCodeInfo> getBeans(String serviceId,
			String serviceVersion) throws Exception {
		
		try{
			AipServiceCodeInfoExample ex=new AipServiceCodeInfoExample();
			AipServiceCodeInfoExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceCodeInfoMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service code failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}

	@Override
	public String insertServiceCodeBatch(List<AipServiceCodeInfoReqDTO> aipServiceCodeInfoReqDTOList) throws Exception {
		if (CollectionUtil.isEmpty(aipServiceCodeInfoReqDTOList)){
			throw new BusinessException("批量插入示例代码信息异常，入参为空");
		}
		String keyString = "";
		try {
			for (AipServiceCodeInfoReqDTO aipServiceCodeInfoReqDTO : aipServiceCodeInfoReqDTOList){
				AipServiceCodeInfo aipServiceCodeInfo = new AipServiceCodeInfo();
				ObjectCopyUtil.copyObjValue(aipServiceCodeInfoReqDTO,aipServiceCodeInfo,null,false);
				String codeId = SeqUtil.getString("SEQ_AIP_SERVICE_CODE_INFO");
				aipServiceCodeInfo.setCodeId(codeId);
				aipServiceCodeInfoMapper.insert(aipServiceCodeInfo);
				keyString = keyString + codeId + ",";
			}
		}catch (Exception e){
			log.error("批量插入示例代码信息异常：",e);
		}
		return keyString;
	}

	@Override
	public void updateServiceCodeByServiceIdAndVersion(AipServiceCodeInfoReqDTO aipServiceCodeInfoReqDTO) throws Exception {
		if (aipServiceCodeInfoReqDTO == null || StringUtil.isBlank(aipServiceCodeInfoReqDTO.getServiceId()) || StringUtil.isBlank(aipServiceCodeInfoReqDTO.getVersion())){
			throw new BusinessException("根据serviceId和version更新示例代码信息异常，入参为空！");
		}
		try {
			AipServiceCodeInfoExample aipServiceCodeInfoExample = new AipServiceCodeInfoExample();
			AipServiceCodeInfoExample.Criteria criteria = aipServiceCodeInfoExample.createCriteria();
			criteria.andServiceIdEqualTo(aipServiceCodeInfoReqDTO.getServiceId());
			criteria.andVersionEqualTo(aipServiceCodeInfoReqDTO.getVersion());
			AipServiceCodeInfo aipServiceCodeInfo = new AipServiceCodeInfo();
			String notCopy = "codeId,serviceId,version";
			ObjectCopyUtil.copyObjValue(aipServiceCodeInfoReqDTO,aipServiceCodeInfo,notCopy,false);
			aipServiceCodeInfoMapper.updateByExampleSelective(aipServiceCodeInfo,aipServiceCodeInfoExample);
		}catch (Exception e){
			log.error("根据serviceId和version更新示例代码信息异常：",e);
		}

	}


}
