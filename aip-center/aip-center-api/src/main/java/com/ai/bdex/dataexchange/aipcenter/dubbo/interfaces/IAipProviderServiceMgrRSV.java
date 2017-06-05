package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

/**
 * Created by yx on 2017/5/12.
 */
public interface IAipProviderServiceMgrRSV {

    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception;

    public AipProviderInfoRespDTO queryAipProviderInfoByProviderId(String providerId) throws Exception;

    public AipProviderServiceInfoRespDTO queryPServiceByKey(String serviceId,String version) throws Exception;
	public PageResponseDTO<AipProviderInfoRespDTO> pageAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception;
	/** 更新AIP服务供应商信息表
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
