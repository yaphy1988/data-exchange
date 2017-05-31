package com.ai.bdex.dataexchange.tradecenter.service.impl.complaint;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.tradecenter.dao.mapper.OrdComplaintContMapper;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaintCont;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaintContExample;
import com.ai.bdex.dataexchange.tradecenter.dao.model.OrdComplaintContExample.Criteria;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContReqDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.complaint.OrdComplaintContRespDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.complaint.IOrdComplaintContSV;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.sequence.SeqUtil;
import com.ai.paas.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("iOrdComplaintContSV")
public class OrdComplaintContSVImpl implements IOrdComplaintContSV {
	@Autowired
	private OrdComplaintContMapper ordComplaintContMapper;
	@Override
	public PageResponseDTO<OrdComplaintContRespDTO> queryOrdComplaintContPageInfo(OrdComplaintContReqDTO complaintContReqDTO)
			throws Exception {
		// 分页信息赋值
		int page = complaintContReqDTO.getPageNo();
		int rows = complaintContReqDTO.getPageSize();
		OrdComplaintContExample example = new OrdComplaintContExample();
		Criteria criteria = example.createCriteria();
		if(complaintContReqDTO.getComplaintContId() != null){
			criteria.andComplaintContIdEqualTo(complaintContReqDTO.getComplaintContId());
		}
		if(complaintContReqDTO.getComplaintId()!=null){
			criteria.andComplaintIdEqualTo(complaintContReqDTO.getComplaintId());
		}
		if(complaintContReqDTO.getComplaintType() != null){
			criteria.andComplaintTypeEqualTo(complaintContReqDTO.getComplaintType());
		}
		example.setOrderByClause( "UPDATE_TIME desc,CREATE_TIME desc");
		PageHelper.startPage(page, rows);
		List<OrdComplaintCont> pageList = ordComplaintContMapper.selectByExample(example);
		// 使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(pageList);
		PageResponseDTO<OrdComplaintContRespDTO> pageResponseDTO = PageResponseFactory.genPageResponse(pageInfo,
				OrdComplaintContRespDTO.class);
		return pageResponseDTO;
	}

	@Override
	public OrdComplaintContRespDTO queryOrdComplaintContByExm(OrdComplaintContReqDTO complaintContReqDTO) throws Exception {
		OrdComplaintContRespDTO complaintRespDTO = new OrdComplaintContRespDTO();
		OrdComplaintContExample example = new OrdComplaintContExample();
		Criteria criteria = example.createCriteria();
		if(complaintContReqDTO.getComplaintContId() != null){
			criteria.andComplaintContIdEqualTo(complaintContReqDTO.getComplaintContId());
		}
		if(complaintContReqDTO.getComplaintId()!=null){
			criteria.andComplaintIdEqualTo(complaintContReqDTO.getComplaintId());
		}
		if(complaintContReqDTO.getComplaintType() != null){
			criteria.andComplaintTypeEqualTo(complaintContReqDTO.getComplaintType());
		}
		example.setOrderByClause( "UPDATE_TIME desc,CREATE_TIME desc");
		List<OrdComplaintCont> ordComplaint = ordComplaintContMapper.selectByExample(example);
		BeanUtils.copyProperties(ordComplaint, complaintRespDTO);
		return complaintRespDTO ;
	}

	@Override
	public long insertOrdComplaintCont(OrdComplaintContReqDTO complaintContReqDTO) throws Exception {
		OrdComplaintCont record = new OrdComplaintCont();
		if(complaintContReqDTO != null){
			BeanUtils.copyProperties(complaintContReqDTO, record);
		}
		long seq = SeqUtil.getLong("SEQ_ORD_COMPLAINTATT");
		record.setComplaintContId(seq);
		record.setCreateTime(DateUtil.getNowAsDate());
		record.setUpdateTime(DateUtil.getNowAsDate());
		record.setCreateStaff(complaintContReqDTO.getCreateStaff());
		record.setUpdateStaff(complaintContReqDTO.getCreateStaff());
		return ordComplaintContMapper.insert(record);
	}

}
