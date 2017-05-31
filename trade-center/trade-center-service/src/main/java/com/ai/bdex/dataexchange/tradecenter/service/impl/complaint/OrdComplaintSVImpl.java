package com.ai.bdex.dataexchange.tradecenter.service.impl.complaint;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdComplaintMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaint;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaintExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaintExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint.IOrdComplaintSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iordComplaintSV")
public class OrdComplaintSVImpl implements IOrdComplaintSV {
	@Autowired
	private OrdComplaintMapper ordComplaintMapper; 

	@Override
	public PageResponseDTO<OrdComplaintRespDTO> queryOrdComplaintPageInfo(
			OrdComplaintReqDTO complaintReqDTO) throws Exception {
		// 分页信息赋值
		int page = complaintReqDTO.getPageNo();
		int rows = complaintReqDTO.getPageSize();
		OrdComplaintExample example = new OrdComplaintExample();
		Criteria criteria = example.createCriteria();
		if(complaintReqDTO.getComplaintId() != null){
			criteria.andComplaintIdEqualTo(complaintReqDTO.getComplaintId());
		}
		if(!StringUtil.isBlank(complaintReqDTO.getOrderId())){
			criteria.andOrderIdEqualTo(complaintReqDTO.getOrderId());
		}
		if(!StringUtil.isBlank(complaintReqDTO.getComplaintStatus())){
			criteria.andComplaintStatusEqualTo(complaintReqDTO.getComplaintStatus());
		}
		if(!StringUtil.isBlank(complaintReqDTO.getComplaintItem())){
			criteria.andComplaintItemEqualTo(complaintReqDTO.getComplaintItem());
		}
		if(complaintReqDTO.getBeginTime() != null){//开始时间
			criteria.andComplaintsTimeGreaterThanOrEqualTo(complaintReqDTO.getBeginTime());
		}
		if(complaintReqDTO.getEndTime() != null){//结束时间
			criteria.andComplaintsTimeLessThanOrEqualTo(complaintReqDTO.getEndTime());
		}
		if(!StringUtil.isBlank(complaintReqDTO.getStaffId())){
			criteria.andStaffIdEqualTo(complaintReqDTO.getStaffId());
		}
		example.setOrderByClause( "UPDATE_TIME desc,CREATE_TIME desc");
		PageHelper.startPage(page, rows);
		List<OrdComplaint> pageList = ordComplaintMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(pageList);
		PageResponseDTO<OrdComplaintRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdComplaintRespDTO.class);
		return pageResponseDTO;
	}

	@Override
	public long insertOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception {
		OrdComplaint record = new OrdComplaint();
		if(ordComplaintReqDTO != null){
			BeanUtils.copyProperties(ordComplaintReqDTO, record);
		}
		Long seq = SeqUtil.getLong("SEQ_ORD_COMPLAINTCONT");
		record.setComplaintId(seq);
		record.setCreateTime(DateUtil.getNowAsDate());
		record.setUpdateTime(DateUtil.getNowAsDate());
		record.setCreateStaff(ordComplaintReqDTO.getCreateStaff());
		record.setUpdateStaff(ordComplaintReqDTO.getCreateStaff());
		record.setComplaintsTime(DateUtil.getNowAsDate());
		long compFlg = ordComplaintMapper.insert(record);
		if (compFlg >0)
			return seq;
		return 0;
	}

	@Override
	public long updateOrdComplaint(OrdComplaintReqDTO ordComplaintReqDTO) throws Exception {
		OrdComplaintExample example = new OrdComplaintExample();
		Criteria criteria = example.createCriteria();

		if(ordComplaintReqDTO.getComplaintId()!=null){
			criteria.andComplaintIdEqualTo(ordComplaintReqDTO.getComplaintId());
		}
		OrdComplaint record = new OrdComplaint();
		ordComplaintReqDTO.setUpdateTime(DateUtil.getNowAsDate());
		ordComplaintReqDTO.setUpdateStaff(ordComplaintReqDTO.getUpdateStaff());
		ObjectCopyUtil.copyObjValue(ordComplaintReqDTO,record,null,false);

		return ordComplaintMapper.updateByExampleSelective(record, example);
	}

}
