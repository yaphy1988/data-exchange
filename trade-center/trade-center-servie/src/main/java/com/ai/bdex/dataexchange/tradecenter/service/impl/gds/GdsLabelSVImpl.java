package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

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
    public List<GdsLabel> queryGdsLabelList(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO ==null){
            throw new Exception("查询商品标签信息列表入参为空");
        }
        List<GdsLabel> gdsLabels = new ArrayList<GdsLabel>();
        GdsLabelExample example = new GdsLabelExample();
        GdsLabelExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsLabelReqDTO);
        gdsLabels = gdsLabelMapper.selectByExample(example);
        return gdsLabels;
    }
    @Override
    public List<GdsLabelRespDTO> queryGdsLabelListDTO(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO ==null){
            throw new Exception("查询商品标签信息列表入参为空");
        }
        List<GdsLabel> gdsLabels = new ArrayList<GdsLabel>();
        List<GdsLabelRespDTO> gdsLabelDTOList = new ArrayList<GdsLabelRespDTO>();

        GdsLabelExample example = new GdsLabelExample();
        GdsLabelExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsLabelReqDTO);
        gdsLabels = gdsLabelMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(gdsLabels)){
        	for(GdsLabel label : gdsLabels){
        		GdsLabelRespDTO gdsLabelRespDTO = new GdsLabelRespDTO();
                BeanUtils.copyProperties(label,gdsLabelRespDTO);
                gdsLabelDTOList.add(gdsLabelRespDTO);
        	}
        }
        return gdsLabelDTOList;
    }

    @Override
    public int insertGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO ==null){
            throw new Exception("插入商品标签信息入参为空");
        }
        GdsLabel gdsLabel = new GdsLabel();
        BeanUtils.copyProperties(gdsLabelReqDTO,gdsLabel);
        int code = gdsLabelMapper.insert(gdsLabel);
        return code;
    }

    @Override
    public int updateGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO ==null){
            throw new Exception("更新商品标签信息入参为空");
        }
        GdsLabel gdsLabel = new GdsLabel();
        BeanUtils.copyProperties(gdsLabelReqDTO,gdsLabel);
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

    private void initCriteria(GdsLabelExample.Criteria criteria , GdsLabelReqDTO gdsLabelReqDTO){
        if (gdsLabelReqDTO.getLabId()!=null && gdsLabelReqDTO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelReqDTO.getLabId());
        }
        if(gdsLabelReqDTO.getGdsId()!=null && gdsLabelReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsLabelReqDTO.getGdsId());
        }
        if(!StringUtils.isEmpty(gdsLabelReqDTO.getLabName())){
            criteria.andLabNameLike("%"+ gdsLabelReqDTO.getLabName()+"%");
        }
        if (!StringUtils.isEmpty(gdsLabelReqDTO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelReqDTO.getLabColor());
        }
        if(gdsLabelReqDTO.getShowOrder()!=null && gdsLabelReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelReqDTO.getShowOrder());
        }
        if(!StringUtils.isEmpty(gdsLabelReqDTO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsLabelReqDTO.getIfEdit());
        }
        if (!StringUtils.isEmpty(gdsLabelReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelReqDTO.getStatus());
        }
    }
}
