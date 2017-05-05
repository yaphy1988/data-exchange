package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public interface IRechargeSV {
    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException;

    /**
     * 根据订单或子订单查询数据账户信息
     * 订单号 和 子订单号不能同时为空
     * @param orderId 订单号
     * @param subOrder 子订单号
     * @return
     * @throws BusinessException
     */
    public DataAccountDTO queryDataAccountByOrderInfo(String orderId,String subOrder) throws BusinessException;

    /**
     * 根据充值信息查询用户数据账户
     * @param rechargeDTO
     * @return
     * @throws BusinessException
     */
    public List<DataAccountDTO> queryDataAccountByRechargInfo(RechargeDTO rechargeDTO) throws BusinessException;
}
