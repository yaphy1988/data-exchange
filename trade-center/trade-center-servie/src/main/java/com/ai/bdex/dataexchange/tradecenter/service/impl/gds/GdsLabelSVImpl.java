package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsLabelSV")
public class GdsLabelSVImpl implements IGdsLabelSV {

    @Resource
    private GdsLabelMapper gdsLabelMapper;

    @Override
    public List<GdsLabel> queryGdsLabelList(GdsLabelReqVO gdsLabelReqVO) throws Exception {
        List<GdsLabel> gdsLabels = new ArrayList<GdsLabel>();
        if(gdsLabelReqVO!=null){
            GdsLabelExample example = new GdsLabelExample();
            GdsLabelExample.Criteria criteria = example.createCriteria();
            initCriteria(criteria,gdsLabelReqVO);
            gdsLabels = gdsLabelMapper.selectByExample(example);
        }
        return gdsLabels;
    }

    private void initCriteria(GdsLabelExample.Criteria criteria , GdsLabelReqVO gdsLabelReqVO){
        if (gdsLabelReqVO.getLabId()!=null && gdsLabelReqVO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelReqVO.getLabId());
        }
        if(gdsLabelReqVO.getGdsId()!=null && gdsLabelReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsLabelReqVO.getGdsId());
        }
        if(!StringUtils.isEmpty(gdsLabelReqVO.getLabName())){
            criteria.andLabNameLike("%"+gdsLabelReqVO.getLabName()+"%");
        }
        if (!StringUtils.isEmpty(gdsLabelReqVO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelReqVO.getLabColor());
        }
        if(gdsLabelReqVO.getShowOrder()!=null && gdsLabelReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelReqVO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsLabelReqVO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsLabelReqVO.getIfEdit());
        }
        if (!StringUtils.isEmpty(gdsLabelReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelReqVO.getStatus());
        }
    }
}
