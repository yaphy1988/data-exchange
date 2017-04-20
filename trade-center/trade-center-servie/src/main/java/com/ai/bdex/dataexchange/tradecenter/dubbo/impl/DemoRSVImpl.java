package com.ai.bdex.dataexchange.tradecenter.dubbo.impl;


import javax.annotation.Resource;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.IDemoRSV;
import com.ai.bdex.dataexchange.tradecenter.service.impl.DemoSVImpl;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.IDemoSV;

/**
 * @author yafei
 * @since 2017/3/31
 */
public class DemoRSVImpl implements IDemoRSV {

    @Resource
    private IDemoSV demoSV;

    @Override
    public String callDemoApi(DemoDTO demoDTO) {
    	return demoSV.callDemo(demoDTO);
        // return "call API success"; 
    }
 
}
