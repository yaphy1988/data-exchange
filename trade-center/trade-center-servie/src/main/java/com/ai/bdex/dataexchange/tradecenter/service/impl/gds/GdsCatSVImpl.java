package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsCatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsCatSV")
public class GdsCatSVImpl implements IGdsCatSV{

    @Resource
    private GdsCatMapper gdsCatMapper;

    @Override
    public GdsCat queryGdsCatById(Integer catId) throws Exception {
        if (catId==null){
            throw new Exception("根据ID查询商品分类信息入参为空");
        }
        GdsCat gdsCat = gdsCatMapper.selectByPrimaryKey(catId);
        return gdsCat;
    }

    private void initCriteria(GdsCatExample.Criteria criteria, GdsCatReqVO gdsCatReqVO){
        if (gdsCatReqVO.getCatId()!=null && gdsCatReqVO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsCatReqVO.getCatId());
        }
        if (gdsCatReqVO.getCatPid()!=null && gdsCatReqVO.getCatPid().intValue()>0){
            criteria.andCatPidEqualTo(gdsCatReqVO.getCatPid());
        }
        if(!StringUtils.isEmpty(gdsCatReqVO.getCatName())){
            criteria.andCatNameLike("%"+gdsCatReqVO.getCatName()+"%");
        }
        if(!StringUtils.isEmpty(gdsCatReqVO.getCatDesc())){
            criteria.andCatDescLike("%" + gdsCatReqVO.getCatDesc() + "%");
        }
        if (gdsCatReqVO.getShowOrder()!=null && gdsCatReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsCatReqVO.getShowOrder());
        }
        if (!StringUtils.isEmpty(gdsCatReqVO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsCatReqVO.getIfEdit());
        }
        if (!StringUtils.isEmpty(gdsCatReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsCatReqVO.getStatus());
        }
    }
}
