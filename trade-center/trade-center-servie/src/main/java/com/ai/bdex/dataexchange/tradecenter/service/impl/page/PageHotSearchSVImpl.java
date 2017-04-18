package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.PageHotSearchMapper;
 import com.ai.bdex.dataexchange.tradecenter.dao.model.PageHotSearch;
 import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.IPageHotSearchSV;
@Service("iPageHotSearchSV")
public class PageHotSearchSVImpl  implements  IPageHotSearchSV{
	 @Resource
	  private PageHotSearchMapper pageHotSearchMapper;
	   /***
	    * 接口没有时，需要定制的信息
	    */
	  @Override
	   public PageHotSearch queryPageHotSearchById(Integer PageInfoid) throws Exception{

	        if (PageInfoid==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	        PageHotSearch pageInfo = null;// pageHotSearchMapper.sel.selectByPrimaryKey(PageInfoid);
	        return pageInfo;
	    }
}
