package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageHeaderNavMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNavExample;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHeaderNavSV;
 @Service("iPageHeaderNavSV")
public class PageHeaderNavSVImpl implements IPageHeaderNavSV{
	 @Resource
	  private PageHeaderNavMapper pageHeaderNavMapper;
 	   /***
	    * 接口没有时， 首页导航查询。
	    */
	   @Override
		public  PageHeaderNav  queryPageHeaderNavById(Integer head_id) throws Exception{
		    if (head_id==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
		    PageHeaderNav pageHeaderNav =   pageHeaderNavMapper.selectByPrimaryKey(head_id);
	        return pageHeaderNav;
	    }
	   @Override
		public  List<PageHeaderNav>  queryPageHeaderNavList(PageHeaderNav exam) throws Exception{
		   PageHeaderNavExample example = new PageHeaderNavExample();
		   PageHeaderNavExample.Criteria criteria = example.createCriteria(); 
		   if(exam.getNavId() != 0){
				criteria.andNavIdEqualTo(exam.getNavId());
			} 
			if(exam.getStatus() != null){
				criteria.andStatusEqualTo(exam.getStatus());
			} 
			else{
				//空值，就只查有效的数据
				criteria.andStatusEqualTo("1");
			} 
			 return pageHeaderNavMapper.selectByExample(example);	
		  } 

}
