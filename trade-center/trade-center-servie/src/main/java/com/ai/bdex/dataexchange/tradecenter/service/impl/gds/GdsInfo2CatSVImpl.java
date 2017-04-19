package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfo2CatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Cat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2CatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2CatReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2CatSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfo2CatSV")
public class GdsInfo2CatSVImpl implements IGdsInfo2CatSV {

    @Resource
    private GdsInfo2CatMapper gdsInfo2CatMapper;

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

    @Override
    public GdsInfo2Cat queryGdsInfo2CatById(Integer gcId) throws Exception {
        if (gcId==null && gcId.intValue()<=0){
            throw new Exception("查询商品与分类关系信息入参为空");
        }
        GdsInfo2Cat gdsInfo2Cat = gdsInfo2CatMapper.selectByPrimaryKey(gcId);
        return gdsInfo2Cat;
    }

    @Override
    public List<GdsInfo2Cat> queryGdsInfo2CatList(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception {
        if(gdsInfo2CatReqVO==null){
            throw new Exception("查询商品与分类关系信息列表入参为空");
        }
        GdsInfo2CatExample example = new GdsInfo2CatExample();
        GdsInfo2CatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsInfo2CatReqVO);
        List<GdsInfo2Cat> gdsInfo2Cats = gdsInfo2CatMapper.selectByExample(example);
        return gdsInfo2Cats;
    }

    @Override
    public int insertGdsInfo2Cat(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception {
        if (gdsInfo2CatReqVO==null){
            throw  new Exception("插入商品与分类信息入参为空");
        }
        GdsInfo2Cat gdsInfo2Cat = new GdsInfo2Cat();
        BeanUtils.copyProperties(gdsInfo2CatReqVO,gdsInfo2Cat);
        int code = gdsInfo2CatMapper.insert(gdsInfo2Cat);
        return code;
    }

    @Override
    public int updateGdsInfo2Cat(GdsInfo2CatReqVO gdsInfo2CatReqVO) throws Exception {
        if (gdsInfo2CatReqVO==null){
            throw new Exception("更新商品与分类信息入参为空");
        }
        GdsInfo2Cat gdsInfo2Cat = new GdsInfo2Cat();
        BeanUtils.copyProperties(gdsInfo2CatReqVO,gdsInfo2Cat);
        int code = gdsInfo2CatMapper.updateByPrimaryKey(gdsInfo2Cat);
        return code;
    }

    @Override
    public int deleteGdsInfo2CatById(Integer gcId) throws Exception {
        if (gcId==null || gcId.intValue()<=0){
            throw new Exception("删除商品与分类信息入参为空");
        }
        int code = gdsInfo2CatMapper.deleteByPrimaryKey(gcId);
        return code;
    }
}
