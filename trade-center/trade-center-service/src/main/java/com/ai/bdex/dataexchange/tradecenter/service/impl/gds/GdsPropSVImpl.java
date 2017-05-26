package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsPropMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsProp;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsPropExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsPropReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsPropSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsPropSV")
public class GdsPropSVImpl implements IGdsPropSV {

    @Resource
    private GdsPropMapper gdsPropMapper;

    private void initCriteria(GdsPropExample.Criteria criteria, GdsPropReqDTO gdsPropReqDTO){
        if (gdsPropReqDTO.getProId()!=null && gdsPropReqDTO.getProId().intValue()>0){
            criteria.andProIdEqualTo(gdsPropReqDTO.getProId());
        }
        if (!StringUtil.isBlank(gdsPropReqDTO.getProType())){
            criteria.andProTypeEqualTo(gdsPropReqDTO.getProType());
        }
        if (!StringUtil.isBlank(gdsPropReqDTO.getProName())){
            criteria.andProNameEqualTo(gdsPropReqDTO.getProName());
        }
        if (gdsPropReqDTO.getCatId()!=null && gdsPropReqDTO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsPropReqDTO.getCatId());
        }
        if (gdsPropReqDTO.getShowOrder()!=null && gdsPropReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsPropReqDTO.getShowOrder());
        }
        if (!StringUtil.isBlank(gdsPropReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsPropReqDTO.getStatus());
        }
    }

    @Override
    public GdsProp queryGdsPropById(Integer proId) throws Exception {
        if (proId==null || proId.intValue()<=0){
            throw new Exception("查询商品属性信息入参为空");
        }
        GdsProp gdsProp = gdsPropMapper.selectByPrimaryKey(proId);
        return gdsProp;
    }

    @Override
    public List<GdsProp> queryGdsPropList(GdsPropReqDTO gdsPropReqDTO) throws Exception {
        if (gdsPropReqDTO ==null){
            throw new Exception("查询商品属性信息列表入参为空");
        }
        GdsPropExample gdsPropExample = new GdsPropExample();
        GdsPropExample.Criteria criteria = gdsPropExample.createCriteria();
        initCriteria(criteria, gdsPropReqDTO);
        List<GdsProp> gdsProps = gdsPropMapper.selectByExample(gdsPropExample);
        return gdsProps;
    }

    @Override
    public int insertGdsProp(GdsPropReqDTO gdsPropReqDTO) throws Exception {
        if (gdsPropReqDTO ==null){
            throw new Exception("插入商品属性信息入参为空");
        }
        GdsProp gdsProp = new GdsProp();
        ObjectCopyUtil.copyObjValue(gdsPropReqDTO,gdsProp,null,false);
//        BeanUtils.copyProperties(gdsPropReqDTO,gdsProp);
        int code = gdsPropMapper.insert(gdsProp);
        return code;
    }

    @Override
    public int updateGdsProp(GdsPropReqDTO gdsPropReqDTO) throws Exception {
        if (gdsPropReqDTO ==null){
            throw new Exception("更新商品属性信息入参为空");
        }
        GdsProp gdsProp = new GdsProp();
        ObjectCopyUtil.copyObjValue(gdsPropReqDTO,gdsProp,null,false);
//        BeanUtils.copyProperties(gdsPropReqDTO,gdsProp);
        int code = gdsPropMapper.updateByPrimaryKey(gdsProp);
        return 0;
    }

    @Override
    public int deleteGdsPropById(Integer proId) throws Exception {
        if(proId==null || proId.intValue()<=0){
            throw new Exception("删除商品属性信息入参为空");
        }
        int code = gdsPropMapper.deleteByPrimaryKey(proId);
        return code;
    }
}
