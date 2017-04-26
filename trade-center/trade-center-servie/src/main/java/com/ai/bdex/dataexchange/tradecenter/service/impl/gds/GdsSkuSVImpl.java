package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsSkuMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSkuExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsSkuReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsSkuSV")
public class GdsSkuSVImpl implements IGdsSkuSV {

    @Resource
    private GdsSkuMapper gdsSkuMapper;

    @Override
    public GdsSku queryGdsSkuById(Integer skuId) throws Exception {
        if (skuId==null || skuId.intValue()<=0){
            throw new Exception("查询单品信息入参为空");
        }
        GdsSku gdsSku = gdsSkuMapper.selectByPrimaryKey(skuId);
        return gdsSku;
    }

    @Override
    public List<GdsSku> queryGdsSkuList(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        if (gdsSkuReqDTO ==null){
            throw new Exception("查询单品信息列表入参为空");
        }
        List<GdsSku> gdsSkus = new ArrayList<GdsSku>();
        GdsSkuExample gdsSkuExample = new GdsSkuExample();
        GdsSkuExample.Criteria criteria = gdsSkuExample.createCriteria();
        initCriteria(criteria, gdsSkuReqDTO);
        gdsSkus = gdsSkuMapper.selectByExample(gdsSkuExample);

        return gdsSkus;
    }

    @Override
    public int insertGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        if (gdsSkuReqDTO ==null){
            throw new Exception("插入单品信息入参为空");
        }
        int skuId=SeqUtil.getInt("SEQ_GDS_SKU");

        GdsSku gdsSku = new GdsSku();
        gdsSkuReqDTO.setSkuId(skuId);
        gdsSkuReqDTO.setCreateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsSkuReqDTO,gdsSku,null,false);
//        BeanUtils.copyProperties(gdsSkuReqDTO,gdsSku);
        int code = gdsSkuMapper.insert(gdsSku);
        return skuId;
    }

    @Override
    public int updateGdsSku(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        if (gdsSkuReqDTO ==null){
            throw new Exception("更新单品信息入参为空");
        }
        GdsSku gdsSku = new GdsSku();
        ObjectCopyUtil.copyObjValue(gdsSkuReqDTO,gdsSku,null,false);
//        BeanUtils.copyProperties(gdsSkuReqDTO,gdsSku);
        int code = gdsSkuMapper.updateByPrimaryKey(gdsSku);
        return code;
    }

    @Override
    public int deleteGdsSku(Integer skuId) throws Exception {
        if (skuId==null || skuId.intValue()<=0){
            throw new Exception("删除单品信息入参为空");
        }
        int code = gdsSkuMapper.deleteByPrimaryKey(skuId);
        return code;
    }
    @Override
    public int deleteGdsSkuByGdsId(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        if (gdsSkuReqDTO.getGdsId()==null || gdsSkuReqDTO.getGdsId()<=0){
            throw new Exception("删除单品信息入参为空");
        }
        GdsSkuExample gdsSkuExample = new GdsSkuExample();
        GdsSkuExample.Criteria criteria = gdsSkuExample.createCriteria();
        initCriteria(criteria, gdsSkuReqDTO);
        int code = gdsSkuMapper.deleteByExample(gdsSkuExample);
        return code;
    }
    @Override
    public int updataGdsSkuByGdsId(GdsSkuReqDTO gdsSkuReqDTO) throws Exception {
        if (gdsSkuReqDTO.getGdsId()==null || gdsSkuReqDTO.getGdsId()<=0){
            throw new Exception("删除单品信息入参为空");
        }
        GdsSkuExample gdsSkuExample = new GdsSkuExample();
        GdsSkuExample.Criteria criteria = gdsSkuExample.createCriteria();
        if (gdsSkuReqDTO.getSkuId()!=null){
            criteria.andSkuIdEqualTo(gdsSkuReqDTO.getSkuId());
        }
        if(gdsSkuReqDTO.getGdsId()!=null){
            criteria.andGdsIdEqualTo(gdsSkuReqDTO.getGdsId());
        }
        GdsSku gdsSku = new GdsSku();
        gdsSkuReqDTO.setUpdateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsSkuReqDTO,gdsSku,null,false);
        int code = gdsSkuMapper.updateByExampleSelective(gdsSku,gdsSkuExample);
        return code;
    }
    private void initCriteria(GdsSkuExample.Criteria criteria,GdsSkuReqDTO gdsSkuReqDTO){
        if (gdsSkuReqDTO.getSkuId()!=null){
            criteria.andSkuIdEqualTo(gdsSkuReqDTO.getSkuId());
        }
        if(gdsSkuReqDTO.getGdsId()!=null){
            criteria.andGdsIdEqualTo(gdsSkuReqDTO.getGdsId());
        }
        if (!StringUtil.isBlank(gdsSkuReqDTO.getSkuName())){
            criteria.andSkuNameLike("%"+ gdsSkuReqDTO.getSkuName()+"%");
        }
        if(gdsSkuReqDTO.getPackPrice()!=null && gdsSkuReqDTO.getPackPrice().intValue()>0){
            criteria.andPackPriceEqualTo(gdsSkuReqDTO.getPackPrice());
        }
        if (gdsSkuReqDTO.getPackDay()!=null && gdsSkuReqDTO.getPackDay().intValue()>0){
            criteria.andPackDayEqualTo(gdsSkuReqDTO.getPackDay());
        }
        if (gdsSkuReqDTO.getPackTimes()!=null && gdsSkuReqDTO.getPackTimes().intValue()>0){
            criteria.andPackTimesEqualTo(gdsSkuReqDTO.getPackTimes());
        }
        if(gdsSkuReqDTO.getShowOrder()!=null && gdsSkuReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsSkuReqDTO.getShowOrder());
        }
        if (!StringUtil.isBlank(gdsSkuReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsSkuReqDTO.getStatus());
        }
    }
}
