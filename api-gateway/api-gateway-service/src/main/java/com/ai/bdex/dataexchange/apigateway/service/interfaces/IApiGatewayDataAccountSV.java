package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DataConsumeDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

/**
 * Created by fangyunfeng on 2017/5/10.
 */
public interface IApiGatewayDataAccountSV {
    /**
     * 消费数据服务
     * @param consumeDTO
     * @return OK-成功，NA-没有权限访问
     * @throws BusinessException
     */
    String dealDataCharge(DataConsumeDTO consumeDTO) throws BusinessException;
}
