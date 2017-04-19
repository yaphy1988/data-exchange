package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfoExample;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
@Service("iSortInfoSV")
public class SortInfoSVImpl  implements ISortInfoSV{
	 @Resource
	  private SortInfoMapper sortInfoMapper;
	   /***
	    * 接口没有时，需要定制的信息
	    */
	  @Override
	    public SortInfo querySortInfoById(Integer SortInfoid) throws Exception {
	        if (SortInfoid==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	        SortInfo sortContent =  sortInfoMapper.selectByPrimaryKey(SortInfoid);
	        return sortContent;
	    }
	  @Override
	   public  List<SortInfo>  querySortInfoList(SortInfo exam) throws Exception{
		  SortInfoExample example = new SortInfoExample();
		  SortInfoExample.Criteria criteria = example.createCriteria(); 
		   if(exam.getSortType()!= null){
			   // 类型
				criteria.andSortTypeEqualTo(exam.getSortType());
			} 
		   if(exam.getSortLevel()!= null){ 
			   //级别
				criteria.andSortLevelEqualTo(exam.getSortLevel());
			} 
		   if(exam.getParentSortId()!= null){ 
			   //父节点ID
				criteria.andParentSortIdEqualTo(exam.getParentSortId());
			} 
			if(exam.getStatus() != null){
				criteria.andStatusEqualTo(exam.getStatus());
			} 
			else{
				//空值，就只查有效的数据
				criteria.andStatusEqualTo("1");
			} 
			//    List<PageInfo> selectByExample(PageInfoExample example);
			 return sortInfoMapper.selectByExample(example);	
		  } 
 }
