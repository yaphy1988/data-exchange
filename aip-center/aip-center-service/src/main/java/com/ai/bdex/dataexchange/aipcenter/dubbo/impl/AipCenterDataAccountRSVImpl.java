package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;
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
    public void dealRecharge(List<RechargeReqDTO> rechargeDTOList) throws BusinessException {
        rechargeSV.dealRecharge(rechargeDTOList);
    }

    @Override
    public void dealRecharge(RechargeReqDTO rechargeDTO) throws BusinessException {
        rechargeSV.dealRecharge(rechargeDTO);
    }

    @Override
    public void dealDisableDataAccount(DataAccountDTO dataAccountDTO) throws BusinessException {
        dataAccountSV.dealDisableDataAccount(dataAccountDTO);
    }

    @Override
    public void dealEnableDataAccount(DataAccountDTO dataAccountDTO) throws BusinessException {
        dataAccountSV.dealEnableDataAccount(dataAccountDTO);
    }

    @Override
    public DataAccountDTO queryDataAccountBySubOrder(String subOrder) throws BusinessException {
        return dataAccountSV.queryDataAccountBySubOrder(subOrder);
    }

    @Override
    public PageResponseDTO<BillDetailDTO> queryBillDetail(BillDetailReqDTO billDetailReqDTO) throws BusinessException {
        return aipCenterBillDetailSV.queryBillDetail(billDetailReqDTO);
    }

    @Override
    public PageResponseDTO<RechargeDTO> queryRechargePageByOption(RechargeReqDTO rechargeReqDTO) throws BusinessException {
        return rechargeSV.queryRechargeRecordPageByOption(rechargeReqDTO);
    }

    public PageResponseDTO<DataAccountRespDTO> queryDataAccountStatisticPageByOption(DataAccountDTO dataAccountReqDTO) throws BusinessException {
        return dataAccountSV.queryDataAccountStatisticPageByOption(dataAccountReqDTO);
    }
}
