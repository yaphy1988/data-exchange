package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public interface IAipCenterDataAccountRSV {

    /**
     * 批量充值，有一个失败全部回滚
     * @param rechargeDTOList
     * @throws BusinessException
     */
    public void recharge(List<RechargeDTO> rechargeDTOList) throws BusinessException;

    /**
     * 单个充值
     * @param rechargeDTO
     * @throws BusinessException
     */
    public void recharge(RechargeDTO rechargeDTO) throws BusinessException;

    /**
     * 根据子订单编码，查询数据账户信息
     * 主要用于余额查询
     * @param subOrder
     * @return
     * @throws BusinessException
     */
    public DataAccountDTO queryDataAccountByOrderInfo(String subOrder) throws BusinessException;
}
