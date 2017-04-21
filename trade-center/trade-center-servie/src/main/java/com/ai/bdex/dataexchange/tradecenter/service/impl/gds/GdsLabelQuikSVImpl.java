package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsLabelQuikMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuik;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsLabelQuikExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsLabelQuikRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsLabelQuikSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;

import com.ai.paas.utils.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsLabelQuikSV")
public class GdsLabelQuikSVImpl implements IGdsLabelQuikSV{

    @Resource
    private GdsLabelQuikMapper gdsLabelQuikMapper;

    private void initCriteria(GdsLabelQuikExample.Criteria criteria, GdsLabelQuikReqDTO gdsLabelQuikReqDTO){
        if (gdsLabelQuikReqDTO.getLabId()!=null && gdsLabelQuikReqDTO.getLabId().intValue()>0){
            criteria.andLabIdEqualTo(gdsLabelQuikReqDTO.getLabId());
        }
        if (!StringUtil.isBlank(gdsLabelQuikReqDTO.getLabName())){
            criteria.andLabNameLike("%"+ gdsLabelQuikReqDTO.getLabName()+"%");
        }
        if (!StringUtil.isBlank(gdsLabelQuikReqDTO.getLabColor())){
            criteria.andLabColorEqualTo(gdsLabelQuikReqDTO.getLabColor());
        }
        if(gdsLabelQuikReqDTO.getCatFirst()!=null && gdsLabelQuikReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsLabelQuikReqDTO.getCatFirst());
        }
        if (gdsLabelQuikReqDTO.getShowOrder()!=null && gdsLabelQuikReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsLabelQuikReqDTO.getShowOrder());
        }
        if(!StringUtil.isBlank(gdsLabelQuikReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsLabelQuikReqDTO.getStatus());
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
    public List<GdsLabelQuik> queryGdsLabelQuikList(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
        if (gdsLabelQuikReqDTO ==null){
            throw new Exception("查询商品便签快速配置信息列表入参为空");
        }
        GdsLabelQuikExample example = new GdsLabelQuikExample();
        GdsLabelQuikExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsLabelQuikReqDTO);
        List<GdsLabelQuik> gdsLabelQuiks = gdsLabelQuikMapper.selectByExample(example);
        return gdsLabelQuiks;
    }
    @Override
    public List<GdsLabelQuikRespDTO> queryGdsLabelQuikListDTO(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
        if (gdsLabelQuikReqDTO ==null){
            throw new Exception("查询商品便签快速配置信息列表入参为空");
        }
        GdsLabelQuikExample example = new GdsLabelQuikExample();
        GdsLabelQuikExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsLabelQuikReqDTO);
        List<GdsLabelQuik> gdsLabelQuiks = gdsLabelQuikMapper.selectByExample(example);
        List<GdsLabelQuikRespDTO> gdsLabelQuikRespList = new ArrayList<GdsLabelQuikRespDTO>();
        if(!CollectionUtil.isEmpty(gdsLabelQuiks)){
        	for(GdsLabelQuik label : gdsLabelQuiks){
        		GdsLabelQuikRespDTO respDTO = new GdsLabelQuikRespDTO();
                ObjectCopyUtil.copyObjValue(label,respDTO,null,false);
//                BeanUtils.copyProperties(label,respDTO);
                gdsLabelQuikRespList.add(respDTO);
        	}
        }
        return gdsLabelQuikRespList;
    }
    
    @Override
    public int insertGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
        if (gdsLabelQuikReqDTO ==null){
            throw new Exception("插入商品便签快速配置信息入参为空");
        }
        GdsLabelQuik gdsLabelQuik = new GdsLabelQuik();
        ObjectCopyUtil.copyObjValue(gdsLabelQuikReqDTO,gdsLabelQuik,null,false);
//        BeanUtils.copyProperties(gdsLabelQuikReqDTO,gdsLabelQuik);
        int code = gdsLabelQuikMapper.insert(gdsLabelQuik);
        return code;
    }

    @Override
    public int updateGdsLabelQuik(GdsLabelQuikReqDTO gdsLabelQuikReqDTO) throws Exception {
        if(gdsLabelQuikReqDTO ==null){
            throw new Exception("更新商品标签快速配置信息入参为空");
        }
        GdsLabelQuik gdsLabelQuik = new GdsLabelQuik();
        ObjectCopyUtil.copyObjValue(gdsLabelQuikReqDTO,gdsLabelQuik,null,false);
//        BeanUtils.copyProperties(gdsLabelQuikReqDTO,gdsLabelQuik);
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
