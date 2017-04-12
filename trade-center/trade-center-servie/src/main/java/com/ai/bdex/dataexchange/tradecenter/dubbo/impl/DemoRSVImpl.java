package com.ai.bdex.dataexchange.tradecenter.dubbo.impl;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IDemoSV;

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
