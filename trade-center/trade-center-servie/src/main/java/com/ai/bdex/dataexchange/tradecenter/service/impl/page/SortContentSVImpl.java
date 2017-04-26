package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortContentMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContentExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContentExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortContentSV;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iSortContentSV")
public class SortContentSVImpl  implements ISortContentSV {
	  @Resource
	  private SortContentMapper sortContentMapper;
	   /***
	    * 分类列表内容 查询
	    */
	  @Override
	  public SortContent querysortContenById(Integer sortContentId) throws Exception {
	        if (sortContentId==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	       SortContent sortContent =  sortContentMapper.selectByPrimaryKey(sortContentId);
	        return sortContent;
	  }
	  @Override
	  public List<SortContent> querysortContenList(SortContent sortContent) throws Exception {
		  	SortContentExample example = new SortContentExample();
		  	Criteria criteria = example.createCriteria();
		  	if(sortContent.getSortId() != null && sortContent.getSortId() != 0){
		  		criteria.andSortIdEqualTo(sortContent.getSortId());
		  	}
		  	if(sortContent.getSortContentId() != null && sortContent.getSortContentId() != 0){
		  		criteria.andSortContentIdEqualTo(sortContent.getSortContentId());
		  	}
		  	if(!StringUtils.isBlank(sortContent.getStatus())){
		  		criteria.andStatusEqualTo(sortContent.getStatus());
		  	}
		  	example.setOrderByClause(" ORDER_NO DESC ");
			return sortContentMapper.selectByExample(example);
	  } 
}
