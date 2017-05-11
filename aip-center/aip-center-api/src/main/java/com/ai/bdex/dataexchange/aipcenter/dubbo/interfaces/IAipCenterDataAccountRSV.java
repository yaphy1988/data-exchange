package com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailReqDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DataAccountDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
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
}
