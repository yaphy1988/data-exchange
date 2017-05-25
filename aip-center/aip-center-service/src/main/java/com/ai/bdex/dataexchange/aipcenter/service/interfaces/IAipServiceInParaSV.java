package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInPara;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInParaDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;

public interface IAipServiceInParaSV {
	List<AipServiceInPara> getBeans(String serviceId,String serviceVersion)throws Exception;

	/**
	 * 插入aip入参信息列表，需传version
	 * @param aipServiceInParaDTO
	 * @return
	 * @throws Exception
     */
	String insertInPara(AipServiceInParaDTO aipServiceInParaDTO) throws Exception;


	/**
	 * 根据serviceId更新所有入参
	 * @param aipServiceInParaDTO
	 * @throws Exception
     */
	void updateInParaByServiceIdAndVersion(AipServiceInParaDTO aipServiceInParaDTO) throws Exception;
}
