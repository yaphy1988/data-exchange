package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortContentMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContent;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContentExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortContentExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortContentRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortContentSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
@Service("iSortContentSV")
public class SortContentSVImpl  implements ISortContentSV {
	  @Resource
	  private SortContentMapper sortContentMapper;
	   /***
	    * 分类列表内容 查询
	    */
	  @Override
	  public SortContentRespDTO querysortContenById(SortContentReqDTO sortContentId) throws Exception {
		  	SortContentRespDTO	contentRespDTO = new SortContentRespDTO();
	        if (sortContentId.getSortContentId()==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	       SortContent sortContent =  sortContentMapper.selectByPrimaryKey(sortContentId.getSortContentId());
	       if(sortContent != null){
	    	 BeanUtils.copyProperties(sortContent, contentRespDTO);  
	       }
	        return contentRespDTO;
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
		  	example.setOrderByClause(" ORDER_NO ASC,UPDATE_TIME DESC ");
			return sortContentMapper.selectByExample(example);
	  }
	@Override
	public long insertSortContent(SortContentReqDTO sortContentReqDTO) throws Exception {
		SortContent record = new SortContent();
		BeanUtils.copyProperties(sortContentReqDTO, record);
		Integer seq =  SeqUtil.getInt("SEQ_SORT_CONTENT");
	    record.setSortContentId(seq);
		record.setCreateStaffId(sortContentReqDTO.getCreateStaffId());
 	    record.setCreateTime(DateUtil.getNowAsDate());
 	    record.setUpdateStaffId(sortContentReqDTO.getUpdateStaffId());
 	    record.setUpdateTime(DateUtil.getNowAsDate());
		return sortContentMapper.insert(record);
	}
	@Override
	public long updateSortContent(SortContentReqDTO sortContentReqDTO) throws Exception {
		SortContent record = new SortContent();
		SortContentExample example = new SortContentExample();
		Criteria createCriteria = example.createCriteria();
		if(sortContentReqDTO.getSortId() != null){
			createCriteria.andSortIdEqualTo(sortContentReqDTO.getSortId());
		}
		ObjectCopyUtil.copyObjValue(sortContentReqDTO,record, null, false);
		record.setUpdateTime(DateUtil.getNowAsDate());
		return sortContentMapper.updateByExampleSelective(record, example);
	}

}
