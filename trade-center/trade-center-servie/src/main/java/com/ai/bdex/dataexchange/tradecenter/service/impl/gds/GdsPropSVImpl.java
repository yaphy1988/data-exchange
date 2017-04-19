package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsPropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsPropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsPropReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsPropSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsPropSV")
public class GdsPropSVImpl implements IGdsPropSV {

    @Resource
    private GdsPropMapper gdsPropMapper;

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

    @Override
    public GdsProp queryGdsPropById(Integer proId) throws Exception {
        if (proId==null || proId.intValue()<=0){
            throw new Exception("查询商品属性信息入参为空");
        }
        GdsProp gdsProp = gdsPropMapper.selectByPrimaryKey(proId);
        return gdsProp;
    }

    @Override
    public List<GdsProp> queryGdsPropList(GdsPropReqVO gdsPropReqVO) throws Exception {
        if (gdsPropReqVO==null){
            throw new Exception("查询商品属性信息列表入参为空");
        }
        GdsPropExample gdsPropExample = new GdsPropExample();
        GdsPropExample.Criteria criteria = gdsPropExample.createCriteria();
        initCriteria(criteria,gdsPropReqVO);
        List<GdsProp> gdsProps = gdsPropMapper.selectByExample(gdsPropExample);
        return gdsProps;
    }

    @Override
    public int insertGdsProp(GdsPropReqVO gdsPropReqVO) throws Exception {
        if (gdsPropReqVO==null){
            throw new Exception("插入商品属性信息入参为空");
        }
        GdsProp gdsProp = new GdsProp();
        BeanUtils.copyProperties(gdsPropReqVO,gdsProp);
        int code = gdsPropMapper.insert(gdsProp);
        return code;
    }

    @Override
    public int updateGdsProp(GdsPropReqVO gdsPropReqVO) throws Exception {
        if (gdsPropReqVO==null){
            throw new Exception("更新商品属性信息入参为空");
        }
        GdsProp gdsProp = new GdsProp();
        BeanUtils.copyProperties(gdsPropReqVO,gdsProp);
        int code = gdsPropMapper.updateByPrimaryKey(gdsProp);
        return 0;
    }

    @Override
    public int deleteGdsPropById(Integer proId) throws Exception {
        if(proId==null || proId.intValue()<=0){
            throw new Exception("删除商品属性信息入参为空");
        }
        int code = gdsPropMapper.deleteByPrimaryKey(proId);
        return code;
    }
}
