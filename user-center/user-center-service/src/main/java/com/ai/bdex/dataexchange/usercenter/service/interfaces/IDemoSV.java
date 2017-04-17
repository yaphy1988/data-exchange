package com.ai.bdex.dataexchange.usercenter.service.interfaces;


import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.github.pagehelper.Page;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public interface IDemoSV {
    String callDemo(DemoDTO demoDTO);

    Page<DemoDTO> queryDemoPage(DemoDTO demoDTO) throws BusinessException;
}
