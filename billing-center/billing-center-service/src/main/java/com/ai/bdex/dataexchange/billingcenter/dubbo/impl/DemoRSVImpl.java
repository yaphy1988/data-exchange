package com.ai.bdex.dataexchange.billingcenter.dubbo.impl;



import com.ai.bdex.dataexchange.billingcenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.billingcenter.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.billingcenter.service.interfaces.IDemoSV;

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
