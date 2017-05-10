package com.ai.bdex.dataexchange.aipcenter.dubbo.impl;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipCenterDataAccountRSV;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IRechargeSV;
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

    @Override
    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException {
        rechargeSV.recharge(rechargeDTOList);
    }

    @Override
    public void recharge(RechargeDTO rechargeDTO) throws BusinessException {
        rechargeSV.recharge(Arrays.asList(rechargeDTO));
    }

    @Override
    public DataAccountDTO queryDataAccountByOrderInfo(String subOrder) throws BusinessException {
        return rechargeSV.queryDataAccountByOrderInfo(subOrder);
    }
}
