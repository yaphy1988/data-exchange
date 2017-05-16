package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeRespDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

/**
 * Created by fangyunfeng on 2017/5/10.
 */
public interface IApiGatewayDataAccountSV {
    /**
     * 扣费接口
     * 必填字段如下：
     * invokeSeq 调用流水
     * userId 用户id
     * realServiceId 接口id
     * consumeNum - 消耗次数，填写 1
     * consumeMoney - 消耗金额，填写接口调用单价
     * @param consumeDTO
     * @return OK-成功，NA-没有权限访问
     * consumeDTO.result 扣费结果：OK-扣费成功，NA-余额不足或找不到记录
     * consumeDTO.billId 扣费成功后返回的账单id
     * @throws BusinessException
     */
    DataConsumeRespDTO dealDataCharge(DataConsumeDTO consumeDTO) throws BusinessException;
}
