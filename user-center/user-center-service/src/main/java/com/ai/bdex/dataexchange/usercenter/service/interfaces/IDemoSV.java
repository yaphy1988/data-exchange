package com.ai.bdex.dataexchange.usercenter.service.interfaces;


import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqDemoDTO;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public interface IDemoSV {
    String callDemo(DemoDTO demoDTO);

    PageResponseDTO<DemoDTO> queryDemoPage(ReqDemoDTO demoDTO) throws BusinessException;
}
