package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceOutPara;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceOutParaDTO;

public interface IAipServiceOutParaSV {
	List<AipServiceOutPara> getbeans(String serviceId,String serviceVersion)throws Exception;

	/**
	 * 插入aip入参信息列表，需传version
	 * @param aipServiceOutParaDTO
	 * @return
	 * @throws Exception
	 */
	String insertOutPara(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception;


	/**
	 * 根据serviceId更新所有出参
	 * @param aipServiceOutParaDTO
	 * @throws Exception
	 */
	void updateOutParaByServiceIdAndVersion(AipServiceOutParaDTO aipServiceOutParaDTO) throws Exception;
}
