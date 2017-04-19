package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortContentMapper; 
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfoExample;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortContentSV;
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
}
