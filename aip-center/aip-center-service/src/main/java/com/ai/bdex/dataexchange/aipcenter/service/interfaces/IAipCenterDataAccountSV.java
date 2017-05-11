package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
public interface IAipCenterDataAccountSV {

    /**
     * 根据订单或子订单查询数据账户信息
     * 订单号 和 子订单号不能同时为空
     * @param subOrder 子订单号
     * @return
     * @throws BusinessException
     */
    public DataAccountDTO queryDataAccountBySubOrder(String subOrder) throws BusinessException;

    /**
     * 根据充值信息查询用户数据账户
     * @param rechargeDTO
     * @return
     * @throws BusinessException
     */
    public List<DataAccountDTO> queryDataAccountByRechargInfo(RechargeDTO rechargeDTO) throws BusinessException;

    /**
     * 用户下单创建数据账户
     * @param rechargeDTO
     * @return 返回创建成功后的数据账户id
     * @throws BusinessException
     */
    public long createDataAccount(RechargeDTO rechargeDTO) throws BusinessException;
}
