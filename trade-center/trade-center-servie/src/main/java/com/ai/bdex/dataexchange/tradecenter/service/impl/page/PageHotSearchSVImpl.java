package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageHotSearchMapper; 
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearchExample;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;
@Service("iPageHotSearchSV")
public class PageHotSearchSVImpl  implements  IPageHotSearchSV{
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
			   if(exam.getSearchId()!= 0){
					criteria.andSearchIdEqualTo(exam.getSearchId());
				} 
				if(exam.getStatus() != null){
					criteria.andStatusEqualTo(exam.getStatus());
				} 
				else{
					//空值，就只查有效的数据
					criteria.andStatusEqualTo("1");
				} 
				 return pageHotSearchMapper.selectByExample(example);	
			  } 
}
