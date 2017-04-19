package com.ai.bdex.dataexchange.tradecenter.service.impl.page; 
import java.util.List;

import javax.annotation.Resource; 
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearchExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfoExample;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageInfoSV;

@Service("iPageInfoSV")
public class PageInfoSVImpl implements IPageInfoSV {
	 @Resource
	  private PageInfoMapper pageInfoMapper;
	   /***
	    * 接口没有时，需要定制的信息
	    */
	  @Override
	   public PageInfo queryPageInfoById(Integer PageInfoid) throws Exception{

	        if (PageInfoid==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	        PageInfo pageInfo =  pageInfoMapper.selectByPrimaryKey(PageInfoid);
	        return pageInfo;
	    }
	  @Override
	   public  List<PageInfo>  queryPageInfoList(PageInfo exam) throws Exception{
		  PageInfoExample example = new PageInfoExample();
		  PageInfoExample.Criteria criteria = example.createCriteria(); 
		   if(exam.getInfoType()!= null){
				criteria.andInfoTypeEqualTo(exam.getInfoType());
			} 
			if(exam.getStatus() != null){
				criteria.andStatusEqualTo(exam.getStatus());
			} 
			else{
				//空值，就只查有效的数据
				criteria.andStatusEqualTo("1");
			} 
			//    List<PageInfo> selectByExample(PageInfoExample example);
			 return pageInfoMapper.selectByExample(example);	
		  }  
}
