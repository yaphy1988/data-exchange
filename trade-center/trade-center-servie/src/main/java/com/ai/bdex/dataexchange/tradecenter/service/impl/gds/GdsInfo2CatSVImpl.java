package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2CatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2CatReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2CatSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfo2CatSV")
public class GdsInfo2CatSVImpl implements IGdsInfo2CatSV {

    private void initCriteria(GdsInfo2CatExample.Criteria criteria , GdsInfo2CatReqVO gdsInfo2CatReqVO){
        if (gdsInfo2CatReqVO.getGcId()!=null && gdsInfo2CatReqVO.getGcId().intValue()>0){
            criteria.andGcIdEqualTo(gdsInfo2CatReqVO.getGcId());
        }
        if (gdsInfo2CatReqVO.getCatId()!=null && gdsInfo2CatReqVO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsInfo2CatReqVO.getCatId());
        }
        if (gdsInfo2CatReqVO.getCatFirst()!=null && gdsInfo2CatReqVO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfo2CatReqVO.getCatFirst());
        }
        if (gdsInfo2CatReqVO.getGdsId()!=null && gdsInfo2CatReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfo2CatReqVO.getGdsId());
        }
        if (!StringUtils.isEmpty(gdsInfo2CatReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsInfo2CatReqVO.getStatus());
        }
    }
}
