package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import java.util.List;

import com.ai.paas.sequence.SeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.paas.utils.ObjectCopyUtil;

@Service("aipClientInfoRSV")
public class AipClientInfoRSVImpl implements IAipClientInfoRSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientInfoRSVImpl.class);
	@Autowired
	private IAipClientInfoSV aipClientInfoSV;
	@Override
	public AipClientInfoDTO getAipClientInfoByKey(String clientId)
			throws Exception {
		
		try {
			AipClientInfoDTO dto=null;
			AipClientInfo vo=aipClientInfoSV.getAipClientInfoByKey(clientId);
			if(null!=vo){
				dto=new AipClientInfoDTO();
				ObjectCopyUtil.copyObjValue(vo, dto, null, false);
			}
			return dto;
		} catch (Exception e) {
			log.error("query by clientId failted:"+clientId, e);
			throw e;
		}
	}

	@Override
	public int insertAipClientInfo(AipClientInfoReqDTO info) throws Exception {
		
		try {
			int c=0;
			if(null!=info){
				AipClientInfo vo=new AipClientInfo();
				ObjectCopyUtil.copyObjValue(info, vo, null, false);
				String clientId = SeqUtil.getString("SEQ_AIP_CLIENT_INFO");
				vo.setClientId(clientId);
				c=aipClientInfoSV.insertAipClientInfo(vo);
			}
			return c;
		} catch (Exception e) {
			log.error("insert client messgae failted:"+info.getClientId(), e);
			throw e;
		}
	}

	@Override
	public PageResponseDTO<AipClientInfoDTO> getAipClientInfoPage(
			AipClientInfoReqDTO req) throws Exception {		
		try {			
			return aipClientInfoSV.getAipClientInfoPage(req);
		} catch (Exception e) {
			log.error("query page info failted ", e);
			throw e;
		}
	}

	@Override
	public int batchUpdateStatus(List<String> ClientIdList, String status)
			throws Exception {		
		try {
			return aipClientInfoSV.batchUpdateStatus(ClientIdList,status);
		} catch (Exception e) {
			log.error("batch update clientList to "+status+" failted",e);
			throw e;
		}
	}

	@Override
	public int updateStatus(String clientId, String status) throws Exception {	
		try {
			return aipClientInfoSV.updateStatus(clientId,status);
		} catch (Exception e) {
			log.error("update status failted,"+clientId+":"+status,e);
			throw e;
		}
	}

	@Override
	public int updateAipClientInfo(AipClientInfoDTO info) throws Exception {		
		try {
			if(info !=null){
				AipClientInfo vo=new AipClientInfo();
				ObjectCopyUtil.copyObjValue(info, vo, null, false);
				return aipClientInfoSV.updateAipClientInfo(vo);
			}
			return 0;
		} catch (Exception e) {
			log.error("update failted,"+info.getClientId(),e);
			throw e;
		}
	}
	
	
	
}
