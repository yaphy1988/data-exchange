package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipClientInfoSV {
	AipClientInfo getAipClientInfoByKey(String clientId)throws Exception;
	int insertAipClientInfo(AipClientInfo info)throws Exception;
	/**
	 * 按页查询AIP客户端信息
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<AipClientInfoDTO> getAipClientInfoPage(AipClientInfoReqDTO req)throws Exception;
	/**
	 * 批量更新AIP客户端状态
	 * @param ClientIdList
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int batchUpdateStatus(List<String> ClientIdList,String status)throws Exception;
	/**
	 * 更新指定客户端状态
	 * @param clientId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateStatus(String clientId,String status)throws Exception;
	/**
	 * 更新指定AIP客户端信息
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int updateAipClientInfo(AipClientInfo info)throws Exception;
}
