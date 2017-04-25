package com.ai.bdex.dataexchange.tradecenter.service.impl.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SearchGdsBaseMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SearchGdsBase;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SearchGdsBaseExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.search.SearchGdsBaseRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.search.ISearchGdsBaseSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
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
 * Date:2017年4月25日上午10:09:48  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Service("searchGdsBaseSV")
public class SearchGdsBaseSVImpl implements ISearchGdsBaseSV{
    
    private static final Logger logger = LoggerFactory.getLogger(SearchGdsBaseSVImpl.class.getName());
    @Autowired
    private SearchGdsBaseMapper searchGdsBaseMapper;
    
    @Override
    public SearchGdsBaseRespDTO querySearchGdsBaseInfo(SearchGdsBaseReqDTO searchGdsBaseReqDTO)
            throws BusinessException {
        SearchGdsBaseRespDTO respDTO = new SearchGdsBaseRespDTO();
        SearchGdsBaseExample example = new SearchGdsBaseExample();
        SearchGdsBaseExample.Criteria criteria = example.createCriteria();
        List<SearchGdsBase> searchGdsBaseList = searchGdsBaseMapper.selectByExample(example);
        initCriteria(criteria, searchGdsBaseReqDTO);
        if (!CollectionUtil.isEmpty(searchGdsBaseList)) {
            ObjectCopyUtil.copyObjValue(searchGdsBaseList.get(0),respDTO,null,false);
        }
        return respDTO;
    }

    @Override
    public List<SearchGdsBaseRespDTO> querySearchGdsBaseList(
            SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException {
        List<SearchGdsBaseRespDTO> resultList = new ArrayList<SearchGdsBaseRespDTO>();
        SearchGdsBaseExample example = new SearchGdsBaseExample();
        SearchGdsBaseExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, searchGdsBaseReqDTO);
        List<SearchGdsBase> searchGdsBaseList = searchGdsBaseMapper.selectByExample(example);
        if (!CollectionUtil.isEmpty(searchGdsBaseList)) {
            SearchGdsBaseRespDTO searchGdsBaseRespDTO = null;
            for(SearchGdsBase searchGdsBase : searchGdsBaseList){
                searchGdsBaseRespDTO = new SearchGdsBaseRespDTO();
                ObjectCopyUtil.copyObjValue(searchGdsBase,searchGdsBaseRespDTO,null,false);
            }
            resultList.add(searchGdsBaseRespDTO);
        }
        return resultList;
    }

    @Override
    public PageResponseDTO<SearchGdsBaseRespDTO> querySearchGdsBasePageInfo(
            SearchGdsBaseReqDTO searchGdsBaseReqDTO) throws BusinessException {
        //分页信息赋值
        int page = searchGdsBaseReqDTO.getPageNo();
        int rows = searchGdsBaseReqDTO.getPageSize();
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是order by排序子句
        PageHelper.startPage(page, rows, "update_time desc");
        //执行查询第一个mybatis查询方法，会被进行分页
        List<SearchGdsBaseRespDTO> resultList = new ArrayList<SearchGdsBaseRespDTO>();
        SearchGdsBaseExample example = new SearchGdsBaseExample();
        SearchGdsBaseExample.Criteria criteria = example.createCriteria();
        initCriteria(criteria, searchGdsBaseReqDTO);
        List<SearchGdsBase> searchGdsBaseList = searchGdsBaseMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(searchGdsBaseList);
        logger.info("IGdsInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + searchGdsBaseList.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<SearchGdsBaseRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,SearchGdsBaseRespDTO.class);
        return resultDTO;
    }
    
    private void initCriteria(SearchGdsBaseExample.Criteria criteria, SearchGdsBaseReqDTO searchGdsBaseReqDTO){
        Integer gdsId = searchGdsBaseReqDTO.getGdsId();
        Integer catFirst = searchGdsBaseReqDTO.getCatFirst();
        Integer catId = searchGdsBaseReqDTO.getCatId();
        String ifRecommend = searchGdsBaseReqDTO.getIfRecommend();
        String status = searchGdsBaseReqDTO.getStatus();
        if(gdsId !=null && gdsId.intValue()>0){
            criteria.andGdsIdEqualTo(gdsId);
        }
        if (catFirst!=null && catFirst.intValue()>0){
            criteria.andCatFirstEqualTo(catFirst);
        }
        if (catId!=null && catId.intValue()>0){
            criteria.andCatIdEqualTo(catId);
        }
        if (!StringUtil.isBlank(ifRecommend)){
            criteria.andIfRecommendEqualTo(ifRecommend);
        }
        if (!StringUtil.isBlank(status)){
            criteria.andStatusEqualTo(status);
        }
    }
}

