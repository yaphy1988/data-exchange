package com.ai.bdex.dataexchange.tradecenter.service.impl.page;

import com.ai.bdex.dataexchange.tradecenter.dao.mapper.SortInfoMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfo;
import com.ai.bdex.dataexchange.tradecenter.dao.model.SortInfoExample;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.page.SortInfoRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.page.ISortInfoSV;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("iSortInfoSV")
public class SortInfoSVImpl  implements ISortInfoSV{
	 @Resource
	  private SortInfoMapper sortInfoMapper;
	   /***
	    * 接口没有时，需要定制的信息
	    */
	  @Override
	    public SortInfoRespDTO querySortInfoById(SortInfoReqDTO sortInfoReqDTO) throws Exception {
		  SortInfoRespDTO sortInfoRespDTO = new SortInfoRespDTO();
	        if (sortInfoReqDTO.getSortId()==null){
	            throw new Exception("根据ID查询 数据定制 信息入参为空");
	        }
	        SortInfo sortContent =  sortInfoMapper.selectByPrimaryKey(sortInfoReqDTO.getSortId());
	        if(sortContent != null){
	        	BeanUtils.copyProperties(sortContent, sortInfoRespDTO);
	        }
	        return sortInfoRespDTO;
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
			example.setOrderByClause(" ORDER_NO DESC");
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
 	    sortInfoMapper.insert(record);
		return seq;
	}
	@Override
	public long updateSortInfoById(SortInfoReqDTO sortInfoReqDTO) throws Exception {
		if (sortInfoReqDTO ==null){
			throw new Exception("更新首页商品分类信息入参为空");
		}
		SortInfo record = new SortInfo();

		record.setUpdateStaffId(sortInfoReqDTO.getUpdateStaffId());
		ObjectCopyUtil.copyObjValue(sortInfoReqDTO,record,null,false);
		record.setUpdateTime(DateUtil.getNowAsDate());
		return sortInfoMapper.updateByPrimaryKeySelective(record);

	} 
 }
