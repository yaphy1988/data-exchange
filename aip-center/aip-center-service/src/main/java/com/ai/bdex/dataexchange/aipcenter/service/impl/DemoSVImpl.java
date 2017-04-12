package com.ai.bdex.dataexchange.aipcenter.service.impl;


import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.aipcenter.service.interfaces.IDemoSV;
import org.springframework.stereotype.Service;

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
