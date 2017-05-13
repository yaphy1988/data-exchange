package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipClientInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipClientInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipClientInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("aipClientInfoSV")
public class AipClientInfoSVImpl implements IAipClientInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipClientInfoSVImpl.class);
	@Autowired
	private AipClientInfoMapper aipClientInfoMapper;
	
	@Override
	public AipClientInfo getAipClientInfoByKey(String clientId)
			throws Exception {
		try{
			AipClientInfoExample ex=new AipClientInfoExample();
			AipClientInfoExample.Criteria sql=ex.createCriteria();
			sql.andClientIdEqualTo(clientId);
			sql.andStatusEqualTo("1");
			List<AipClientInfo> list=aipClientInfoMapper.selectByExample(ex);
			if(!CollectionUtil.isEmpty(list)){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			log.error("query clientId message error:"+clientId, e);
			throw e;
		}
	}

	@Override
	public int insertAipClientInfo(AipClientInfo info) throws Exception {
		try{
			return aipClientInfoMapper.insert(info);
		}catch(Exception e){
			log.error("insert client message error:"+info.getClientId(), e);
			throw e;
		}
	}

	@Override
	public PageResponseDTO<AipClientInfoDTO> getAipClientInfoPage(
			AipClientInfoReqDTO req) throws Exception {		
		try {
			AipClientInfoExample ex=new AipClientInfoExample();
			AipClientInfoExample.Criteria sql=ex.createCriteria();
			initParam(sql,req);
			ex.setOrderByClause("create_time desc");		
			PageHelper.startPage(req.getPageNo(), req.getPageSize());
			List<AipClientInfo> list=aipClientInfoMapper.selectByExample(ex);
			
			 PageInfo<AipClientInfo> pageInfo = new PageInfo<AipClientInfo>(list);
			 PageResponseDTO<AipClientInfoDTO> rspDto= PageResponseFactory.genPageResponse(pageInfo,AipClientInfoDTO.class);
			 return rspDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int batchUpdateStatus(List<String> ClientIdList, String status)
			throws Exception {		
		try {
			AipClientInfoExample ex=new AipClientInfoExample();
			AipClientInfoExample.Criteria sql=ex.createCriteria();
			sql.andClientIdIn(ClientIdList);
			AipClientInfo record=new AipClientInfo();
			record.setStatus(status);		
			return aipClientInfoMapper.updateByExampleSelective(record, ex);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int updateStatus(String clientId, String status) throws Exception {
		try {
			AipClientInfoExample ex=new AipClientInfoExample();
			AipClientInfoExample.Criteria sql=ex.createCriteria();
			sql.andClientIdEqualTo(clientId);
			AipClientInfo record=new AipClientInfo();
			record.setStatus(status);		
			return aipClientInfoMapper.updateByExampleSelective(record, ex);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int updateAipClientInfo(AipClientInfo info) throws Exception {
		try {		
			return aipClientInfoMapper.updateByPrimaryKeySelective(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void initParam(AipClientInfoExample.Criteria sql,AipClientInfoReqDTO req){
		if(req.getClientId()!=null){
			sql.andClientIdEqualTo(req.getClientId());
		}
		if(req.getCreateStaff()!=null){
			sql.andCreateStaffEqualTo(req.getCreateStaff());
		}
		if(req.getFromCreateTime()!=null&&req.getToCreateTime()!=null){
			sql.andCreateTimeBetween(req.getFromCreateTime(), req.getToCreateTime());
		}
		if(req.getFromEffectiveTime()!=null&&req.getToEffectiveTime()!=null){
			sql.andEffectiveTimeBetween(req.getFromEffectiveTime(), req.getToEffectiveTime());
		}
		if(!CollectionUtils.isEmpty(req.getStatusList())){
			if(req.getStatusList().size()>1){
				sql.andStatusIn(req.getStatusList());
			}else{
				sql.andStatusEqualTo(req.getStatusList().get(0));
			}
		}
		if(req.getUsername()!=null){
			sql.andUsernameEqualTo(req.getUsername());
		}
	}
}
