package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsCatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.Gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
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
@Service("iGdsCatSV")
public class GdsCatSVImpl implements IGdsCatSV{

    @Resource
    private GdsCatMapper gdsCatMapper;

    @Override
    public GdsCat queryGdsCatById(Integer catId) throws Exception {
        if (catId==null || catId.intValue()<=0){
            throw new Exception("根据ID查询商品分类信息入参为空");
        }
        GdsCat gdsCat = gdsCatMapper.selectByPrimaryKey(catId);
        return gdsCat;
    }

    @Override
    public List<GdsCat> queryGdsCatList(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        if (gdsCatReqDTO ==null){
            throw new Exception("查询分类列表的入参为空");
        }
        GdsCatExample example = new GdsCatExample();
        GdsCatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsCatReqDTO);
        List<GdsCat> gdsCatList = gdsCatMapper.selectByExample(example);

        return gdsCatList;
    }

    @Override
    public int insertGdsCat(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        if (gdsCatReqDTO ==null){
            throw new Exception("插入商品分类信息的入参为空");
        }
        GdsCat gdsCat = new GdsCat();
        BeanUtils.copyProperties(gdsCatReqDTO,gdsCat);
        int code = gdsCatMapper.insert(gdsCat);

        return code;
    }

    @Override
    public int updateGdsCat(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        if (gdsCatReqDTO ==null){
            throw new Exception("更新商品分类信息的入参为空");
        }

        GdsCat gdsCat = new GdsCat();
        BeanUtils.copyProperties(gdsCatReqDTO,gdsCat);
        int code = gdsCatMapper.updateByPrimaryKey(gdsCat);

        return code;
    }

    @Override
    public int deleteGdsCatById(Integer catId) throws Exception {
        if (catId==null || catId.intValue()<=0){
            throw new Exception("删除商品分类信息入参为空");
        }
        int code = gdsCatMapper.deleteByPrimaryKey(catId);
        return code;
    }
    @Override
    public List<GdsCatRespDTO> queryGdsCatListDTO(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        if (gdsCatReqDTO ==null){
            throw new Exception("查询分类列表的入参为空");
        }
        GdsCatExample example = new GdsCatExample();
        GdsCatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsCatReqDTO);
        List<GdsCat> gdsCatList = gdsCatMapper.selectByExample(example);
        List<GdsCatRespDTO> respDTOList = new ArrayList<GdsCatRespDTO>();
        if(CollectionUtils.isNotEmpty(gdsCatList)){
        	for(GdsCat gdsCat : gdsCatList){
        		GdsCatRespDTO respDTO = new GdsCatRespDTO();
                BeanUtils.copyProperties(gdsCat,respDTO);
                respDTOList.add(respDTO);
        	}
        }
        return respDTOList;
    }
    private void initCriteria(GdsCatExample.Criteria criteria, GdsCatReqDTO gdsCatReqDTO){
        if (gdsCatReqDTO.getCatId()!=null && gdsCatReqDTO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsCatReqDTO.getCatId());
        }
        if (gdsCatReqDTO.getCatPid()!=null && gdsCatReqDTO.getCatPid().intValue()>0){
            criteria.andCatPidEqualTo(gdsCatReqDTO.getCatPid());
        }
        if(!StringUtils.isEmpty(gdsCatReqDTO.getCatName())){
            criteria.andCatNameLike("%"+ gdsCatReqDTO.getCatName()+"%");
        }
        if(!StringUtils.isEmpty(gdsCatReqDTO.getCatDesc())){
            criteria.andCatDescLike("%" + gdsCatReqDTO.getCatDesc() + "%");
        }
        if (gdsCatReqDTO.getShowOrder()!=null && gdsCatReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsCatReqDTO.getShowOrder());
        }
        if (!StringUtils.isEmpty(gdsCatReqDTO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsCatReqDTO.getIfEdit());
        }
        if (!StringUtils.isEmpty(gdsCatReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsCatReqDTO.getStatus());
        }
    }
}
