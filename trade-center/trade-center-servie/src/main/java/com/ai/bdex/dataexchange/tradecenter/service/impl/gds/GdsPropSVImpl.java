package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsPropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsPropReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsPropSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsPropSV")
public class GdsPropSVImpl implements IGdsPropSV {

    private void initCriteria(GdsPropExample.Criteria criteria, GdsPropReqVO gdsPropReqVO){
        if (gdsPropReqVO.getProId()!=null && gdsPropReqVO.getProId().intValue()>0){
            criteria.andProIdEqualTo(gdsPropReqVO.getProId());
        }
        if (!StringUtils.isEmpty(gdsPropReqVO.getProType())){
            criteria.andProTypeEqualTo(gdsPropReqVO.getProType());
        }
        if (gdsPropReqVO.getProName()!=null && gdsPropReqVO.getProName().intValue()>0){
            criteria.andProNameEqualTo(gdsPropReqVO.getProName());
        }
        if (gdsPropReqVO.getCatId()!=null && gdsPropReqVO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsPropReqVO.getCatId());
        }
        if (gdsPropReqVO.getShowOrder()!=null && gdsPropReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsPropReqVO.getShowOrder());
        }
        if (!StringUtils.isEmpty(gdsPropReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsPropReqVO.getStatus());
        }
    }
}
