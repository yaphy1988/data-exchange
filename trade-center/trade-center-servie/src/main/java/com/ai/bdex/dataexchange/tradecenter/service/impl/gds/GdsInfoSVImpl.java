package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqVO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsInfoSV")
public class GdsInfoSVImpl implements IGdsInfoSV{

    @Autowired
    private GdsInfoMapper gdsInfoMapper;

    @Override
    public GdsInfo queryGdsInfoById(Integer gdsId) throws Exception {

        if (gdsId==null || gdsId.intValue()<=0){
            throw new Exception("查询商品信息入参为空");
        }
        GdsInfo gdsInfo = gdsInfoMapper.selectByPrimaryKey(gdsId);
        return gdsInfo;
    }

    @Override
    public List<GdsInfo> queryGdsInfoList(GdsInfoReqVO gdsInfoReqVO) throws Exception {
        if (gdsInfoReqVO==null){
            throw new Exception("查询商品信息列表入参为空");
        }
        GdsInfoExample example = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,gdsInfoReqVO);
        List<GdsInfo> gdsInfos = gdsInfoMapper.selectByExample(example);
        return gdsInfos;
    }

    @Override
    public int insertGdsInfo(GdsInfoReqVO gdsInfoReqVO) throws Exception {
        if (gdsInfoReqVO==null){
            throw new Exception("插入商品信息入参为空");
        }
        GdsInfo gdsInfo = new GdsInfo();
        BeanUtils.copyProperties(gdsInfoReqVO,gdsInfo);
        int code = gdsInfoMapper.insert(gdsInfo);
        return code;
    }

    @Override
    public int updateGdsInfo(GdsInfoReqVO gdsInfoReqVO) throws Exception {
        if (gdsInfoReqVO==null){
            throw new Exception("更新商品信息入参为空");
        }
        GdsInfo gdsInfo = new GdsInfo();
        BeanUtils.copyProperties(gdsInfoReqVO,gdsInfo);
        int code = gdsInfoMapper.updateByPrimaryKey(gdsInfo);
        return code;
    }

    @Override
    public int deleteGdsInfoById(Integer gdsId) throws Exception {
        if (gdsId==null || gdsId.intValue()<=0){
            throw new Exception("删除商品信息入参为空");
        }
        int code = gdsInfoMapper.deleteByPrimaryKey(gdsId);
        return code;
    }

    private void initCriteria(GdsInfoExample.Criteria criteria, GdsInfoReqVO gdsInfoReqVO){
        if(gdsInfoReqVO.getGdsId()!=null && gdsInfoReqVO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfoReqVO.getGdsId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getGdsName())){
            criteria.andGdsNameLike("%"+gdsInfoReqVO.getGdsName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getGdsSubtitle())){
            criteria.andGdsSubtitleLike("%"+gdsInfoReqVO.getGdsSubtitle()+"%");
        }
        if (gdsInfoReqVO.getCatFirst()!=null && gdsInfoReqVO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfoReqVO.getCatFirst());
        }
        if (gdsInfoReqVO.getApiId()!=null && gdsInfoReqVO.getApiId().intValue()>0){
            criteria.andApiIdEqualTo(gdsInfoReqVO.getApiId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getIfRecommend())){
            criteria.andIfRecommendEqualTo(gdsInfoReqVO.getIfRecommend());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getFunIntroduction())){
            criteria.andFunIntroductionEqualTo(gdsInfoReqVO.getFunIntroduction());
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getCommpanyName())){
            criteria.andCommpanyNameLike("%"+gdsInfoReqVO.getCommpanyName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqVO.getStatus())){
            criteria.andStatusEqualTo(gdsInfoReqVO.getStatus());
        }
    }
}
