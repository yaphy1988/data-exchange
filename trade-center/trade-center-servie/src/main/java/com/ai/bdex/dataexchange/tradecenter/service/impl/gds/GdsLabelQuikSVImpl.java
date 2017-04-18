package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuikExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelQuikSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsLabelQuikSV")
public class GdsLabelQuikSVImpl implements IGdsLabelQuikSV{

    private void initCriteria(GdsLabelQuikExample.Criteria criteria, GdsLabelQuikReqVO gdsLabelQuikReqVO){
        if (gdsLabelQuikReqVO.getLabId()!=null && gdsLabelQuikReqVO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelQuikReqVO.getLabId());
        }
        if (!StringUtils.isEmpty(gdsLabelQuikReqVO.getLabName())){
            criteria.andLabNameLike("%"+gdsLabelQuikReqVO.getLabName()+"%");
        }
        if (!StringUtils.isEmpty(gdsLabelQuikReqVO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelQuikReqVO.getLabColor());
        }
        if(gdsLabelQuikReqVO.getCatFirst()!=null && gdsLabelQuikReqVO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsLabelQuikReqVO.getCatFirst());
        }
        if (gdsLabelQuikReqVO.getShowOrder()!=null && gdsLabelQuikReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelQuikReqVO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsLabelQuikReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelQuikReqVO.getStatus());
        }
    }
}
