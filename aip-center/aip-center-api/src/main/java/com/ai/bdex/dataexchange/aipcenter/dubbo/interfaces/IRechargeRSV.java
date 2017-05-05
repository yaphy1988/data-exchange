package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public interface IRechargeRSV {

    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException;

    public void recharge(RechargeDTO rechargeDTO) throws BusinessException;

    public DataAccountDTO queryDataAccountByOrderInfo(String orderId,String subOrder) throws BusinessException;
}
