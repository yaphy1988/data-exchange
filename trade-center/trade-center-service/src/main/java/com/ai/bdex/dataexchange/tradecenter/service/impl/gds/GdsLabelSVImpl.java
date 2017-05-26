package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabel;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;

import org.springframework.stereotype.Service;

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
        if(!CollectionUtil.isEmpty(gdsLabels)){
        	for(GdsLabel label : gdsLabels){
        		GdsLabelRespDTO gdsLabelRespDTO = new GdsLabelRespDTO();
                ObjectCopyUtil.copyObjValue(label,gdsLabelRespDTO,null,false);
//                BeanUtils.copyProperties(label,gdsLabelRespDTO);
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
        int labId=SeqUtil.getInt("SEQ_GDS_LABEL");
        gdsLabelReqDTO.setLabId(labId);
        gdsLabelReqDTO.setCreateTime(DateUtil.getNowAsDate());
        gdsLabelReqDTO.setUpdateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO,gdsLabel,null,false);
        int code = gdsLabelMapper.insert(gdsLabel);
        return code;
    }

    @Override
    public int updateGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO ==null){
            throw new Exception("更新商品标签信息入参为空");
        }
        GdsLabel gdsLabel = new GdsLabel();
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO,gdsLabel,null,false);
//        BeanUtils.copyProperties(gdsLabelReqDTO,gdsLabel);
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
    @Override
    public int deleteGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO.getGdsId()==null || gdsLabelReqDTO.getGdsId()<=0){
            throw new Exception("删除商品标签入参为空");
        }
        GdsLabelExample gdsLabelExample = new GdsLabelExample();
        GdsLabelExample.Criteria criteria = gdsLabelExample.createCriteria();
        initCriteria(criteria, gdsLabelReqDTO);
        int code = gdsLabelMapper.deleteByExample(gdsLabelExample);
        return code;
    }
    public int updataGdslabelByGdsId(GdsLabelReqDTO gdsLabelReqDTO) throws Exception {
        if (gdsLabelReqDTO.getGdsId()==null || gdsLabelReqDTO.getGdsId()<=0){
            throw new Exception("删除商品标签入参为空");
        }
        GdsLabelExample gdsLabelExample = new GdsLabelExample();
        GdsLabelExample.Criteria criteria = gdsLabelExample.createCriteria();
        if (gdsLabelReqDTO.getLabId()!=null && gdsLabelReqDTO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelReqDTO.getLabId());
        }
        if(gdsLabelReqDTO.getGdsId()!=null && gdsLabelReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsLabelReqDTO.getGdsId());
        }
        GdsLabel gdsLabel = new GdsLabel();
        gdsLabelReqDTO.setUpdateTime(DateUtil.getNowAsDate());
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO,gdsLabel,null,false);
        int code = gdsLabelMapper.updateByExampleSelective(gdsLabel,gdsLabelExample);
        return code;
    }
    private void initCriteria(GdsLabelExample.Criteria criteria , GdsLabelReqDTO gdsLabelReqDTO){
        if (gdsLabelReqDTO.getLabId()!=null && gdsLabelReqDTO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelReqDTO.getLabId());
        }
        if(gdsLabelReqDTO.getGdsId()!=null && gdsLabelReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsLabelReqDTO.getGdsId());
        }
        if(!StringUtil.isBlank(gdsLabelReqDTO.getLabName())){
            criteria.andLabNameLike("%"+ gdsLabelReqDTO.getLabName()+"%");
        }
        if (!StringUtil.isBlank(gdsLabelReqDTO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelReqDTO.getLabColor());
        }
        if(gdsLabelReqDTO.getShowOrder()!=null && gdsLabelReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelReqDTO.getShowOrder());
        }
        if(!StringUtil.isBlank(gdsLabelReqDTO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsLabelReqDTO.getIfEdit());
        }
        if (!StringUtil.isBlank(gdsLabelReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelReqDTO.getStatus());
        }
    }
}
