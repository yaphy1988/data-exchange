package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
 
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.PageNewsInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
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
		  if(exam.getSortId() != null && exam.getSortId() != 0){
			  criteria.andSortIdEqualTo(exam.getSortId());
		  }
		   if(!StringUtils.isBlank(exam.getSortType())){
			   // 类型
				criteria.andSortTypeEqualTo(exam.getSortType());
			} 
		   if(!StringUtils.isBlank(exam.getSortLevel())){ 
			   //级别
				criteria.andSortLevelEqualTo(exam.getSortLevel());
			} 
		   if(exam.getParentSortId()!= null && exam.getParentSortId()!= 0){ 
			   //父节点ID
				criteria.andParentSortIdEqualTo(exam.getParentSortId());
			} 
			if(!StringUtils.isBlank(exam.getStatus())){
				criteria.andStatusEqualTo(exam.getStatus());
			} 
			else{
				//空值，就只查有效的数据
				criteria.andStatusEqualTo("1");
			} 
			//    List<PageInfo> selectByExample(PageInfoExample example);
			 return sortInfoMapper.selectByExample(example);	
		  }
	@Override
	public long insertSortInfo(SortInfoReqDTO sortInfoReqDTO) throws Exception {
		SortInfo record = new SortInfo();
		BeanUtils.copyProperties(sortInfoReqDTO, record);
		Integer seq =  SeqUtil.getInt("SEQ_SORT_INFO");
	    record.setSortId(seq);
		record.setCreateStaffId(sortInfoReqDTO.getCreateStaffId());
 	    record.setCreateTime(DateUtil.getNowAsDate());
 	    record.setUpdateStaffId(sortInfoReqDTO.getUpdateStaffId());
 	    record.setUpdateTime(DateUtil.getNowAsDate());
		return sortInfoMapper.insert(record);
	}
	@Override
	public long updateSortInfoById(SortInfoReqDTO sortInfoReqDTO) throws Exception {
		SortInfo record = sortInfoMapper.selectByPrimaryKey(sortInfoReqDTO.getSortId());
		if(!StringUtils.isBlank(sortInfoReqDTO.getSortName())){
			record.setSortName(sortInfoReqDTO.getSortName());
		}
		if(!StringUtils.isBlank(sortInfoReqDTO.getOrderNo())){
			record.setOrderNo(sortInfoReqDTO.getOrderNo());
		}
		if(!StringUtils.isBlank(sortInfoReqDTO.getStatus())){
			record.setStatus(sortInfoReqDTO.getStatus());
		}
		record.setUpdateStaffId(sortInfoReqDTO.getUpdateStaffId());
		record.setUpdateTime(DateUtil.getNowAsDate());
		return sortInfoMapper.updateByPrimaryKey(record);
	} 
 }
