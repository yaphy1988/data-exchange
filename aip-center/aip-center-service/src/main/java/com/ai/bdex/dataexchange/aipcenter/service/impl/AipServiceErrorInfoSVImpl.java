package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceErrorInfoReqDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceErrorInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfoExample;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceErrorInfoSV;
import org.springframework.stereotype.Service;

@Service("iAipServiceErrorInfoSV")
public class AipServiceErrorInfoSVImpl implements IAipServiceErrorInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceErrorInfoSVImpl.class);
	@Autowired
	AipServiceErrorInfoMapper aipServiceErrorInfoMapper;
	
	@Override
	public List<AipServiceErrorInfo> getbeans(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceErrorInfoExample ex=new AipServiceErrorInfoExample();
			AipServiceErrorInfoExample.Criteria sql= ex.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");
			return aipServiceErrorInfoMapper.selectByExample(ex);
		}catch(Exception e){
			log.error("query service error code failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}

	@Override
	public String insertErrorInfoBatch(List<AipServiceErrorInfoReqDTO> aipServiceErrorInfoReqDTOList) throws Exception {
		if (CollectionUtil.isEmpty(aipServiceErrorInfoReqDTOList)){
			throw new BusinessException("批量插入aip服务错误代码信息异常，入参为空！");
		}
		String returnStr = "";
		try{
			for (AipServiceErrorInfoReqDTO aipServiceErrorInfoReqDTO : aipServiceErrorInfoReqDTOList){
				String errorId = SeqUtil.getString("SEQ_AIP_SERVICE_ERROR_INFO");
				AipServiceErrorInfo aipServiceErrorInfo = new AipServiceErrorInfo();
				ObjectCopyUtil.copyObjValue(aipServiceErrorInfoReqDTO,aipServiceErrorInfo,null,false);
				aipServiceErrorInfo.setErrorId(errorId);
				aipServiceErrorInfoMapper.insert(aipServiceErrorInfo);
				returnStr = returnStr + "," + errorId;
			}

		}catch (Exception e){
			log.error("批量插入aip服务错误代码信息异常：",e);
		}
		return returnStr;
	}

	@Override
	public void updateErrorInfoByServiceIdAndVersion(AipServiceErrorInfoReqDTO aipServiceErrorInfoReqDTO) throws Exception {
		if (aipServiceErrorInfoReqDTO == null || StringUtil.isBlank(aipServiceErrorInfoReqDTO.getServiceId()) || StringUtil.isBlank(aipServiceErrorInfoReqDTO.getVersion())){
			throw new BusinessException("更新aip服务错误代码信息异常，入参为空！");
		}
		try{
			AipServiceErrorInfo aipServiceErrorInfo = new AipServiceErrorInfo();
			AipServiceErrorInfoExample aipServiceErrorInfoExample = new AipServiceErrorInfoExample();
			AipServiceErrorInfoExample.Criteria criteria = aipServiceErrorInfoExample.createCriteria();
			criteria.andServiceIdEqualTo(aipServiceErrorInfoReqDTO.getServiceId());
			criteria.andVersionEqualTo(aipServiceErrorInfoReqDTO.getVersion());
			String notCopy = "errorId,serviceId,vesion";
			ObjectCopyUtil.copyObjValue(aipServiceErrorInfoReqDTO,aipServiceErrorInfo,notCopy,false);
			aipServiceErrorInfoMapper.updateByExampleSelective(aipServiceErrorInfo,aipServiceErrorInfoExample);
		}catch (Exception e){
			log.error("更新aip服务错误代码信息异常：",e);
		}
	}


}
