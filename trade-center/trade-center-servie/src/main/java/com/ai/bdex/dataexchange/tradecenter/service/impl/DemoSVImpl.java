package com.ai.bdex.dataexchange.tradecenter.service.impl;


import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.tradecenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.tradecenter.service.interfaces.IDemoSV;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
@Service("demoSV")
public class DemoSVImpl implements IDemoSV {
    @Override
    public String callDemo(DemoDTO demoDTO) {
        return null;
    }
}
