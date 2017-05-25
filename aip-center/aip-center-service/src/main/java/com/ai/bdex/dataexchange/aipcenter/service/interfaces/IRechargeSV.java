package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.RechargeReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

import java.util.List;

/**
 * Created by fangyunfeng on 2017/5/4.
 */
public interface IRechargeSV {

    /**
     * 充值服务
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
     * 充值服务
     * @param rechargeDTOList
     * @throws BusinessException
     */
    public void dealRecharge(List<RechargeReqDTO> rechargeDTOList) throws BusinessException;

    /**
     * 查找充值信息
     * @param rechargeDTO
     * @return
     */
    public List<RechargeDTO> queryRechargeRecordListByOption(RechargeReqDTO rechargeDTO) throws BusinessException;

    /**
     * 充值信息分页查询
     * @param rechargeDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RechargeDTO> queryRechargeRecordPageByOption(RechargeReqDTO rechargeDTO) throws BusinessException;
}
