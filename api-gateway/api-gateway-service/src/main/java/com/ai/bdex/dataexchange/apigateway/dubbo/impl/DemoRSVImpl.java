package com.ai.bdex.dataexchange.apigateway.dubbo.impl;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.apigateway.service.interfaces.IDemoSV;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yafei
 * @since 2017/3/31
 */
public class DemoRSVImpl implements IDemoRSV {

    @Resource
    private IDemoSV demoSV;

    @Override
    public String callDemoApi(DemoDTO demoDTO) {
        return "call API success";
    }
}
