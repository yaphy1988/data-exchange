package com.ai.bdex.dataexchange.tradecenter.service.impl.gds;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.GdsInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.GdsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.gds.GdsInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.gds.IGdsInfoQuerySV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.utils.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:trade-center-servie <br>
 * Description: <br>
 * Date:2017年4月25日上午11:07:26  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("gdsInfoQuerySV")
public class GdsInfoQuerySVImpl implements IGdsInfoQuerySV{
    private static final Logger logger = LoggerFactory.getLogger(GdsInfoQuerySVImpl.class.getName());
    @Resource
    private GdsInfoMapper gdsInfoMapper;
    
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO)
            throws Exception {
        //TODO: to龚哥，PageHelper.startPage(page, rows)只对mybatis查询有效，后面跟sv无法分页的，请改之

        //分页信息赋值
        int page = gdsInfoReqDTO.getPageNo();
        int rows = gdsInfoReqDTO.getPageSize();
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是order by排序子句
        PageHelper.startPage(page, rows);
        //执行查询第一个mybatis查询方法，会被进行分页
        GdsInfoExample example = new GdsInfoExample();
        GdsInfoExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, gdsInfoReqDTO);
        List<GdsInfo> lists = gdsInfoMapper.selectByExample(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<GdsInfoRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,GdsInfoRespDTO.class);
        return resultDTO;
    }
    private void initCriteria(GdsInfoExample.Criteria criteria, GdsInfoReqDTO gdsInfoReqDTO){
        if(gdsInfoReqDTO.getGdsId()!=null && gdsInfoReqDTO.getGdsId().intValue()>0){
            criteria.andGdsIdEqualTo(gdsInfoReqDTO.getGdsId());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getGdsName())){
            criteria.andGdsNameLike("%"+ gdsInfoReqDTO.getGdsName()+"%");
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getGdsSubtitle())){
            criteria.andGdsSubtitleLike("%"+ gdsInfoReqDTO.getGdsSubtitle()+"%");
        }
        if (gdsInfoReqDTO.getCatFirst()!=null && gdsInfoReqDTO.getCatFirst().intValue()>0){
            criteria.andCatFirstEqualTo(gdsInfoReqDTO.getCatFirst());
        }
        if (gdsInfoReqDTO.getApiId()!=null && gdsInfoReqDTO.getApiId().intValue()>0){
            criteria.andApiIdEqualTo(gdsInfoReqDTO.getApiId());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getIfRecommend())){
            criteria.andIfRecommendEqualTo(gdsInfoReqDTO.getIfRecommend());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getFunIntroduction())){
            criteria.andFunIntroductionEqualTo(gdsInfoReqDTO.getFunIntroduction());
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getCommpanyName())){
            criteria.andCommpanyNameLike("%"+ gdsInfoReqDTO.getCommpanyName()+"%");
        }
        if (!StringUtil.isBlank(gdsInfoReqDTO.getStatus())){
            criteria.andStatusEqualTo(gdsInfoReqDTO.getStatus());
        }
        if (!CollectionUtil.isEmpty(gdsInfoReqDTO.getGdsIds())){
            criteria.andGdsIdIn(gdsInfoReqDTO.getGdsIds());
        }
        
    }

}

