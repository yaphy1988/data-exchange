package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortInfoMapper; 
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
@Service("iSortInfoSV")
public class SortInfoRSVImpl  implements ISortInfoSV{
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
 }
