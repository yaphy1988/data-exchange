package com.ai.bdex.dataexchange.tradecenter.service.impl.pic;

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
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PicLibMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PicLib;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PicLibExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.pic.PicLibRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pic.IPicInfoSV;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.pic.IPicLibSV;
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
 * Date:2017年5月10日上午10:21:39  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("iPicLibSV")
public class PicLibSVImpl implements IPicLibSV{

    private static Logger logger = LoggerFactory.getLogger(PicLibSVImpl.class.getName());
    @Resource
    private PicLibMapper PicLibMapper;
    @Resource
    private IPicInfoSV iPicInfoSV;
    
    @Override
    public List<PicLibRespDTO> queryPicLibList(
            PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        List<PicLib> picLibs = PicLibMapper.selectByExample(example);
        List<PicLibRespDTO> resultList = null;
        if(!CollectionUtil.isEmpty(picLibs)){
            resultList = new ArrayList<PicLibRespDTO>();
            PicLibRespDTO PicLibRespDTO = null;
            for(PicLib picLib : picLibs){
                PicLibRespDTO = new PicLibRespDTO();
                ObjectCopyUtil.copyObjValue(picLib, PicLibRespDTO, null, false);
                resultList.add(PicLibRespDTO);
            }
        }
        return resultList;
    }

    @Override
    public int insertPicLib(PicLibReqDTO picLibReqDTO)
            throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        PicLib picLib = new PicLib();
        int libId =SeqUtil.getInt("SEQ_PIC_LIB");
        ObjectCopyUtil.copyObjValue(picLibReqDTO, picLib, null, false);
        picLib.setLibId(libId);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        picLib.setCreateTime(time);
        picLib.setUpdateTime(time);
        int code = PicLibMapper.insert(picLib);
        return code;
    }

    @Override
    public int updatePicLib(PicLibReqDTO picLibReqDTO)
            throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        PicLib picLib = new PicLib();
        ObjectCopyUtil.copyObjValue(picLibReqDTO, picLib, null, false);
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        picLib.setUpdateTime(time);
        int code = PicLibMapper.updateByExampleSelective(picLib, example);
        return code;
    }

    @Override
    public int deletePicLib(PicLibReqDTO picLibReqDTO)
            throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        int code = PicLibMapper.deleteByExample(example);
        //删除图片库的同时，也要删除图片库下的所有图片
        PicInfoReqDTO picInfoReqDTO = new PicInfoReqDTO();
        if(picLibReqDTO.getLibId() != null && picLibReqDTO.getLibId()>0){
            picInfoReqDTO.setLibId(picLibReqDTO.getLibId());
            iPicInfoSV.deletePicInfo(picInfoReqDTO);
        }else{
            List<PicLib> piclibs= PicLibMapper.selectByExample(example);
            if(piclibs != null && piclibs.size()>=1){
                picInfoReqDTO.setLibId(piclibs.get(0).getLibId());
                iPicInfoSV.deletePicInfo(picInfoReqDTO);
            }
        }
        return code;
    }

    @Override
    public PicLibRespDTO queryPicLib(PicLibReqDTO picLibReqDTO)
            throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        List<PicLib> picLibs = PicLibMapper.selectByExample(example);
        PicLibRespDTO PicLibRespDTO = null;
        if(!CollectionUtil.isEmpty(picLibs)){
            PicLibRespDTO = new PicLibRespDTO();
            ObjectCopyUtil.copyObjValue(picLibs.get(0), PicLibRespDTO, null, false);
        }
        return PicLibRespDTO;
    }

    @Override
    public long count(PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参PicLibReqDTO不能为null");
        }
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        long count = PicLibMapper.countByExample(example);
        return count;
    }

    @Override
    public PageResponseDTO<PicLibRespDTO> queryPicLibPage(
            PicLibReqDTO picLibReqDTO) throws BusinessException {
        if(picLibReqDTO == null){
            throw new BusinessException("入参picLibReqDTO不能为null");
        }
        int page = picLibReqDTO.getPageNo();
        int rows = picLibReqDTO.getPageSize();
        //执行查询第一个mybatis查询方法，会被进行分页
        PicLibExample example = new PicLibExample();
        PicLibExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria,picLibReqDTO);
        example.setOrderByClause("create_time desc");
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是排序子句
        PageHelper.startPage(page, rows);
        List<PicLib> picLibs = PicLibMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(picLibs);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + picLibs.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<PicLibRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,PicLibRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(PicLibExample.Criteria criteria, PicLibReqDTO picLibReqDTO){
        if(picLibReqDTO.getLibId()!=null && picLibReqDTO.getLibId().intValue()>0){
            criteria.andLibIdEqualTo(picLibReqDTO.getLibId());
        }
        if (StringUtil.isNotBlank(picLibReqDTO.getLibName())){
            criteria.andLibNameLike("%"+picLibReqDTO.getLibName()+"%");
        }
        if (StringUtil.isNotBlank(picLibReqDTO.getLibPic())){
            criteria.andLibPicEqualTo(picLibReqDTO.getLibPic());
        }
        if(picLibReqDTO.getShowOrder()!=null && picLibReqDTO.getShowOrder().intValue()>0){
            criteria.andShowOrderEqualTo(picLibReqDTO.getShowOrder());
        }
        if (!StringUtil.isBlank(picLibReqDTO.getStatus())){
            criteria.andStatusEqualTo(picLibReqDTO.getStatus());
        }
        if (!StringUtil.isBlank(picLibReqDTO.getCreateUser())){
            criteria.andCreateUserEqualTo(picLibReqDTO.getCreateUser());
        }
        
    }

}

