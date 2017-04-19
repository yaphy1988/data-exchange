package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsSkuMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSku;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsSkuExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsSkuReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsSkuSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<GdsSku> queryGdsSkuList(GdsSkuReqVO gdsSkuReqVO) throws Exception {
        if (gdsSkuReqVO==null){
            throw new Exception("查询单品信息列表入参为空");
        }
        List<GdsSku> gdsSkus = new ArrayList<GdsSku>();
        GdsSkuExample gdsSkuExample = new GdsSkuExample();
        GdsSkuExample.Criteria criteria = gdsSkuExample.createCriteria();
        initCriteria(criteria,gdsSkuReqVO);
        gdsSkus = gdsSkuMapper.selectByExample(gdsSkuExample);

        return gdsSkus;
    }

    @Override
    public int insertGdsSku(GdsSkuReqVO gdsSkuReqVO) throws Exception {
        if (gdsSkuReqVO==null){
            throw new Exception("插入单品信息入参为空");
        }
        GdsSku gdsSku = new GdsSku();
        BeanUtils.copyProperties(gdsSkuReqVO,gdsSku);
        int code = gdsSkuMapper.insert(gdsSku);
        return code;
    }

    @Override
    public int updateGdsSku(GdsSkuReqVO gdsSkuReqVO) throws Exception {
        if (gdsSkuReqVO==null){
            throw new Exception("更新单品信息入参为空");
        }
        GdsSku gdsSku = new GdsSku();
        BeanUtils.copyProperties(gdsSkuReqVO,gdsSku);
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

    private void initCriteria(GdsSkuExample.Criteria criteria,GdsSkuReqVO gdsSkuReqVO){
        if (gdsSkuReqVO.getSkuId()!=null){
            criteria.andSkuIdEqualTo(gdsSkuReqVO.getSkuId());
        }
        if(gdsSkuReqVO.getGdsId()!=null){
            criteria.andGdsIdEqualTo(gdsSkuReqVO.getGdsId());
        }
        if (!StringUtils.isEmpty(gdsSkuReqVO.getSkuName())){
            criteria.andSkuNameLike("%"+gdsSkuReqVO.getSkuName()+"%");
        }
        if(gdsSkuReqVO.getPackPrice()!=null && gdsSkuReqVO.getPackPrice().intValue()>0){
            criteria.andPackPriceEqualTo(gdsSkuReqVO.getPackPrice());
        }
        if (gdsSkuReqVO.getPackDay()!=null && gdsSkuReqVO.getPackDay().intValue()>0){
            criteria.andPackDayEqualTo(gdsSkuReqVO.getPackDay());
        }
        if (gdsSkuReqVO.getPackTimes()!=null && gdsSkuReqVO.getPackTimes().intValue()>0){
            criteria.andPackTimesEqualTo(gdsSkuReqVO.getPackTimes());
        }
        if(gdsSkuReqVO.getShowOrder()!=null && gdsSkuReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsSkuReqVO.getShowOrder());
        }
        if (!StringUtils.isEmpty(gdsSkuReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsSkuReqVO.getStatus());
        }
    }
}
