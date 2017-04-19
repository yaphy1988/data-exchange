package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;
import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.ReqDemoDTO;

/**
 * @author yafei
 * @since 2017/3/31
 */
public interface IDemoRSV {
    String callDemoApi(DemoDTO demoDTO);

    PageResponseDTO<DemoDTO> queryDemoPage(ReqDemoDTO baseInfo) throws BusinessException;
}
