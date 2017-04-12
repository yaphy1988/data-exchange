package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DemoDTO;

/**
 * @author yafei
 * @since 2017/3/31
 */
public interface IDemoRSV {
    String callDemoApi(DemoDTO demoDTO);
}
