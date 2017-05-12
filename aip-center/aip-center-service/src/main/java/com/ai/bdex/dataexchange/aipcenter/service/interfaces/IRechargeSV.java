package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public interface IRechargeSV {
    public void recharge(List<RechargeReqDTO> rechargeDTOList) throws BusinessException;

    /**
     * 查找充值信息
     * @param rechargeDTO
     * @return
     */
    public List<RechargeDTO> queryRechargeRecordListByOption(RechargeReqDTO rechargeDTO) throws BusinessException;
}
