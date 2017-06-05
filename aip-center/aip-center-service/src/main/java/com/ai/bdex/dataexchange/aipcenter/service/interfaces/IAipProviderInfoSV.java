package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipProviderInfoSV {
	AipProviderInfo getAipProviderInfo(String providerId)throws Exception;
	/**
	 * 分页查询AIP服务供应商信息表
	 * @param aipProviderInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public PageResponseDTO<AipProviderInfoRespDTO> pageAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO)
			throws Exception;
	/**
	 * 更新AIP服务供应商信息表
	 * @param aipProviderInfoReqDTO
	 * @throws Exception
	 */
	public void updateAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception;
	
	/**
	 * 插入AIP服务供应商信息表
	 * @param aipProviderInfoReqDTO
	 * @return
	 * @throws Exception
	 */
	public String insertAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception;
	/**
	 * 调整AIP服务供应商排序
	 * @param aipProviderInfoReqDTO
	 * @throws Exception
	 */
    public void sortAipProviderInfoByOrder(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception;
	
}
