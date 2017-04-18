package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageHeaderNavMapper; 
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHeaderNav; 
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHeaderNavSV;
@Service("iPageHeaderNavSV")
public class PageHeaderNavRSVImpl implements IPageHeaderNavSV{
	 @Resource
	  private PageHeaderNavMapper pageHeaderNavMapper;
	   /***
	    * 接口没有时， 首页热门搜索
	    */
	   @Override
		public  PageHeaderNav  queryPageHeaderNavById(Integer head_id) throws Exception{
		    if (head_id==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
		    PageHeaderNav pageHeaderNav =   pageHeaderNavMapper.selectByPrimaryKey(head_id);
	        return pageHeaderNav;
	    }
}
