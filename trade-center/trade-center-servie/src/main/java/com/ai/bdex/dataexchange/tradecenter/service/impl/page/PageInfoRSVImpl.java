package com.ai.bdex.dataexchange.tradecenter.service.impl.page; 
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageInfoMapper; 
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageInfo; 
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageInfoSV;

@Service("iPageInfoSV")
public class PageInfoRSVImpl implements IPageInfoSV {
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
}
