package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipServiceInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipServiceInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("aipServiceInfoSV")
public class AipServiceInfoSVImpl implements IAipServiceInfoSV{
	@Autowired
	private AipServiceInfoMapper aipServiceInfoMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AipServiceInfoSVImpl.class);

	@Override
	public AipServiceInfo getAipServiceInfo(String serviceId,
			String serviceVersion) throws Exception {
		try{
			AipServiceInfoExample key=new AipServiceInfoExample();
			AipServiceInfoExample.Criteria sql=key.createCriteria();
			sql.andServiceIdEqualTo(serviceId);
			sql.andVersionEqualTo(serviceVersion);
			sql.andStatusEqualTo("1");		
			List<AipServiceInfo> list=aipServiceInfoMapper.selectByExample(key);
			if(!CollectionUtil.isEmpty(list)){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			log.error("query service failted."+serviceId+":"+serviceVersion, e);
			throw e;
		}
	}

	@Override
	public PageResponseDTO<AipServiceInfoDTO> selectServiceWithPage(
			AipServiceInfoReqDTO req) throws Exception {
		try{
			int pageNo=req.getPageNo();
			int pageSize=req.getPageSize();
			AipServiceInfoExample ex=new AipServiceInfoExample();
			AipServiceInfoExample.Criteria sql=ex.createCriteria();
			//查询条件
			if(!StringUtil.isBlank(req.getType())){
				sql.andTypeEqualTo(req.getType());
			}
			if(!StringUtil.isBlank(req.getProviderId())){
				sql.andProviderIdEqualTo(req.getProviderId());
			}
			if(!StringUtil.isBlank(req.getSupportFormat())){
				sql.andSupportFormatEqualTo(req.getSupportFormat());
			}
			if(!StringUtil.isBlank(req.getReqType())){
				sql.andReqTypeEqualTo(req.getReqType());
			}			
			
			if(StringUtil.isBlank(req.getStatus())){
				sql.andStatusEqualTo("1");
			}else{
				sql.andStatusEqualTo(req.getStatus());
			}
			ex.setOrderByClause("create_time desc");
			//分页设置
			PageHelper.startPage(pageNo, pageSize);

			List<AipServiceInfo> list=aipServiceInfoMapper.selectByExample(ex);
	        PageInfo<AipServiceInfo> pageInfo = new PageInfo<AipServiceInfo>(list);
	       
	        log.info("aipServiceInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + list.size());
	        PageResponseDTO<AipServiceInfoDTO> rspDto= PageResponseFactory.genPageResponse(pageInfo,AipServiceInfoDTO.class);
			return rspDto;
		}catch(Exception e){
			log.error("query service of page failted", e);
			throw e;
		}
	}
	
	
	
}
