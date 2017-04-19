package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<GdsInfo> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("查询商品信息列表入参为空");
        }
        GdsInfoExample example = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfoReqDTO);
        List<GdsInfo> gdsInfos = gdsInfoMapper.selectByExample(example);
        return gdsInfos;
    }

    @Override
    public int insertGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("插入商品信息入参为空");
        }
        GdsInfo gdsInfo = new GdsInfo();
        BeanUtils.copyProperties(gdsInfoReqDTO,gdsInfo);
        int code = gdsInfoMapper.insert(gdsInfo);
        return code;
    }

    @Override
    public int updateGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws Exception {
        if (gdsInfoReqDTO ==null){
            throw new Exception("更新商品信息入参为空");
        }
        GdsInfo gdsInfo = new GdsInfo();
        BeanUtils.copyProperties(gdsInfoReqDTO,gdsInfo);
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

    private void initCriteria(GdsInfoExample.Criteria criteria, GdsInfoReqDTO gdsInfoReqDTO){
        if(gdsInfoReqDTO.getGdsId()!=null && gdsInfoReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfoReqDTO.getGdsId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getGdsName())){
            criteria.andGdsNameLike("%"+ gdsInfoReqDTO.getGdsName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getGdsSubtitle())){
            criteria.andGdsSubtitleLike("%"+ gdsInfoReqDTO.getGdsSubtitle()+"%");
        }
        if (gdsInfoReqDTO.getCatFirst()!=null && gdsInfoReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfoReqDTO.getCatFirst());
        }
        if (gdsInfoReqDTO.getApiId()!=null && gdsInfoReqDTO.getApiId().intValue()>0){
            criteria.andApiIdEqualTo(gdsInfoReqDTO.getApiId());
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getIfRecommend())){
            criteria.andIfRecommendEqualTo(gdsInfoReqDTO.getIfRecommend());
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getFunIntroduction())){
            criteria.andFunIntroductionEqualTo(gdsInfoReqDTO.getFunIntroduction());
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getCommpanyName())){
            criteria.andCommpanyNameLike("%"+ gdsInfoReqDTO.getCommpanyName()+"%");
        }
        if (!StringUtils.isEmpty(gdsInfoReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsInfoReqDTO.getStatus());
        }
    }
}
