package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2PropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfo2PropSV")
public class GdsInfo2PropSVImpl implements IGdsInfo2PropSV {

    private void initCriteria(GdsInfo2PropExample.Criteria criteria, GdsInfo2PropReqVO gdsInfo2PropReqVO){
        if (gdsInfo2PropReqVO.getGpId()!=null && gdsInfo2PropReqVO.getGpId().intValue()>0){
            criteria.andGpIdEqualTo(gdsInfo2PropReqVO.getGpId());
        }
        if (gdsInfo2PropReqVO.getGdsId()!=null && gdsInfo2PropReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfo2PropReqVO.getGdsId());
        }
        if (gdsInfo2PropReqVO.getProId()!=null && gdsInfo2PropReqVO.getProId().intValue()>0){
            criteria.andProIdEqualTo(gdsInfo2PropReqVO.getProId());
        }
        if (!StringUtils.isEmpty(gdsInfo2PropReqVO.getProType())){
            criteria.andProTypeEqualTo(gdsInfo2PropReqVO.getProType());
        }
        if (!StringUtils.isEmpty(gdsInfo2PropReqVO.getProValue())){
            criteria.andProValueEqualTo(gdsInfo2PropReqVO.getProValue());
        }
        if (gdsInfo2PropReqVO.getShowOrder()!=null && gdsInfo2PropReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsInfo2PropReqVO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsInfo2PropReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsInfo2PropReqVO.getStatus());
        }
    }
}
