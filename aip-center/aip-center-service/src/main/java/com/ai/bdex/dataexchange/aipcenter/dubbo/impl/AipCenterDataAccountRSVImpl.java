package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterBillDetailSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IAipCenterDataAccountSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
@Service("aipCenterDataAccountRSV")
public class AipCenterDataAccountRSVImpl implements IAipCenterDataAccountRSV {

    @Autowired
    private IRechargeSV rechargeSV;

    @Autowired
    private IAipCenterDataAccountSV dataAccountSV;

    @Autowired
    private IAipCenterBillDetailSV aipCenterBillDetailSV;

    @Override
    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException {
        rechargeSV.recharge(rechargeDTOList);
    }

    @Override
    public void recharge(RechargeDTO rechargeDTO) throws BusinessException {
        rechargeSV.recharge(Arrays.asList(rechargeDTO));
    }

    @Override
    public DataAccountDTO queryDataAccountBySubOrder(String subOrder) throws BusinessException {
        return dataAccountSV.queryDataAccountBySubOrder(subOrder);
    }

    @Override
    public PageResponseDTO<BillDetailDTO> queryBillDetail(BillDetailReqDTO billDetailReqDTO) throws BusinessException {
        return aipCenterBillDetailSV.queryBillDetail(billDetailReqDTO);
    }
}
