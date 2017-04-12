package com.ai.bdex.dataexchange.apigateway.service.interfaces;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DemoDTO;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
public interface IDemoSV {
    String callDemo(DemoDTO demoDTO);
}
