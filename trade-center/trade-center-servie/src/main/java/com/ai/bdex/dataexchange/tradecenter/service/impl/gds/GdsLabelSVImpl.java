package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import org.springframework.beans.BeanUtils;
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
    public GdsLabel queryGdsLabelById(Integer labId) throws Exception {
        if (labId==null || labId.intValue()<=0){
            throw new Exception("查询商品标签信息入参为空");
        }
        GdsLabel gdsLabel = gdsLabelMapper.selectByPrimaryKey(labId);
        return gdsLabel;
    }

    @Override
    public List<GdsLabel> queryGdsLabelList(GdsLabelReqVO gdsLabelReqVO) throws Exception {
        if (gdsLabelReqVO==null){
            throw new Exception("查询商品标签信息列表入参为空");
        }
        List<GdsLabel> gdsLabels = new ArrayList<GdsLabel>();
        GdsLabelExample example = new GdsLabelExample();
        GdsLabelExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsLabelReqVO);
        gdsLabels = gdsLabelMapper.selectByExample(example);
        return gdsLabels;
    }

    @Override
    public int insertGdsLabel(GdsLabelReqVO gdsLabelReqVO) throws Exception {
        if (gdsLabelReqVO==null){
            throw new Exception("插入商品标签信息入参为空");
        }
        GdsLabel gdsLabel = new GdsLabel();
        BeanUtils.copyProperties(gdsLabelReqVO,gdsLabel);
        int code = gdsLabelMapper.insert(gdsLabel);
        return code;
    }

    @Override
    public int updateGdsLabel(GdsLabelReqVO gdsLabelReqVO) throws Exception {
        if (gdsLabelReqVO==null){
            throw new Exception("更新商品标签信息入参为空");
        }
        GdsLabel gdsLabel = new GdsLabel();
        BeanUtils.copyProperties(gdsLabelReqVO,gdsLabel);
        int code = gdsLabelMapper.updateByPrimaryKey(gdsLabel);
        return code;
    }

    @Override
    public int deleteGdslabelById(Integer labId) throws Exception {
        if (labId==null || labId.intValue()<=0){
            throw new Exception("删除商品标签信息入参为空");
        }
        int code = gdsLabelMapper.deleteByPrimaryKey(labId);
        return code;
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
