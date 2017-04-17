package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.github.pagehelper.Page;

/**
 * @author yafei
 * @since 2017/3/31
 */
public interface IDemoRSV {
    String callDemoApi(DemoDTO demoDTO);

    Page<DemoDTO> queryDemoPage(DemoDTO demoDTO) throws BusinessException;
}
