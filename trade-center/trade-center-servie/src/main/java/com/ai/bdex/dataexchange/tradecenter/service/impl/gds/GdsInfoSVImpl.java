package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfoSV")
public class GdsInfoSVImpl implements IGdsInfoSV{

    @Autowired
    private GdsInfoMapper gdsInfoMapper;

    @Override
    public GdsInfo queryGdsInfoById(Integer gdsId) throws Exception {

        GdsInfo gdsInfo = gdsInfoMapper.selectByPrimaryKey(gdsId);
        return gdsInfo;
    }

    private void initCriteria(GdsInfoExample.Criteria criteria, GdsInfoReqVO gdsInfoReqVO){
        if(gdsInfoReqVO.getGdsId()!=null && gdsInfoReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfoReqVO.getGdsId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getGdsName())){
            criteria.andGdsNameLike("%"+gdsInfoReqVO.getGdsName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getGdsSubtitle())){
            criteria.andGdsSubtitleLike("%"+gdsInfoReqVO.getGdsSubtitle()+"%");
        }
        if (gdsInfoReqVO.getCatFirst()!=null && gdsInfoReqVO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfoReqVO.getCatFirst());
        }
        if (gdsInfoReqVO.getApiId()!=null && gdsInfoReqVO.getApiId().intValue()>0){
            criteria.andApiIdEqualTo(gdsInfoReqVO.getApiId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getIfRecommend())){
            criteria.andIfRecommendEqualTo(gdsInfoReqVO.getIfRecommend());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getFunIntroduction())){
            criteria.andFunIntroductionEqualTo(gdsInfoReqVO.getFunIntroduction());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getCommpanyName())){
            criteria.andCommpanyNameLike("%"+gdsInfoReqVO.getCommpanyName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsInfoReqVO.getStatus());
        }
    }
}
