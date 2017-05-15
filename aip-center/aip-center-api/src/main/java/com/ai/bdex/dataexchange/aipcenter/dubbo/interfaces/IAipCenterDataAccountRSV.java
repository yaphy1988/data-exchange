package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.*;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
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
    public void dealRecharge(List<RechargeReqDTO> rechargeDTOList) throws BusinessException;

    /**
     * 单个充值服务
     * 必填字段如下
     * rechargeUserId
     * subOrder
     * rechargeType (1-次数，2-金额)
     * periodType (1-有有效期，2-永久有效)
     * totalNum (当rechargeType="1")
     * totalMoney(当rechargeType="2")
     * serviceId(当rechargeType="1")
     * startDate(当periodType="1")
     * endDate(当periodType="1")
     * @param rechargeDTO
     * @throws BusinessException
     */
    public void dealRecharge(RechargeReqDTO rechargeDTO) throws BusinessException;

    /**
     * 数据账户失效操作
     * @param dataAccountId
     * @throws BusinessException
     */
    public void dealDisableDataAccount(long dataAccountId) throws BusinessException;

    /**
     * 数据账户生效操作
     * @param dataAccountId
     * @throws BusinessException
     */
    public void dealEnableDataAccount(long dataAccountId) throws BusinessException;

    /**
     * 根据子订单编码，查询数据账户信息
     * 主要用于余额查询
     * @param subOrder
     * @return
     * @throws BusinessException
     */
    public DataAccountDTO queryDataAccountBySubOrder(String subOrder) throws BusinessException;

    /**
     * 数据账单流水查询服务
     * 支持按子订单查询
     * 支持按用户id查询
     * 支持按流水id查询
     * 支持按数据账户查询
     * @param billDetailReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<BillDetailDTO> queryBillDetail(BillDetailReqDTO billDetailReqDTO) throws BusinessException;

    /**
     * 查询充值记录信息
     * @param rechargeReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RechargeDTO> queryRechargePageByOption(RechargeReqDTO rechargeReqDTO) throws BusinessException;
}
