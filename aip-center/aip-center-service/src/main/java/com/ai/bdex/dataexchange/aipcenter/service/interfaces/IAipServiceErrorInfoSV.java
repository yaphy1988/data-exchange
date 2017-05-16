package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceErrorInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceErrorInfoReqDTO;

public interface IAipServiceErrorInfoSV {
	
	List<AipServiceErrorInfo> getbeans(String serviceId,String serviceVersion)throws Exception;

	/**
	 * 批量插入aip服务错误代码信息
	 * @param aipServiceErrorInfoReqDTOList
	 * @return
	 * @throws Exception
	 */
	public String insertErrorInfoBatch(List<AipServiceErrorInfoReqDTO> aipServiceErrorInfoReqDTOList) throws Exception;

	/**
	 * 根据serviceId和version更新服务错误代码信息
	 * @param aipServiceErrorInfoReqDTO
	 * @throws Exception
	 */
	public void updateErrorInfoByServiceIdAndVersion(AipServiceErrorInfoReqDTO aipServiceErrorInfoReqDTO) throws Exception;
}
