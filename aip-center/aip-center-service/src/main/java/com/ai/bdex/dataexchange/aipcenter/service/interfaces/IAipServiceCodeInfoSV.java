package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceCodeInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceCodeInfoReqDTO;

public interface IAipServiceCodeInfoSV {
	List<AipServiceCodeInfo> getBeans(String serviceId,String serviceVersion)throws Exception;

	/**
	 * 批量插入示例代码信息
	 * @param aipServiceCodeInfoReqDTOList
	 * @return
	 * @throws Exception
	 */
	public String insertServiceCodeBatch(List<AipServiceCodeInfoReqDTO> aipServiceCodeInfoReqDTOList) throws Exception;

	/**
	 * 根据serviceId和version更新示例代码信息
	 * @param aipServiceCodeInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public void updateServiceCodeByServiceIdAndVersion(AipServiceCodeInfoReqDTO aipServiceCodeInfoReqDTO) throws Exception;
}
