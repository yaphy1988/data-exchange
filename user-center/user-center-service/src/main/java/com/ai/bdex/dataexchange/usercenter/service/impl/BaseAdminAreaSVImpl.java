package com.ai.bdex.dataexchange.usercenter.service.impl;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.BaseAdminAreaMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseAdminArea;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseAdminAreaExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseAdminAreaReqDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IBaseAdminAreaSV;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/5/9.
 */
@Service("iBaseADminAreaSV")
public class BaseAdminAreaSVImpl implements IBaseAdminAreaSV {

    private final static Logger log = LoggerFactory.getLogger(BaseAdminAreaSVImpl.class);

    @Autowired
    private BaseAdminAreaMapper baseAdminAreaMapper;

    @Override
    public List<BaseAdminArea> queryBaseAdminAreaList(BaseAdminAreaReqDTO baseAdminAreaReqDTO) throws Exception {
        if (baseAdminAreaReqDTO==null){
            throw new BusinessException("查询区域信息列表入参为空");
        }
        List<BaseAdminArea> baseAdminAreas = new ArrayList<BaseAdminArea>();
        try {
            BaseAdminAreaExample example = new BaseAdminAreaExample();
            BaseAdminAreaExample.Criteria criteria = example.createCriteria();
            initCriteria(criteria,baseAdminAreaReqDTO);
            baseAdminAreas = baseAdminAreaMapper.selectByExample(example);
        }catch (Exception e){
            log.error("查询区域列表异常：",e);
        }
        return baseAdminAreas;
    }

    private void initCriteria(BaseAdminAreaExample.Criteria criteria,BaseAdminAreaReqDTO baseAdminAreaReqDTO){
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getAreaCode())){
            criteria.andAreaCodeEqualTo(baseAdminAreaReqDTO.getAreaCode());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getAreaName())){
            criteria.andAreaNameLike("%"+baseAdminAreaReqDTO.getAreaName()+"%");
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getParentAreaCode())){
            criteria.andParentAreaCodeEqualTo(baseAdminAreaReqDTO.getParentAreaCode());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getAreaLevel())){
            criteria.andAreaLevelEqualTo(baseAdminAreaReqDTO.getAreaLevel());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getStatus())){
            criteria.andStatusEqualTo(baseAdminAreaReqDTO.getStatus());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getCenterFlag())){
            criteria.andCenterFlagEqualTo(baseAdminAreaReqDTO.getCenterFlag());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getAreaCodeShort())){
            criteria.andAreaCodeShortEqualTo(baseAdminAreaReqDTO.getAreaCodeShort());
        }
        if (!StringUtil.isBlank(baseAdminAreaReqDTO.getAdminAreaCode())){
            criteria.andAdminAreaCodeEqualTo(baseAdminAreaReqDTO.getAdminAreaCode());
        }
    }
}
