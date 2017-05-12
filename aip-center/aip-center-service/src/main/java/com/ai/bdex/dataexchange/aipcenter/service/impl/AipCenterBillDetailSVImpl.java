package com.ai.bdex.dataexchange.aipcenter.service.impl;

import com.ai.bdex.dataexchange.aipcenter.dao.mapper.BillDetailMapper;
import com.ai.bdex.dataexchange.aipcenter.dao.model.BillDetail;
import com.ai.bdex.dataexchange.aipcenter.dao.model.BillDetailExample;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterBillDetailSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.util.PageResponseFactory;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
@Service("aipCenterBillDetailSV")
public class AipCenterBillDetailSVImpl implements IAipCenterBillDetailSV {

    @Autowired
    private BillDetailMapper billDetailMapper;

    @Autowired
    private IRechargeSV rechargeSV;

    @Override
    public PageResponseDTO<BillDetailDTO> queryBillDetail(BillDetailReqDTO billDetailReqDTO) throws BusinessException {

        BillDetailExample billDetailExample = new BillDetailExample();
        BillDetailExample.Criteria criteria = billDetailExample.createCriteria();
        if(!StringUtil.isBlank(billDetailReqDTO.getUserId())){
            criteria.andUserIdEqualTo(billDetailReqDTO.getUserId());
        }
        if(!StringUtil.isBlank(billDetailReqDTO.getServiceId())){
            criteria.andRealServiceIdEqualTo(billDetailReqDTO.getServiceId());
        }
        if(!StringUtil.isBlank(billDetailReqDTO.getSubOrder())){
            RechargeReqDTO queryRechargeDTO = new RechargeReqDTO();
            queryRechargeDTO.setSubOrder(billDetailReqDTO.getSubOrder());
            List<RechargeDTO>  rechargeDTOList = rechargeSV.queryRechargeRecordListByOption(queryRechargeDTO);
            //subOrder 在充值表中是唯一所以，所以最多只会返回一个记录
            if(!CollectionUtil.isEmpty(rechargeDTOList)){
                criteria.andDataAcctIdEqualTo(rechargeDTOList.get(0).getDataAccountId());
            }
        }
        if(billDetailReqDTO.getDataAcctId() != null && billDetailReqDTO.getDataAcctId() > new Long(0)){
            criteria.andDataAcctIdEqualTo(billDetailReqDTO.getDataAcctId());
        }
        if(!StringUtil.isBlank(billDetailReqDTO.getBillId())){
            criteria.andBillIdEqualTo(billDetailReqDTO.getBillId());
        }

        if(billDetailReqDTO.getQueryStartDate() != null){
            criteria.andConsumeTimeGreaterThanOrEqualTo(billDetailReqDTO.getQueryStartDate());
        }
        if(billDetailReqDTO.getQueryEndDate() != null){
            criteria.andConsumeTimeLessThanOrEqualTo(billDetailReqDTO.getQueryEndDate());
        }

        PageHelper.startPage(billDetailReqDTO.getPageNo(),billDetailReqDTO.getPageSize());
        List<BillDetail> billDetailList = billDetailMapper.selectByExample(billDetailExample);

        PageResponseDTO<BillDetailDTO> respPageDTO = PageResponseFactory.genPageResponse(new PageInfo(billDetailList),BillDetailDTO.class);
        return respPageDTO;
    }
}
