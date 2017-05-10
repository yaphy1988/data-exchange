package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import java.util.List;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;

public interface IAipServiceInfoRSV {
	/**
	 * 查询服务
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req)throws Exception;
	/**
	 * 查询serviceid下所有版本且有效的服务
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	List<AipServiceInfoDTO> selectServiceByServiceId(String serviceId)throws Exception;
	/**
	 * 查询serviceid中version版本的有效的服务
	 * @param serviceId
	 * @param version
	 * @return
	 * @throws Exception
	 */
	AipServiceInfoDTO selectServiceByPk(String serviceId,String version)throws Exception;
	/**
	 * 返回service_id,version为初始版本(1.0)的信息
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	AipServiceInfoDTO selectServiceByServiceIdWithInitversion(String serviceId)throws Exception;
	
	/**
	 * 查询serviceId中某个版本信息
	 * @param serviceId non-null
	 * @param serviceVersion nullable
	 * @param status nullable
	 * @return
	 * @throws Exception
	 */
	AipServiceInfoDTO getAipServiceInfo(String serviceId,String serviceVersion,String status) throws Exception;

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

}
