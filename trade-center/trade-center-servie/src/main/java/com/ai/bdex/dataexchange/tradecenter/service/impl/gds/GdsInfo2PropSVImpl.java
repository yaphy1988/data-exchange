package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfo2PropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2Prop;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo2PropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfo2PropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfo2PropSV;
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
@Service("iGdsInfo2PropSV")
public class GdsInfo2PropSVImpl implements IGdsInfo2PropSV {

    @Resource
    private GdsInfo2PropMapper gdsInfo2PropMapper;

    private void initCriteria(GdsInfo2PropExample.Criteria criteria, GdsInfo2PropReqDTO gdsInfo2PropReqDTO){
        if (gdsInfo2PropReqDTO.getGpId()!=null && gdsInfo2PropReqDTO.getGpId().intValue()>0){
            criteria.andGpIdEqualTo(gdsInfo2PropReqDTO.getGpId());
        }
        if (gdsInfo2PropReqDTO.getGdsId()!=null && gdsInfo2PropReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfo2PropReqDTO.getGdsId());
        }
        if (gdsInfo2PropReqDTO.getProId()!=null && gdsInfo2PropReqDTO.getProId().intValue()>0){
            criteria.andProIdEqualTo(gdsInfo2PropReqDTO.getProId());
        }
        if (!StringUtil.isBlank(gdsInfo2PropReqDTO.getProType())){
            criteria.andProTypeEqualTo(gdsInfo2PropReqDTO.getProType());
        }
        if (!StringUtil.isBlank(gdsInfo2PropReqDTO.getProValue())){
            criteria.andProValueEqualTo(gdsInfo2PropReqDTO.getProValue());
        }
        if (gdsInfo2PropReqDTO.getShowOrder()!=null && gdsInfo2PropReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsInfo2PropReqDTO.getShowOrder());
        }
        if(!StringUtil.isBlank(gdsInfo2PropReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsInfo2PropReqDTO.getStatus());
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
    public List<GdsInfo2Prop> queryGdsInfo2PropList(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
        if(gdsInfo2PropReqDTO ==null){
            throw new Exception("查询商品属性关系信息列表入参为空");
        }
        GdsInfo2PropExample example = new GdsInfo2PropExample();
        GdsInfo2PropExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfo2PropReqDTO);
        List<GdsInfo2Prop> gdsInfo2Props = gdsInfo2PropMapper.selectByExample(example);
        return gdsInfo2Props;
    }

    @Override
    public int insertGdsInfo2Prop(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
        if (gdsInfo2PropReqDTO ==null){
            throw new Exception("插入商品属性关系信息入参为空");
        }
        GdsInfo2Prop gdsInfo2Prop = new GdsInfo2Prop();
        int gpId=SeqUtil.getInt("SEQ_GDS_INFO_2_PROP");
        gdsInfo2PropReqDTO.setGpId(gpId);
        gdsInfo2PropReqDTO.setCreateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsInfo2PropReqDTO,gdsInfo2Prop,null,false);
        int code = gdsInfo2PropMapper.insert(gdsInfo2Prop);
        return gpId;
    }

    @Override
    public int updateGdsInfo2Prop(GdsInfo2PropReqDTO gdsInfo2PropReqDTO) throws Exception {
        if (gdsInfo2PropReqDTO ==null){
            throw new Exception("更新商品属性关系信息入参为空");
        }
        GdsInfo2Prop gdsInfo2Prop = new GdsInfo2Prop();
        ObjectCopyUtil.copyObjValue(gdsInfo2PropReqDTO,gdsInfo2Prop,null,false);
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
