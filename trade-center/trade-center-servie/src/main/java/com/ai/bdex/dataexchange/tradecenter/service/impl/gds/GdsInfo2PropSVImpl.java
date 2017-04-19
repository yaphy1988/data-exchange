package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfo2PropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Prop;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2PropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfo2PropReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfo2PropSV")
public class GdsInfo2PropSVImpl implements IGdsInfo2PropSV {

    @Resource
    private GdsInfo2PropMapper gdsInfo2PropMapper;

    private void initCriteria(GdsInfo2PropExample.Criteria criteria, GdsInfo2PropReqVO gdsInfo2PropReqVO){
        if (gdsInfo2PropReqVO.getGpId()!=null && gdsInfo2PropReqVO.getGpId().intValue()>0){
            criteria.andGpIdEqualTo(gdsInfo2PropReqVO.getGpId());
        }
        if (gdsInfo2PropReqVO.getGdsId()!=null && gdsInfo2PropReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfo2PropReqVO.getGdsId());
        }
        if (gdsInfo2PropReqVO.getProId()!=null && gdsInfo2PropReqVO.getProId().intValue()>0){
            criteria.andProIdEqualTo(gdsInfo2PropReqVO.getProId());
        }
        if (!StringUtils.isEmpty(gdsInfo2PropReqVO.getProType())){
            criteria.andProTypeEqualTo(gdsInfo2PropReqVO.getProType());
        }
        if (!StringUtils.isEmpty(gdsInfo2PropReqVO.getProValue())){
            criteria.andProValueEqualTo(gdsInfo2PropReqVO.getProValue());
        }
        if (gdsInfo2PropReqVO.getShowOrder()!=null && gdsInfo2PropReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsInfo2PropReqVO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsInfo2PropReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsInfo2PropReqVO.getStatus());
        }
    }

    @Override
    public GdsInfo2Prop queryGdsInfo2PropById(Integer gpId) throws Exception {
        if (gpId==null || gpId.intValue()<=0){
            throw new Exception("查询商品属性关系信息入参为空");
        }
        GdsInfo2Prop gdsInfo2Prop = gdsInfo2PropMapper.selectByPrimaryKey(gpId);

        return gdsInfo2Prop;
    }

    @Override
    public List<GdsInfo2Prop> queryGdsInfo2PropList(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception {
        if(gdsInfo2PropReqVO==null){
            throw new Exception("查询商品属性关系信息列表入参为空");
        }
        GdsInfo2PropExample example = new GdsInfo2PropExample();
        GdsInfo2PropExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsInfo2PropReqVO);
        List<GdsInfo2Prop> gdsInfo2Props = gdsInfo2PropMapper.selectByExample(example);
        return gdsInfo2Props;
    }

    @Override
    public int insertGdsInfo2Prop(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception {
        if (gdsInfo2PropReqVO==null){
            throw new Exception("插入商品属性关系信息入参为空");
        }
        GdsInfo2Prop gdsInfo2Prop = new GdsInfo2Prop();
        BeanUtils.copyProperties(gdsInfo2PropReqVO,gdsInfo2Prop);
        int code = gdsInfo2PropMapper.insert(gdsInfo2Prop);
        return code;
    }

    @Override
    public int updateGdsInfo2Prop(GdsInfo2PropReqVO gdsInfo2PropReqVO) throws Exception {
        if (gdsInfo2PropReqVO==null){
            throw new Exception("更新商品属性关系信息入参为空");
        }
        GdsInfo2Prop gdsInfo2Prop = new GdsInfo2Prop();
        BeanUtils.copyProperties(gdsInfo2PropReqVO,gdsInfo2Prop);
        int code = gdsInfo2PropMapper.updateByPrimaryKey(gdsInfo2Prop);
        return code;
    }

    @Override
    public int deleteGdsInfo2PropById(Integer gpId) throws Exception {
        if (gpId==null || gpId.intValue()<=0){
            throw new Exception("删除商品属性关系信息入参为空");
        }
        int code = gdsInfo2PropMapper.deleteByPrimaryKey(gpId);
        return code;
    }
}
