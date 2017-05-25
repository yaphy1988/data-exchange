package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipClientInfoRSV {
	/**
	 * 根据主键查询信息
	 * @param clientId
	 * @return
	 * @throws Exception
	 */
	AipClientInfoDTO getAipClientInfoByKey(String clientId)throws Exception;
	/**
	 * 保存AIP客户端信息
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int insertAipClientInfo(AipClientInfoReqDTO info)throws Exception;
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
	int updateAipClientInfo(AipClientInfoDTO info)throws Exception;
	
}
