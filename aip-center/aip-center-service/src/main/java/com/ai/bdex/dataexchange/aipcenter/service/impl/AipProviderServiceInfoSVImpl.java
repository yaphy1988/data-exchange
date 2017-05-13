package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.AipProviderServiceInfoMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderServiceInfo;
import com.ai.bdex.dataexchange.aipcenter.dao.model.AipProviderServiceInfoExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipProviderServiceInfoRespDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipProviderServiceInfoSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yx on 2017/5/12.
 */
@Service("aipProviderServiceSV")
public class AipProviderServiceInfoSVImpl implements IAipProviderServiceInfoSV {

    private final static Logger log = LoggerFactory.getLogger(AipProviderServiceInfoSVImpl.class);

    @Autowired
    private AipProviderServiceInfoMapper aipProviderServiceInfoMapper;

    @Override
    public PageResponseDTO<AipProviderServiceInfoRespDTO> pagePServiceInfo(AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO) throws Exception {
        PageResponseDTO<AipProviderServiceInfoRespDTO> pageResponseDTO = null;

        int pageNo = aipProviderServiceInfoReqDTO.getPageNo();
        int pageSize = aipProviderServiceInfoReqDTO.getPageSize();

        AipProviderServiceInfoExample example = new AipProviderServiceInfoExample();
        AipProviderServiceInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,aipProviderServiceInfoReqDTO);
        PageHelper.startPage(pageNo,pageSize);
        List<AipProviderServiceInfo> list = aipProviderServiceInfoMapper.selectByExample(example);
        PageInfo pageInfo  = new PageInfo(list);
        pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,AipProviderServiceInfoRespDTO.class);

        return pageResponseDTO;
    }

    private void initCriteria(AipProviderServiceInfoExample.Criteria criteria,AipProviderServiceInfoReqDTO aipProviderServiceInfoReqDTO){
        if (!StringUtil.isBlank(aipProviderServiceInfoReqDTO.getpServiceId())){
            criteria.andPServiceIdEqualTo(aipProviderServiceInfoReqDTO.getpServiceId());
        }
        if (!StringUtil.isBlank(aipProviderServiceInfoReqDTO.getVersion())){
            criteria.andVersionEqualTo(aipProviderServiceInfoReqDTO.getVersion());
        }
        if (!StringUtil.isBlank(aipProviderServiceInfoReqDTO.getStatus())){
            criteria.andStatusEqualTo(aipProviderServiceInfoReqDTO.getStatus());
        }
        if (!StringUtil.isBlank(aipProviderServiceInfoReqDTO.getServiceName())){
            criteria.andServiceNameLike("%"+aipProviderServiceInfoReqDTO.getServiceName()+"%");
        }
        if (!StringUtil.isBlank(aipProviderServiceInfoReqDTO.getProviderId())){
            criteria.andProviderIdEqualTo(aipProviderServiceInfoReqDTO.getProviderId());
        }
    }
}
