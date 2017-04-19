package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelQuikMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuik;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuikExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelQuikReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelQuikSV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsLabelQuikSV")
public class GdsLabelQuikSVImpl implements IGdsLabelQuikSV{

    @Resource
    private GdsLabelQuikMapper gdsLabelQuikMapper;

    private void initCriteria(GdsLabelQuikExample.Criteria criteria, GdsLabelQuikReqVO gdsLabelQuikReqVO){
        if (gdsLabelQuikReqVO.getLabId()!=null && gdsLabelQuikReqVO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelQuikReqVO.getLabId());
        }
        if (!StringUtils.isEmpty(gdsLabelQuikReqVO.getLabName())){
            criteria.andLabNameLike("%"+gdsLabelQuikReqVO.getLabName()+"%");
        }
        if (!StringUtils.isEmpty(gdsLabelQuikReqVO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelQuikReqVO.getLabColor());
        }
        if(gdsLabelQuikReqVO.getCatFirst()!=null && gdsLabelQuikReqVO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsLabelQuikReqVO.getCatFirst());
        }
        if (gdsLabelQuikReqVO.getShowOrder()!=null && gdsLabelQuikReqVO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelQuikReqVO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsLabelQuikReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelQuikReqVO.getStatus());
        }
    }

    @Override
    public GdsLabelQuik queryGdsLabelQuikById(Integer labId) throws Exception {
        if (labId==null || labId.intValue()<=0){
            throw new Exception("查询商品便签快速配置信息入参为空");
        }
        GdsLabelQuik gdsLabelQuik = gdsLabelQuikMapper.selectByPrimaryKey(labId);
        return gdsLabelQuik;
    }

    @Override
    public List<GdsLabelQuik> queryGdsLabelQuikList(GdsLabelQuikReqVO gdsLabelQuikReqVO) throws Exception {
        if (gdsLabelQuikReqVO==null){
            throw new Exception("查询商品便签快速配置信息列表入参为空");
        }
        GdsLabelQuikExample example = new GdsLabelQuikExample();
        GdsLabelQuikExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsLabelQuikReqVO);
        List<GdsLabelQuik> gdsLabelQuiks = gdsLabelQuikMapper.selectByExample(example);
        return gdsLabelQuiks;
    }

    @Override
    public int insertGdsLabelQuik(GdsLabelQuikReqVO gdsLabelQuikReqVO) throws Exception {
        if (gdsLabelQuikReqVO==null){
            throw new Exception("插入商品便签快速配置信息入参为空");
        }
        GdsLabelQuik gdsLabelQuik = new GdsLabelQuik();
        BeanUtils.copyProperties(gdsLabelQuikReqVO,gdsLabelQuik);
        int code = gdsLabelQuikMapper.insert(gdsLabelQuik);
        return code;
    }

    @Override
    public int updateGdsLabelQuik(GdsLabelQuikReqVO gdsLabelQuikReqVO) throws Exception {
        if(gdsLabelQuikReqVO==null){
            throw new Exception("更新商品标签快速配置信息入参为空");
        }
        GdsLabelQuik gdsLabelQuik = new GdsLabelQuik();
        BeanUtils.copyProperties(gdsLabelQuikReqVO,gdsLabelQuik);
        int code = gdsLabelQuikMapper.updateByPrimaryKey(gdsLabelQuik);
        return code;
    }

    @Override
    public int deleteGdsLabelQuikById(Integer labId) throws Exception {
        if (labId==null || labId.intValue()<=0){
            throw new Exception("删除商品标签快速配置信息入参为空");
        }
        int code = gdsLabelQuikMapper.deleteByPrimaryKey(labId);
        return code;
    }
}
