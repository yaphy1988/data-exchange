package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipServiceInfoSV {
	/**
	 * 返回service_id,version为初始版本(1.0)的信息
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	AipServiceInfo selectServiceByServiceIdWithInitversion(String serviceId)throws Exception;
	
	/**
	 * 查询serviceId中某个版本信息
	 * @param serviceId non-null
	 * @param serviceVersion nullable
	 * @param status nullable
	 * @return
	 * @throws Exception
	 */
	AipServiceInfo getAipServiceInfo(String serviceId,String serviceVersion,String status) throws Exception;
	/**
	 * 查询有效的version版本的serviceid对应的服务
	 * @param serviceId
	 * @param serviceVersion
	 * @return
	 * @throws Exception
	 */
	AipServiceInfo getAipServiceInfo(String serviceId,String serviceVersion)throws Exception;
	/**
	 * 查询服务
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req) throws Exception;	
	/**
	 * 返回version为初始版本(1.0)的服务
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPageWithInitVersion(AipServiceInfoReqDTO req) throws Exception;		
	/**
	 * 返回version为初始版本(1.0)且有效的服务
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPageWithInitVersionAndValidstatus(AipServiceInfoReqDTO req) throws Exception;
	/**
	 * 查询serviceid下所有版本且有效的服务
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	List<AipServiceInfo> selectServiceByServiceId(String serviceId)throws Exception;

	/**
	 * 插入aip服务基本信息，需传version版本号
	 * @param aipServiceInfoReqDTO
	 * @return
	 * @throws Exception
     */
	String insertAipService(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception;

	/**
	 * 更新aip服务信息，根据serviceId更新
	 * @param aipServiceInfoReqDTO
	 * @throws Exception
     */
	void updateAipServiceByServiceId(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception;

	/**
	 * 查询单个aip服务信息
	 * @param aipServiceInfoReqDTO
	 * @return
	 * @throws Exception
     */
	AipServiceInfo queryAipServiceInfo(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception;

}