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
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PicInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PicInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PicInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.PicInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IPicInfoSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年5月10日上午10:29:08  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("iPicInfoSV")
public class PicInfoSVImpl implements IPicInfoSV{
    private static Logger logger = LoggerFactory.getLogger(PicInfoSVImpl.class.getName());
    @Resource
    private PicInfoMapper PicInfoMapper;
    
    @Override
    public List<PicInfoRespDTO> queryPicInfoList(
            PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        List<PicInfo> picInfos = PicInfoMapper.selectByExample(example);
        List<PicInfoRespDTO> resultList = null;
        if(!CollectionUtil.isEmpty(picInfos)){
            resultList = new ArrayList<PicInfoRespDTO>();
            PicInfoRespDTO PicInfoRespDTO = null;
            for(PicInfo picInfo : picInfos){
                PicInfoRespDTO = new PicInfoRespDTO();
                ObjectCopyUtil.copyObjValue(picInfo, PicInfoRespDTO, null, false);
                resultList.add(PicInfoRespDTO);
            }
        }
        return resultList;
    }

    @Override
    public int insertPicInfo(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfo picInfo = new PicInfo();
        int picId =SeqUtil.getInt("SEQ_PIC_INFO");
        ObjectCopyUtil.copyObjValue(picInfoReqDTO, picInfo, null, false);
        picInfo.setPicId(picId);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        picInfo.setCreateTime(time);
        int code = PicInfoMapper.insert(picInfo);
        return code;
    }

    @Override
    public int updatePicInfo(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        PicInfo picInfo = new PicInfo();
        ObjectCopyUtil.copyObjValue(picInfoReqDTO, picInfo, null, false);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        picInfo.setUpdateTime(time);
        int code = PicInfoMapper.updateByExampleSelective(picInfo, example);
        return code;
    }

    @Override
    public int deletePicInfo(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        int code = PicInfoMapper.deleteByExample(example);
        return code;
    }

    @Override
    public PicInfoRespDTO queryPicInfo(PicInfoReqDTO picInfoReqDTO)
            throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        List<PicInfo> picInfos = PicInfoMapper.selectByExample(example);
        PicInfoRespDTO PicInfoRespDTO = null;
        if(!CollectionUtil.isEmpty(picInfos)){
            PicInfoRespDTO = new PicInfoRespDTO();
            ObjectCopyUtil.copyObjValue(picInfos.get(0), PicInfoRespDTO, null, false);
        }
        return PicInfoRespDTO;
    }

    @Override
    public long count(PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        long count = PicInfoMapper.countByExample(example);
        return count;
    }

    @Override
    public PageResponseDTO<PicInfoRespDTO> queryPicInfoPage(
            PicInfoReqDTO picInfoReqDTO) throws BusinessException {
        if(picInfoReqDTO == null){
            throw new BusinessException("入参picInfoReqDTO不能为null");
        }
        int page = picInfoReqDTO.getPageNo();
        int rows = picInfoReqDTO.getPageSize();
        //执行查询第一个mybatis查询方法，会被进行分页
        PicInfoExample example = new PicInfoExample();
        PicInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picInfoReqDTO);
        example.setOrderByClause("create_time desc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是排序子句
        PageHelper.startPage(page, rows);
        List<PicInfo> picInfos = PicInfoMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(picInfos);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + picInfos.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<PicInfoRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,PicInfoRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(PicInfoExample.Criteria criteria, PicInfoReqDTO picInfoReqDTO){
        if(picInfoReqDTO.getPicId()!=null && picInfoReqDTO.getPicId().intValue()>0){
            criteria.andPicIdEqualTo(picInfoReqDTO.getPicId());
        }
        if(picInfoReqDTO.getLibId()!=null && picInfoReqDTO.getLibId().intValue()>0){
            criteria.andLibIdEqualTo(picInfoReqDTO.getLibId());
        }
        if (StringUtil.isNotBlank(picInfoReqDTO.getPicName())){
            criteria.andPicNameLike("%"+picInfoReqDTO.getPicName()+"%");
        }
        if (StringUtil.isNotBlank(picInfoReqDTO.getPicSpe())){
            criteria.andPicSpeEqualTo(picInfoReqDTO.getPicSpe());
        }
        if(picInfoReqDTO.getShowOrder()!=null && picInfoReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(picInfoReqDTO.getShowOrder());
        }
        if (!StringUtil.isBlank(picInfoReqDTO.getStatus())){
            criteria.andStatusEqualTo(picInfoReqDTO.getStatus());
        }
        if (!StringUtil.isBlank(picInfoReqDTO.getCreateUser())){
            criteria.andCreateUserEqualTo(picInfoReqDTO.getCreateUser());
        }
        
    }
}

