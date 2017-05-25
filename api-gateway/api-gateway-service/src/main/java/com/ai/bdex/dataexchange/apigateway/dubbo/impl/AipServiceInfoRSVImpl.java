package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.apigateway.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipServiceInfoRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipServiceInfoRSV")
public class AipServiceInfoRSVImpl implements IAipServiceInfoRSV{
	private static final Logger log = LoggerFactory.getLogger(AipServiceInfoRSVImpl.class);
	@Autowired
	private IAipServiceInfoSV aipServiceInfoSV;
	@Override
	public PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(AipServiceInfoReqDTO req) throws Exception {
		try{	        
			return aipServiceInfoSV.selectServiceWithPage(req);
		}catch(Exception e){
			log.error("query service failted", e);
			throw e;
		}
	}
	@Override
	public List<AipServiceInfoDTO> selectServiceByServiceId(String serviceId)
			throws Exception {
		try{
			List<AipServiceInfoDTO> list=null;
			List<AipServiceInfo> voList=aipServiceInfoSV.selectServiceByServiceId(serviceId);
			
			if(!CollectionUtil.isEmpty(voList)){
				list=new ArrayList<AipServiceInfoDTO>();
				AipServiceInfoDTO dto=null;
				for(AipServiceInfo vo:voList){
					dto=new AipServiceInfoDTO();
					ObjectCopyUtil.copyObjValue(vo, dto, null, false);
					list.add(dto);
				}
			}
			return list;
		}catch(Exception e){
			log.error("query service by service_Id failted:"+serviceId, e);
			throw e;
		}
	}
	
	@Override
	public AipServiceInfoDTO selectServiceByPk(String serviceId, String version)
			throws Exception {		
		try{
			AipServiceInfoDTO dto=null;
			AipServiceInfo info=aipServiceInfoSV.getAipServiceInfo(serviceId,version);
			if(null!=info){
				dto=new AipServiceInfoDTO();
				ObjectCopyUtil.copyObjValue(info, dto, null, false);
			}
			return dto;
		}catch(Exception e){
			log.error("query service by service_Id and service_version failted:"+serviceId+":"+version, e);
			throw e;
		}
	}
	@Override
	public AipServiceInfoDTO selectServiceByServiceIdWithInitversion(
			String serviceId) throws Exception {
		try{
			AipServiceInfoDTO dto=null;
			AipServiceInfo info=aipServiceInfoSV.selectServiceByServiceIdWithInitversion(serviceId);
			if(null!=info){
				dto=new AipServiceInfoDTO();
				ObjectCopyUtil.copyObjValue(info, dto, null, false);
			}
			return dto;
		}catch(Exception e){
			log.error("query service by service_Id and service_version failted:"+serviceId, e);
			throw e;
		}
	}
	@Override
	public AipServiceInfoDTO getAipServiceInfo(String serviceId,
			String serviceVersion, String status) throws Exception {
		try{
			AipServiceInfoDTO dto=null;
			AipServiceInfo info=aipServiceInfoSV.getAipServiceInfo(serviceId, serviceVersion, status);
			if(null!=info){
				dto=new AipServiceInfoDTO();
				ObjectCopyUtil.copyObjValue(info, dto, null, false);
			}
			return dto;
		}catch(Exception e){
			log.error("query service by service_Id and service_version failted:"+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}
	
	@Override
	public PageResponseDTO<AipServiceInfoDTO> selectServiceWithPageWithInitVersion(
			AipServiceInfoReqDTO req) throws Exception {
		try{	        
			return aipServiceInfoSV.selectServiceWithPageWithInitVersion(req);
		}catch(Exception e){
			log.error("query service failted", e);
			throw e;
		}
	}
	@Override
	public PageResponseDTO<AipServiceInfoDTO> selectServiceWithPageWithInitVersionAndValidstatus(
			AipServiceInfoReqDTO req) throws Exception {
		try{	        
			return aipServiceInfoSV.selectServiceWithPageWithInitVersionAndValidstatus(req);
		}catch(Exception e){
			log.error("query service failted", e);
			throw e;
		}
	}

	@Override
	public AipServiceInfoDTO queryAipServiceInfo(AipServiceInfoReqDTO aipServiceInfoReqDTO) throws Exception {
		AipServiceInfoDTO aipServiceInfoDTO = new AipServiceInfoDTO();
		try {
			AipServiceInfo aipServiceInfo = aipServiceInfoSV.queryAipServiceInfo(aipServiceInfoReqDTO);
			if (aipServiceInfo!=null){
				ObjectCopyUtil.copyObjValue(aipServiceInfo,aipServiceInfoDTO,null,false);
			}
		}catch (Exception e){
			log.error("查询aip服务基本信息异常：",e);
		}
		return aipServiceInfoDTO;
	}


}
