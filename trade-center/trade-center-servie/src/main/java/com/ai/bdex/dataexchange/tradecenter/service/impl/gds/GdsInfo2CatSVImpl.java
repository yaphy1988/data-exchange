package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfo2CatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Cat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2CatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2CatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2CatSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfo2CatSV")
public class GdsInfo2CatSVImpl implements IGdsInfo2CatSV {

    @Resource
    private GdsInfo2CatMapper gdsInfo2CatMapper;

    private void initCriteria(GdsInfo2CatExample.Criteria criteria , GdsInfo2CatReqDTO gdsInfo2CatReqDTO){
        if (gdsInfo2CatReqDTO.getGcId()!=null && gdsInfo2CatReqDTO.getGcId().intValue()>0){
            criteria.andGcIdEqualTo(gdsInfo2CatReqDTO.getGcId());
        }
        if (gdsInfo2CatReqDTO.getCatId()!=null && gdsInfo2CatReqDTO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsInfo2CatReqDTO.getCatId());
        }
        if (gdsInfo2CatReqDTO.getCatFirst()!=null && gdsInfo2CatReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfo2CatReqDTO.getCatFirst());
        }
        if (gdsInfo2CatReqDTO.getGdsId()!=null && gdsInfo2CatReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfo2CatReqDTO.getGdsId());
        }
        if (!StringUtil.isBlank(gdsInfo2CatReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsInfo2CatReqDTO.getStatus());
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
    public List<GdsInfo2Cat> queryGdsInfo2CatList(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception {
        if(gdsInfo2CatReqDTO ==null){
            throw new Exception("查询商品与分类关系信息列表入参为空");
        }
        GdsInfo2CatExample example = new GdsInfo2CatExample();
        GdsInfo2CatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfo2CatReqDTO);
        List<GdsInfo2Cat> gdsInfo2Cats = gdsInfo2CatMapper.selectByExample(example);
        return gdsInfo2Cats;
    }

    @Override
    public int insertGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception {
        if (gdsInfo2CatReqDTO ==null){
            throw  new Exception("插入商品与分类信息入参为空");
        }
        GdsInfo2Cat gdsInfo2Cat = new GdsInfo2Cat();
        int gcId=SeqUtil.getInt("SEQ_GDS_INFO_2_CAT");
        gdsInfo2CatReqDTO.setGcId(gcId);
        gdsInfo2CatReqDTO.setCreateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsInfo2CatReqDTO,gdsInfo2Cat,null,false);
        int code = gdsInfo2CatMapper.insert(gdsInfo2Cat);
        return gcId;
    }

    @Override
    public int updateGdsInfo2Cat(GdsInfo2CatReqDTO gdsInfo2CatReqDTO) throws Exception {
        if (gdsInfo2CatReqDTO ==null){
            throw new Exception("更新商品与分类信息入参为空");
        }
        GdsInfo2Cat gdsInfo2Cat = new GdsInfo2Cat();
        ObjectCopyUtil.copyObjValue(gdsInfo2CatReqDTO,gdsInfo2Cat,null,false);
//        BeanUtils.copyProperties(gdsInfo2CatReqDTO,gdsInfo2Cat);
        int code = gdsInfo2CatMapper.updateByPrimaryKey(gdsInfo2Cat);
        return gdsInfo2CatReqDTO.getGcId();
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
