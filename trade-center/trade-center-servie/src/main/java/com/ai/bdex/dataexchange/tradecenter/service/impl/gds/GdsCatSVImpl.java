package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsCatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsCatSV")
public class GdsCatSVImpl implements IGdsCatSV{

    @Resource
    private GdsCatMapper gdsCatMapper;

    @Override
    public GdsCat queryGdsCatById(Integer catId) throws Exception {
        if (catId==null || catId.intValue()<=0){
            throw new Exception("根据ID查询商品分类信息入参为空");
        }
        GdsCat gdsCat = gdsCatMapper.selectByPrimaryKey(catId);
        return gdsCat;
    }

    @Override
    public List<GdsCat> queryGdsCatList(GdsCatReqVO gdsCatReqVO) throws Exception {
        if (gdsCatReqVO==null){
            throw new Exception("查询分类列表的入参为空");
        }
        GdsCatExample example = new GdsCatExample();
        GdsCatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsCatReqVO);
        List<GdsCat> gdsCatList = gdsCatMapper.selectByExample(example);

        return gdsCatList;
    }

    @Override
    public int insertGdsCat(GdsCatReqVO gdsCatReqVO) throws Exception {
        if (gdsCatReqVO==null){
            throw new Exception("插入商品分类信息的入参为空");
        }
        GdsCat gdsCat = new GdsCat();
        BeanUtils.copyProperties(gdsCatReqVO,gdsCat);
        int code = gdsCatMapper.insert(gdsCat);

        return code;
    }

    @Override
    public int updateGdsCat(GdsCatReqVO gdsCatReqVO) throws Exception {
        if (gdsCatReqVO==null){
            throw new Exception("更新商品分类信息的入参为空");
        }

        GdsCat gdsCat = new GdsCat();
        BeanUtils.copyProperties(gdsCatReqVO,gdsCat);
        int code = gdsCatMapper.updateByPrimaryKey(gdsCat);

        return code;
    }

    @Override
    public int deleteGdsCatById(Integer catId) throws Exception {
        if (catId==null || catId.intValue()<=0){
            throw new Exception("删除商品分类信息入参为空");
        }
        int code = gdsCatMapper.deleteByPrimaryKey(catId);
        return code;
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
