package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageHotSearchMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearchExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.PageHotSearchRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iPageHotSearchSV")
public class PageHotSearchSVImpl  implements  IPageHotSearchSV{
    private static final Logger logger = LoggerFactory.getLogger(PageHotSearchSVImpl.class.getName());
	 @Resource
	 private PageHotSearchMapper pageHotSearchMapper;
	   /***
	    * 接口没有时， 首页热门搜索
	    */
	 @Override
	 public PageHotSearch queryPageHotSearchById(Integer Pagehotid) throws Exception{
	     if (Pagehotid==null){
	         throw new Exception("根据ID查询 数据定制 信息入参为空");
	     }
	     PageHotSearch pageInfo =   pageHotSearchMapper.selectByPrimaryKey(Pagehotid);
	     return pageInfo;
	 }
	 @Override
	 public  List<PageHotSearch>  queryPageHotSearchNavList(PageHotSearch exam) throws Exception{
	     PageHotSearchExample example = new PageHotSearchExample();
	     PageHotSearchExample.Criteria criteria = example.createCriteria(); 
	     if(exam.getSearchId() != null &&exam.getSearchId()!= 0){
			criteria.andSearchIdEqualTo(exam.getSearchId());
	     } 
	     if(!StringUtils.isBlank(exam.getStatus())){
			criteria.andStatusEqualTo(exam.getStatus());
	     } 
	     else{
			//空值，就只查有效的数据
	         criteria.andStatusEqualTo("1");
	     } 
	     return pageHotSearchMapper.selectByExample(example);	
	 }
    @Override
    public PageResponseDTO<PageHotSearchRespDTO> queryPageHotSearchPageInfo(
            PageHotSearchReqDTO pageHotSearchReqDTO) throws BusinessException {
        //TODO: to龚哥，PageHelper.startPage(page, rows)只对mybatis查询有效，后面跟sv无法分页的，请改之
        //分页信息赋值
        int page = pageHotSearchReqDTO.getPageNo();
        int rows = pageHotSearchReqDTO.getPageSize();
        //开启分页查询，使用mybatis-PageHelper分页插件，第三个条件是order by排序子句
        PageHelper.startPage(page, rows);
        //执行查询第一个mybatis查询方法，会被进行分页
        PageHotSearchExample example = new PageHotSearchExample();
        PageHotSearchExample.Criteria criteria = example.createCriteria(); 
        if(pageHotSearchReqDTO.getSearchId() != null &&pageHotSearchReqDTO.getSearchId()!= 0){
           criteria.andSearchIdEqualTo(pageHotSearchReqDTO.getSearchId());
        } 
        if(!StringUtils.isBlank(pageHotSearchReqDTO.getStatus())){
           criteria.andStatusEqualTo(pageHotSearchReqDTO.getStatus());
        } 
        else{
           //空值，就只查有效的数据
            criteria.andStatusEqualTo("1");
        } 
        List<PageHotSearch> lists = pageHotSearchMapper.selectByExample(example);   
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("pageHotSearchMapper查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<PageHotSearchRespDTO> resultDTO = PageResponseFactory.genPageResponse(pageInfo,PageHotSearchRespDTO.class);
        return resultDTO;
    } 
}
