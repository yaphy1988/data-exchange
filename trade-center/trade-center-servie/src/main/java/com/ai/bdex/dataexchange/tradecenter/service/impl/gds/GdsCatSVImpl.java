package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsCatMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCat;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsCatExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsCatRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsCatSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by yx on 2017/4/17.
 */
@Service("iGdsCatSV")
public class GdsCatSVImpl implements IGdsCatSV{
    private static final Logger logger = LoggerFactory.getLogger(GdsCatSVImpl.class.getName());

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
        int catId =SeqUtil.getInt("SEQ_GDS_CAT");
        GdsCat gdsCat = new GdsCat();
        gdsCat.setCatId(catId);
        ObjectCopyUtil.copyObjValue(gdsCatReqDTO,gdsCat,null,false);
        gdsCat.setStatus("1");
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        gdsCat.setCreateTime(time);
        gdsCat.setUpdateTime(time);
        int code = gdsCatMapper.insert(gdsCat);

        return code;
    }

    @Override
    public int updateGdsCat(GdsCatReqDTO gdsCatReqDTO) throws Exception {
        if (gdsCatReqDTO ==null){
            throw new Exception("更新商品分类信息的入参为空");
        }

        GdsCat gdsCat = new GdsCat();
        ObjectCopyUtil.copyObjValue(gdsCatReqDTO,gdsCat,null,false);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        gdsCat.setUpdateTime(time);
        int code = gdsCatMapper.updateByPrimaryKeySelective(gdsCat);

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
                ObjectCopyUtil.copyObjValue(gdsCat,respDTO,null,false);
//                BeanUtils.copyProperties(gdsCat,respDTO);
                respDTOList.add(respDTO);
        	}
        }
        return respDTOList;
    }
    private void initCriteria(GdsCatExample.Criteria criteria, GdsCatReqDTO gdsCatReqDTO){
        if (gdsCatReqDTO.getCatId()!=null && gdsCatReqDTO.getCatId().intValue()>0){
            criteria.andCatIdEqualTo(gdsCatReqDTO.getCatId());
        }
        if (gdsCatReqDTO.getCatPid()!=null){
            criteria.andCatPidEqualTo(gdsCatReqDTO.getCatPid());
        }
        if(!StringUtil.isBlank(gdsCatReqDTO.getCatName())){
            criteria.andCatNameLike("%"+ gdsCatReqDTO.getCatName()+"%");
        }
        if(!StringUtil.isBlank(gdsCatReqDTO.getCatDesc())){
            criteria.andCatDescLike("%" + gdsCatReqDTO.getCatDesc() + "%");
        }
        if (gdsCatReqDTO.getShowOrder()!=null && gdsCatReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(gdsCatReqDTO.getShowOrder());
        }
        if (!StringUtil.isBlank(gdsCatReqDTO.getIfEdit())){
            criteria.andIfEditEqualTo(gdsCatReqDTO.getIfEdit());
        }
        if (!StringUtil.isBlank(gdsCatReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsCatReqDTO.getStatus());
        }
    }

    @Override
    public PageResponseDTO<GdsCatRespDTO> queryCatPageInfo(GdsCatReqDTO gdsCatReqDTO)
            throws BusinessException {
        //分页信息赋值
        int page = gdsCatReqDTO.getPageNo();
        int rows = gdsCatReqDTO.getPageSize();
        
        //执行查询第一个mybatis查询方法，会被进行分页
        GdsCatExample example = new GdsCatExample();
        GdsCatExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsCatReqDTO);
        example.setOrderByClause("show_order asc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是order by排序子句
        PageHelper.startPage(page, rows);
        List<GdsCat> lists = gdsCatMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("IGdsCatSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<GdsCatRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,GdsCatRespDTO.class);
        return resultDTO;
    }

    @Override
    public int deleteGdsCatInfo(Integer catId) throws BusinessException {
        int code = gdsCatMapper.deleteByPrimaryKey(catId);
        //递归删除子节点
        code = recursionDelteGdsCat(catId);
        return code;
    }
    
    public int recursionDelteGdsCat(Integer catPid){
        int code = 0;
        code = gdsCatMapper.deleteByPrimaryKey(catPid);
        GdsCatExample gdsCatExample = new GdsCatExample();
        GdsCatExample.Criteria criteria = gdsCatExample.createCriteria();
        criteria.andCatPidEqualTo(catPid);
        List<GdsCat> list = gdsCatMapper.selectByExample(gdsCatExample);
        if(list != null && list.size() >= 1){
            for(GdsCat gdsCat : list){
                code = gdsCatMapper.deleteByExample(gdsCatExample);
                recursionDelteGdsCat(gdsCat.getCatId());
            }
        }else{
            return 1;
        }
       return code;
    }
}
