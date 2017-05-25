package com.ai.bdex.dataexchange.aipcenter.service.interfaces;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.BillDetailReqDTO;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;

/**
 * Created by fangyunfeng on 2017/5/11.
 */
public interface IAipCenterBillDetailSV {

    /**
     * 查询数据消费账单
     * @param billDetailReqDTO
     * @return
     * @throws BusinessException
     */
    PageResponseDTO<BillDetailDTO> queryBillDetail(BillDetailReqDTO billDetailReqDTO) throws BusinessException;
}
