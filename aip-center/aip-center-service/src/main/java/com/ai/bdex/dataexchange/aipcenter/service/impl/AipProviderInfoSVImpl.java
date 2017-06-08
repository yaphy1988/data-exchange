package com.ai.bdex.dataexchange.aipcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipProviderInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("aipProviderInfoSV")
public class AipProviderInfoSVImpl implements IAipProviderInfoSV{
	private static final Logger log = LoggerFactory.getLogger(AipProviderInfoSVImpl.class);
	@Autowired
	private AipProviderInfoMapper aipProviderInfoMapper;
	@Override
	public AipProviderInfo getAipProviderInfo(String providerId)
			throws Exception {
		try{
			
			return aipProviderInfoMapper.selectByPrimaryKey(providerId);
		}catch(Exception e){
			log.error("query provider failted."+providerId, e);
			throw e;
		}		
	}

	@Override
	public PageResponseDTO<AipProviderInfoRespDTO> pageAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO)
			throws Exception {
		PageResponseDTO<AipProviderInfoRespDTO> pageResponseDTO = null;

		int pageNo = aipProviderInfoReqDTO.getPageNo();
		int pageSize = aipProviderInfoReqDTO.getPageSize();

		AipProviderInfoExample example = new AipProviderInfoExample();
		AipProviderInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, aipProviderInfoReqDTO);
		PageHelper.startPage(pageNo, pageSize);
		example.setOrderByClause("update_time DESC");
		List<AipProviderInfo> list = aipProviderInfoMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo(list);
		pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo, AipProviderInfoRespDTO.class);

		return pageResponseDTO;
	}
	public List<AipProviderInfoRespDTO> queryAipProviderInfoList(AipProviderInfoReqDTO aipProviderInfoReqDTO)throws Exception {
		AipProviderInfoExample example = new AipProviderInfoExample();
		AipProviderInfoExample.Criteria criteria = example.createCriteria();
		initCriteria(criteria, aipProviderInfoReqDTO);
		example.setOrderByClause("provider_sort DESC");
		List<AipProviderInfo> list = aipProviderInfoMapper.selectByExample(example);
		List<AipProviderInfoRespDTO> resultList = new ArrayList<>();
		if(!CollectionUtil.isEmpty(list)){
			for(AipProviderInfo info : list){
				AipProviderInfoRespDTO respDTO = new AipProviderInfoRespDTO();
				ObjectCopyUtil.copyObjValue(info,respDTO,null,false);
				resultList.add(respDTO);
			}
		}
		return resultList;
	}

	@Override
	public String insertAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception {
		if (aipProviderInfoReqDTO==null){
			throw new BusinessException("插入aip服务商信息入参为空！");
		}
		AipProviderInfoReqDTO aipProvider= new AipProviderInfoReqDTO();
		aipProvider.setStatus(Constants.Page.STATUS_VALID);
		List<AipProviderInfoRespDTO> aipProviderList = this.queryAipProviderInfoList(aipProvider);
		int providerSort=0;
		if(CollectionUtils.isNotEmpty(aipProviderList)){
			providerSort=aipProviderList.get(0).getProviderSort();
			providerSort=providerSort+1;
			aipProviderInfoReqDTO.setProviderSort(providerSort);
		}
		String aipProviderId = SeqUtil.getString("SEQ_AIP_PROVIDER_INFO");
		if (StringUtil.isBlank(aipProviderId)){
			throw new BusinessException("获取到的序列aipProviderId为空！");
		}
		AipProviderInfo aipProviderInfo = new AipProviderInfo();
		aipProviderInfoReqDTO.setCreateTime(DateUtil.getNowAsDate());
		aipProviderInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		ObjectCopyUtil.copyObjValue(aipProviderInfoReqDTO,aipProviderInfo,null,false);
		aipProviderInfo.setProviderId(aipProviderId);
		aipProviderInfoMapper.insert(aipProviderInfo);

		return aipProviderId;
	}

	@Override
	public void updateAipProviderInfo(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception {
		if (aipProviderInfoReqDTO==null){
			throw new BusinessException("更新aip服务商信息入参为空！");
		}
		AipProviderInfoExample example = new AipProviderInfoExample();
		AipProviderInfoExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isBlank(aipProviderInfoReqDTO.getProviderId())) {
			criteria.andProviderIdEqualTo(aipProviderInfoReqDTO.getProviderId());
		}
		aipProviderInfoReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		AipProviderInfo aipProviderInfo = new AipProviderInfo();
		ObjectCopyUtil.copyObjValue(aipProviderInfoReqDTO,aipProviderInfo,null,false);
		aipProviderInfoMapper.updateByExampleSelective(aipProviderInfo,example);
	}

	private void initCriteria(AipProviderInfoExample.Criteria criteria, AipProviderInfoReqDTO aipProviderInfoReqDTO) {
		if (!StringUtil.isBlank(aipProviderInfoReqDTO.getProviderId())) {
			criteria.andProviderIdEqualTo(aipProviderInfoReqDTO.getProviderId());
		}

		if (!StringUtil.isBlank(aipProviderInfoReqDTO.getStatus())) {
			criteria.andStatusEqualTo(aipProviderInfoReqDTO.getStatus());
		}
		if (!StringUtil.isBlank(aipProviderInfoReqDTO.getProviderName())) {
			criteria.andProviderNameLike("%" + aipProviderInfoReqDTO.getProviderName() + "%");
		}
		if (aipProviderInfoReqDTO.getProviderSort() != null) {
			criteria.andProviderSortEqualTo(aipProviderInfoReqDTO.getProviderSort());
		}
	}
	 /**
     * 排序调整
     */
    public void modidyAipProviderInfoSort(AipProviderInfoReqDTO aipProviderInfoReqDTO) throws Exception {
    	//当前排序
    	AipProviderInfo currentAipProviderInfo = getAipProviderInfo(aipProviderInfoReqDTO.getProviderId());
        int newOrder = aipProviderInfoReqDTO.getProviderSort();
        
        AipProviderInfo replaceAipProviderInfo = getAipProviderInfoByOrder(currentAipProviderInfo, newOrder);
        if(null == replaceAipProviderInfo ){
            // 该位置没有服务商,直接替换.
        	currentAipProviderInfo.setProviderSort(newOrder);
            currentAipProviderInfo.setUpdateTime(DateUtil.getNowAsDate());
        	aipProviderInfoMapper.updateByPrimaryKeySelective(currentAipProviderInfo);
        }else{
            //  已有服务商,调整顺序,顺序对换
            int currentOrder =currentAipProviderInfo.getProviderSort();
            replaceAipProviderInfo.setProviderSort(currentOrder);
        	currentAipProviderInfo.setProviderSort(newOrder);
            replaceAipProviderInfo.setUpdateTime(DateUtil.getNowAsDate());
            currentAipProviderInfo.setUpdateTime(DateUtil.getNowAsDate());
        	aipProviderInfoMapper.updateByPrimaryKeySelective(replaceAipProviderInfo);
        	aipProviderInfoMapper.updateByPrimaryKeySelective(currentAipProviderInfo);
        }

    }
    private AipProviderInfo getAipProviderInfoByOrder(AipProviderInfo currentProviderInfo , int newOrder) throws Exception{
    	AipProviderInfoExample example = new AipProviderInfoExample();
		AipProviderInfoExample.Criteria criteria = example.createCriteria();
		if (newOrder!= 0) {
			criteria.andProviderSortEqualTo(newOrder);
		}
		List<AipProviderInfo> list = aipProviderInfoMapper.selectByExample(example);

        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

}
